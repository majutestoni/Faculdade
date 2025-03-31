import cv2
import matplotlib.pyplot as plt

image = cv2.imread('colorida.jpg')

gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

plt.figure(figsize=(10, 7))

plt.subplot(2, 2, 1)
plt.title('Imagem Original em Cinza')
plt.imshow(gray_image, cmap='gray')
plt.axis('off')

plt.subplot(2, 2, 2)
plt.title('Histograma da Imagem Original')
plt.hist(gray_image.ravel(), bins=256, range=(0, 255), color='black')
plt.xlabel('Intensidade de pixel')
plt.ylabel('Número de pixels')

equalized_image = cv2.equalizeHist(gray_image)

plt.subplot(2, 2, 3)
plt.title('Imagem Equalizada')
plt.imshow(equalized_image, cmap='gray')
plt.axis('off')

plt.subplot(2, 2, 4)
plt.title('Histograma da Imagem Equalizada')
plt.hist(equalized_image.ravel(), bins=256, range=(0, 255), color='black')
plt.xlabel('Intensidade de pixel')
plt.ylabel('Número de pixels')

plt.tight_layout()
plt.show()

cv2.imwrite('imagem_equalizada.jpg', equalized_image)
