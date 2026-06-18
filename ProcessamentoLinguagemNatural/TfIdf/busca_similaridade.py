import argparse
import pickle
from sklearn.metrics.pairwise import cosine_similarity

MODELO_PKL = "tfidf_model.pkl"


def buscar(query: str, top: int):
    with open(MODELO_PKL, "rb") as f:
        modelo = pickle.load(f)

    vectorizer = modelo["vectorizer"]
    matrix = modelo["matrix"]
    df = modelo["df"]

    q_vec = vectorizer.transform([query])
    sims = cosine_similarity(q_vec, matrix).flatten()
    top_idx = sims.argsort()[::-1][:top]

    print(f"\nQuery: {query!r}")
    print(f"Top {top} resultados:\n")
    for rank, idx in enumerate(top_idx, start=1):
        score = sims[idx]
        linha = df.iloc[idx]
        print(f"#{rank}  score={score:.4f}  [{linha.get('Time', '')}]  {linha.get('Usuario', '')}")
        print(f"    {linha.get('Texto', '')}\n")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Busca de similaridade TF-IDF.")
    parser.add_argument("--query", required=True, help="Texto da consulta.")
    parser.add_argument("--top", type=int, default=5, help="Quantos resultados retornar.")
    args = parser.parse_args()
    buscar(args.query, args.top)
