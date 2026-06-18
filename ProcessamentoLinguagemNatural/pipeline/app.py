#!/usr/bin/env python3
"""Front simples (Streamlit) para o pipeline de sentimentos do G12.

Duas abas:
  - Dashboard: lê o CSV de sentimentos de um run em pipeline/runs/ e mostra
    métricas, gráfico de sentimento por time, distribuições e a tabela filtrável.
  - Análise ao vivo: cola um tweet e o normaliza + analisa via Gemini na hora.

Rodar (a partir da raiz do projeto OU de dentro de pipeline/):
    streamlit run pipeline/app.py
"""
import os
import sys
from pathlib import Path

# os módulos do pipeline usam imports diretos (import config, ...); garante que
# a pasta pipeline/ esteja no path mesmo rodando `streamlit run pipeline/app.py`
PASTA = Path(__file__).parent
if str(PASTA) not in sys.path:
    sys.path.insert(0, str(PASTA))

import pandas as pd
import streamlit as st

import config

CORES_SENTIMENTO = {
    "positivo": "#2ecc71",
    "negativo": "#e74c3c",
    "neutro": "#95a5a6",
    "indefinido": "#bdc3c7",
}

st.set_page_config(page_title="Sentimento G12", page_icon="⚽", layout="wide")


# --------------------------------------------------------------------------- #
# Carregamento dos runs
# --------------------------------------------------------------------------- #
def listar_runs():
    """Runs (pastas com 04_sentimentos.csv), do mais recente para o mais antigo.

    Os nomes seguem YYYY-MM-DD_HH-MM-SS, então ordem alfabética = cronológica.
    """
    if not config.PASTA_RUNS.exists():
        return []
    runs = [p for p in config.PASTA_RUNS.glob("*/04_sentimentos.csv")]
    return sorted((p.parent for p in runs), reverse=True)


@st.cache_data(show_spinner=False)
def carregar_sentimentos(caminho_csv):
    return pd.read_csv(caminho_csv)


# --------------------------------------------------------------------------- #
# Aba Dashboard
# --------------------------------------------------------------------------- #
def aba_dashboard():
    runs = listar_runs()
    if not runs:
        st.info(
            "Nenhum resultado encontrado em `pipeline/runs/`.\n\n"
            "Rode o pipeline primeiro:\n\n"
            "```\npython pipeline/run_pipeline.py --sem-scraping\n```"
        )
        return

    rotulos = {p.name: p for p in runs}
    escolhido = st.selectbox("Run", list(rotulos.keys()), index=0)
    pasta_run = rotulos[escolhido]

    df = carregar_sentimentos(pasta_run / "04_sentimentos.csv")
    if "Sentimento" not in df.columns:
        st.error("O CSV não tem a coluna `Sentimento`. Esse run foi gerado em dry-run?")
        return

    df["Sentimento"] = df["Sentimento"].fillna("").replace("", "indefinido")

    # ----- Métricas -----
    total = len(df)
    contagem = df["Sentimento"].value_counts()
    c1, c2, c3, c4 = st.columns(4)
    c1.metric("Tweets", total)
    for col, rotulo in ((c2, "positivo"), (c3, "negativo"), (c4, "neutro")):
        n = int(contagem.get(rotulo, 0))
        pct = (n / total * 100) if total else 0
        col.metric(rotulo.capitalize(), n, f"{pct:.0f}%")

    st.divider()

    # ----- Sentimento por time -----
    esq, dir_ = st.columns([3, 2])
    with esq:
        st.subheader("Sentimento por time")
        if "Time" in df.columns:
            crosstab = pd.crosstab(df["Time"], df["Sentimento"])
            ordem = [c for c in ("positivo", "neutro", "negativo", "indefinido") if c in crosstab.columns]
            crosstab = crosstab[ordem]
            st.bar_chart(crosstab, color=[CORES_SENTIMENTO[c] for c in ordem])
        else:
            st.caption("CSV sem coluna `Time`.")

    with dir_:
        st.subheader("Distribuição geral")
        dist = contagem.rename_axis("Sentimento").reset_index(name="Quantidade")
        st.bar_chart(dist.set_index("Sentimento"), y="Quantidade")
        if "Confianca" in df.columns and df["Confianca"].notna().any():
            st.caption(f"Confiança média: {df['Confianca'].mean():.2f}")

    st.divider()

    # ----- Tabela filtrável -----
    st.subheader("Tweets")
    f1, f2 = st.columns(2)
    times = ["(todos)"] + (sorted(df["Time"].dropna().unique()) if "Time" in df.columns else [])
    sentimentos = ["(todos)"] + sorted(df["Sentimento"].unique())
    time_sel = f1.selectbox("Time", times)
    sent_sel = f2.selectbox("Sentimento", sentimentos)

    filtro = df
    if time_sel != "(todos)" and "Time" in df.columns:
        filtro = filtro[filtro["Time"] == time_sel]
    if sent_sel != "(todos)":
        filtro = filtro[filtro["Sentimento"] == sent_sel]

    colunas = [c for c in ("Time", "Texto", "Texto_normalizado", "Sentimento", "Confianca") if c in filtro.columns]
    st.caption(f"{len(filtro)} de {total} tweets")
    st.dataframe(filtro[colunas], use_container_width=True, hide_index=True)


# --------------------------------------------------------------------------- #
# Aba Análise ao vivo
# --------------------------------------------------------------------------- #
def aba_ao_vivo():
    st.subheader("Analise um tweet na hora")

    if not os.environ.get("GEMINI_API_KEY", "").strip():
        st.warning(
            "Defina a variável de ambiente `GEMINI_API_KEY` antes de rodar o app "
            "para usar a análise ao vivo.\n\n"
            "```\nexport GEMINI_API_KEY='sua-chave'   # https://aistudio.google.com/apikey\n```"
        )
        return

    texto = st.text_area(
        "Tweet",
        placeholder="Cole ou digite um tweet sobre futebol...",
        height=120,
    )
    filtrar = st.checkbox("Checar se é sobre futebol", value=True)

    if not st.button("Analisar", type="primary"):
        return
    if not texto.strip():
        st.error("Escreva um texto primeiro.")
        return

    from analise_texto import analisar_texto

    try:
        with st.spinner("Consultando o Gemini..."):
            resultado = analisar_texto(texto, filtrar=filtrar)
    except Exception as e:
        st.error(f"Falha ao analisar: {type(e).__name__}: {e}")
        return

    sentimento = resultado["sentimento"] or "indefinido"
    cor = CORES_SENTIMENTO.get(sentimento, "#bdc3c7")
    confianca = resultado["confianca"]

    c1, c2, c3 = st.columns(3)
    c1.markdown(
        f"**Sentimento**\n\n"
        f"<span style='font-size:1.6rem;color:{cor};font-weight:700'>{sentimento}</span>",
        unsafe_allow_html=True,
    )
    c2.metric("Confiança", f"{confianca:.2f}" if confianca is not None else "—")
    if filtrar:
        rotulo_fut = {True: "Sim", False: "Não", None: "Indefinido"}[resultado["futebol"]]
        c3.metric("É futebol?", rotulo_fut)

    st.markdown("**Texto normalizado**")
    st.info(resultado["texto_normalizado"])


# --------------------------------------------------------------------------- #
def main():
    st.title("⚽ Análise de sentimento — tweets do G12")
    st.caption("Pipeline Gemini: filtro de futebol → normalização → sentimento")

    dashboard, ao_vivo = st.tabs(["📊 Dashboard", "⚡ Análise ao vivo"])
    with dashboard:
        aba_dashboard()
    with ao_vivo:
        aba_ao_vivo()


main()
