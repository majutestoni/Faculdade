import undetected_chromedriver as uc
from selenium.webdriver.common.by import By
import urllib.parse
import time
import signal
import sys
import os
from datetime import datetime
import pandas as pd

# ============================================================
# CONFIGURAÇÕES
# ============================================================
ROLAGENS_DE_TELA = 100
PASTA_SAIDA = "coletas_g12"
TEMPO_ENTRE_ROLAGENS = 4  # segundos

TIMES_G12 = [
    "Flamengo", "Fluminense", "Vasco", "Botafogo",
    "Corinthians", "Palmeiras", "São Paulo", "Santos",
    "Grêmio", "Internacional", "Atlético Mineiro", "Cruzeiro"
]

# ============================================================
# VARIÁVEIS GLOBAIS
# ============================================================
dados_tweets = []
driver = None
pasta_execucao = ""
inicio = None


def salvar_dados(motivo="finalização"):
    """Salva os dados coletados até o momento no CSV dentro da pasta da execução."""
    if not dados_tweets:
        print(f"\nNenhum tweet coletado até o momento ({motivo}).")
        return None

    df = pd.DataFrame(dados_tweets)
    arquivo = os.path.join(pasta_execucao, "tweets_g12.csv")
    df.to_csv(arquivo, index=False, encoding="utf-8")

    duracao = time.time() - inicio
    minutos = int(duracao // 60)
    segundos = int(duracao % 60)

    print(f"\n{'=' * 50}")
    print(f"Dados salvos ({motivo})")
    print(f"Pasta:   {pasta_execucao}")
    print(f"Arquivo: {arquivo}")
    print(f"Tweets:  {len(df)}")
    print(f"Tempo:   {minutos}min {segundos}s")
    print(f"{'=' * 50}")

    # Salva também um resumo por time
    resumo = df["Time"].value_counts().reset_index()
    resumo.columns = ["Time", "Quantidade"]
    resumo_arquivo = os.path.join(pasta_execucao, "resumo_por_time.csv")
    resumo.to_csv(resumo_arquivo, index=False, encoding="utf-8")
    print(f"Resumo por time salvo em: {resumo_arquivo}")

    return arquivo


def encerrar(signum=None, frame=None):
    """Handler chamado quando o usuário aperta Ctrl+C."""
    print("\n\nInterrupção detectada (Ctrl+C)! Salvando dados coletados...")
    salvar_dados(motivo="interrupção pelo usuário")

    if driver:
        try:
            print("Fechando o navegador...")
            driver.quit()
        except Exception:
            pass

    sys.exit(0)


# Registra o handler de Ctrl+C
signal.signal(signal.SIGINT, encerrar)

# ============================================================
# PREPARAÇÃO DA PASTA DE SAÍDA
# ============================================================
agora = datetime.now()
nome_pasta = agora.strftime("%Y-%m-%d_%H-%M-%S")
pasta_execucao = os.path.join(PASTA_SAIDA, nome_pasta)
os.makedirs(pasta_execucao, exist_ok=True)

print(f"Pasta desta coleta: {pasta_execucao}")

# ============================================================
# CONFIGURAÇÃO DO NAVEGADOR
# ============================================================
print("Configurando o navegador anti-bloqueio...")

options = uc.ChromeOptions()
driver = uc.Chrome(options=options, version_main=146)

# Login manual
driver.get("https://x.com/login")

print("\n" + "=" * 50)
print("ATENÇÃO: Uma janela do Chrome foi aberta.")
print("1. Vá até essa janela e faça o login com a sua conta do X.")
print("2. Assim que você ver a sua página inicial (feed), volte aqui.")
print("3. Pressione ENTER para o robô começar a coleta.")
print("=" * 50 + "\n")
input("Pressione ENTER quando estiver logado...")

# ============================================================
# MONTANDO A BUSCA
# ============================================================
query = (
    '("Flamengo" OR "Fluminense" OR "Vasco" OR "Botafogo" '
    'OR "Corinthians" OR "Palmeiras" OR "São Paulo" OR "Santos" '
    'OR "Grêmio" OR "Internacional" OR "Atlético Mineiro" OR "Cruzeiro") '
    'lang:pt'
)
safe_query = urllib.parse.quote(query)
search_url = f"https://x.com/search?q={safe_query}&src=typed_query&f=live"

print("Indo para a página de busca...")
driver.get(search_url)
time.sleep(5)

# ============================================================
# RASPAGEM
# ============================================================
inicio = time.time()

print(f"\nIniciando coleta — {ROLAGENS_DE_TELA} rolagens planejadas")
print(f"   Ctrl+C a qualquer momento para parar e salvar\n")

for i in range(ROLAGENS_DE_TELA):
    pagina_inicio = time.time()
    novos_nesta_rolagem = 0

    tweets_na_tela = driver.find_elements(By.CSS_SELECTOR, 'article[data-testid="tweet"]')

    for tweet in tweets_na_tela:
        try:
            texto = tweet.find_element(By.CSS_SELECTOR, 'div[data-testid="tweetText"]').text
            autor_texto = tweet.find_element(By.CSS_SELECTOR, 'div[data-testid="User-Name"]').text.split('\n')

            nome = autor_texto[0] if len(autor_texto) > 0 else ""
            usuario = autor_texto[1] if len(autor_texto) > 1 else ""
            tempo = autor_texto[3] if len(autor_texto) > 3 else ""

            texto_minusculo = texto.lower()
            time_citado = next((t for t in TIMES_G12 if t.lower() in texto_minusculo), None)

            if time_citado and texto not in [t['Texto'] for t in dados_tweets]:
                dados_tweets.append({
                    "Time": time_citado,
                    "Nome": nome,
                    "Usuario": usuario,
                    "Tempo": tempo,
                    "Texto": texto
                })
                novos_nesta_rolagem += 1

        except Exception:
            continue

    # Log de cada rolagem
    tempo_rolagem = time.time() - pagina_inicio
    print(
        f"Rolagem {i + 1:>3}/{ROLAGENS_DE_TELA} | "
        f"+{novos_nesta_rolagem:>3} novos | "
        f"Total: {len(dados_tweets):>5} tweets | "
        f"({tempo_rolagem:.1f}s)"
    )

    # Salva checkpoint a cada 10 rolagens para não perder dados
    if (i + 1) % 10 == 0 and dados_tweets:
        checkpoint = os.path.join(pasta_execucao, "tweets_g12.csv")
        pd.DataFrame(dados_tweets).to_csv(checkpoint, index=False, encoding="utf-8")
        print(f"Checkpoint salvo — {len(dados_tweets)} tweets até agora")

    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
    time.sleep(TEMPO_ENTRE_ROLAGENS)

# ============================================================
# FINALIZAÇÃO
# ============================================================
print("\nFechando o navegador...")
driver.quit()
driver = None  # Evita que o handler tente fechar de novo

salvar_dados(motivo="coleta completa")