package Trabalho.Listas;

public class ListaEstatica<T> implements Lista<T> {

	private Integer tamanho;
	private T[] info;

	public ListaEstatica() {
		info = (T[]) new Object[10];
		this.tamanho = 0;
	}

	@Override
	public void inserir(T valor) {
		if (tamanho == info.length) {
			this.redimensionar();
		}
		info[tamanho] = valor;
		tamanho++;
	}

	@Override
	public void inserir(T valor, int pos) {
		if (pos < 0 || pos > tamanho) {
			throw new IndexOutOfBoundsException("Pos=" + pos + ". Lenght=" + this.tamanho);
		}
		if (pos == tamanho) {
			this.inserir(valor);
		} else {
			if (tamanho == info.length) {
				this.redimensionar();
			}
			for (int i = tamanho; i > pos; i--) {
				info[i] = info[i - 1];
			}
			info[pos] = valor;
			tamanho++;
		}
	}

	private void redimensionar() {
		T[] novo = (T[]) new Object[info.length + 10];
		for (int i = 0; i < info.length; i++) {
			novo[i] = info[i];
		}
		this.info = novo;
	}

	@Override
	public int buscar(T valor) {
		for (int i = 0; i < tamanho; i++) {
			if (info[i].equals(valor)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean estaVazia() {
		return (tamanho == 0);
	}

	@Override
	public void retirar(T valor) {
		int pos = this.buscar(valor);
		if (pos != -1) {
			for (int i = pos; i < tamanho - 1; i++) {
				info[i] = info[i + 1];
			}
		}
	}

	@Override
	public String exibir() {
		String list = "[";
		for (int i = 0; i < tamanho; i++) {
			list += info[i] + ", ";
		}
		return list += "]";
	}

	@Override
	public void concatenar(Lista<T> outra) {
		for (int i = 0; i < outra.getTamanho(); i++) {
			this.inserir(outra.pegar(i));
		}
	}

	@Override
	public T pegar(int pos) {
		if (pos >= this.tamanho) {
			throw new IndexOutOfBoundsException("Pos=" + pos + ". Lenght=" + this.tamanho);
		}
		return this.info[pos];
	}

	@Override
	public Lista<T> dividir() {
		int metade = this.tamanho / 2;
		Lista outra = new ListaEstatica();
		for (int i = metade; i < this.tamanho; i++) {
			outra.inserir(this.info[i]);
		}
		this.tamanho = metade;
		return outra;
	}

	@Override
	public Lista<T> copiar() {
		ListaEstatica nova = new ListaEstatica();
		for (int i = 0; i < this.tamanho; i++) {
			nova.inserir(this.info[i]);
		}
		nova.tamanho = this.tamanho;
		return nova;
	}

	@Override
	public int getTamanho() {
		return this.tamanho;
	}
}
