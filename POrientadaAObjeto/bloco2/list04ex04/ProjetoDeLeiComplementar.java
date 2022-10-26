package list04ex04;
import java.time.LocalDate;

public class ProjetoDeLeiComplementar extends ProjetoDeLei {
	private int artigoLO;
	private int qntVotosFavoraveis;

	public ProjetoDeLeiComplementar(String titulo, LocalDate dataApresentacao, LocalDate dataAprovacao,
			int numeroProjeto, int artigoLO, int qntVotosFavoraveis) {
		super(titulo, dataApresentacao, dataAprovacao, numeroProjeto);
		setArtigoLO(artigoLO);
		setQntVotosFavoraveis(qntVotosFavoraveis);
	}

	public int getArtigoLO() {
		return artigoLO;
	}

	public void setArtigoLO(int artigoLO) {
		if (artigoLO > 0) {
			this.artigoLO = artigoLO;
		} else {
			throw new IllegalArgumentException("Digite um número válido.");
		}
	}

	public int getQntVotosFavoraveis() {
		return qntVotosFavoraveis;
	}

	public void setQntVotosFavoraveis(int qntVotosFavoraveis) {
		if (qntVotosFavoraveis > 0) {
			this.qntVotosFavoraveis = qntVotosFavoraveis;
		} else {
			throw new IllegalArgumentException("Digite o número de votos favoráveis.");
		}
	}

	public String mostrar() {
		return super.mostrar() + " ArtigoLO: " + this.getArtigoLO() + "\n" + "Qtd de Votos Favoráveis: " + this.getQntVotosFavoraveis();
	}
}
