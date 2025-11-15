# Trabalho Final – Perceptron

**Aluna:** Maria Júlia Testoni

---

## Objetivo

Implementar um **Perceptron** para prever se um pedido de empréstimo será aprovado, usando dados reais e técnicas como:

- Normalização
- Remoção de outliers
- Cálculo de acurácia
- Gráfico de convergência
- Fronteira de decisão (3D)

---

## Dataset

Fonte: Kaggle  
**Loan Database**  
https://www.kaggle.com/datasets/maramsa/loan-database

### ✔ Features usadas

| Coluna                | Descrição                                |
| --------------------- | ---------------------------------------- |
| **Credit_History**    | Histórico de crédito (0 = ruim, 1 = bom) |
| **ApplicantIncome**   | Renda do aplicante                       |
| **CoapplicantIncome** | Renda do co-aplicante                    |
| **LoanAmount**        | Valor do empréstimo solicitado           |

> Obs.: No modelo final, algumas análises foram simplificadas para 3 features.

---

## Pré-processamento aplicado

- Substituição de valores ausentes
- Conversão da variável alvo (`Y → 1`, `N → 0`)
- Corte de outliers no percentil 99
- Normalização (média 0; desvio padrão 1)
- Embaralhamento dos dados a cada época

---

## Funcionamento do Perceptron

O Perceptron aprende um **plano de separação linear** entre as classes:

<pre> 
w1x1 + w2x2 + w3*x3 + b = 0
 </pre>

E ajusta os pesos somente quando erra a previsão:

<pre>  
    w = w + taxa * erro * xi 
    b = b + taxa * erro 
    </pre>

## Exemplo de dados usados

| Credit_History | ApplicantIncome | LoanAmount | Loan_Status |
| -------------- | --------------- | ---------- | ----------- |
| 1              | 4583            | 128        | 1           |
| 1              | 3000            | 66         | 0           |
| 0              | 2600            | 120        | 0           |
| 1              | 6000            | 141        | 1           |
| 1              | 3500            | 95         | 1           |

---

## Resultados alcançados

- **Pesos finais:** próximos de zero (efeito da normalização)
- **Bias final:** pequeno e estável
- **Acurácia final:** ~ **65% - 73%**
- **Convergência suave** após a normalização e remoção de outliers

---

## Gráficos incluídos no projeto

- **Gráfico de convergência** (erros por época)
- **Superfície de decisão 3D**

