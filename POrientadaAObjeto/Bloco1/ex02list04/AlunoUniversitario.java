package ex02list04;

import java.time.LocalDate;

public class AlunoUniversitario extends Aluno {
	private char formaIngresso;
	private Curso cursoAluno;

	public AlunoUniversitario(String nome, LocalDate dataNascimento, char formaIngresso, Curso cursoAluno) {
		super(nome, dataNascimento);
		setFormaIngresso(formaIngresso);
		addCurso(cursoAluno);
	}

	public char getFormaIngresso() {
		return formaIngresso;
	}

	public void setFormaIngresso(char formaIngresso) {
		if (formaIngresso == 'V' || formaIngresso == 'E' || formaIngresso == 'S' || formaIngresso == 'T'
				|| formaIngresso == 'I') {
			this.formaIngresso = formaIngresso;
		}
	}
	
	public void addCurso(Curso c) {
		this.cursoAluno = c;
	}
	
	public Curso getCursos(){
		return cursoAluno;
	}
	
	public String mostra() {
		return getNome() + getCursos() + getFormaIngresso();
	}

}
