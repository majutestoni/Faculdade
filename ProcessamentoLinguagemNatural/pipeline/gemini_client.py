"""Cliente Gemini compartilhado pelas etapas do pipeline.

Toda etapa que usa LLM segue o mesmo padrão:
  - monta um prompt com N tweets numerados (1. texto / 2. texto / ...)
  - pede resposta em JSON [{"id": 1, ...}, ...]
  - mapeia cada item de volta para a posição do tweet no DataFrame

`processar_em_batches` centraliza esse loop (batches, retry, pausa entre
chamadas e tolerância a falha parcial) para não repetir código em cada etapa.
"""
import json
import math
import re
import time

import config


class ClienteGemini:
    def __init__(self, modelo=config.MODELO_GEMINI):
        from google import genai  # import lazy: dry-run funciona sem o pacote
        self._client = genai.Client(api_key=config.api_key())
        self.modelo = modelo

    def gerar_json(self, prompt):
        """Chama o modelo pedindo JSON; tenta de novo com backoff em caso de erro."""
        from google.genai import types
        ultimo_erro = None
        for tentativa in range(1, config.MAX_TENTATIVAS + 1):
            try:
                response = self._client.models.generate_content(
                    model=self.modelo,
                    contents=prompt,
                    config=types.GenerateContentConfig(
                        response_mime_type="application/json",
                    ),
                )
                return extrair_json(response.text)
            except Exception as e:
                ultimo_erro = e
                espera = 10 * (2 ** (tentativa - 1))
                print(f"    [aviso] tentativa {tentativa}/{config.MAX_TENTATIVAS} falhou "
                      f"({type(e).__name__}: {e}). Aguardando {espera}s...")
                time.sleep(espera)
        raise RuntimeError(
            f"Gemini falhou após {config.MAX_TENTATIVAS} tentativas: {ultimo_erro}"
        )


def extrair_json(texto):
    try:
        return json.loads(texto)
    except (json.JSONDecodeError, TypeError):
        pass
    match = re.search(r"\[.*\]", str(texto), re.DOTALL)
    if not match:
        raise ValueError(f"Sem JSON na resposta: {str(texto)[:200]}")
    return json.loads(match.group(0))


def processar_em_batches(client, df, coluna_texto, instrucao,
                         dry_run=False, rotulo="processando",
                         batch_size=config.BATCH_SIZE,
                         pausa=config.PAUSA_ENTRE_CHAMADAS):
    """Aplica a `instrucao` a todos os tweets de `df[coluna_texto]` em batches.

    Retorna (resultados, prompts):
      resultados: dict {posicao_no_df (0-based) -> item dict devolvido pelo modelo}
      prompts:    lista dos prompts montados (para debug/inspeção)

    Em dry-run nenhuma chamada é feita; `resultados` volta vazio.
    """
    resultados, prompts = {}, []
    n = len(df)
    total_batches = max(1, math.ceil(n / batch_size))

    for inicio in range(0, n, batch_size):
        fim = min(inicio + batch_size, n)
        linhas = []
        for pos in range(inicio, fim):
            # quebras de linha dentro do tweet bagunçariam a numeração do prompt
            texto = str(df.iloc[pos][coluna_texto]).replace("\n", " ").replace("\r", " ").strip()
            linhas.append(f"{pos + 1}. {texto}")
        prompt = instrucao + "\nTweets:\n" + "\n".join(linhas)
        prompts.append(prompt)

        n_batch = inicio // batch_size + 1
        print(f"  [{rotulo}] batch {n_batch}/{total_batches} "
              f"(tweets {inicio + 1}..{fim})", flush=True)

        if dry_run:
            continue

        try:
            itens = client.gerar_json(prompt)
        except Exception as e:
            if n_batch == 1:
                # a primeira chamada falhando costuma ser problema de API key/quota:
                # melhor abortar logo do que rodar tudo e devolver vazio
                raise
            print(f"    [erro] batch {n_batch} descartado: {e}")
            continue

        for item in itens:
            try:
                pos = int(item["id"]) - 1
            except (KeyError, TypeError, ValueError):
                continue
            if 0 <= pos < n:
                resultados[pos] = item

        if fim < n:
            time.sleep(pausa)

    return resultados, prompts


def salvar_prompts_debug(prompts, caminho):
    caminho.write_text("\n\n=====\n\n".join(prompts), encoding="utf-8")
