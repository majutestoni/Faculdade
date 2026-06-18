#!/usr/bin/env python3
"""Orquestrador do pipeline completo:

    Scraping -> Filtro futebol (Gemini) -> Normalização (Gemini, opcional)
             -> Análise de sentimento (Gemini ou rede neural)

Cada execução cria pipeline/runs/<timestamp>/ com os CSVs intermediários,
então qualquer etapa pode ser inspecionada ou reexecutada isoladamente.

Orçamento de requisições: 1 batch = 1 requisição Gemini. Com o batch grande do
config, cada etapa cabe em 1 chamada, então o pipeline completo custa ~3
requisições (filtro + normalização + sentimento). O padrão processa só os
primeiros LIMITE_TWEETS_PADRAO tweets e aborta se a estimativa passar do teto
(--max-requisicoes), para caber na cota do free tier (~20/dia).

Exemplos:
    python pipeline/run_pipeline.py                          # ~30 tweets, ~3 requisições
    python pipeline/run_pipeline.py --sem-scraping           # idem, sem abrir o navegador
    python pipeline/run_pipeline.py --pular-normalizacao     # ~2 requisições
    python pipeline/run_pipeline.py --limite 60              # mais tweets (estimativa antes)
    python pipeline/run_pipeline.py --sem-scraping --dry-run # mostra o orçamento sem gastar API
    python pipeline/run_pipeline.py --limite 0               # todos (aborta se exceder o teto)
"""
import argparse
import math
import sys
from datetime import datetime
from pathlib import Path

import pandas as pd

import config
import filtro_futebol
import normalizador
from analise_sentimento import analisar_arquivo, criar_analisador


def encontrar_coleta_mais_recente():
    candidatos = sorted(config.PASTA_COLETAS.glob("*/tweets_g12.csv"))
    if not candidatos:
        raise FileNotFoundError(
            f"Nenhuma coleta encontrada em {config.PASTA_COLETAS}. "
            "Rode sem --sem-scraping ou informe --entrada."
        )
    return candidatos[-1]   # as pastas têm nome YYYY-MM-DD_HH-MM-SS: ordem alfabética = cronológica


def derivar_time(texto):
    texto_minusculo = str(texto).lower()
    return next((t for t in config.TIMES_G12 if t.lower() in texto_minusculo), "desconhecido")


def estimar_requisicoes(n_tweets, pular_normalizacao, analisador):
    """Estimativa (pior caso) de requisições Gemini para n_tweets.

    1 batch = 1 requisição. Normalização e sentimento assumem que TODOS os
    tweets passam no filtro (pior caso), então o número real tende a ser menor.
    Retorna (total, [(nome_etapa, n_batches), ...]).
    """
    batches = max(1, math.ceil(n_tweets / config.BATCH_SIZE))
    etapas = [("filtro", batches)]
    if not pular_normalizacao:
        etapas.append(("normalização", batches))
    if analisador == "gemini":
        etapas.append(("sentimento", batches))
    return sum(b for _, b in etapas), etapas


def verificar_orcamento(n_tweets, args):
    """Imprime a estimativa de requisições e aborta se exceder --max-requisicoes."""
    total, etapas = estimar_requisicoes(n_tweets, args.pular_normalizacao, args.analisador)
    detalhe = " + ".join(f"{nome}={b}" for nome, b in etapas)
    print(f"Orçamento estimado p/ {n_tweets} tweets: {detalhe} = ~{total} "
          f"requisição(ões) Gemini (teto: {args.max_requisicoes})\n")
    if not args.dry_run and total > args.max_requisicoes:
        sys.exit(
            f"A estimativa ({total}) excede o teto de {args.max_requisicoes} requisições.\n"
            f"  - Reduza os tweets:   --limite {config.LIMITE_TWEETS_PADRAO}\n"
            f"  - Pule a normalização: --pular-normalizacao  (economiza 1/3)\n"
            f"  - Ou aumente o teto:   --max-requisicoes N   (se sua cota permitir)"
        )


