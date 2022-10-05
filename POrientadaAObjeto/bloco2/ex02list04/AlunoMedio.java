package ex02list04;

import java.time.LocalDate;

public class AlunoMedio extends Aluno {
	private int ano;

	public AlunoMedio(String nome, LocalDate dataNascimento, int ano) {
		super(nome, dataNascimento);
		setAno(ano);
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		if (ano == 1 || ano == 2 || ano == 3) {
			this.ano = ano;
		} else {
			IllegalArgumentException exc = new IllegalArgumentException("Ano inválido");
			throw exc;
		}

	}

	public String mostra() {
		return getNome() + " está cursando o " + getAno() + "o ano do ensino médio e tem  " + getIdade() + " anos.";
	}

}
