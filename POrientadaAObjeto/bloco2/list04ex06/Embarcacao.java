package list04ex06;

public abstract class Embarcacao {
	private String registroCapitania;
	private int qntdPessoas;

	public Embarcacao(String registroCapitania, int qntdPessoas) {
		setRegistroCapitania(registroCapitania);
		setQntdPessoas(qntdPessoas);
	}

	public abstract String verificaSeguranca();

	public String getRegistroCapitania() {
		return registroCapitania;
	}

	public void setRegistroCapitania(String registroCapitania) {
		if (!registroCapitania.equals(null) && !registroCapitania.isBlank()) {
			this.registroCapitania = registroCapitania;
		}else {
			IllegalArgumentException exc = new IllegalArgumentException("Nome inválido");
			throw exc;
		}
	}

	public int getQntdPessoas() {
		return qntdPessoas;
	}

	public void setQntdPessoas(int qntdPessoas) {
		if (qntdPessoas > 0) {
			this.qntdPessoas = qntdPessoas;
		} else {
			IllegalArgumentException exc = new IllegalArgumentException("Número inválido");
			throw exc;
		}
	}

}
