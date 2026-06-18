import pandas as pd
import nltk
from nltk.corpus import stopwords
from nltk.stem import RSLPStemmer
from sklearn.feature_extraction.text import CountVectorizer

# Download (1x)
nltk.download('rslp')
nltk.download('stopwords')

# =========================================================
# Carregar CSV
# =========================================================
df = pd.read_csv("tweets_tratados_final.csv")
corpus = df["Texto"].astype(str).tolist()

# =========================================================
# Stemming + Stopwords
# =========================================================
stemmer = RSLPStemmer()
stop_words = stopwords.words('portuguese')
stop_words_stemmed = set([stemmer.stem(word) for word in stop_words])

# =========================================================
# Pré-processamento manual (ESSENCIAL)
# =========================================================
def preprocess(text):
    tokens = text.lower().split()
    tokens = [stemmer.stem(t) for t in tokens]
    tokens = [t for t in tokens if t not in stop_words_stemmed]
    return " ".join(tokens)  # 🔥 retorna string limpa

corpus_processado = [preprocess(doc) for doc in corpus]

# =========================================================
# Bag of Words (sem tokenizer customizado)
# =========================================================
bow_vec = CountVectorizer(
    binary=True  # presença (0 ou 1)
)

X_bow = bow_vec.fit_transform(corpus_processado)

df_bow = pd.DataFrame(
    X_bow.toarray(),
    columns=bow_vec.get_feature_names_out()
)

# Ordenar colunas
df_bow = df_bow.reindex(sorted(df_bow.columns), axis=1)

# =========================================================
# Salvar resultado
# =========================================================
df_bow.to_csv("bow_resultado.csv", index=False)
