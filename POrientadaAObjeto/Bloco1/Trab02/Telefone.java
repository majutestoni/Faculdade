package Trab02;

import java.time.LocalDate;

public class Telefone {
	private Character tipo;
	private String numero;
	private String endereco;
	private LocalDate dataInstalacao;
	
	private int ocorrencias;
	private boolean internet;
	private Character ramoAtividade;

	public boolean isInternet() {
		return internet;
	}

	public void setInternet(boolean internet) {
		this.internet = internet;
	}

	public Character getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(Character ramoAtividade) {
		ramoAtividade = Character.toUpperCase(ramoAtividade);
		if(ramoAtividade == 'S' || ramoAtividade == 'C') {			
			this.ramoAtividade = ramoAtividade;
		}
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		tipo = Character.toUpperCase(tipo);
		if (tipo == 'R' || tipo == 'C' || tipo == 'E') {
			this.tipo = tipo;
		} else {
			throw new IllegalArgumentException("Tipo inválido");
		}
	}

	public int getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(int ocorrencias) {
		if(ocorrencias > 0) {			
			this.ocorrencias = ocorrencias;
		}else {
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
		if (tipo == 'R') {
			return 15.00f;
		} else if (tipo == 'C') {
			return getValorComercial();
		} else {
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
