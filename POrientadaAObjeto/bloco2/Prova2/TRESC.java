package Prova2;

// Maria Júlia Testoni


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TRESC {
	private List<Equipamento> equipamentos = new ArrayList<Equipamento>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public int getVolume() {
		int contar = 0;

		for (Equipamento equipamento : equipamentos) {
			contar += this.volumeUnitario(equipamento);
		}
		return contar;
	}

	private int volumeUnitario(Equipamento e) {
		if (e.getTipo().equals("Computador")) {
			return (int) 352.000;
		} else {
			return (int) 489.000;
		}
	}

	public String listarEquipamento() {
		String saida = "";
		for (Equipamento e : equipamentos) {
			saida += e.getTipo() + " " + e.getCodPat() + ", chave " + e.getChave() + ", volume "
					+ this.volumeUnitario(e) + ", fabricada em " + e.getDataFabri().format(formatter) + "/n";
		}
		return saida;
	}

	public List<Equipamento> quaisEquipamentosFaixa(LocalDate inicio, LocalDate finall) {
		List<Equipamento> saida = new ArrayList<>();

		for (Equipamento e : saida) {
			if (e.getDataFabri().isAfter(inicio) && e.getDataFabri().isBefore(finall)) {
				saida.add(e);
			}
		}
		return saida;
	}

	public String addEquipamento(Equipamento e) {
		equipamentos.add(e);
		return e.getChave();

	}
}
