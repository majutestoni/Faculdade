import math
import time
import ipywidgets as widgets
from IPython.display import display, HTML

# --- LÓGICA DO JOGO

JOGADOR_X = 'X'
JOGADOR_O = 'O'
VAZIO = ' '

def inicializar_tabuleiro():
    return [[VAZIO, VAZIO, VAZIO], [VAZIO, VAZIO, VAZIO], [VAZIO, VAZIO, VAZIO]]

def verificar_vencedor(tabuleiro):
    for i in range(3):
        if tabuleiro[i][0] == tabuleiro[i][1] == tabuleiro[i][2] and tabuleiro[i][0] != VAZIO:
            return tabuleiro[i][0]
    for j in range(3):
        if tabuleiro[0][j] == tabuleiro[1][j] == tabuleiro[2][j] and tabuleiro[0][j] != VAZIO:
            return tabuleiro[0][j]
    if tabuleiro[0][0] == tabuleiro[1][1] == tabuleiro[2][2] and tabuleiro[0][0] != VAZIO:
        return tabuleiro[0][0]
    if tabuleiro[0][2] == tabuleiro[1][1] == tabuleiro[2][0] and tabuleiro[0][2] != VAZIO:
        return tabuleiro[0][2]
    return None

def tabuleiro_cheio(tabuleiro):
    for linha in tabuleiro:
        if VAZIO in linha:
            return False
    return True

def obter_movimentos_validos(tabuleiro):
    movimentos = []
    for i in range(3):
        for j in range(3):
            if tabuleiro[i][j] == VAZIO:
                movimentos.append((i, j))
    return movimentos



def minimax(tabuleiro, profundidade, is_maximizando):
    vencedor = verificar_vencedor(tabuleiro)
    if vencedor == JOGADOR_O: return 10
    if vencedor == JOGADOR_X: return -10
    if tabuleiro_cheio(tabuleiro): return 0

    if is_maximizando:
        melhor_escore = -math.inf
        for linha, coluna in obter_movimentos_validos(tabuleiro):
            tabuleiro[linha][coluna] = JOGADOR_O
            escore = minimax(tabuleiro, profundidade + 1, False)
            tabuleiro[linha][coluna] = VAZIO
            melhor_escore = max(escore, melhor_escore)
        return melhor_escore
    else:
        melhor_escore = math.inf
        for linha, coluna in obter_movimentos_validos(tabuleiro):
            tabuleiro[linha][coluna] = JOGADOR_X
            escore = minimax(tabuleiro, profundidade + 1, True)
            tabuleiro[linha][coluna] = VAZIO
            melhor_escore = min(escore, melhor_escore)
        return melhor_escore

def encontrar_melhor_jogada(tabuleiro):
    melhor_escore = -math.inf
    melhor_jogada = None
    for linha, coluna in obter_movimentos_validos(tabuleiro):
        tabuleiro[linha][coluna] = JOGADOR_O
        escore = minimax(tabuleiro, 0, False)
        tabuleiro[linha][coluna] = VAZIO
        if escore > melhor_escore:
            melhor_escore = escore
            melhor_jogada = (linha, coluna)
    return melhor_jogada

# INTERFACE GRÁFICA COM IPYWIDGETS

# Variáveis de estado do jogo
tabuleiro = inicializar_tabuleiro()
game_over = False

# Widgets da Interface
status_label = widgets.HTML(value="<h2>Bem-vindo ao Jogo da Velha!</h2><p>Sua vez de jogar ('X').</p>")

# ---> MODIFICAÇÃO VISUAL AQUI <---
# Criamos os botões com um layout aprimorado: borda, fonte maior e em negrito.
button_layout = widgets.Layout(
    width='80px',
    height='80px',
    font_size='40px',
    font_weight='bold',
    border='1px solid lightgray'
)
grid_buttons = [[widgets.Button(description=VAZIO, layout=button_layout) for _ in range(3)] for _ in range(3)]

reset_button = widgets.Button(description="Reiniciar Jogo", button_style='success')
output_area = widgets.Output()

# Layout da UI
# Ajustamos o GridBox para não ter espaçamento, deixando as bordas dos botões formarem o grid.
grid = widgets.GridBox(
    [item for sublist in grid_buttons for item in sublist],
    layout=widgets.Layout(grid_template_columns="80px 80px 80px", grid_gap="0px 0px")
)
ui = widgets.VBox([status_label, grid, reset_button, output_area])


# --- FUNÇÕES DE CONTROLE DA INTERFACE (Inalteradas) ---

def disable_board():
    """Desabilita todos os botões do tabuleiro."""
    for i in range(3):
        for j in range(3):
            grid_buttons[i][j].disabled = True

def check_game_status():
    """Verifica o estado do jogo (vitória, empate) e atualiza a UI."""
    global game_over
    vencedor = verificar_vencedor(tabuleiro)
    if vencedor:
        status_label.value = f"<h2>Fim de Jogo!</h2><p>O jogador '{vencedor}' venceu!</p>"
        game_over = True
        disable_board()
        return True
    elif tabuleiro_cheio(tabuleiro):
        status_label.value = "<h2>Fim de Jogo!</h2><p>O jogo terminou em empate!</p>"
        game_over = True
        disable_board()
        return True
    return False

def on_button_clicked(b):
    """Callback executado quando um botão do tabuleiro é clicado."""
    global tabuleiro, game_over
    if game_over: return

    coords = None
    for i in range(3):
        for j in range(3):
            if grid_buttons[i][j] == b:
                coords = (i, j); break

    if coords and tabuleiro[coords[0]][coords[1]] == VAZIO:
        linha, coluna = coords
        tabuleiro[linha][coluna] = JOGADOR_X
        b.description = JOGADOR_X
        b.disabled = True

        if not check_game_status():
            status_label.value = "<h3>IA ('O') está pensando...</h3>"
            time.sleep(0.5)

            ai_linha, ai_coluna = encontrar_melhor_jogada(tabuleiro)
            tabuleiro[ai_linha][ai_coluna] = JOGADOR_O
            grid_buttons[ai_linha][ai_coluna].description = JOGADOR_O
            grid_buttons[ai_linha][ai_coluna].disabled = True

            with output_area:
                print(f"IA jogou na posição ({ai_linha}, {ai_coluna})")

            if not check_game_status():
                status_label.value = "<p>Sua vez de jogar ('X').</p>"

def on_reset_clicked(b):
    """Callback para reiniciar o jogo."""
    global tabuleiro, game_over
    tabuleiro = inicializar_tabuleiro()
    game_over = False
    status_label.value = "<h2>Novo Jogo!</h2><p>Sua vez de jogar ('X').</p>"
    output_area.clear_output()
    for i in range(3):
        for j in range(3):
            grid_buttons[i][j].description = VAZIO
            grid_buttons[i][j].disabled = False

# Associando as funções de callback aos eventos dos botões
for i in range(3):
    for j in range(3):
        grid_buttons[i][j].on_click(on_button_clicked)
reset_button.on_click(on_reset_clicked)

# --- INICIAR A APLICAÇÃO ---
display(ui)