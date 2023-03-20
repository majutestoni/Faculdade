package lista03ex03;

public class PilhaLista<T> implements Pilha<T> {
	private ListaEncadeada<T> lista;

	public PilhaLista() {
		lista = new ListaEncadeada<T>();
	}

	public String toString() {
		return lista.toString();

	}

	@Override
	public void push(T data) {
		lista.inserir(data);
	}

	@Override
	public T pop() {
		if (estaVazia()) {
			throw new RuntimeException("Lista vazia");
		}
		T valor = this.peek();
		lista.retirar(valor);

		return null;
	}

	@Override
	public T peek() {
		if (estaVazia()) {
			throw new RuntimeException("Lista vazia");
		}
		return lista.getPrimeiro().getInfo();
	}

	@Override
	public boolean estaVazia() {
		return lista.estaVazia();
	}

	@Override
	public void liberar() {
		ListaEncadeada<T> novo = new ListaEncadeada<T>();
		lista = novo;
	}

}
