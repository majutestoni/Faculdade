package unidade3;

public class NoArvoreBinaria<T> {
	private T info;
	private NoArvoreBinaria<T> esq;
	private NoArvoreBinaria<T> dir;

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public NoArvoreBinaria<T> getEsq() {
		return esq;
	}

	public void setEsq(NoArvoreBinaria<T> esq) {
		this.esq = esq;
	}

	public NoArvoreBinaria<T> getDir() {
		return dir;
	}

	public void setDir(NoArvoreBinaria<T> dir) {
		this.dir = dir;
	}

	public NoArvoreBinaria(T info) {
		this.info = info;
	}

	public NoArvoreBinaria(T info, NoArvoreBinaria<T> esq, NoArvoreBinaria<T> dir) {
		this.info = info;
		this.esq = esq;
		this.dir = dir;
	}

	public NoArvoreBinaria<T> pertence(T info) {
		NoArvoreBinaria<T> primeiro = this;

		if (primeiro == null) {
			return null;
		}

		if (primeiro.equals(info)) {
			return primeiro;
		}

		if (primeiro.getEsq() != null && primeiro.getEsq().getInfo().equals(info)) {
			return primeiro.getEsq();
		}

		if (primeiro.getDir() != null && primeiro.getDir().getInfo().equals(info)) {
			return primeiro.getDir();
		}
		NoArvoreBinaria<T> teste = null;
		if (primeiro.getEsq() != null) {
			teste = primeiro.getEsq().pertence(info);

			if (teste != null) {
				return teste;
			}
		}

		if (primeiro.getDir() != null) {
			teste = primeiro.getDir().pertence(info);
			if (teste != null) {
				return teste;
			}
		}

		return null;
	}

	public String imprimePre() {
		return "" + this.info + "";

	}

}
