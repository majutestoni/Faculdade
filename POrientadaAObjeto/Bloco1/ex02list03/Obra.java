package ex02list03;

import java.util.ArrayList;

public class Obra {
	private String titulo;
	private String autor;
	private Parecer[] pareceres = new Parecer[3];
	private ArrayList<Parecer> pareceristas = new ArrayList<Parecer>();
	private int indice;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public Parecer getParecer(int pos) {
		return pareceres[pos];
	}
	public Parecer getParecerista() {
		return pareceristas.toString;
	}
	public Parecer getPareceristaTudo(int pos) {
		return pareceristas.get(pos);
	}

	public void addParecer(Parecer p) {
		if (indice < pareceres.length) {
			pareceres[indice] = p;
			indice++;
		}
	}

	public void addParecerista(Parecer p) {
		if (pareceristas.size() < 4) {
			pareceristas.add(p);
		}
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

}
