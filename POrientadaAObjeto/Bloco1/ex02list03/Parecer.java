package ex02list03;

import java.time.LocalDate;

public class Parecer {
	private String parecerista;
	private String conteudo;
	private LocalDate data;
	
	
	
	public String getParecerista() {
		return parecerista;
	}
	public void setParecerista(String parecerista) {
		if(parecerista != null) {
			this.parecerista = parecerista;		
		}
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}
