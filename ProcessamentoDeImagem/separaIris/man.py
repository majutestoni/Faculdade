# Maria Júlia Testoni
# Separar a iris

import cv2
import matplotlib.pyplot as plt
import numpy as np

image = cv2.imread('002_L_03.jpg')

roi = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

## Identificação do olho inteiro
# Aplica um Treshold para otimizar o processamento
_, threshold = cv2.threshold(roi, 100, 255, cv2.THRESH_BINARY)

# Aplica o algoritmo, param1 = valor do parametro.
circles = cv2.HoughCircles(threshold,cv2.HOUGH_GRADIENT,2,200, param1=20,param2=20,minRadius=180,maxRadius=184)
circles = np.uint16(np.around(circles))

# itera os circulos obtidos e desenha na imagem
for i in circles[0,:]:
    cv2.circle(roi,(i[0],i[1]),i[2],(0,255,0),2)
    cv2.circle(roi,(i[0],i[1]),2,(0,0,255),3)

plt.figure(figsize=(12, 6))
plt.subplot(2, 3, 1)
plt.title('Imagem Original')
plt.imshow(image)
plt.axis('off')