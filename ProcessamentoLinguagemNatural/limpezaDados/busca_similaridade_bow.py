import argparse
import pandas as pd
import nltk
from nltk.corpus import stopwords
from nltk.stem import RSLPStemmer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

for recurso, caminho in [("rslp", "stemmers/rslp"), ("stopwords", "corpora/stopwords")]:
    try:
        nltk.data.find(caminho)
    except LookupError:
        nltk.download(recurso, quiet=True)

CSV_PATH = "tweets_tratados_final.csv"

stemmer = RSLPStemmer()
stop_words_stemmed = set(stemmer.stem(w) for w in stopwords.words("portuguese"))


def preprocess(text: str) -> str:
    tokens = str(text).lower().split()
    tokens = [stemmer.stem(t) for t in tokens]
    tokens = [t for t in tokens if t not in stop_words_stemmed]
    return " ".join(tokens)


def construir_indice():
    df = pd.read_csv(CSV_PATH)
    corpus = df["Texto"].astype(str).tolist()
    corpus_proc = [preprocess(doc) for doc in corpus]

    vectorizer = CountVectorizer(binary=True)
    matrix = vectorizer.fit_transform(corpus_proc)
    return df, vectorizer, matrix


def buscar(query: str, top: int):
    df, vectorizer, matrix = construir_indice()

    q_proc = preprocess(query)
    q_vec = vectorizer.transform([q_proc])

    sims = cosine_similarity(q_vec, matrix).flatten()
    top_idx = sims.argsort()[::-1][:top]

    print(f"\nQuery: {query!r}")
    print(f"Query pré-processada: {q_proc!r}")
    print(f"Top {top} resultados:\n")
    for rank, idx in enumerate(top_idx, start=1):
        score = sims[idx]
        linha = df.iloc[idx]
        print(f"#{rank}  score={score:.4f}  [{linha.get('Time', '')}]  {linha.get('Usuario', '')}")
        print(f"    {linha.get('Texto', '')}\n")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Busca de similaridade sobre Bag of Words.")
    parser.add_argument("--query", required=True, help="Texto da consulta.")
    parser.add_argument("--top", type=int, default=5, help="Quantos resultados retornar.")
    args = parser.parse_args()
    buscar(args.query, args.top)
