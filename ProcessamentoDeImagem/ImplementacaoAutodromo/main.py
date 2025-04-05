# Maria Júlia Testoni
# O USO DE MORFOLOGIA MATEMÁTICA NA DETECÇÃO DE PISTAS EM AUTÓDROMO
# Autodromo: Sao Jose Carlos

import cv2
import matplotlib.pyplot as plt
import numpy as np

image = cv2.imread('autodromo3.png', cv2.IMREAD_GRAYSCALE)

kernel_cross = cv2.getStructuringElement(cv2.MORPH_CROSS, (3, 3)) 
kernel_disk_small = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (1,1))
kernel_disk = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (5,5))

# ======= pré processamento  ===========
# diferença entre a img original e a com erosão
img_bas = cv2.subtract(image, 50)
imagem_clohole = cv2.morphologyEx(img_bas, cv2.MORPH_CLOSE, kernel_cross)

# 2 - closeth - sedisk de tamanho 5 - 
fechamento = cv2.morphologyEx(imagem_clohole, cv2.MORPH_CLOSE, kernel_disk)
imagem_closeth = cv2.subtract(fechamento, imagem_clohole)
    
# 3 - operador symdif - partir dos operadores closeth e clohole
imagem_symdif = cv2.absdiff(imagem_closeth, imagem_clohole)


# 4 - operador - histeq
imagem_histeq = cv2.equalizeHist(imagem_symdif)

# 5 - operador hdome com limiar 50,
imagem_hdome = cv2.subtract(imagem_histeq, 50)


# 6 - operador Hmin
imagem_hmin = cv2.add(imagem_hdome, 50)

# 7 - operador Subm
imagem_subm = cv2.subtract(imagem_hmin, imagem_closeth)
imagem_subm = cv2.convertScaleAbs(imagem_subm)

# ===  detecção ====
imagem_binaria = cv2.adaptiveThreshold(imagem_subm.astype(np.uint8), 255, 
                                       cv2.ADAPTIVE_THRESH_GAUSSIAN_C, 
                                       cv2.THRESH_BINARY, 11, 2)

# ======== pós processamento
num_labels, labels, stats, _ = cv2.connectedComponentsWithStats(imagem_binaria, connectivity=8)
output = np.zeros_like(imagem_binaria)

for i in range(1, num_labels):  # Ignora o fundo
    if stats[i, cv2.CC_STAT_AREA] >= 500:  # Ajustável
        output[labels == i] = 255

# Refinamento morfológico para suavizar
imagem_pos = cv2.morphologyEx(output, cv2.MORPH_CLOSE, kernel_cross)


plt.figure(figsize=(12, 6))
plt.subplot(2, 3, 1)
plt.title('Imagem Original')
plt.imshow(image, cmap='gray')
plt.axis('off')

plt.subplot(2, 3, 2)
plt.title('Pré-Processamento')
plt.imshow(imagem_clohole, cmap='gray')
plt.axis('off')

plt.subplot(2, 3, 3)
plt.title('Imagem com Subm')
plt.imshow(imagem_subm, cmap='gray')
plt.axis('off')

plt.subplot(2, 3, 4)
plt.title('Pós-Processamento')
plt.imshow(imagem_pos, cmap='gray')
plt.axis('off')

plt.tight_layout()
plt.show()
