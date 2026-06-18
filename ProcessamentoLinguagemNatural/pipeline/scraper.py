#!/usr/bin/env python3
"""Etapa 1 — Coleta de tweets do X (Twitter) com sessão persistente.

O X bloqueia agressivamente automação, então a estratégia é:
  - undetected_chromedriver (navegador real, anti-detecção)
  - perfil do Chrome salvo em pipeline/.chrome_profile: o login manual é feito
    UMA única vez e a sessão (cookies) fica guardada no perfil
  - nas execuções seguintes o scraper roda sozinho, sem intervenção

Primeiro uso:
    python pipeline/scraper.py --login
    (faça o login na janela que abrir e pressione ENTER)

Depois disso:
    python pipeline/scraper.py --rolagens 30          # standalone
    python pipeline/run_pipeline.py                   # dentro do pipeline
"""
import argparse
import os
import shutil
import sys
import time
import urllib.parse
from datetime import datetime
from pathlib import Path

import pandas as pd

import config

URL_HOME = "https://x.com/home"
URL_LOGIN = "https://x.com/login"
SELETOR_LOGADO = '[data-testid="AppTabBar_Home_Link"]'  # só existe com sessão ativa
SELETOR_TWEET = 'article[data-testid="tweet"]'


# Locais onde um navegador Chromium costuma ficar no macOS (em ordem de preferência).
CAMINHOS_CHROME = [
    "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome",
    str(Path.home() / "Applications/Google Chrome.app/Contents/MacOS/Google Chrome"),
    "/Applications/Chromium.app/Contents/MacOS/Chromium",
    "/Applications/Brave Browser.app/Contents/MacOS/Brave Browser",
    "/Applications/Microsoft Edge.app/Contents/MacOS/Microsoft Edge",
]


def descobrir_chrome():
    """Caminho de um Chrome/Chromium instalado, ou None.

    O undetected_chromedriver erra a detecção em selenium novo (gera o críptico
    'Binary Location Must be a String'), então localizamos o binário nós mesmos.
    Override manual: export CHROME_BINARY=/caminho/para/o/chrome
    """
    override = os.environ.get("CHROME_BINARY", "").strip()
    if override:
        return override
    for caminho in CAMINHOS_CHROME:
        if os.path.exists(caminho) and os.access(caminho, os.X_OK):
            return caminho
    for nome in ("google-chrome", "chromium", "chromium-browser", "chrome"):
        achado = shutil.which(nome)
        if achado:
            return achado
    return None


def _eh_macho_arm64(caminho):
    """True se o arquivo é um Mach-O nativo arm64."""
    try:
        with open(caminho, "rb") as f:
            head = f.read(8)
    except OSError:
        return False
    # Mach-O 64-bit (little-endian): magic CF FA ED FE; cputype nos bytes 4..8.
    # arm64 = 0x0100000C, x86_64 = 0x01000007
    return len(head) == 8 and int.from_bytes(head[4:8], "little") == 0x0100000C


def _assinar_adhoc(caminho):
    """Re-assina o chromedriver ad-hoc.

    O uc modifica o binário (patch anti-detecção) DEPOIS de ele vir assinado,
    invalidando a assinatura; o kernel arm64 mata binário com assinatura inválida
    (SIGKILL / exit 137). 'codesign --force' recalcula os hashes sobre os bytes
    já modificados, tornando a assinatura válida de novo.
    """
    import subprocess
    try:
        subprocess.run(
            ["codesign", "--sign", "-", "--force", str(caminho)],
            capture_output=True, check=True,
        )
    except Exception as e:
        print(f"  [aviso] não consegui re-assinar o chromedriver: {e}")


