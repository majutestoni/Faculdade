import cv2
import matplotlib.pyplot as plt

# Carregar a imagem colorida
image = cv2.imread('colorida.jpg')

# Converter a imagem para escala de cinza
gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

# Visualizar a imagem original em cinza e seu histograma
plt.figure(figsize=(10, 7))

# Exibir a imagem original em cinza
plt.subplot(2, 2, 1)
plt.title('Imagem Original em Cinza')
plt.imshow(gray_image, cmap='gray')
plt.axis('off')

# Exibir o histograma da imagem original
plt.subplot(2, 2, 2)
plt.title('Histograma da Imagem Original')
plt.hist(gray_image.ravel(), bins=256, range=(0, 255), color='black')
plt.xlabel('Intensidade de pixel')
plt.ylabel('Número de pixels')

# Aplicar a equalização de histograma
equalized_image = cv2.equalizeHist(gray_image)

# Exibir a imagem equalizada
plt.subplot(2, 2, 3)
plt.title('Imagem Equalizada')
plt.imshow(equalized_image, cmap='gray')
plt.axis('off')

# Exibir o histograma da imagem equalizada
plt.subplot(2, 2, 4)
plt.title('Histograma da Imagem Equalizada')
plt.hist(equalized_image.ravel(), bins=256, range=(0, 255), color='black')
plt.xlabel('Intensidade de pixel')
plt.ylabel('Número de pixels')

# Exibir os resultados
plt.tight_layout()
plt.show()

# Salvar a imagem equalizada
cv2.imwrite('imagem_equalizada.jpg', equalized_image)
