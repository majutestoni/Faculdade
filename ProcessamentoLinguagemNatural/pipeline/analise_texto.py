#!/usr/bin/env python3
"""Análise de um único texto em memória (para o front "ao vivo").

Reaproveita exatamente as mesmas instruções e o mesmo cliente das etapas do
pipeline (filtro -> normalização -> sentimento), só que sobre um DataFrame de
uma linha, sem escrever CSV nenhum. Assim o prompt/regra de cada etapa fica em
um lugar só.

Uso:
    from analise_texto import analisar_texto
    analisar_texto("o flamengo jogou demais hoje, que golaço")
"""
import pandas as pd

import filtro_futebol
import normalizador
from analise_sentimento import AnalisadorGemini, escolher_coluna_texto
from gemini_client import ClienteGemini, processar_em_batches


def analisar_texto(texto, filtrar=True):
    """Normaliza e analisa o sentimento de UM texto, devolvendo um dict.

    Retorna:
        {
          "texto": str,                  # original
          "texto_normalizado": str,      # versão arrumada pelo Gemini
          "futebol": bool | None,        # None se filtrar=False ou sem resposta
          "sentimento": str,             # positivo/negativo/neutro/indefinido
          "confianca": float | None,     # 0.0 a 1.0
        }
    """
    texto = str(texto or "").strip()
    if not texto:
        raise ValueError("Texto vazio.")

    client = ClienteGemini()
    df = pd.DataFrame({"Texto": [texto]})

    # ----- Filtro: é futebol? (opcional) -----
    futebol = None
    if filtrar:
        resultados, _ = processar_em_batches(
            client, df, "Texto", filtro_futebol.INSTRUCAO, rotulo="filtro futebol"
        )
        futebol = filtro_futebol._para_bool(resultados.get(0, {}).get("futebol"))

    # ----- Normalização -----
    resultados, _ = processar_em_batches(
        client, df, "Texto", normalizador.INSTRUCAO, rotulo="normalização"
    )
    normalizado = str(resultados.get(0, {}).get("texto", "")).strip() or texto
    df["Texto_normalizado"] = [normalizado]

    # ----- Sentimento (reusa o analisador do pipeline) -----
    df = AnalisadorGemini().analisar(df, escolher_coluna_texto(df))

    return {
        "texto": texto,
        "texto_normalizado": normalizado,
        "futebol": futebol,
        "sentimento": df.loc[0, "Sentimento"],
        "confianca": df.loc[0, "Confianca"],
    }


if __name__ == "__main__":
    import json
    import sys

    entrada = " ".join(sys.argv[1:]) or "o flamengo jogou demais hoje, que golaço"
    print(json.dumps(analisar_texto(entrada), ensure_ascii=False, indent=2))