def _preparar_driver_arm64():
    """Corrige dois bugs do undetected_chromedriver 3.5.5 em Apple Silicon:

    1. Arquitetura: o uc fixa o chromedriver Intel ('mac-x64') e ignora arm64,
       gerando 'Bad CPU type in executable'. Forçamos o build 'mac-arm64'.
    2. Assinatura: o uc patcheia o binário e invalida a assinatura; o kernel
       arm64 mata binário com assinatura inválida (SIGKILL). Re-assinamos ad-hoc
       logo após o patch (envolvendo Patcher.auto).

    Em Macs Intel/Linux/Windows nada disso é aplicado.
    """
    import platform as _plat
    if not (sys.platform.startswith("darwin") and _plat.machine() == "arm64"):
        return
    from undetected_chromedriver.patcher import Patcher

    if not getattr(Patcher, "_arm64_corrigido", False):
        set_platform_original = Patcher._set_platform_name
        auto_original = Patcher.auto

        def _set_platform_name_arm(self):
            set_platform_original(self)
            if not self.is_old_chromedriver:   # Chrome >= 115 usa Chrome for Testing
                self.platform_name = "mac-arm64"

        def _auto_reassinando(self, *args, **kwargs):
            resultado = auto_original(self, *args, **kwargs)
            _assinar_adhoc(self.executable_path)
            return resultado

        Patcher._set_platform_name = _set_platform_name_arm
        Patcher.auto = _auto_reassinando
        Patcher._arm64_corrigido = True

    cache = Path(Patcher.data_path) / "undetected_chromedriver"
    if cache.exists() and not _eh_macho_arm64(cache):
        cache.unlink()  # cache Intel -> remove para o uc rebaixar o arm64


def criar_driver():
    import undetected_chromedriver as uc

    _preparar_driver_arm64()
    chrome = descobrir_chrome()
    if not chrome:
        raise RuntimeError(
            "Nenhum navegador Chrome/Chromium foi encontrado nesta máquina.\n"
            "O scraper precisa de um Chrome real para funcionar. Opções:\n"
            "  1) Instalar o Chrome:   brew install --cask google-chrome\n"
            "  2) Rodar sem scraping:  python pipeline/run_pipeline.py --sem-scraping\n"
            "Se o Chrome estiver em um caminho fora do padrão, aponte com:\n"
            "  export CHROME_BINARY='/caminho/para/o/Chrome'"
        )

    options = uc.ChromeOptions()
    options.binary_location = chrome              # evita o bug de detecção do uc
    kwargs = {
        "options": options,
        "browser_executable_path": chrome,
        "user_data_dir": str(config.PERFIL_CHROME),  # é isso que persiste o login
    }
    if config.VERSAO_CHROME:
        kwargs["version_main"] = int(config.VERSAO_CHROME)
    return uc.Chrome(**kwargs)


def esta_logado(driver, timeout=12):
    from selenium.webdriver.common.by import By
    from selenium.webdriver.support.ui import WebDriverWait
    from selenium.webdriver.support import expected_conditions as EC

    driver.get(URL_HOME)
    try:
        WebDriverWait(driver, timeout).until(
            EC.presence_of_element_located((By.CSS_SELECTOR, SELETOR_LOGADO))
        )
        return True
    except Exception:
        return False


def garantir_login(driver, interativo):
    if esta_logado(driver):
        print("Sessão do X reaproveitada do perfil salvo — sem login manual.")
        return

    if not interativo:
        raise RuntimeError(
            "Sessão do X expirada ou inexistente e não há terminal interativo.\n"
            "Rode uma vez:  python pipeline/scraper.py --login"
        )

    print("\n" + "=" * 50)
    print("Sessão não encontrada. Faça o login manualmente:")
    print("1. Na janela do Chrome que abriu, entre com sua conta do X.")
    print("2. Quando aparecer o seu feed, volte aqui e pressione ENTER.")
    print("   (o login fica salvo; nas próximas execuções é automático)")
    print("=" * 50 + "\n")
    driver.get(URL_LOGIN)
    input("Pressione ENTER quando estiver logado...")

    if not esta_logado(driver):
        raise RuntimeError("Login não detectado. Tente novamente com --login.")


def montar_url_busca():
    nomes = " OR ".join(f'"{t}"' for t in config.TIMES_G12)
    query = f"({nomes}) lang:pt"
    return f"https://x.com/search?q={urllib.parse.quote(query)}&src=typed_query&f=live"


def _extrair_tweets_da_tela(driver, dados, textos_vistos):
    from selenium.webdriver.common.by import By

    novos = 0
    for tweet in driver.find_elements(By.CSS_SELECTOR, SELETOR_TWEET):
        try:
            texto = tweet.find_element(By.CSS_SELECTOR, 'div[data-testid="tweetText"]').text
            autor = tweet.find_element(By.CSS_SELECTOR, 'div[data-testid="User-Name"]').text.split("\n")

            nome = autor[0] if len(autor) > 0 else ""
            usuario = autor[1] if len(autor) > 1 else ""
            tempo = autor[3] if len(autor) > 3 else ""

            texto_minusculo = texto.lower()
            time_citado = next(
                (t for t in config.TIMES_G12 if t.lower() in texto_minusculo), None
            )

            if time_citado and texto not in textos_vistos:
                textos_vistos.add(texto)
                dados.append({
                    "Time": time_citado,
                    "Nome": nome,
                    "Usuario": usuario,
                    "Tempo": tempo,
                    "Texto": texto,
                    "Coletado_em": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
                })
                novos += 1
        except Exception:
            continue
    return novos


