package lista04ex04;


public class FilaEncadeada<T> implements Fila<T> {
	ListaEncadeada<T> lista = new ListaEncadeada<>();
	private int tamanho = 0;
	private int primeiro = 0;

	public FilaEncadeada() {
	}

	void inserirNoFinal(T valor) {
		lista.inserir(valor, tamanho);
		tamanho++;
	}

	@Override
	public T peek() {
		if (this.estaVazia())
			throw new RuntimeException("Lista vazia");
		return lista.pegar(0);
	}

	@Override
	public T retirar() {
		T resp = this.peek();
		lista.retirar(resp);
		return resp;
	}

	@Override
	public void liberar() {
		lista = new ListaEncadeada<>();

	}

	@Override
	public void inserir(T valor) {
		lista.inserir(valor);
		tamanho++;
	}

	@Override
	public boolean estaVazia() {
		return lista.estaVazia();
	}

}
