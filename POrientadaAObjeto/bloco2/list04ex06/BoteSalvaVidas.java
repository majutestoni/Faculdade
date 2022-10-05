package list04ex06;

public class BoteSalvaVidas extends Embarcacao {
	private boolean infavel;
	private int qtdeRemos;
	private int qtdeColetes;

	public BoteSalvaVidas(String registroCapitania, int qntdPessoas, boolean infavel, int qtdeRemos, int qtdeColetes) {
		super(registroCapitania, qntdPessoas);
		setInfavel(infavel);
		setQtdeRemos(qtdeRemos);
		setQtdeColetes(qtdeColetes);
	}

	public boolean isInfavel() {
		return infavel;
	}

	public void setInfavel(boolean infavel) {
		this.infavel = infavel;
	}

	public int getQtdeRemos() {
		return qtdeRemos;
	}

	public void setQtdeRemos(int qtdeRemos) {
		if (qtdeRemos > 0) {
			this.qtdeRemos = qtdeRemos;
		} else {
			IllegalArgumentException exc = new IllegalArgumentException("Número inválido");
			throw exc;
		}
	}

	public int getQtdeColetes() {
		return qtdeColetes;
	}

	public void setQtdeColetes(int qtdeColetes) {
		if (qtdeColetes > 0) {
			this.qtdeColetes = qtdeColetes;
		} else {
			IllegalArgumentException exc = new IllegalArgumentException("Número inválido");
			throw exc;
		}
	}

	public String verificaSeguranca() {
		if (qtdeColetes >= getQntdPessoas()) {
			return "Bote OK";
		} else {
			return " “Insuficiência de " + getQntdPessoas() + qtdeColetes + " coletes salva-vidas”";
		}
	}

}
