import pandas as pd
from datetime import datetime, timedelta
import re

# =========================================================
# Stop words (português básico)
# =========================================================
STOP_WORDS = set([
    "a", "o", "e", "de", "da", "do", "das", "dos",
    "em", "um", "uma", "uns", "umas",
    "para", "por", "com", "sem",
    "que", "se", "na", "no", "nas", "nos",
    "é", "foi", "ser", "tem", "tá", "ta",
    "isso", "essa", "esse", "isso", "aquilo",
    "eu", "tu", "ele", "ela", "nós", "vos", "eles", "elas"
])

# =========================================================
# Funções auxiliares
# =========================================================
def converter_tempo(t):
    t = str(t).strip().lower()

    match = re.search(r"(\d+)\s*([a-z]+)", t)
    if not match:
        return 0

    valor, unidade = match.groups()
    valor = int(valor)

    if unidade.startswith("s"):
        return valor
    elif unidade.startswith("m") or unidade.startswith("i"):
        return valor * 60
    elif unidade.startswith("h"):
        return valor * 3600
    else:
        return 0

def extrair_hashtags(texto):
    return re.findall(r"#\w+", str(texto))

def remover_hashtags(texto):
    return re.sub(r"#\w+", "", str(texto))

def remover_mencoes(texto):
    return re.sub(r"@\w+", "", str(texto))

# 🔥 Remoção robusta de URLs (com ou sem http/www)
def remover_urls(texto):
    return re.sub(r"\b(?:https?://|www\.)?\S+\.\S+\S*", "", str(texto))

def processar_stopwords(texto):
    palavras = str(texto).lower().split()

    stop_encontradas = [p for p in palavras if p in STOP_WORDS]
    palavras_filtradas = [p for p in palavras if p not in STOP_WORDS]

    texto_limpo = " ".join(palavras_filtradas)

    return texto_limpo, ", ".join(stop_encontradas)

def tratar_arquivo(arquivo):
    df = pd.read_csv(arquivo)

    base_datetime = datetime.strptime(
        arquivo.replace(".csv", ""), "%Y%m%d_%H%M%S"
    )

    # Tempo → Data
    df["Tempo_segundos"] = df["Tempo"].apply(converter_tempo)

    df["Data"] = df["Tempo_segundos"].apply(
        lambda x: base_datetime - timedelta(seconds=x)
    )

    # Hashtags → tópicos
    df["topicos"] = df["Texto"].apply(extrair_hashtags)
    df["topicos"] = df["topicos"].apply(
        lambda lista: [tag.replace("#", "") for tag in lista]
    )
    df["topicos"] = df["topicos"].apply(lambda x: ", ".join(x))

    # Limpeza inicial
    df["Texto"] = df["Texto"].apply(remover_hashtags)
    df["Texto"] = df["Texto"].apply(remover_mencoes)
    df["Texto"] = df["Texto"].apply(remover_urls)
    df["Texto"] = df["Texto"].str.replace(r"\s+", " ", regex=True).str.strip()

    # Stop words
    resultado = df["Texto"].apply(processar_stopwords)

    df["Texto"] = resultado.apply(lambda x: x[0])
    df["Stop words"] = resultado.apply(lambda x: x[1])

    # Remover colunas auxiliares
    df = df.drop(columns=["Tempo", "Tempo_segundos"])

    return df

# =========================================================
# Lista de arquivos
# =========================================================
arquivos = [
    "20260319_190337.csv",
    "20260319_191323.csv",
    "20260326_190533.csv"
]

# =========================================================
# Processar todos e juntar
# =========================================================
dfs = [tratar_arquivo(arq) for arq in arquivos]

df_final = pd.concat(dfs, ignore_index=True)

# =========================================================
# Salvar resultado final
# =========================================================
df_final.to_csv("tweets_tratados_final.csv", index=False)

print(df_final.head())