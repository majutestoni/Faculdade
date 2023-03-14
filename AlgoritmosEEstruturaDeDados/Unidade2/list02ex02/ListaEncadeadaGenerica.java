package ativ;

public class ListaEncadeada<T> implements Lista<T> {

	private NoLista<T> primeiro;
	private NoLista<T> ultimo;
	private int qntElementos;

	public ListaEncadeada() {
		primeiro = null;
		ultimo = null;
		qntElementos = 0;
	}

	@Override
	public String exibir() {
		NoLista<T> p = primeiro;
		String resp = "[ ";
		while (p != null) {
			resp += p.getInfo() + ", ";
			p = p.getProximo();
		}
		return resp += " ]";
	}

	@Override
	public void inserir(T valor) {
		NoLista novo = new NoLista();
		novo.setInfo(valor);
		if (this.estaVazia()) {
			primeiro = novo;
		} else {
			ultimo.setProximo(novo);
		}
		ultimo = novo;
		qntElementos++;

	}

	public void inserir(T valor, int pos) {
		int cont = 0;
		NoLista p = primeiro;
		NoLista ant = null;
		if (pos == 0) {
			primeiro.setProximo(primeiro);
			primeiro.setInfo(valor);
			qntElementos++;
		} else {
			while (p != null && pos != cont) {
				ant = p;
				p = p.getProximo();
				cont++;
			}
			if (p != null) {
				p.setProximo(p);
				p.setInfo(valor);
				qntElementos++;
			}
		}
	}

	@Override
	public int buscar(T valor) {
		int resp = -1;
		NoLista p = primeiro;
		int cont = 0;
		while (p != null && resp == -1) {
			if (p.getInfo().equals(valor)) {
				resp = cont;
			}
			p = p.getProximo();
			cont++;
		}
		return resp;
	}

	@Override
	public Lista dividir() {
		int metade = qntElementos / 2;
		int cont = 0;
		NoLista<T> p = primeiro;
		ListaEncadeada<T> nova = new ListaEncadeada<T>();

		while (cont != metade) {
			nova.inserir(p.getInfo());
			this.retirar(p.getInfo());
			p = p.getProximo();
			cont++;
		}
		return nova;
	}

	@Override
	public void retirar(T valor) {
		NoLista<T> p = primeiro;
		NoLista<T> ant = null;
		if (primeiro.getInfo() == valor) {
			primeiro = primeiro.getProximo();
			qntElementos--;
		} else {
			while (p != null && p.getInfo() != valor) {
				ant = p;
				p = p.getProximo();
			}
			if (p != null) {
				ant.setProximo(p.getProximo());
			}
			if (p == ultimo) {
				ultimo = ant;
			}
		}

	}

	@Override
	public boolean estaVazia() {
		if (this.qntElementos < 1) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Lista copiar() {
		ListaEncadeada<T> nova = new ListaEncadeada<T>();
		NoLista<T> lista = primeiro;
		while (lista != null) {
			nova.inserir(lista.getInfo());
			lista = lista.getProximo();
		}
		return nova;
	}

	@Override
	public int getTamanho() {
		return qntElementos;
	}

	@Override
	public void concatenar(Lista<T> outraLista) {


	}

	@Override
	public T pegar(int pos) {
		if (pos < 0 || pos >= qntElementos) {
			throw new IndexOutOfBoundsException("não");
		}

		NoLista p = primeiro;
		int cont = 0;
		while (p != null) {
			if (cont == pos) {
				return (T) p.getInfo();
			}
			p = p.getProximo();
			cont++;
		}
		return null;

	}


}
