package ex02list04;

import java.time.LocalDate;

public class AlunoUniversitario extends Aluno {
	private char formaIngresso;
	private Curso cursoAluno;

	public AlunoUniversitario(String nome, LocalDate dataNascimento, String formaIngresso, Curso cursoAluno) {
		super(nome, dataNascimento);
		setFormaIngresso(formaIngresso);
		addCurso(cursoAluno);
	}

	public char getFormaIngresso() {
		return formaIngresso;
	}

	public void setFormaIngresso(String formaIngresso) {
		char a = formaIngresso.toUpperCase().charAt(0);
		if (a == 'V' || a == 'E' || a == 'S' || a == 'T' || a == 'I') {
			this.formaIngresso = a;
		}
	}

	public void addCurso(Curso c) {
		this.cursoAluno = c;
	}

	public String getCursos() {
		return cursoAluno.getSigla() + " - " + cursoAluno.getNome();
	}

	public String mostra() {
		return getNome() + " é aluno universitário do curso de " + getCursos() + ", ingressando por " + getFormaIngresso();
	}

}
