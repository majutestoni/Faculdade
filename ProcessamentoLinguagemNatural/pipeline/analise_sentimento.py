#!/usr/bin/env python3
"""Etapa 4 — Análise de sentimento, desacoplada do backend.

A interface `AnalisadorSentimento` permite trocar o Gemini por uma rede neural
(ex.: o BERT do notebook PLN_2026_1_Sentimento_Tweets.ipynb) sem tocar no resto
do pipeline — basta implementar `analisar()` e registrar na factory.

Standalone:
    python pipeline/analise_sentimento.py --entrada caminho.csv --saida saida.csv
"""
import argparse
from abc import ABC, abstractmethod
from pathlib import Path

import pandas as pd

import config

ROTULOS_VALIDOS = ("positivo", "negativo", "neutro")

INSTRUCAO = """Voce e um analisador de sentimentos de tweets de torcedores de futebol brasileiro, em portugues.

Para cada tweet numerado abaixo, classifique o sentimento DO AUTOR em relacao ao time/jogador citado:
- "positivo": elogio, comemoracao, orgulho, apoio
- "negativo": critica, raiva, deboche contra o proprio alvo, frustracao
- "neutro": informativo, pergunta, sem opiniao clara

Cuidados:
- Ironia e sarcasmo sao comuns ("que belezinha, 3 a 0 contra" = negativo).
- Palavroes nem sempre sao negativos ("que golaco do caralho" = positivo).
- Zoacao contra o rival e positiva para o time do autor, mas classifique em
  relacao ao time citado no tweet.

Responda APENAS com um JSON valido, com um item para CADA id, no formato:
[
  {"id": 1, "sentimento": "negativo", "confianca": 0.9},
  {"id": 2, "sentimento": "positivo", "confianca": 0.7}
]
"confianca" vai de 0.0 a 1.0.
"""


class AnalisadorSentimento(ABC):
    """Contrato: recebe um DataFrame e o nome da coluna de texto; devolve o
    DataFrame com as colunas `Sentimento` (positivo/negativo/neutro/indefinido)
    e `Confianca` (0.0 a 1.0 ou vazio)."""

    nome = "base"

    @abstractmethod
    def analisar(self, df, coluna_texto):
        ...


class AnalisadorGemini(AnalisadorSentimento):
    nome = "gemini"

    def __init__(self, dry_run=False, caminho_debug=None):
        self.dry_run = dry_run
        self.caminho_debug = caminho_debug

    def analisar(self, df, coluna_texto):
        from gemini_client import ClienteGemini, processar_em_batches, salvar_prompts_debug

        client = None if self.dry_run else ClienteGemini()
        resultados, prompts = processar_em_batches(
            client, df, coluna_texto, INSTRUCAO,
            dry_run=self.dry_run, rotulo="sentimento",
        )
        if self.caminho_debug:
            salvar_prompts_debug(prompts, Path(self.caminho_debug))

        df = df.copy()
        df["Sentimento"] = [
            _normalizar_rotulo(resultados.get(i, {}).get("sentimento"))
            for i in range(len(df))
        ]
        df["Confianca"] = [
            _para_float(resultados.get(i, {}).get("confianca"))
            for i in range(len(df))
        ]
        return df


class AnalisadorRedeNeural(AnalisadorSentimento):
    """Esqueleto para o substituto do Gemini.

    Para implementar: carregue aqui o modelo treinado no notebook
    PLN_2026_1_Sentimento_Tweets.ipynb (ex.: BERTimbau fine-tunado ou a
    LogisticRegression sobre TF-IDF) e preencha `Sentimento`/`Confianca`
    com as predições. Nada mais no pipeline precisa mudar.
    """

    nome = "rede-neural"

    def analisar(self, df, coluna_texto):
        raise NotImplementedError(
            "AnalisadorRedeNeural ainda não implementado — treine/exporte o modelo "
            "do notebook PLN_2026_1_Sentimento_Tweets.ipynb e carregue-o aqui."
        )


def criar_analisador(nome, dry_run=False, caminho_debug=None):
    if nome == "gemini":
        return AnalisadorGemini(dry_run=dry_run, caminho_debug=caminho_debug)
    if nome == "rede-neural":
        return AnalisadorRedeNeural()
    raise ValueError(f"Analisador desconhecido: {nome!r} (use 'gemini' ou 'rede-neural')")


def _normalizar_rotulo(valor):
    rotulo = str(valor or "").strip().lower()
    for valido in ROTULOS_VALIDOS:
        if rotulo.startswith(valido[:3]):
            return valido
    return "indefinido" if rotulo else ""


def _para_float(valor):
    try:
        return round(float(valor), 2)
    except (TypeError, ValueError):
        return None


def escolher_coluna_texto(df):
    # usa o texto normalizado quando a etapa 3 rodou; senão, o original
    return "Texto_normalizado" if "Texto_normalizado" in df.columns else "Texto"


def analisar_arquivo(csv_entrada, csv_saida, analisador, limite=None):
    """Roda o analisador sobre o CSV e retorna o Path do resultado."""
    df = pd.read_csv(csv_entrada).reset_index(drop=True)
    if limite is not None:
        df = df.head(limite).copy()
    coluna = escolher_coluna_texto(df)
    print(f"Sentimento ({analisador.nome}): {len(df)} tweets, coluna '{coluna}'")

    df = analisador.analisar(df, coluna)

    csv_saida = Path(csv_saida)
    csv_saida.parent.mkdir(parents=True, exist_ok=True)
    df.to_csv(csv_saida, index=False, encoding="utf-8")
    print(f"  Salvo: {csv_saida}")

    if "Time" in df.columns and df["Sentimento"].astype(str).str.strip().any():
        resumo = pd.crosstab(df["Time"], df["Sentimento"])
        resumo.to_csv(csv_saida.parent / "resumo_sentimento_por_time.csv", encoding="utf-8")
        print("\nResumo por time:")
        print(resumo.to_string())
    return csv_saida


def main():
    parser = argparse.ArgumentParser(description="Analisa o sentimento dos tweets.")
    parser.add_argument("--entrada", type=Path, required=True)
    parser.add_argument("--saida", type=Path, default=config.BASE_DIR / "sentimentos.csv")
    parser.add_argument("--analisador", choices=["gemini", "rede-neural"], default="gemini")
    parser.add_argument("--limite", type=int, default=None)
    parser.add_argument("--dry-run", action="store_true", help="só monta os prompts, sem chamar a API")
    args = parser.parse_args()

    analisador = criar_analisador(
        args.analisador, dry_run=args.dry_run,
        caminho_debug=args.saida.with_suffix(".prompts.txt"),
    )
    analisar_arquivo(args.entrada, args.saida, analisador, limite=args.limite)


if __name__ == "__main__":
    main()
