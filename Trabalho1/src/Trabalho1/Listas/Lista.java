package Trabalho.Listas;

public interface Lista<T> {

	void inserir(T valor);
	
	void inserir(T valor, int po);

	int buscar(T valor);

	boolean estaVazia();

	void retirar(T valor);

	String exibir();

	void concatenar(Lista<T> outra);

	T pegar(int pos);

	Lista<T> dividir();

	Lista<T> copiar();

	int getTamanho();
}
