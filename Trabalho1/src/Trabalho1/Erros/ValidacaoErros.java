package Trabalho.Erros;

public class ValidacaoErros {
	
	public static String verificaErros(String[] vetor) {
		
		var contadorNum = 0;
		var contadorOpe = 0;
		var msgErro = "";
		
		if (vetor[0].isBlank()) {
			msgErro = "ERROR: O primeiro item do vetor esta vazio.";
        	return msgErro;
		} else if (vetor[0].equals("+") || vetor[0].equals("-") || vetor[0].equals("*") || vetor[0].equals("/")) {
			contadorOpe++;
		} else {
	        boolean isNumeric =  vetor[0].matches("[+-]?\\d*(\\.\\d+)?");
	        if (isNumeric == false) {
	        	msgErro = "ERROR: A expressão contém um caracter inválido.";
	        	return msgErro;
	        } else {
	        	contadorNum++;
	        }
		}		
		for (int i = 1; i < vetor.length; i++) {			
			if (vetor[i].equals("+") || vetor[i].equals("-") || vetor[i].equals("*") || vetor[i].equals("/")) {
				contadorOpe++;
			} else {
		        boolean isNum =  vetor[i].matches("[+-]?\\d*(\\.\\d+)?");
		        if (isNum == false) {
		        	msgErro = "ERROR: A expressão contém um caracter inválido.";
		        	return msgErro;
		        } else {
		        	contadorNum++;
		        }
			}
			if (contadorNum <= contadorOpe) {
				msgErro = "ERROR: Não há operandos suficientes na expressão para realizar a conta";
	        	return msgErro;
			}			
		}
		if (contadorNum > contadorOpe + 1) {
			msgErro = "ERROR: Faltou algum operador para usar todos os elementos da expressão";
		}
		return msgErro;		
	}
}
