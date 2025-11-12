import random
import math
import base64

def is_prime(n, t=20):
    if n < 2:
        return False
    for p in [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]:
        if n % p == 0:
            return n == p
    r, d = 0, n - 1
    while d % 2 == 0:
        r += 1
        d //= 2
    for _ in range(t):
        a = random.randrange(2, n - 1)
        x = pow(a, d, n)
        if x == 1 or x == n - 1:
            continue
        for _ in range(r - 1):
            x = pow(x, 2, n)
            if x == n - 1:
                break
        else:
            return False
    return True

def gerar_primo(bits=512):
    while True:
        c = random.getrandbits(bits)
        c |= (1 << bits - 1) | 1
        if is_prime(c):
            return c

def gerar_expoente(phi):
    while True:
        e = random.randrange(3, phi - 1, 2)
        if math.gcd(e, phi) == 1:
            return e

p = gerar_primo()
q = gerar_primo()
while q == p:
    q = gerar_primo()

n = p * q
phi = (p - 1) * (q - 1)
e = gerar_expoente(phi)
d = pow(e, -1, phi)

print("Chave pública (n, e):")
print(n, e)
print("Chave privada (d):")
print(d)

mensagem = "65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80"
m_int = int.from_bytes(mensagem.encode(), "big")
if m_int >= n:
    raise ValueError("Mensagem muito grande")
c = pow(m_int, e, n)
c_b64 = base64.b64encode(c.to_bytes((c.bit_length() + 7) // 8, "big")).decode()
print("Mensagem cifrada:", c_b64)
print("Mensagem cifrada sem b64:", c)
