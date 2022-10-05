package list04ex06;

import java.time.LocalDate;
import java.util.HashMap;

public class Transatlantico extends Embarcacao {

	private String nome;
	private LocalDate dataInauguracao;
	private HashMap<String, BoteSalvaVidas> botes = new HashMap<>();

	public Transatlantico(String registroCapitania, int qntdPessoas, String nome, LocalDate dataInauguracao) {
		super(registroCapitania, qntdPessoas);
		setNome(nome);
		setDataInauguracao(dataInauguracao);
	}

	public String verificaSeguranca() {
		if (LocalDate.now().isAfter(dataInauguracao)) {
			return "“CUIDADO: navio em perigo";
		}
		if (qntdTotalPessoasBote() >= getQntdPessoas()) {
			return "Está em condições adequadas de segurança";
		} else {
			return "Alerta: navio necessitando de mais botes!";
		}
	}

	private int qntdTotalPessoasBote() {
		int cont = 0;
		for (BoteSalvaVidas bote : botes.values()) {
			cont += bote.getQntdPessoas();
		}
		return cont;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (!nome.equals(null) && !nome.isBlank()) {
			this.nome = nome;
		} else {
			IllegalArgumentException exc = new IllegalArgumentException("Nome inválido");
			throw exc;
		}
	}

	public LocalDate getDataInauguracao() {
		return dataInauguracao;
	}

	public void setDataInauguracao(LocalDate dataInauguracao) {
		this.dataInauguracao = dataInauguracao;
	}

	public void addBote(BoteSalvaVidas b) {
		if (botes.containsKey(b.getRegistroCapitania())) {
			botes.put(b.getRegistroCapitania(), b);
		} else {
			IllegalArgumentException exc = new IllegalArgumentException("Bot já existe");
			throw exc;
		}
	}

	public void addBote(String registroCapitania, int qntdPessoas, boolean infavel, int qtdeRemos, int qtdeColetes) {
		BoteSalvaVidas b = new BoteSalvaVidas(registroCapitania, qntdPessoas, infavel, qtdeRemos, qtdeColetes);

		if (botes.containsKey(b.getRegistroCapitania())) {
			botes.put(b.getRegistroCapitania(), b);
		} else {
			IllegalArgumentException exc = new IllegalArgumentException("Bot já existe");
			throw exc;
		}
	}

}
