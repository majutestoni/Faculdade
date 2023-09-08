// Maria Júlia Testoni

package aula;

import java.util.Arrays;

public class Grafo {

	public static void main(String[] args) {

		int[][] matriz = { { 0, 1, 1, 1 }, { 1, 0, 1, 0 }, { 1, 1, 0, 1 }, { 1, 0, 1, 0 } };

		int[][] matrizCompleta = { { 0, 1, 1, 1 }, { 1, 0, 1, 1 }, { 1, 1, 0, 1 }, { 1, 1, 1, 0 } };

		int[][] matrizRegular = { { 0, 1, 0, 1 }, { 1, 0, 1, 0 }, { 0, 1, 0, 1 }, { 1, 0, 1, 0 } };

		int[][] matrizMultiGrafo = { { 0, 1, 0, 3 }, { 1, 0, 2, 0 }, { 0, 2, 0, 1 }, { 3, 0, 1, 0 } };

		int[][] matrizNula = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

		int[][] matrizDirigida = { { 0, 1, 0, 1 }, { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 0, 1, 0, 0 } };

		System.out.println(tipoDoGrafo(matriz));
		System.out.println(tipoDoGrafo(matrizCompleta));
		System.out.println(tipoDoGrafo(matrizRegular));
		System.out.println(tipoDoGrafo(matrizMultiGrafo));
		System.out.println(tipoDoGrafo(matrizNula));
		System.out.println(tipoDoGrafo(matrizDirigida));
	}
	
	/*
	 * PARA DEFINIR QUANTOS ELEMENTOS VOU TER NA METADE DA MATRIZ:
	 * ((n*n) - n) / 2 sendo n igual a quantiade de linhas ou colunas
	 */

	private static String tipoDoGrafo(int[][] matriz) {
		String resp = "Este grafo é";
		// pega o grau da parte de cima da matriz - lado direito
		int[] parteCima = percorrerLinha(matriz);
		// pega o grau da parte de baixo da matriz - lado esquerdo
		int[] parteBaixo = percorrerColuna(matriz);
		// pega a diagonal principal
		int[] parteMeio = percorrerMeio(matriz);

		boolean simples = true;
		boolean nulo = true;
		boolean regular = true;
		boolean completo = true;

		// auxiliar para comparar se é grau regular - parte cima
		int auxiliarCima = parteCima[0];
		// auxiliar para comparar se é grau regular - parte baixo
		int auxiliarBaixo = parteBaixo[0];

		for (int i = 0; i < parteCima.length; i++) {
			if (parteCima[i] > 0 || parteBaixo[i] > 0) {
				nulo = false;
			}
			if (parteCima[i] > 1 || parteBaixo[i] > 1) {
				simples = false;
			}

			if (auxiliarCima != parteCima[i] || auxiliarBaixo != parteBaixo[i]) {
				regular = false;
			}
			auxiliarCima = parteCima[i];
			auxiliarBaixo = parteBaixo[i];
		}

		if (simples) {
			for (int i = 0; i < parteCima.length; i++) {
				if (parteCima[i] == 0 || parteBaixo[i] == 0) {
					completo = false;
				}
			}
		}

		for (int i : parteMeio) {
			if (i > 0) {
				simples = false;
			}
		}

		if (nulo) {
			resp += " nulo";
		} else {
			if (dirigido(matriz)) {
				resp += " dirigido";
			} else {
				resp += " não dirigido";
			}
		}

		if (simples) {
			resp += ", simples";

			if (completo) {
				resp += ", completo";
			}
		} else {
			resp += ", multigrafo";
		}
		
		if(regular) {
			resp += " e regular";
		}

		return resp;

	}

	private static String arestasDoGrafo(int[][] matriz) {
		int contador = 0;
		String list = "{";

		boolean isDirigido = dirigido(matriz);

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {

				if (j > i && !isDirigido) {
					if (matriz[i][j] > 0) {
						for (int arestas = 0; arestas < matriz[i][j]; arestas++) {
							list += "(" + i + ", " + j + ")";
						}
					}
				} else if (isDirigido) {
					if (matriz[i][j] > 0) {
						for (int arestas = 0; arestas < matriz[i][j]; arestas++) {
							list += "(" + i + ", " + j + ")";
						}
					}
				}

				contador += matriz[i][j];
			}

		}
		list += "}";
		if (!isDirigido) {
			contador = contador / 2;
		}

		return "A quantidade é " + contador + " e o conjunto " + list;
	}

	public static String grausDoVertice(int[][] matriz) {

		if (!dirigido(matriz)) {
			int[] sequencia = new int[matriz.length];
			String[] relacao = new String[matriz.length];
			int contador = 0;
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[i].length; j++) {
					contador += matriz[i][j];
				}
				sequencia[i] = contador;
				relacao[i] = "vertice: " + i + " e grau: " + contador;
				contador = 0;
			}
			return Arrays.toString(relacao) + ", sequencia de graus: " + Arrays.toString(sequencia);
		} else {
			int[] grauSaida = new int[matriz.length];
			int[] grauEntrada = new int[matriz[0].length];
			String[] relacao = new String[matriz.length];
			int contador = 0;
			int contadorDois = 0;
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[i].length; j++) {
					contador += matriz[i][j];
					contadorDois += matriz[j][i];
					relacao[i] = "vertice: " + i + ", grau: " + (contador + contadorDois);
				}
				grauSaida[i] = contador;
				grauEntrada[i] = contadorDois;
				contador = 0;
				contadorDois = 0;
			}

			return Arrays.toString(relacao) + "\n" + "Grau de entrada: " + Arrays.toString(grauEntrada) + " e "
					+ "Grau de sáida: " + Arrays.toString(grauSaida);

		}

	}

	private static boolean dirigido(int[][] matriz) {
		int[] parteCima = percorrerLinha(matriz);
		int[] parteBaixo = percorrerColuna(matriz);

		if (!Arrays.toString(parteCima).equals(Arrays.toString(parteBaixo))) {
			return true;
		} else {
			return false;
		}

	}

	private static int[] percorrerColuna(int[][] matriz) {
		int calculo = ((matriz.length * matriz.length) - matriz.length) / 2;
		int[] parteDois = new int[calculo];
		int contadorDois = 0;
		for (int coluna = 0; coluna < matriz[0].length; coluna++) {
			for (int linha = 0; linha < matriz.length; linha++) {
				if (coluna < linha) {
					parteDois[contadorDois] = matriz[linha][coluna];
					contadorDois++;
				}
			}

		}

		return parteDois;
	}

	private static int[] percorrerLinha(int[][] matriz) {
		int calculo = ((matriz.length * matriz.length) - matriz.length) / 2;
		int[] parteUm = new int[calculo];
		int contadorUm = 0;
		for (int linha = 0; linha < matriz.length; linha++) {
			for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
				if (coluna > linha) {
					parteUm[contadorUm] = matriz[linha][coluna];
					contadorUm++;
				}
			}

		}
		return parteUm;
	}

	private static int[] percorrerMeio(int[][] matriz) {
		int[] meio = new int[matriz.length];
		int contadorUm = 0;
		for (int i = 0; i < matriz.length; i++) {

			meio[contadorUm] = matriz[i][i];
			contadorUm++;
		}

		return meio;
	}

}
