#!/usr/bin/env python3
"""Etapa 3 (opcional) — Normalização do texto via Gemini.

Corrige ortografia e expande abreviações SEM mexer no sentimento: gírias,
palavrões e ironia são o sinal que a próxima etapa precisa, então a instrução
proíbe suavizá-los. (Mesmas regras validadas em reg/normalizar_tweets.py.)

Adiciona a coluna Texto_normalizado; se um batch falhar, o texto original é
mantido como fallback para não travar o pipeline.

Standalone:
    python pipeline/normalizador.py --entrada caminho.csv --saida saida.csv
"""
import argparse
from pathlib import Path

import pandas as pd

import config
from gemini_client import ClienteGemini, processar_em_batches, salvar_prompts_debug

INSTRUCAO = """Voce e um normalizador de texto para um pipeline de analise de sentimentos sobre tweets de futebol em portugues.

Para cada tweet recebido, devolva uma versao normalizada seguindo ESTAS regras:

1. Corrija apenas erros ortograficos obvios e expanda APENAS as seguintes abreviacoes (lista fechada):
   "vc"->"voce", "vcs"->"voces", "pq"->"porque", "pqp"->"pqp" (mantenha),
   "tb"/"tbm"->"tambem", "q"->"que", "n"->"nao", "tmj"->"tamo junto",
   "pra"->"para", "pro"->"para o", "msm"->"mesmo".
   NAO expanda mais nada. Em particular, NAO altere formatos de hora ("20h" continua "20h"),
   data, numeros, siglas, nomes de times ou jogadores.

2. NUNCA substitua girias, palavroes, xingamentos ou expressoes de raiva/zoacao por sinonimos
   neutros. Eles carregam o sentimento que precisamos preservar. Mantenha EXATAMENTE as palavras
   emocionais do original ("fudido", "foda", "lixo", "merda", "porra", "caralho", etc).

3. Remova URLs, mencoes (@usuario) e hashtags vazias. Hashtags com conteudo semantico viram
   texto (#golaco -> golaco).

4. Mantenha emojis que carreguem sentimento.

5. NAO altere o sentimento, ironia, sarcasmo nem o alvo do tweet (time, jogador).

6. NAO invente conteudo. Se o tweet nao tiver texto util (so pontuacao, so nomes proprios,
   so numeros), devolva o texto original inalterado.

Responda APENAS com um JSON valido, com um item para CADA id, no formato:
[
  {"id": 1, "texto": "..."},
  {"id": 2, "texto": "..."}
]
"""


def normalizar(csv_entrada, csv_saida, dry_run=False, limite=None):
    """Adiciona Texto_normalizado e retorna o Path do CSV de saída."""
    df = pd.read_csv(csv_entrada).dropna(subset=["Texto"]).reset_index(drop=True)
    if limite is not None:
        df = df.head(limite).copy()
    print(f"Normalização: {len(df)} tweets")

    client = None if dry_run else ClienteGemini()
    resultados, prompts = processar_em_batches(
        client, df, "Texto", INSTRUCAO, dry_run=dry_run, rotulo="normalização"
    )

    csv_saida = Path(csv_saida)
    csv_saida.parent.mkdir(parents=True, exist_ok=True)
    salvar_prompts_debug(prompts, csv_saida.with_suffix(".prompts.txt"))

    normalizados, fallbacks = [], 0
    for i in range(len(df)):
        texto = resultados.get(i, {}).get("texto", "")
        if not str(texto).strip():
            texto = df.loc[i, "Texto"]   # fallback: segue com o original
            fallbacks += 1
        normalizados.append(texto)

    df["Texto_normalizado"] = normalizados
    df.to_csv(csv_saida, index=False, encoding="utf-8")

    if not dry_run and fallbacks:
        print(f"  [aviso] {fallbacks} tweets sem resposta — mantido o texto original")
    print(f"  Salvo: {csv_saida}")
    return csv_saida


def main():
    parser = argparse.ArgumentParser(description="Normaliza o texto dos tweets (Gemini).")
    parser.add_argument("--entrada", type=Path, required=True)
    parser.add_argument("--saida", type=Path, default=config.BASE_DIR / "normalizados.csv")
    parser.add_argument("--limite", type=int, default=None)
    parser.add_argument("--dry-run", action="store_true", help="só monta os prompts, sem chamar a API")
    args = parser.parse_args()
    normalizar(args.entrada, args.saida, dry_run=args.dry_run, limite=args.limite)


if __name__ == "__main__":
    main()
