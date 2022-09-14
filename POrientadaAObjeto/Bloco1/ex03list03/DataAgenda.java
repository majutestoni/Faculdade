package ex03list03;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataAgenda {
	private LocalDate data;
	private String efemeridade;
	private ArrayList<Compromisso> compromissos = new ArrayList<Compromisso>();

	public boolean addCompromisso(Compromisso c) {
		for (Compromisso d2 : compromissos) {
			if (d2.getHora().equals(c.getHora())) {
				return false;
			}
		}
		compromissos.add(c);
		return true;

	}

	public int tempoMedio() {
		int contador = 0;
		for (Compromisso c : compromissos) {
			contador += c.getTempo();
		}
		return contador / compromissos.size();
	}

	public ArrayList<Compromisso> getPrioridadeCompromissos(char prioridade) {
		ArrayList<Compromisso> retornar = new ArrayList<Compromisso>();
		for (Compromisso c : compromissos) {
			if (c.getPrioridade() == prioridade) {
				retornar.add(c);
			}
		}
		return retornar;
	}

	public int qtdCompromissosPrioridade(char prioridade) {
		int contador = 0;
		for (Compromisso c : compromissos) {
			if (c.getPrioridade() == prioridade) {
				contador++;
			}
		}
		return contador;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getEfemeridade() {
		return efemeridade;
	}

	public void setEfemeridade(String efemeridade) {
		this.efemeridade = efemeridade;
	}

}
