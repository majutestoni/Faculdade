package Trab02;

/* 
 * Jonathan Ilchemin Ribeiro
 * Maria Julia Testoni
 * Martin Lange de Assis
 */


import java.time.LocalDate;

public class Telefone {
	private String tipo;
	private String numero;
	private String endereco;
	private LocalDate dataInstalacao;
	private int ocorrencias;
	private String internet;
	private String ramoAtividade;
	private String cliente;

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		if (cliente != null && !cliente.isBlank()) {
			this.cliente = cliente;
		} else {
			IllegalArgumentException exc = new IllegalArgumentException("Por favor, digite um nome válido.");
			throw exc;
		}
	}

	public String getInternet() {
		return internet;
	}

	public void setInternet(String internet) {
		internet = internet.toUpperCase();
		if(internet.equals("SIM") || internet.equals("NAO") || internet.equals("NÃO")) {			
			this.internet = internet;
		}else {
			IllegalArgumentException exc = new IllegalArgumentException(
					"Inválido");
			throw exc;
		}
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		ramoAtividade = ramoAtividade.toUpperCase();
		if (ramoAtividade.equals("SERVIÇOS") || ramoAtividade.equals("COMÉRCIO")) {
			this.ramoAtividade = ramoAtividade;
		} else {
			IllegalArgumentException exc = new IllegalArgumentException(
					"Ramo inexistente. Por favor, digite um válido");
			throw exc;
		}
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		tipo = tipo.toUpperCase();
		if (tipo.equals("RESIDENCIAL") || tipo.equals("COMERCIAL") || tipo.equals("ESPECIALIZADO")) {
			this.tipo = tipo;
		} else {
			throw new IllegalArgumentException("Tipo inválido");
		}
	}

	public int getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(int ocorrencias) {
		if (ocorrencias > 0) {
			this.ocorrencias = ocorrencias;
		} else {
			throw new IllegalArgumentException("Número de ocorrências inválido");
		}
	}

	public LocalDate getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(LocalDate dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if (numero.length() == 10) {
			this.numero = numero;
		} else {
			throw new IllegalArgumentException("Telefone invalido, deve conter 10 dígitos");
		}
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		if (endereco != null && !endereco.isBlank()) {
			this.endereco = endereco;
		} else {
			throw new IllegalArgumentException("Endereço vazio");
		}
	}

	public float getValorMensal() {
		if (tipo.equals("RESIDENCIAL") ) {
			return 15.00f;
		} else if (tipo.equals("COMERCIAL")) {
			return getValorComercial();
		} else  {
			return getValorEspecializado();
		}
	}

	public float getValorComercial() {
		if (dataInstalacao.getYear() > 2018) {
			return 37.50f;
		} else {
			return 25.00f;
		}

	}

	public float getValorEspecializado() {
		if (ocorrencias > 0 && ocorrencias <= 1000) {
			return 50.00f;
		} else if (ocorrencias > 1000 && ocorrencias <= 5000) {
			return 67.80f;
		} else if (ocorrencias > 5000 && ocorrencias <= 10000) {
			return 98.50f;
		} else if (ocorrencias > 10000 && ocorrencias <= 50000) {
			return 123.90f;
		} else {
			return 187.82f;
		}
	}
}
