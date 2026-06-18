from google import genai
import pandas as pd
from pathlib import Path

BASE_DIR = Path(__file__).parent
PATH_DATASET = BASE_DIR.parent / "limpezaDados" / "20260319_190337.csv"

df = pd.read_csv(PATH_DATASET)
tweets = df["Texto"].astype(str).tolist()
tweets = tweets[:10]

prompt = f"""
Para cada texto abaixo, responda apenas com JSON.

[
{{"id":1,"futebol":true}},
{{"id":2,"futebol":false}}
]

Textos:
"""

client = genai.Client(api_key="")
for i, tweet in enumerate(tweets, start=1):
    prompt += f"\n{i}. {tweet}"
    

response = client.models.generate_content(
    model="gemini-2.5-flash",
    contents=prompt
)

print(response.text)

## exemplo de saida ##
"""
[
  {
    "id": 1,
    "futebol": true
  },
  {
    "id": 2,
    "futebol": true
  },
  {
    "id": 3,
    "futebol": true
  },
  {
    "id": 4,
    "futebol": true
  },
  {
    "id": 5,
    "futebol": true
  }
] 
"""