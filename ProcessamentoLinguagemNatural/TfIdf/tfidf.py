import pickle
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer

CSV_PATH = "../limpezaDados/tweets_tratados_final.csv"
RESULTADO_CSV = "tfidf_resultado.csv"
MODELO_PKL = "tfidf_model.pkl"

df = pd.read_csv(CSV_PATH)
corpus = df["Texto"].astype(str).tolist()

vectorizer = TfidfVectorizer(
    lowercase=True,
    token_pattern=r"\w+",
    min_df=2,
    norm="l2",
    sublinear_tf=True,
)

X_tfidf = vectorizer.fit_transform(corpus)

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
