import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Majú Testoni
 */
public class CifraVegenere {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1 - Codificar | 2 - Decodificar | 0 - Finalizar");
		int opcao = scanner.nextInt();
		scanner.nextLine();

		while (opcao != 0) {
			if (opcao == 1) {
				codificarMSG(scanner);
			} else if (opcao == 2) {
				decodificarMSG(scanner);
			}

			System.out.println("\n1 - Codificar | 2 - Decodificar | 0 - Finalizar");
			if (scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} else {
				break;
			}
		}
		scanner.close();
	}

	private static void codificarMSG(Scanner scanner) {
		System.out.println("Informe a chave: ");
		String chave = scanner.nextLine().toUpperCase();

		System.out.println("Informe a mensagem: ");
		String mensagem = scanner.nextLine().toUpperCase();
		mensagem = mensagem.trim();

		List<String> listaCriptografada = new ArrayList<>();
		int indice = 0;

		for (char letra : mensagem.toCharArray()) {
			if (letra == ' ') {
				continue;
			}

			char letraChaveAtual = getLetraChave(chave, indice);
			int deslocamento = calculaDistanciaLetraA(letraChaveAtual);

			char letraCriptografada = (char) ('A' + (letra - 'A' + deslocamento) % 26);

			listaCriptografada.add(String.valueOf(letraCriptografada));
			indice = (indice + 1) % chave.length();
		}

		String mensagemCriptografada = String.join("", listaCriptografada);
		System.out.println("Mensagem criptografada: " + mensagemCriptografada);
	}

	private static void decodificarMSG(Scanner scanner) {
		System.out.println("Informe a chave: ");
		String chave = scanner.nextLine().toUpperCase();

		System.out.println("Informe a mensagem criptografada: ");
		String mensagemC = scanner.nextLine().toUpperCase();
		mensagemC = mensagemC.trim();

		List<String> listaDecodificada = new ArrayList<>();
		int indice = 0;

		for (char letra : mensagemC.toCharArray()) {
			if (letra == ' ') {
				continue;
			}

			char letraChaveAtual = getLetraChave(chave, indice);
			int deslocamento = calculaDistanciaLetraA(letraChaveAtual);

			char letraDecodificada = (char) ('A' + (letra - 'A' - deslocamento + 26) % 26);

			listaDecodificada.add(String.valueOf(letraDecodificada));
			indice = (indice + 1) % chave.length();
		}

		String mensagemDecodificada = String.join("", listaDecodificada);
		System.out.println("Mensagem decodificada: " + mensagemDecodificada);
	}

	private static char getLetraChave(String chave, int indiceAtual) {
		return chave.charAt(indiceAtual);
	}

	private static int calculaDistanciaLetraA(char letraChave) {
		return letraChave - 'A';
	}
}
