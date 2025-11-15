import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

# medicao de acurácia
## medir acurácia é a forma de saber se seu Perceptron funciona.
def accuracy(X, y, w, b):
    preds = np.where(np.dot(X, w) + b >= 0, 1, 0)
    return (preds == y).mean()

path = r"C:\Users\majut\Documents\repositorios_git\Faculdade\InteligenciaArtificial\Perceptron\loan.xlsx"
df = pd.read_excel(path)


# colunas escolhidas para treinar o perceptron
cols = ['Credit_History', 'ApplicantIncome', 'LoanAmount', 'Loan_Status']
df_sel = df[cols].copy()

# limpeza
df_sel['Credit_History'] = df_sel['Credit_History'].fillna(0)
df_sel['ApplicantIncome'] = df_sel['ApplicantIncome'].fillna(df_sel['ApplicantIncome'].median())
df_sel['LoanAmount'] = df_sel['LoanAmount'].fillna(df_sel['LoanAmount'].median())
df_sel['Loan_Status'] = df_sel['Loan_Status'].map({'Y': 1, 'N': 0})

# ===============================
# CORTE DE OUTLIERS (percentil 99)
# ===============================
df_sel = df_sel[df_sel['ApplicantIncome'] < df_sel['ApplicantIncome'].quantile(0.99)]
df_sel = df_sel[df_sel['LoanAmount'] < df_sel['LoanAmount'].quantile(0.99)]

# matriz X e vetor y
X = df_sel[['Credit_History', 'ApplicantIncome', 'LoanAmount']].values.astype(float)
y = df_sel['Loan_Status'].values.astype(int)

# normalizacao -> pesos ficam estáveis
X_mean = X.mean(axis=0)
X_std = X.std(axis=0)
X = (X - X_mean) / X_std

# hiperparâmetros
taxa = 0.001
epochs = 3000

# inicialização dos pesos
w = np.zeros(X.shape[1]) 
b = 0.0

# será usado depois para gerar grafico
lista_erros = []

for epoch in range(epochs):
    # embaralhar
    idx = np.random.permutation(len(X))
    X = X[idx]
    y = y[idx]
    
    erros = 0

    # percorre cada linha
    for xi, yi in zip(X, y):
        
        # soma z = w.x + b
        soma = np.dot(w, xi) + b
        
        # previsao
        y_pred = 1 if soma >= 0 else 0
        
        # erro
        erro = yi - y_pred
        
        # ajuste dos pesos
        if erro != 0:
            w = w + taxa * erro * xi
            b = b + taxa * erro
            erros += 1
            
    lista_erros.append(erros)
    
    # condição de convergência
    if erros == 0:
        print(f"Modelo convergiu na epoch {epoch}!")
        break

acc = accuracy(X, y, w, b)
print("\nPesos finais:", w)
print("Bias final:", b)
print(f"Acurácia final: {acc*100:.2f}%")

# ======================
# GRÁFICO DE CONVERGÊNCIA -(erros)
# ======================
plt.figure(figsize=(10,5))
plt.plot(lista_erros, marker='o')
plt.title("Convergência do Perceptron")
plt.xlabel("Época")
plt.ylabel("Erros")
plt.grid(True)
plt.show()

# ======================
# GRÁFICO 3D DA FRONTEIRA
# ======================
fig = plt.figure(figsize=(10, 7))
ax = fig.add_subplot(111, projection='3d')

# cria grid para superfície
x1 = np.linspace(X[:,0].min(), X[:,0].max(), 20)  # Credit_History normalizado
x2 = np.linspace(X[:,1].min(), X[:,1].max(), 20)  # ApplicantIncome normalizado
x1_grid, x2_grid = np.meshgrid(x1, x2)

# evita divisão por zero caso w3 seja ~0
if abs(w[2]) < 1e-8:
    print("w3 quase zero, superfície não pode ser criada.")
else:
    x3_grid = -(w[0] * x1_grid + w[1] * x2_grid + b) / w[2]

    # plot dos pontos reais
    ax.scatter(X[:,0], X[:,1], X[:,2], c=y, cmap='coolwarm', s=30)

    # superfície de decisão
    ax.plot_surface(x1_grid, x2_grid, x3_grid, alpha=0.4)

    ax.set_title("Superfície 3D da Decisão do Perceptron", fontsize=14)
    ax.set_xlabel("Credit_History (normalizado)")
    ax.set_ylabel("ApplicantIncome (normalizado)")
    ax.set_zlabel("LoanAmount (normalizado)")

plt.show()