def etapa_scraping(args, pasta_run):
    destino = pasta_run / "01_tweets_brutos.csv"

    if args.entrada or args.sem_scraping:
        origem = Path(args.entrada) if args.entrada else encontrar_coleta_mais_recente()
        print(f"Scraping pulado — usando coleta existente: {origem}")
        df = pd.read_csv(origem)
    else:
        from scraper import coletar
        resultado = coletar(destino, rolagens=args.rolagens)
        if resultado is None:
            sys.exit(
                "Scraping não coletou nenhum tweet (bloqueio ou sessão expirada?).\n"
                "Alternativas: python pipeline/scraper.py --login  "
                "ou rode com --sem-scraping para usar a última coleta."
            )
        df = pd.read_csv(resultado)

    if "Texto" not in df.columns:
        sys.exit(f"O CSV de entrada precisa ter a coluna 'Texto'. Colunas: {list(df.columns)}")
    if "Time" not in df.columns:
        df["Time"] = df["Texto"].apply(derivar_time)

    df = df.dropna(subset=["Texto"]).reset_index(drop=True)
    if args.limite and args.limite > 0:   # 0 = processar todos
        df = df.head(args.limite).copy()

    df.to_csv(destino, index=False, encoding="utf-8")
    print(f"Etapa 1 concluída: {len(df)} tweets em {destino}\n")
    return destino


def main():
    parser = argparse.ArgumentParser(
        description="Pipeline: scraping -> filtro futebol -> normalização -> sentimento."
    )
    parser.add_argument("--sem-scraping", action="store_true",
                        help="não abre o navegador; usa a coleta mais recente do projeto")
    parser.add_argument("--entrada", type=Path, default=None,
                        help="CSV de tweets já coletados (implica não fazer scraping)")
    parser.add_argument("--rolagens", type=int, default=config.ROLAGENS_DE_TELA,
                        help=f"rolagens de tela no scraping (padrão: {config.ROLAGENS_DE_TELA})")
    parser.add_argument("--limite", type=int, default=config.LIMITE_TWEETS_PADRAO,
                        help=f"processa só os N primeiros tweets "
                             f"(padrão: {config.LIMITE_TWEETS_PADRAO}; use 0 para todos)")
    parser.add_argument("--max-requisicoes", type=int, default=config.MAX_REQUISICOES,
                        help=f"aborta se a estimativa de requisições Gemini passar disso "
                             f"(padrão: {config.MAX_REQUISICOES})")
    parser.add_argument("--pular-normalizacao", action="store_true",
                        help="vai direto do filtro para o sentimento (economiza ~1/3 das requisições)")
    parser.add_argument("--analisador", choices=["gemini", "rede-neural"], default="gemini",
                        help="backend da análise de sentimento (padrão: gemini)")
    parser.add_argument("--dry-run", action="store_true",
                        help="monta os prompts e gera os CSVs sem chamar a API Gemini")
    args = parser.parse_args()

    pasta_run = config.PASTA_RUNS / datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
    pasta_run.mkdir(parents=True, exist_ok=True)
    print(f"{'=' * 60}\nPipeline iniciado — saídas em {pasta_run}\n{'=' * 60}\n")

    # valida a API key antes de gastar minutos de scraping à toa
    if not args.dry_run:
        config.api_key()

    # ----- Etapa 1: scraping (ou reuso de coleta) -----
    csv_brutos = etapa_scraping(args, pasta_run)

    # checa o orçamento com o número real de tweets, antes de gastar qualquer requisição
    verificar_orcamento(len(pd.read_csv(csv_brutos)), args)

    # ----- Etapa 2: filtro "é futebol?" -----
    csv_futebol = filtro_futebol.filtrar(
        csv_brutos, pasta_run / "02_classificados_futebol.csv", dry_run=args.dry_run
    )
    print(f"Etapa 2 concluída\n")

    # ----- Etapa 3: normalização (opcional) -----
    if args.pular_normalizacao:
        print("Etapa 3 pulada (--pular-normalizacao)\n")
        csv_para_sentimento = csv_futebol
    else:
        csv_para_sentimento = normalizador.normalizar(
            csv_futebol, pasta_run / "03_normalizados.csv", dry_run=args.dry_run
        )
        print(f"Etapa 3 concluída\n")

    # ----- Etapa 4: sentimento -----
    analisador = criar_analisador(
        args.analisador, dry_run=args.dry_run,
        caminho_debug=pasta_run / "04_sentimentos.prompts.txt",
    )
    csv_final = analisar_arquivo(
        csv_para_sentimento, pasta_run / "04_sentimentos.csv", analisador
    )

    print(f"\n{'=' * 60}")
    print("Pipeline concluído!")
    print(f"  Brutos:      {csv_brutos}")
    print(f"  Só futebol:  {csv_futebol}")
    if not args.pular_normalizacao:
        print(f"  Normalizado: {csv_para_sentimento}")
    print(f"  Resultado:   {csv_final}")
    if args.dry_run:
        print("\n[dry-run] Nenhuma chamada ao Gemini foi feita — confira os *.prompts.txt")
    print(f"{'=' * 60}")


if __name__ == "__main__":
    main()
