package arvore;

public class Arvore<T> {

	private NoArvore<T> raiz;
	
	public void setRaiz(NoArvore<T> noRaiz) {
		this.raiz = noRaiz;
	}
	
	public NoArvore<T> getRaiz() {
		return this.raiz;
	}
	
	public String toString() {
		if (this.vazia()) {
			return "<>";
		}
		return this.raiz.imprimePre();
	}
	
	public boolean vazia() {
		return this.raiz == null;
	}
	
	public NoArvore<T> pertence(T info) {
		if (this.vazia()) {
			return null;
		}
		return this.raiz.pertence(info);
	}
	
	public int getAltura() {
        if (this.vazia()) {
            return -1;
        } else {
            return this.raiz.getAltura();
        }
    }
	
	public int getNivel(T info) {
		if (this.vazia()) {
			return -1;
		}
		if(this.raiz.getInfo() == info) {
			return 0;
		}
		return this.raiz.getNivel(info);
		
	}
	
	public boolean isBalanceada() {
		if (this.vazia()) {
			return true;
		}
		return this.raiz.isBalanceada();
	}
}
