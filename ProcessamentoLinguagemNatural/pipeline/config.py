"""Configurações compartilhadas do pipeline."""
import os
from pathlib import Path

BASE_DIR = Path(__file__).parent           # pipeline/
RAIZ_PROJETO = BASE_DIR.parent

PASTA_RUNS = BASE_DIR / "runs"             # saídas de cada execução do pipeline
PASTA_COLETAS = RAIZ_PROJETO / "WebScrapping" / "coletas_g12"
PERFIL_CHROME = BASE_DIR / ".chrome_profile"   # sessão do X fica salva aqui (gitignored)

# ----- Gemini -----
MODELO_GEMINI = "gemini-3.1-flash-lite"
# 1 batch = 1 requisição à API. Batch grande faz cada etapa LLM (filtro,
# normalização, sentimento) caber em UMA chamada, então o pipeline completo
# custa ~3 requisições. Tweets são curtos, cabem de sobra no contexto do flash.
BATCH_SIZE = 40            # tweets por chamada
PAUSA_ENTRE_CHAMADAS = 6   # segundos (free tier ~10 req/min)
MAX_TENTATIVAS = 3

# ----- Orçamento de requisições (free tier pode limitar a ~20/dia) -----
LIMITE_TWEETS_PADRAO = 30  # quantos tweets o pipeline processa por padrão (0 = todos)
MAX_REQUISICOES = 20       # teto de segurança: o pipeline aborta se for exceder

# ----- Scraping -----
ROLAGENS_DE_TELA = 30
TEMPO_ENTRE_ROLAGENS = 4   # segundos
# None = deixar o undetected_chromedriver detectar a versão do Chrome instalado.
# Se der erro de versão, exporte CHROME_VERSION_MAIN=146 (por exemplo).
VERSAO_CHROME = os.environ.get("CHROME_VERSION_MAIN")

TIMES_G12 = [
    "Flamengo", "Fluminense", "Vasco", "Botafogo",
    "Corinthians", "Palmeiras", "São Paulo", "Santos",
    "Grêmio", "Internacional", "Atlético Mineiro", "Cruzeiro",
]


def api_key():
    key = os.environ.get("GEMINI_API_KEY", "").strip()
    if not key:
        raise RuntimeError(
            "Variável de ambiente GEMINI_API_KEY não definida.\n"
            "  export GEMINI_API_KEY='sua-chave'  (https://aistudio.google.com/apikey)"
        )
    return key
