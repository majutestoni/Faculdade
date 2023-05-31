package Trabalho.Calculadoras;

import Trabalho.Erros.ValidacaoErros;
import Trabalho.Pilhas.PilhaLista;

public class CalculadoraDinamica {
	
	private String[] vetor;
	private PilhaLista<Double> lista = new PilhaLista<>();
	private String msgErro = "";
	private boolean comErro = false;

	public CalculadoraDinamica(String[] vetor) {
		
		this.vetor = vetor;
	}
	
	public String resultado() {
		
		this.calculo();
		if (this.comErro) {
			return this.msgErro;
		} else {
			String resultado = String.valueOf(this.lista.peek());
			return resultado;			
		}
	}

	private void calculo() {
		
		if (ValidacaoErros.verificaErros(this.vetor) != "") {
			this.msgErro = ValidacaoErros.verificaErros(this.vetor);
			this.comErro = true;
			return;
		} else {
			for (int i = 0; i < this.vetor.length; i++) {
				
				if (this.vetor[i].equals("+")) {
					this.lista.push(calculo2());		
				} else if (this.vetor[i].equals("-")) {
					this.lista.push(calculo3());
				} else if (this.vetor[i].equals("/")) {
					this.lista.push(calculo4());
				} else if (this.vetor[i].equals("*")) {
					this.lista.push(calculo5());
				} else {
					try {
						this.lista.push(Double.parseDouble(this.vetor[i]));						
					}
					catch (Exception e) {
						this.msgErro = "Expressão vazia";
						this.comErro = true;
			        	return;
					}
				}
			}			
		}
	}

	private Double calculo2() {
		
		Double var2 = this.lista.pop();
		Double var1 = this.lista.pop();

		return var1 + var2;
	}

	private Double calculo3() {
		
		Double var2 = this.lista.pop();
		Double var1 = this.lista.pop();

		return var1 - var2;
	}

	private Double calculo4() {
		
		Double var2 = this.lista.pop();
		Double var1 = this.lista.pop();

		return var1 / var2;
	}

	private Double calculo5() {
		
		Double var2 = this.lista.pop();
		Double var1 = this.lista.pop();

		return var1 * var2;
	}	
}