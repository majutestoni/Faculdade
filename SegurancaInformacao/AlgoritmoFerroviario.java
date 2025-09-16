import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class AlgoritmoFerroviario {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1 - Codificar ou 2 - Decodificar?? 0 - Finalizar");
		int opcao = scanner.nextInt();
		scanner.nextLine();

		while (opcao != 0) {
			if (opcao == 1) {
				codificarMSG(scanner);
			} else if (opcao == 2) {
				decodificarMSG(scanner);
			}

			System.out.println("1 - Codificar ou 2 - Decodificar?? 0 - Finalizar");
			if (scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} else {
				break;
			}
		}
	}

	private static void decodificarMSG(Scanner scanner) {
		System.out.print("Informe quantidade de trilhos: ");
		int trilhos = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Informe a mensagem codificada: ");
		String msg = scanner.nextLine().trim().replace(" ", "");

		char[][] matriz = new char[trilhos][msg.length()];
		for (int i = 0; i < trilhos; i++)
			java.util.Arrays.fill(matriz[i], '\0');

		boolean descendo;
		int linha;

		descendo = false;
		linha = 0;
		for (int col = 0; col < msg.length(); col++) {
			matriz[linha][col] = '*';
			if (linha == 0) descendo = true;
			else if (linha == trilhos - 1) descendo = false;
			linha += descendo ? 1 : -1;
		}

		int index = 0;
		for (int i = 0; i < trilhos; i++) {
			for (int j = 0; j < msg.length(); j++) {
				if (matriz[i][j] == '*' && index < msg.length()) {
					matriz[i][j] = msg.charAt(index++);
				}
			}
		}

		StringBuilder resultado = new StringBuilder();
		descendo = false;
		linha = 0;
		for (int col = 0; col < msg.length(); col++) {
			resultado.append(matriz[linha][col]);
			if (linha == 0) descendo = true;
			else if (linha == trilhos - 1) descendo = false;
			linha += descendo ? 1 : -1;
		}

		System.out.println("\nMensagem decodificada: " + resultado);
	}


	private static void codificarMSG(Scanner scanner) {
		System.out.print("Informe quantidade de trilhos: ");
		int trilhos = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Informe a mensagem: ");
		String msg = scanner.nextLine().trim().replace(" ", "");

		char[][] matriz = new char[trilhos][msg.length()];
		for (int i = 0; i < trilhos; i++)
			java.util.Arrays.fill(matriz[i], '.');

		Queue<Character> fila = new LinkedList<>();
		for (char c : msg.toCharArray())
			fila.offer(c);

		boolean descendo = false;
		int linha = 0;
		for (int col = 0; col < msg.length(); col++) {
			matriz[linha][col] = fila.poll();
			if (linha == 0) descendo = true;
			else if (linha == trilhos - 1) descendo = false;
			linha += descendo ? 1 : -1;
		}

		Queue<Character> filaResult = new LinkedList<>();
		for (char[] r : matriz)
			for (char c : r)
				if (c != '.')
					filaResult.add(c);

		mostrarFilaConcatenada(new LinkedList<>(filaResult));
	}

	private static void mostrarFilaConcatenada(Queue<Character> fila) {
		StringBuilder sb = new StringBuilder();
		while (!fila.isEmpty())
			sb.append(fila.poll());
		System.out.println("\nMensagem codificada: " + sb);
	}
}
