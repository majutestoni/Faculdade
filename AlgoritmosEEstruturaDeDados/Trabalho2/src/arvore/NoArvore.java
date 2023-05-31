package arvore;

import java.util.ArrayList;
import java.util.Arrays;

public class NoArvore<T> {

	private T info;
	private NoArvore<T> filho;
	private NoArvore<T> irmao;

	public NoArvore(T info) {
		this.info = info;
		this.filho = null;
		this.irmao = null;
	}

	public String imprimePre() {
		String str = "<";

		str += this.info.toString();
		str += this.filho != null ? this.filho.imprimePre() : "";
		str += ">";
		str += this.irmao != null ? this.irmao.imprimePre() : "";

		return str;
	}

	public void inserirFilho(NoArvore<T> filhoNo) {
		filhoNo.irmao = this.filho;
		this.filho = filhoNo;
	}

	public NoArvore<T> pertence(T info) {
		if (this.info.equals(info)) {
			return this;
		} else {
			NoArvore<T> noEncontrado = null;
			if (this.filho != null) {
				noEncontrado = this.filho.pertence(info);
			}
			if (noEncontrado == null && this.irmao != null) {
				noEncontrado = this.irmao.pertence(info);
			}
			return noEncontrado;
		}
	}

	public T getInfo() {
		return info;
	}

	public int getAltura() {

		int cont = 1;

		if (this.filho != null) {
			cont += this.filho.getAltura();
		}
		if (this.irmao != null) {
			int alturaIrmao = this.irmao.getAltura();
			return Math.max(cont, alturaIrmao);
		}
		return cont;

	}

	public int getNivel(T info) {

		int cont = 1;
		if (this.info.equals(info)) {
			return cont - 1;
		} else {
			if (this.filho != null) {
				cont += this.filho.getNivel(info);
			}
			if (this.irmao != null) {
				return this.irmao.getNivel(info);
			}
			return cont;
		}

	}

	public NoArvore<T> getFilho() {
		return filho;
	}

	public NoArvore<T> getIrmao() {
		return irmao;
	}

	public void setIrmao(NoArvore<T> irmao) {
		this.irmao = irmao;
	}

	public boolean isBalanceada() {

		int vetor = getBalanceada(this.info);
		if(vetor == -1) { return false; } else { return true;}
	}

	private int getBalanceada(T info) {
	    int alturaFilhos = -1;
	    int alturaMinima = Integer.MAX_VALUE;
	    int alturaMaxima = 0;
	    
	    System.out.println(this.info);

	    NoArvore<T> filho = this.filho;
	    while (filho != null) {
	        int alturaSubarvore = filho.getBalanceada(info);
	        if (alturaSubarvore == -1) {
	            return -1;
	        }

	        alturaFilhos = Math.max(alturaFilhos, alturaSubarvore);
	        alturaMinima = Math.min(alturaMinima, alturaSubarvore);
	        alturaMaxima = Math.max(alturaMaxima, alturaSubarvore);
	        filho = filho.irmao;
	    }

	    if (alturaMaxima - alturaMinima > 1) {
	        return -1; 
	    }

	    return alturaFilhos + 1;
	}

}
