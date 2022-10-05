package ex02list04;

public class Curso {
	private String sigla;
	private String nome;
	
	public Curso(String nome, String sigla) {
		setNome(nome);
		setSigla(sigla);
	}
	
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		sigla = sigla.toUpperCase();
		if(sigla.length() == 3){			
			this.sigla = sigla;
		}
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome.length() > 4) {			
			this.nome = nome;
		}
	}
}
