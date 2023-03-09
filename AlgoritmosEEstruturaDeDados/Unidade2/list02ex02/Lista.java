package lista02ex01;



public interface Lista {

	void inserir(int valor);
	
	void inserir(int valor, int pos);
	
	int buscar(int valor);

	boolean estaVazia();

	void retirar(int valor);

	String exibir();

	Lista copiar();

	Lista dividir();

	int getTamanho();

	void concatenar(Lista outraLista);

	int pegar(int pos);

}