package list04ex04;

import java.time.LocalDate;

public class ProjetoDeLei {

	private String titulo;
	private LocalDate dataApresentacao;
	private LocalDate dataAprovacao;
	private int numeroProjeto;

	public ProjetoDeLei(String titulo, LocalDate dataApresentacao, LocalDate dataAprovacao, int numeroProjeto) {
		setTitulo(titulo);
		setDataApresentacao(dataApresentacao);
		setDataAprovacao(dataAprovacao);
		setNumeroProjeto(numeroProjeto);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (!titulo.equals(null) && !titulo.isBlank()) {
			this.titulo = titulo;
		} else {
			throw new IllegalArgumentException("Digite um título ao projeto.");
		}
	}

	public LocalDate getDataApresentacao() {
		return dataApresentacao;
	}

	public void setDataApresentacao(LocalDate dataApresentacao) {
		if (dataApresentacao != null) {
			this.dataApresentacao = dataApresentacao;
		} else {
			throw new IllegalArgumentException("Digite uma data.");
		}
	}

	public LocalDate getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(LocalDate dataAprovacao) {
		if (dataAprovacao != null) {
			this.dataAprovacao = dataAprovacao;
		} else {
			throw new IllegalArgumentException("Digite uma data válida.");
		}

	}

	public int getNumeroProjeto() {
		return numeroProjeto;
	}

	public void setNumeroProjeto(int numeroProjeto) {
		if (numeroProjeto > 0) {
			this.numeroProjeto = numeroProjeto;
		} else {
			throw new IllegalArgumentException("Digite o número de projetos.");
		}
	}

	public String mostrar() {
		return "Título: " + this.getTitulo() + "\n" + "Data de Apresentacao: " + this.getDataApresentacao() + "\n"
				+ "Data de Aprovacao: " + this.getDataAprovacao() + "\n" + "Número do Projeto: "
				+ this.getNumeroProjeto();
	}

}
