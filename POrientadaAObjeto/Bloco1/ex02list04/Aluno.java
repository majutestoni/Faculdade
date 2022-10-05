package ex02list04;

import java.time.LocalDate;
import java.time.Period;

public abstract class Aluno {

	private String nome;
	private LocalDate dataNascimento;

	public Aluno(String nome, LocalDate dataNascimento) {
		setNome(nome);
		setDataNascimento(dataNascimento);
	}
	
	public abstract String mostra();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(!nome.equals(null) && !nome.isBlank() && nome.length() > 4) {			
			this.nome = nome;
		}else {
			IllegalArgumentException exc = new IllegalArgumentException("Nome inválido");
			throw exc;
		}
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		LocalDate dataHoje = LocalDate.now();
		Period periodo = Period.between(dataNascimento, dataHoje);
		return periodo.getYears();
	}
}
