package unidade3;

public class NoArvoreBST<T extends Comparable<T>> extends NoArvoreBinariaAbstrata<T>{
	NoArvoreBST<T> nossa = new NoArvoreBST();

	public NoArvoreBST() {
	}

	public NoArvoreBST(T info) {
		super(info);

	}

	public void inserir(T info) {
		NoArvoreBST<T> no = new NoArvoreBST<T>(info);
		if (nossa.compareTo(info) < 0) {
			if (nossa.getEsq() == null) {
				nossa.setEsq(no);
			} else {
				((NoArvoreBST<T>) nossa.getEsq()).inserir(info);
			}
		} else {
			if (nossa.getDir() == null) {
				nossa.setDir(no);
			} else {
				((NoArvoreBST<T>) nossa.getDir()).inserir(info);
			}
		}
	}
	
	public NoArvoreBST<T> buscar(T valor){
		if(valor.compareTo(nossa.getInfo()) == 0) {
			return nossa;
		}else {
			if(valor.compareTo(nossa.getInfo()) < 0) {
				return ((NoArvoreBST<T>) nossa.getEsq()).buscar(valor);
			}
		}
		return null;
	}
	
	public void retirar(T valor) {
		NoArvoreBST no = this.buscar(valor);
		
		
		
	}

	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
