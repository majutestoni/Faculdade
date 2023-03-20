package lista03ex03;

import java.util.Iterator;

public class PilhaVetor<T> implements Pilha<T> {
	T[] info;

	private int limite;

	private int tamanho;

	public PilhaVetor(int limite) {
		this.limite = limite;
		info = (T[]) new Object[limite];
	}

	public String toString() {
		String retorno = "[";
		for (int i = 0; i < tamanho; i++) {
			retorno += info[i] + ", ";
		}
		
		retorno += "]";
		return retorno;
	}

	@Override
	public void push(T data) {
		if (tamanho == limite) {
			throw new RuntimeException("Lista cheia");
		}

		info[tamanho] = data;
		tamanho++;
	}

	@Override
	public T pop() {
		if(estaVazia()) {
			throw new RuntimeException("Lista vazia");
		}
		T retorno = info[tamanho - 1];
		info[tamanho - 1] = null;
		tamanho--;
		return retorno;
	}

	@Override
	public T peek() {
		if(estaVazia()) {
			throw new RuntimeException("Lista vazia");
		}
		return info[tamanho - 1];
	}

	@Override
	public boolean estaVazia() {
		if (tamanho == 0)
			return true;
		else
			return false;
	}

	@Override
	public void liberar() {
		T[] novo = (T[]) new Object[limite];
		tamanho = 0;
		info = novo;

	}

}
