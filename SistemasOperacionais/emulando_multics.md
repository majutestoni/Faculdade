# 💻 Emulando o Multics (MR12.8)

Guia rápido para instalar e executar o **Multics MR12.8** no simulador **DPS8M**.

---

## 🧩 1. Instalar o Simulador

O **DPS8M** emula os mainframes Honeywell/Bull usados pelo Multics.

🔗 **Download:**  
👉 [https://dps8m.gitlab.io/dps8m/Releases/](https://dps8m.gitlab.io/dps8m/Releases/)

Baixe a versão compatível com seu sistema (ex.: *Windows*).

---

## 📀 2. Obter o Multics MR12.8

Acesse:  
👉 [https://multics-wiki.swenson.org/index.php/MR12.8#Release_Artifacts](https://multics-wiki.swenson.org/index.php/MR12.8#Release_Artifacts)

Baixe o pacote **MR12.8 QuickStart** e extraia tudo na pasta `C:\multics`.

---

## ⚙️ 3. Preparar o Ambiente

1. Coloque o executável `dps8` e os arquivos do Multics na pasta `C:\multics`.  
2. Edite o arquivo `MR12.8_boot.ini` e adicione no final:
   ```ini
   attach fnp g.000 telnet=localhost:6180
   ```

---

## ▶️ 4. Executar o Multics

1. Abra o **PowerShell** ou **Prompt de Comando**:
   ```bash
   .\dps8 MR12.8_boot.ini
   ```
2. Abra outro terminal e conecte-se:
   ```bash
   telnet localhost 6180
   ```
3. Faça login:
   ```bash
   login Repair.SysAdmin -cpw
   Password: repair
   New Password: 1234
   ```

---

## 🧠 5. Comandos Básicos

| Comando | Função |
|----------|--------|
| `list` | lista arquivos do diretório atual |
| `pwd` | mostra o diretório atual |
| `who` | mostra usuários logados |
| `status` | mostra status do sistema |
| `help` | abre o sistema de ajuda |
| `logout` | encerra a sessão |

---

## 📁 6. Trabalhando com Diretórios e Arquivos

```bash
# Criar diretório
create_dir MeuDir.dir

# Entrar no diretório
change_wdir MeuDir.dir

# Criar arquivo
create texto

# Editar arquivo
edm texto
```

Dentro do editor:
```
i           # entra no modo de inserção
(digite seu texto)
.           # ele entra modo editor ou input
w           # salva
q           # sai
```

ler:
```bash
print texto
```

Remover:
```bash
delete texto
delete_dir MeuDir.dir -noask
```

---
