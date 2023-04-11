package unidade3;

public class ArvoreBinaria<T> {
	private NoArvoreBinaria<T> raiz;

	public ArvoreBinaria() {
		this.raiz = new NoArvoreBinaria<T>(null);
	}

	public void setNoRaiz(NoArvoreBinaria<T> raiz) {
		this.raiz = raiz;
	}

	public boolean vazia() {
		if (this.raiz == null) {
			return true;
		} else {
			return false;
		}
	}

	public NoArvoreBinaria<T> pertence(T info) {
		return this.raiz.pertence(info);

	}
}
