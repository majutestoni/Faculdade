#!/usr/bin/env python3
"""Etapa 2 — Filtro via Gemini: o tweet fala sobre futebol?

Os tweets são coletados por nome de time, mas "São Paulo", "Internacional",
"Santos" etc. são ambíguos — este filtro descarta o que não é futebol.

Gera dois arquivos:
  - <saida>            : todos os tweets + coluna Futebol (True/False/vazio se erro)
  - <saida sufixo _so_futebol> : apenas os tweets classificados como futebol

Standalone:
    python pipeline/filtro_futebol.py --entrada caminho.csv --saida saida.csv
"""
import argparse
from pathlib import Path

import pandas as pd

import config
from gemini_client import ClienteGemini, processar_em_batches, salvar_prompts_debug

INSTRUCAO = """Voce e um classificador de tweets em portugues para um trabalho de analise de sentimentos sobre futebol brasileiro.

Para cada tweet numerado abaixo, decida se ele fala sobre FUTEBOL: partidas, clubes, jogadores, tecnicos, contratacoes, campeonatos, torcida, zoacao entre torcedores etc.

Atencao aos nomes ambiguos: "Sao Paulo", "Santos", "Internacional", "Botafogo", "America" podem ser cidade, bairro, sobrenome ou outro assunto. Nesses casos, so marque true se o contexto for claramente futebol.

Responda APENAS com um JSON valido, com um item para CADA id, no formato:
[
  {"id": 1, "futebol": true},
  {"id": 2, "futebol": false}
]
"""


def _para_bool(valor):
    if valor in (True, "true", "True", 1, "1"):
        return True
    if valor in (False, "false", "False", 0, "0"):
        return False
    return None


def caminho_so_futebol(csv_saida):
    csv_saida = Path(csv_saida)
    return csv_saida.with_name(csv_saida.stem + "_so_futebol.csv")


def filtrar(csv_entrada, csv_saida, dry_run=False, limite=None):
    """Classifica os tweets e retorna o Path do CSV só com os de futebol."""
    df = pd.read_csv(csv_entrada).dropna(subset=["Texto"]).reset_index(drop=True)
    if limite is not None:
        df = df.head(limite).copy()
    print(f"Filtro de futebol: {len(df)} tweets para classificar")

    client = None if dry_run else ClienteGemini()
    resultados, prompts = processar_em_batches(
        client, df, "Texto", INSTRUCAO, dry_run=dry_run, rotulo="filtro futebol"
    )

    csv_saida = Path(csv_saida)
    csv_saida.parent.mkdir(parents=True, exist_ok=True)
    salvar_prompts_debug(prompts, csv_saida.with_suffix(".prompts.txt"))

    df["Futebol"] = [_para_bool(resultados.get(i, {}).get("futebol")) for i in range(len(df))]
    df.to_csv(csv_saida, index=False, encoding="utf-8")

    saida_futebol = caminho_so_futebol(csv_saida)
    if dry_run:
        # sem chamadas não há classificação; passa tudo adiante para testar o pipeline
        print("  [dry-run] sem classificação real — repassando todos os tweets")
        df_futebol = df.drop(columns=["Futebol"])
    else:
        df_futebol = df[df["Futebol"] == True].drop(columns=["Futebol"]).reset_index(drop=True)  # noqa: E712
        n_nao = int((df["Futebol"] == False).sum())  # noqa: E712
        n_erro = int(df["Futebol"].isna().sum())
        print(f"  Futebol: {len(df_futebol)} | Não futebol: {n_nao} | Sem resposta: {n_erro}")

    df_futebol.to_csv(saida_futebol, index=False, encoding="utf-8")
    print(f"  Salvo: {csv_saida}")
    print(f"  Salvo: {saida_futebol}")
    return saida_futebol


def main():
    parser = argparse.ArgumentParser(description="Classifica tweets como futebol ou não (Gemini).")
    parser.add_argument("--entrada", type=Path, required=True)
    parser.add_argument("--saida", type=Path, default=config.BASE_DIR / "filtro_futebol.csv")
    parser.add_argument("--limite", type=int, default=None)
    parser.add_argument("--dry-run", action="store_true", help="só monta os prompts, sem chamar a API")
    args = parser.parse_args()
    filtrar(args.entrada, args.saida, dry_run=args.dry_run, limite=args.limite)


if __name__ == "__main__":
    main()
