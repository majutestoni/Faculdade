package Trabalho.Listas;

public class ListaEncadeada<T> implements Lista<T> {

	public NoLista<T> primeiro;
	public NoLista<T> ultimo;
	private int qtdElementos;

	public ListaEncadeada() {
		this.primeiro = null;
		this.ultimo = null;
		this.qtdElementos = 0;
	}
	
	
	public NoLista<T> getPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(NoLista<T> primeiro) {
		this.primeiro = primeiro;
	}

	public NoLista<T> getUltimo() {
		return ultimo;
	}

	public void setUltimo(NoLista<T> ultimo) {
		this.ultimo = ultimo;
	}

	@Override
	public void inserir(T valor) {
		if (buscar(valor) == -1) {
			NoLista<T> novo = new NoLista<T>();
			novo.setInfo(valor);
			if (this.estaVazia()) {
				primeiro = novo;
			} else {
				ultimo.setProximo(novo);
			}
			ultimo = novo;
			qtdElementos++;
		}
	}

	@Override
	public void inserir(T valor, int po) {
		if (po <= qtdElementos) {
			NoLista<T> novo = new NoLista<T>();
			NoLista<T> atual = primeiro;
			NoLista<T> anterior = null;
			novo.setInfo(valor);
			int contador = 0;
			if (this.estaVazia()) {
				primeiro = novo;
				ultimo = novo;
				qtdElementos++;
			} else if (po == 0) {
				novo.setProximo(primeiro);
				primeiro = novo;
				qtdElementos++;
			} else {
				while (contador != po) {
					anterior = atual;
					atual = atual.getProximo();
					contador++;
				}
				anterior.setProximo(novo);
				novo.setProximo(atual);
				qtdElementos++;
			}			
		}
	}

	@Override
	public int buscar(T valor) {
		NoLista<T> p = primeiro;
		int contador = 0;
		while (p != null) {
			if (p.getInfo() == valor) {
				return contador;
			}
			p = p.getProximo();
			contador++;
		}
		return -1;
	}

	@Override
	public boolean estaVazia() {
		if (this.qtdElementos == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void retirar(T valor) {
		if (buscar(valor) != -1) {
			NoLista<T> atual = this.primeiro;
			NoLista<T> anterior = null;

			if (this.primeiro.getInfo() == valor) {
				this.primeiro = this.primeiro.getProximo();
				this.qtdElementos--;
			} else {
				while (atual.getInfo() != valor) {
					anterior = atual;
					atual = atual.getProximo();
				}
				if (this.ultimo == atual) {
					this.ultimo = anterior;
					this.ultimo.setProximo(null);
				} else if (atual.getInfo() == valor) {
					anterior.setProximo(atual.getProximo());
				}
				this.qtdElementos--;
			}
		}
	}

	@Override
	public String exibir() {
		NoLista<T> p = primeiro;
		String list = "[ ";
		while (p != null) {
			list += p.getInfo() + ", ";
			p = p.getProximo();
		}
		return list + " ]";
	}

	@Override
	public void concatenar(Lista<T> outraLista) {
		for (int i = 0; i < outraLista.getTamanho(); i++) {
			this.inserir(outraLista.pegar(i));
		}
	}

	@Override
	public T pegar(int pos) {
		if (pos < 0 || pos >= this.qtdElementos) {
			throw new IndexOutOfBoundsException("Pos=" + pos + ". Lenght=" + this.qtdElementos);
		}
		NoLista<T> p = this.primeiro;
		int contador = 0;
		while (p != null) {
			if (contador == pos) {
				return p.getInfo();
			}
			p = p.getProximo();
			contador++;
		}
		return null;
	}

	@Override
	public Lista<T> dividir() {
		ListaEncadeada<T> nova = new ListaEncadeada<T>();
		NoLista<T> atual = this.primeiro;
		NoLista<T> anterior = null;		
		for (int i = 0; i < this.getTamanho() / 2; i++) {
			anterior = atual;
			atual = atual.getProximo();
		}
		nova.primeiro = atual;
		nova.ultimo = this.ultimo;
		this.setUltimo(anterior);
		anterior.setProximo(null);
		nova.qtdElementos = this.getTamanho() - (this.getTamanho() / 2);
		this.qtdElementos = this.getTamanho() / 2;
		
		return nova;
	}

	@Override
	public Lista<T> copiar() {
		ListaEncadeada<T> novaLista = new ListaEncadeada<>();
		for (NoLista<T> no = this.primeiro; no != null; no = no.getProximo()) {
			novaLista.inserir(no.getInfo());
		}
		return novaLista;
	}

	@Override
	public int getTamanho() {
		return this.qtdElementos;
	}
}