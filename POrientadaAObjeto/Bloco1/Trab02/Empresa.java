package 
Trab02;

import java.util.ArrayList;
import java.util.HashMap;

public class Empresa {
	private ArrayList<Telefone> telefones = new ArrayList<>();
	private HashMap<String, Telefone>telefones2 = new HashMap<>();

	public void addTelefone(Telefone t) {
		if(telefones2.containsKey(t.getNumero())) {
			telefones2.put(t.getNumero(), t);
		}
	}
	public float getFaturamento() {
		float cont = 0;
		for (Telefone telefone : telefones) {
			cont = +telefone.getValorMensal();
		}
		return cont;
	}
}
