public class Contribuinte {
	private String nome;
	private String cpf;
	private String uf;
	private double rendaAnual;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		if (uf.equals("SC") || uf.equals("RS") || uf.equals("PR")) {
			this.uf = uf;
		}
	}

	public double getRendaAnual() {
		return rendaAnual;
	}

	public void setRendaAnual(double rendaAnual) {
		if (rendaAnual >= 0) {
			this.rendaAnual = rendaAnual;
		}
	}
	
	public double getAliquota() {
        if (this.rendaAnual > 35000) {
            return 0.30;
        } else if (this.rendaAnual >= 25001) {
            return 0.25;
        } else if (this.rendaAnual >= 9001) {
            return 0.15;
        } else if (this.rendaAnual >= 4001) {
            return 0.058;
        } else {
            return 0;
        }
    }
    public double calcularImposto() {
        return this.rendaAnual * this.getAliquota();
    }
}