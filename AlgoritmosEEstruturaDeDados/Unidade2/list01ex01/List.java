package list01ex01;


public class List {
	private int[] info;
	private int tamanho;

	public List() {
		info = new int[5];
		tamanho = 0;
	}

	public int[] exibir() {
		return info;
	}

	private void redimensionar() {
		int[] novoVetor = new int[tamanho + 5];

		for (int i = 0; i < info.length; i++) {
			novoVetor[i] = info[i];
		}
		this.info = novoVetor;
	}

	public void inserir(int valor) {
		if (tamanho == (info.length)) {
			this.redimensionar();
		}

		info[tamanho] = valor;
		this.tamanho++;
	}

	public int buscar(int valor) {
		for (int i = 0; i < tamanho; i++) {
			if (info[i] == valor) {
				return i;
			}
		}
		return -1;
	}

	public void retirar(int valor) {
		int posicao = this.buscar(valor);
		int pos = 0;
		for (int i = 0; i < info.length; i++) {
			if (i != posicao) {
				info[pos] = info[i];
				pos++;
			}
		}

	}

	public boolean listaVazia() {
		if (tamanho == 0) {
			return true;
		} else {
			return false;
		}
	}

}
