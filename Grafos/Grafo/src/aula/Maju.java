// Maria Júlia Testoni
package aula;

import java.util.ArrayList;
import java.util.Arrays;

public class Maju {

	public static void main(String[] args) {
		// matriz caderno
		int[][] matriz = new int[4][4];
		matriz[0][0] = 0;
		matriz[0][1] = 1;
		matriz[0][2] = 1;
		matriz[0][3] = 1;
		matriz[1][0] = 1;
		matriz[1][1] = 0;
		matriz[1][2] = 1;
		matriz[1][3] = 0;
		matriz[2][0] = 1;
		matriz[2][1] = 1;
		matriz[2][2] = 0;
		matriz[2][3] = 1;
		matriz[3][0] = 1;
		matriz[3][1] = 0;
		matriz[3][2] = 1;
		matriz[3][3] = 0;

		int[][] matrizRegular = { { 0, 1, 0, 1 }, { 1, 0, 1, 0 }, { 0, 1, 0, 1 }, { 1, 0, 1, 0 } };

		int[][] matrizNula = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

		int[][] matrizDirigida = new int[4][4];
		matrizDirigida[0][0] = 0;
		matrizDirigida[0][1] = 1;
		matrizDirigida[0][2] = 0;
		matrizDirigida[0][3] = 1;
		matrizDirigida[1][0] = 0;
		matrizDirigida[1][1] = 0;
		matrizDirigida[1][2] = 0;
		matrizDirigida[1][3] = 0;
		matrizDirigida[2][0] = 1;
		matrizDirigida[2][1] = 0;
		matrizDirigida[2][2] = 0;
		matrizDirigida[2][3] = 0;
		matrizDirigida[3][0] = 0;
		matrizDirigida[3][1] = 1;
		matrizDirigida[3][2] = 0;
		matrizDirigida[3][3] = 0;

		System.out.println(tipoDoGrafo(matriz));
		//System.out.println(tipoDoGrafo(matrizRegular));
		//System.out.println(tipoDoGrafo(matrizNula));
		//System.out.println(tipoDoGrafo(matrizDirigida));

		System.out.println(grausDoVertice(matriz));
		//System.out.println(grausDoVertice(matrizRegular));
		//System.out.println(grausDoVertice(matrizDirigida));

		System.out.println(arestasDoGrafo(matriz));
		//System.out.println(arestasDoGrafo(matrizRegular));
		//System.out.println(arestasDoGrafo(matrizDirigida));
	}

	public static String tipoDoGrafo(int[][] matriz) {
		String resp = "";
		boolean simples = true;
		boolean regular = true;
		boolean nulo = true;
		int[] parteUm = new int[6];
		int[] parteDois = new int[6];
		int contadorUm = 0;
		int contadorDois = 0;

		for (int linha = 0; linha < matriz.length; linha++) {
			// verifica caso esteja na linha e coluna da diagonal principal
			if (matriz[linha][linha] != 0) {
				simples = false;
			}
			for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
				// verifica multi-grafo
				if (matriz[linha][coluna] > 1) {
					simples = false;
				}

				if (linha != coluna) {
					if (coluna > linha) {
						parteUm[contadorUm] = matriz[linha][coluna];
						contadorUm++;
					} else {
						parteDois[contadorDois] = matriz[linha][coluna];
						contadorDois++;
					}
				}
			}

		}

		for (int i = 1; i < parteUm.length; i++) {
			if (parteUm[i - 1] != parteUm[i]) {
				regular = false;
			}

			if (parteUm[i] != 0) {
				nulo = false;
			}
		}

		if (nulo) {
			resp = " é um grafo nulo";
		} else {
			if (!regular) {
				resp += " não é um grafo regular";
			} else {
				resp += " é um grafo regular";
			}

			if (!Arrays.toString(parteUm).equals(Arrays.toString(parteDois))) {
				resp += " é um grafo dirigido";
			} else {
				resp += " é um grafo não dirigido";
			}

			if (!simples) {
				resp += " é um multi-grafo";
			} else {
				resp += " é um grafo simples";
			}
		}

		return resp;
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
			return Arrays.toString(relacao) + ", sequencia de graus: " +  Arrays.toString(sequencia);
		} else {
			int[] grauSaida = new int[matriz.length];
			int[] grauEntrada = new int[matriz[0].length];
			int contador = 0;
			int contadorDois = 0;
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[i].length; j++) {
					contador += matriz[i][j];
					contadorDois += matriz[j][i];		
				}
				grauSaida[i] = contador;
				grauEntrada[i] = contadorDois;
				contador = 0;
				contadorDois = 0;
			}

			return "Grau de entrada: " + Arrays.toString(grauEntrada) + "\n" + "Grau de sáida: "
					+ Arrays.toString(grauSaida);

		}

	}

	public static String arestasDoGrafo(int[][] matriz) {
		String resp = "";
		// formula não dirigido = totalDeGraus / 2;

		if (!dirigido(matriz)) {
			resp = graus(matriz, false);
		} else {
			resp = graus(matriz, true);
		}

		return resp;
	}

	private static String graus(int[][] matriz, boolean isDirigido) {

		int contador = 0;
		int percorrer = 0;
		ArrayList<String> list = new ArrayList<>();
		
		if (!isDirigido) {
			percorrer = matriz[0].length / 2;
		} else {
			percorrer = matriz[0].length;
		}

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < percorrer; j++) {
				contador += matriz[i][j];
				if (matriz[i][j] > 0) {
					list.add("(" + i + ", " + j + ")");
				}
			}

		}


		return "A quantidade é " + contador + " e o conjunto {" + list.toString() + "}";
	}

	private static boolean dirigido(int[][] matriz) {
		int[] parteUm = new int[6];
		int[] parteDois = new int[6];
		int contadorUm = 0;
		int contadorDois = 0;

		for (int linha = 0; linha < matriz.length; linha++) {
			for (int coluna = 0; coluna < matriz[linha].length; coluna++) {

				if (linha != coluna) {
					if (coluna > linha) {
						parteUm[contadorUm] = matriz[linha][coluna];
						contadorUm++;
					} else {
						parteDois[contadorDois] = matriz[linha][coluna];
						contadorDois++;
					}
				}
			}

		}

		if (!Arrays.toString(parteUm).equals(Arrays.toString(parteDois))) {
			return true;
		} else {
			return false;
		}

	}

}
