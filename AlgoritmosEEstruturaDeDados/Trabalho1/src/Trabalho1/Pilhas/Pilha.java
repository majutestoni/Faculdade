package Trabalho.Pilhas;

public interface Pilha<T> {
	
	void push(T data);
	
	T pop();
	
	T peek();
	
	boolean estaVazia();
	
	void liberar();
}