def _salvar(dados, destino_csv, motivo):
    if not dados:
        print(f"\nNenhum tweet coletado ({motivo}).")
        return None

    destino_csv.parent.mkdir(parents=True, exist_ok=True)
    df = pd.DataFrame(dados)
    df.to_csv(destino_csv, index=False, encoding="utf-8")

    resumo = df["Time"].value_counts().reset_index()
    resumo.columns = ["Time", "Quantidade"]
    resumo.to_csv(destino_csv.parent / "resumo_por_time.csv", index=False, encoding="utf-8")

    print(f"\n{'=' * 50}")
    print(f"Coleta salva ({motivo})")
    print(f"Arquivo: {destino_csv}")
    print(f"Tweets:  {len(df)}")
    print(f"{'=' * 50}")
    return destino_csv


def coletar(destino_csv, rolagens=config.ROLAGENS_DE_TELA, interativo=None):
    """Executa a coleta e retorna o Path do CSV salvo (ou None se nada foi coletado)."""
    if interativo is None:
        interativo = sys.stdin.isatty()
    destino_csv = Path(destino_csv)

    dados, textos_vistos = [], set()
    driver = None
    motivo = "coleta completa"

    try:
        print("Abrindo o navegador anti-bloqueio...")
        driver = criar_driver()
        garantir_login(driver, interativo)

        print("Indo para a página de busca...")
        driver.get(montar_url_busca())
        time.sleep(5)

        inicio = time.time()
        print(f"\nIniciando coleta — {rolagens} rolagens (Ctrl+C para parar e salvar)\n")

        for i in range(rolagens):
            t0 = time.time()
            novos = _extrair_tweets_da_tela(driver, dados, textos_vistos)

            print(f"Rolagem {i + 1:>3}/{rolagens} | +{novos:>3} novos | "
                  f"Total: {len(dados):>5} tweets | ({time.time() - t0:.1f}s)")

            if (i + 1) % 10 == 0 and dados:
                destino_csv.parent.mkdir(parents=True, exist_ok=True)
                pd.DataFrame(dados).to_csv(destino_csv, index=False, encoding="utf-8")
                print(f"Checkpoint salvo — {len(dados)} tweets até agora")

            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            time.sleep(config.TEMPO_ENTRE_ROLAGENS)

        duracao = time.time() - inicio
        print(f"\nColeta finalizada em {int(duracao // 60)}min {int(duracao % 60)}s")

    except KeyboardInterrupt:
        motivo = "interrupção pelo usuário"
        print("\n\nInterrupção detectada (Ctrl+C)! Salvando dados coletados...")
    finally:
        if driver:
            try:
                driver.quit()
            except Exception:
                pass

    return _salvar(dados, destino_csv, motivo)


def fazer_login():
    """Abre o navegador só para criar/renovar a sessão salva no perfil."""
    driver = criar_driver()
    try:
        garantir_login(driver, interativo=True)
        print("\nSessão salva com sucesso em pipeline/.chrome_profile.")
        print("A partir de agora o scraper roda sem login manual.")
    finally:
        driver.quit()


def main():
    parser = argparse.ArgumentParser(description="Coleta tweets do X sobre os times do G12.")
    parser.add_argument("--login", action="store_true",
                        help="só faz o login manual e salva a sessão (rodar uma vez)")
    parser.add_argument("--rolagens", type=int, default=config.ROLAGENS_DE_TELA)
    parser.add_argument("--saida", type=Path, default=None,
                        help="CSV de destino (padrão: WebScrapping/coletas_g12/<timestamp>/tweets_g12.csv)")
    args = parser.parse_args()

    if args.login:
        fazer_login()
        return

    destino = args.saida
    if destino is None:
        pasta = config.PASTA_COLETAS / datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
        destino = pasta / "tweets_g12.csv"

    coletar(destino, rolagens=args.rolagens)


if __name__ == "__main__":
    main()
