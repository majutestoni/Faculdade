import pickle
import pandas as pd
import nltk
import spacy
from nltk.corpus import stopwords
from nltk.stem import RSLPStemmer
from sklearn.feature_extraction.text import TfidfVectorizer

CSV_PATH = "../limpezaDados/tweets_tratados_final.csv"
RESULTADO_CSV = "tfidf_stem_lemma_resultado.csv"
MODELO_PKL = "tfidf_stem_lemma_model.pkl"

for recurso, caminho in [("rslp", "stemmers/rslp"), ("stopwords", "corpora/stopwords")]:
    try:
        nltk.data.find(caminho)
    except LookupError:
        nltk.download(recurso, quiet=True)

nlp = spacy.load("pt_core_news_sm", disable=["parser", "ner"])
stemmer = RSLPStemmer()
stop_words = set(stopwords.words("portuguese"))
stop_words_stem = set(stemmer.stem(w) for w in stop_words)


def preprocess(text: str) -> str:
    doc = nlp(str(text).lower())
    tokens = []
    for t in doc:
        if not t.is_alpha:
            continue
        lemma = t.lemma_.lower()
        if lemma in stop_words:
            continue
        stem = stemmer.stem(lemma)
        if stem in stop_words_stem:
            continue
        tokens.append(stem)
    return " ".join(tokens)


if __name__ == "__main__":
    df = pd.read_csv(CSV_PATH)
    corpus = df["Texto"].astype(str).tolist()

    print(f"Pré-processando {len(corpus)} documentos (lemma + stem + stopwords)...")
    corpus_proc = [preprocess(doc) for doc in corpus]

    vectorizer = TfidfVectorizer(
        lowercase=False,
        token_pattern=r"\S+",
        min_df=2,
        norm="l2",
        sublinear_tf=True,
    )
    X_tfidf = vectorizer.fit_transform(corpus_proc)

    df_tfidf = pd.DataFrame(
        X_tfidf.toarray(),
        columns=vectorizer.get_feature_names_out(),
    )
    df_tfidf = df_tfidf.reindex(sorted(df_tfidf.columns), axis=1)
    df_tfidf.to_csv(RESULTADO_CSV, index=False)

    with open(MODELO_PKL, "wb") as f:
        pickle.dump({"vectorizer": vectorizer, "matrix": X_tfidf, "df": df}, f)

    print(f"Documentos: {X_tfidf.shape[0]}")
    print(f"Termos no vocabulário: {X_tfidf.shape[1]}")
    print(f"Salvo em: {RESULTADO_CSV} e {MODELO_PKL}")
