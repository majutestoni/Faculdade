import cv2
import matplotlib.pyplot as plt

# Carregar imagem colorida
image = cv2.imread('colorida.jpg')

# Converter para escala de cinza
gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

# Visualizar a imagem original e a imagem em escala de cinza
plt.figure(figsize=(10, 7))

plt.subplot(1, 2, 1)
plt.title('Imagem Original')
plt.imshow(cv2.cvtColor(image, cv2.COLOR_BGR2RGB))
plt.axis('off')

plt.subplot(1, 2, 2)
plt.title('Imagem em Cinza')
plt.imshow(gray_image, cmap='gray')
plt.axis('off')

plt.show()

# Aplicar limiarização (thresholding)
# O valor 127 é o limiar. Tudo acima de 127 se torna branco, abaixo se torna preto.
_, threshold_image = cv2.threshold(gray_image, 127, 255, cv2.THRESH_BINARY)

# Visualizar o resultado da limiarização
plt.figure(figsize=(7, 7))
plt.title('Imagem Limiarizada')
plt.imshow(threshold_image, cmap='gray')
plt.axis('off')
plt.show()

# Salvar a imagem limiarizada
cv2.imwrite('imagem_limiarizada.jpg', threshold_image)
