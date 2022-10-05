package ex02list04;

import java.time.LocalDate;

public class AlunoMedio extends Aluno {
	private int ano;

	public AlunoMedio(String nome, LocalDate dataNascimento) {
		super(nome, dataNascimento);
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
		return getAno() + getNome() + getIdade();
	}

}
