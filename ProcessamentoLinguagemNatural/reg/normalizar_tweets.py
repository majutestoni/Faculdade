import pandas as pd
import json
import re
import traceback
from pathlib import Path

BASE_DIR = Path(__file__).parent
PATH_DATASET = BASE_DIR.parent / "limpezaDados" / "tweets_tratados_final.csv"
PATH_SAIDA = BASE_DIR / "tweets_normalizados.csv"
PATH_PROMPTS_DEBUG = BASE_DIR / "prompts_debug.txt"

DRY_RUN = False          # True = NAO chama a API; apenas monta e salva os prompts
BATCH_SIZE = 20         # quantos tweets por chamada (reduz custo/latencia)
LIMITE_AMOSTRA = 40     # use None para processar todo o dataset
MODELO = "gemini-2.5-flash"

INSTRUCAO = """Voce e um normalizador de texto para um pipeline de analise de sentimentos sobre tweets de futebol em portugues.

Para cada tweet recebido, devolva uma versao normalizada seguindo ESTAS regras:

1. Corrija apenas erros ortograficos obvios e expanda APENAS as seguintes abreviacoes (lista fechada):
   "vc"->"voce", "vcs"->"voces", "pq"->"porque", "pqp"->"pqp" (mantenha),
   "tb"/"tbm"->"tambem", "q"->"que", "n"->"nao", "tmj"->"tamo junto",
   "pra"->"para", "pro"->"para o", "msm"->"mesmo", "tlg"->"tipo logo".
   NAO expanda mais nada. Em particular, NAO altere formatos de hora ("20h" continua "20h"),
   data, numeros, siglas, nomes de times ou jogadores.

2. NUNCA substitua girias, palavroes, xingamentos ou expressoes de raiva/zoacao por sinonimos
   neutros. Eles carregam o sentimento que precisamos preservar. Mantenha EXATAMENTE as palavras
   emocionais do original ("fudido", "foda", "lixo", "merda", "porra", "caralho", etc).

3. Remova URLs, mencoes (@usuario) e hashtags vazias. Hashtags com conteudo semantico viram
   texto (#golaco -> golaco).

4. Mantenha emojis que carreguem sentimento.

5. NAO altere o sentimento, ironia, sarcasmo nem o alvo do tweet (time, jogador).

6. NAO invente conteudo. Se o tweet nao tiver texto util (so pontuacao, so nomes proprios,
   so numeros), devolva o texto original inalterado.

7. PRESERVE TODOS OS ACENTOS E CEDILHAS exatamente como no original.
   "nao" no original continua "nao"; "não" no original continua "não".
   NUNCA troque "grêmio" por "gremio", "são" por "sao", "também" por "tambem",
   "araújo" por "araujo", etc.

8. PRESERVE censura intencional do usuario. Se a palavra original tem digitos no
   lugar de letras ou asteriscos (ex: "f0der", "p*rra", "c@ralho"), MANTENHA assim.
   NAO "corrija" a censura.

Responda APENAS com um JSON valido no formato:
[
  {"id": 1, "texto": "..."},
  {"id": 2, "texto": "..."}
]
"""


def carregar_tweets():
    df = pd.read_csv(PATH_DATASET)
    df = df.dropna(subset=["Texto"]).reset_index(drop=True)
    if LIMITE_AMOSTRA is not None:
        df = df.head(LIMITE_AMOSTRA).copy()
    return df


def montar_prompt(batch):
    linhas = [f'{tid}. {texto}' for tid, texto in batch]
    return INSTRUCAO + "\nTweets:\n" + "\n".join(linhas)


def chamar_gemini(client, prompt):
    from google.genai import types
    response = client.models.generate_content(
        model=MODELO,
        contents=prompt,
        config=types.GenerateContentConfig(
            response_mime_type="application/json",
        ),
    )
    return response.text


def parse_resposta(texto_resposta):
    match = re.search(r'\[.*\]', texto_resposta, re.DOTALL)
    if not match:
        raise ValueError(f"Sem JSON na resposta: {texto_resposta[:200]}")
    return json.loads(match.group(0))


def main():
    df = carregar_tweets()
    
    client = None
    if not DRY_RUN:
        from google import genai                # import lazy: so necessario na chamada real
        client = genai.Client(api_key="")       # preencher API key quando for rodar

    resultados = {} 
    debug_prompts = []

    for inicio in range(0, len(df), BATCH_SIZE):
        fim = min(inicio + BATCH_SIZE, len(df))
        batch = [(i + 1, df.loc[i, "Texto"]) for i in range(inicio, fim)]
        prompt = montar_prompt(batch)
        debug_prompts.append(prompt)

        n_chamada = inicio // BATCH_SIZE + 1
        print(f"  Batch {n_chamada}: tweets {inicio + 1}..{fim}  |  chars do prompt: {len(prompt)}")

        if DRY_RUN:
            continue

        try:
            resposta = chamar_gemini(client, prompt)

            itens = parse_resposta(resposta)
            for item in itens:
                global_idx = int(item["id"]) - 1
                resultados[global_idx] = item.get("texto", "")
        except Exception as e:
            print(f"  [erro batch {n_chamada}]: {type(e).__name__}: {e}")
            traceback.print_exc()

    # salva os prompts montados para inspecao manual
    PATH_PROMPTS_DEBUG.write_text(
        "\n\n=====\n\n".join(debug_prompts),
        encoding="utf-8",
    )

    if DRY_RUN:
        print(debug_prompts[0][:1200] + ("\n... [truncado]" if len(debug_prompts[0]) > 1200 else ""))
        return

    df_out = df.copy()
    df_out["Texto_normalizado"] = [resultados.get(i, "") for i in range(len(df))]
    df_out.to_csv(PATH_SAIDA, index=False, encoding="utf-8")

if __name__ == "__main__":
    main()
