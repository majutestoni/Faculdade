import cv2
import matplotlib.pyplot as plt

image = cv2.imread('colorida.jpg')

gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

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

_, threshold_image = cv2.threshold(gray_image, 127, 255, cv2.THRESH_BINARY)

plt.figure(figsize=(7, 7))
plt.title('Imagem Limiarizada')
plt.imshow(threshold_image, cmap='gray')
plt.axis('off')
plt.show()

cv2.imwrite('imagem_limiarizada.jpg', threshold_image)
