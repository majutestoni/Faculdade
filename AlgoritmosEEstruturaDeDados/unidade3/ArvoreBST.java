package unidade3;

public class ArvoreBST<T extends Comparable<T>> extends ArvoreBinariaAbstrata<T> {
	
	

	private NoArvoreBST raiz;

	public void inserindo(T info) {
		if(super.vazia()) {
			this.raiz = new NoArvoreBST(info);
		} else {
			this.raiz.inserir(info);
		}
	}
	
	public NoArvoreBST<T> buscar(T info){
		
	}

	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
