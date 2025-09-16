import java.util.Scanner;

public class CifraTransposicao {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1 - Codificar ou 2 - Decodificar?? 0 - Finalizar");
		int opcao = scanner.nextInt();
		scanner.nextLine();

		while (opcao != 0) {
			if (opcao == 1) codificarMSG(scanner);
			else if (opcao == 2) decodificarMSG(scanner);

			System.out.println("1 - Codificar ou 2 - Decodificar?? 0 - Finalizar");
			if (scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} else break;
		}
	}

	private static void codificarMSG(Scanner scanner) {
		System.out.print("Informe número de colunas: ");
		int colunas = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Informe a mensagem: ");
		String msg = scanner.nextLine().trim().replace(" ", "");

		int linhas = (int) Math.ceil(msg.length() / (double) colunas);
		char[][] matriz = new char[linhas][colunas];

		int index = 0;
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				matriz[i][j] = index < msg.length() ? msg.charAt(index++) : 'X';
			}
		}

		StringBuilder cifrada = new StringBuilder();
		for (int j = 0; j < colunas; j++) {
			for (int i = 0; i < linhas; i++) {
				cifrada.append(matriz[i][j]);
			}
		}

		System.out.println("\nMensagem codificada: " + cifrada);
	}

	private static void decodificarMSG(Scanner scanner) {
		System.out.print("Informe número de colunas: ");
		int colunas = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Informe a mensagem codificada: ");
		String msg = scanner.nextLine().trim().replace(" ", "");

		int linhas = (int) Math.ceil(msg.length() / (double) colunas);
		char[][] matriz = new char[linhas][colunas];

		int index = 0;
		for (int j = 0; j < colunas; j++) {
			for (int i = 0; i < linhas; i++) {
				matriz[i][j] = index < msg.length() ? msg.charAt(index++) : 'X';
			}
		}

		StringBuilder decodificada = new StringBuilder();
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				decodificada.append(matriz[i][j]);
			}
		}

		System.out.println("\nMensagem decodificada: " + decodificada);
	}
}
