import cv2
import matplotlib.pyplot as plt

image = cv2.imread('colorida.jpg')

gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

median_filtered = cv2.medianBlur(gray_image, 5)

blurred_image = cv2.blur(gray_image, (5, 5))

plt.figure(figsize=(10, 7))
plt.subplot(1, 4, 1)
plt.title('Imagem Original')
plt.imshow(cv2.cvtColor(image, cv2.COLOR_BGR2RGB))
plt.axis('off')

plt.subplot(1, 4, 2)
plt.title('Imagem em Cinza')
plt.imshow(gray_image, cmap='gray')
plt.axis('off')

plt.subplot(1, 4, 3)
plt.title('Filtro Mediano')
plt.imshow(median_filtered, cmap='gray')
plt.axis('off')

plt.subplot(1, 4, 4)
plt.title('Filtro de Média')
plt.imshow(blurred_image, cmap='gray')
plt.axis('off')

plt.show()

cv2.imwrite('imagem_median_filtered.jpg', median_filtered)
cv2.imwrite('imagem_blurred.jpg', blurred_image)
