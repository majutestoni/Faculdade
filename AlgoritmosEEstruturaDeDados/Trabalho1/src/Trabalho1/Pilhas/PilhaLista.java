package Trabalho.Pilhas;

import Trabalho.Listas.ListaEncadeada;

public class PilhaLista<T> implements Pilha<T> {

	private ListaEncadeada<T> lista;	
	
	public PilhaLista() {
		lista = new ListaEncadeada<T>();
	}
	
	public int getTamanho() {
		return lista.getTamanho();
	}

	@Override
	public String toString() {
		String str = lista.exibir();
		return str;
	}
	
	@Override
	public void push(T v) {
		lista.inserir(v);
	}
	
	@Override
	public T pop() {
		T valor;
		valor = peek();
		lista.retirar(valor);
		return valor;
	}
	
	@Override
	public T peek() {
		if (estaVazia()) {
			throw new RuntimeException("Pilha está vazia");
		}
		return lista.ultimo.getInfo();
	}
	
	@Override
	public boolean estaVazia() {
		return lista.estaVazia();
	}
	
	@Override
	public void liberar() {
		lista = new ListaEncadeada<T>();
	}	
}