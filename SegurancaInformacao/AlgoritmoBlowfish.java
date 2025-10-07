import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Autora: Maria Júlia Testoni
 */
public class AlgoritmoBlowfish {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1 - Codificar texto");
		System.out.println("2 - Decodificar texto");
		System.out.println("3 - Criptografar arquivo (PDF → saída.bin)");
		System.out.println("4 - Descriptografar arquivo (saída.bin → descriptografado.pdf)");
		System.out.println("0 - Finalizar");

		int opcao = scanner.nextInt();
		scanner.nextLine();

		while (opcao != 0) {
			switch (opcao) {
				case 1 -> codificarMSG(scanner);
				case 2 -> decodificarMSG(scanner);
				case 3 -> criptografarArquivo();
				case 4 -> descriptografarArquivo();
				default -> System.out.println("Opção inválida.");
			}

			System.out.println();
			System.out.println("1 - Codificar texto");
			System.out.println("2 - Decodificar texto");
			System.out.println("3 - Criptografar arquivo");
			System.out.println("4 - Descriptografar arquivo");
			System.out.println("0 - Finalizar");

			if (scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} else {
				break;
			}
		}

		scanner.close();
	}

	// ===========================
	//  FUNÇÕES DE TEXTO
	// ===========================
	private static void codificarMSG(Scanner scanner) throws Exception {
		System.out.println("Informe a mensagem: ");
		String mensagem = scanner.nextLine();

		System.out.println("1 - ECB ou 2 - CBC");
		int opcao = scanner.nextInt();

		if (opcao == 1) {
			byte[] numero4 = {8, 8, 8, 8, 8, 8, 8, 8};
			// codificaECB(numero4);
			codificaECB(mensagem.getBytes());
		} else if (opcao == 2) {
			codificaCBC(mensagem.getBytes());
		} else {
			System.out.println("Modo inválido.");
		}
	}

	private static void codificaECB(byte[] dados) throws Exception {
		byte[] chave = {65, 66, 67, 68, 69};

		SecretKeySpec keySpec = new SecretKeySpec(chave, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);

		byte[] textoCifrado = cipher.doFinal(dados);

		System.out.println("\nTexto cifrado (hexadecimal):");
		imprimirHexFormatado(textoCifrado);
	}


	private static void codificaCBC(byte[] dados) throws Exception {
		byte[] chave = "ABCDE".getBytes(StandardCharsets.UTF_8);
		SecretKeySpec keySpec = new SecretKeySpec(chave, "Blowfish");

		byte[] iv = {1, 1, 2, 2, 3, 3, 4, 4};
		// byte[] iv = {10,20,30,40,50,60,70,80};
		IvParameterSpec ivSpec = new IvParameterSpec(iv);

		Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		byte[] textoCifrado = cipher.doFinal(dados);

		System.out.println("\nTexto cifrado (hexadecimal) [CBC]:");
		imprimirHexFormatado(textoCifrado);
	}

	private static void decodificaCBC(String mensagem) throws Exception {
		String[] partes = mensagem.split("\\s+");
		byte[] dados = new byte[partes.length];
		for (int i = 0; i < partes.length; i++) {
			dados[i] = (byte) Integer.parseInt(partes[i], 16);
		}

		byte[] chave = "ABCDE".getBytes(StandardCharsets.UTF_8);
		SecretKeySpec keySpec = new SecretKeySpec(chave, "Blowfish");

		byte[] iv = {1, 1, 2, 2, 3, 3, 4, 4};
		IvParameterSpec ivSpec = new IvParameterSpec(iv);

		Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		byte[] textoDecifrado = cipher.doFinal(dados);
		String texto = new String(textoDecifrado, StandardCharsets.UTF_8);

		System.out.println("\nTexto decifrado (modo CBC): " + texto);
	}

	private static void decodificarMSG(Scanner scanner) throws Exception {
		System.out.println("Informe os bytes em hexadecimal (separados por espaço):");
		String linha = scanner.nextLine().trim();
		if (linha.isEmpty()) {
			System.out.println("Entrada vazia.");
			return;
		}

		System.out.println("1 - ECB ou 2 - CBC");
		int opcao = scanner.nextInt();

		if (opcao == 1) {
			decodificaECB(linha);
		} else if (opcao == 2) {
			decodificaCBC(linha);
		} else {
			System.out.println("Modo inválido.");
		}

	}

	private static void decodificaECB(String mensagem) throws Exception {
		String[] partes = mensagem.split("\\s+");
		byte[] dados = new byte[partes.length];
		for (int i = 0; i < partes.length; i++) {
			dados[i] = (byte) Integer.parseInt(partes[i], 16);
		}

// 		byte[] chave = "ABCDE".getBytes(StandardCharsets.UTF_8);
		byte[] chave = {1, 1, 1, 1, 1};

		SecretKeySpec keySpec = new SecretKeySpec(chave, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);

		byte[] textoDecifrado = cipher.doFinal(dados);
		String texto = new String(textoDecifrado, StandardCharsets.UTF_8);

		System.out.println("\nTexto decifrado: " + texto);
	}

	private static void criptografarArquivo() throws Exception {
		File arquivoEntrada = new File("C:/Users/majut/Documents/paracriptografar.pdf");
		// File arquivoEntrada = new File("paracriptografar.pdf");
		File arquivoSaida = new File("saida.bin");

		byte[] chave = "ABCDE".getBytes(StandardCharsets.UTF_8);
		SecretKeySpec keySpec = new SecretKeySpec(chave, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);

		byte[] dadosArquivo = lerArquivo(arquivoEntrada);
		byte[] dadosCriptografados = cipher.doFinal(dadosArquivo);

		salvarArquivo(arquivoSaida, dadosCriptografados);

		System.out.println("Arquivo criptografado com sucesso!");
		System.out.println("Entrada: " + arquivoEntrada.length() + " bytes");
		System.out.println("Saída: " + arquivoSaida.length() + " bytes");
	}

	private static void descriptografarArquivo() throws Exception {
		File arquivoEntrada = new File("saida.bin");
		File arquivoSaida = new File("descriptografado.pdf");

		byte[] chave = "ABCDE".getBytes(StandardCharsets.UTF_8);
		SecretKeySpec keySpec = new SecretKeySpec(chave, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);

		byte[] dadosArquivo = lerArquivo(arquivoEntrada);
		byte[] dadosDecifrados = cipher.doFinal(dadosArquivo);

		salvarArquivo(arquivoSaida, dadosDecifrados);

		System.out.println("Arquivo descriptografado com sucesso!");
		System.out.println("Tamanho: " + arquivoSaida.length() + " bytes");
		System.out.println("Verifique se o PDF abre corretamente.");
	}

	// ===========================
	//  FUNÇÕES AUXILIARES
	// ===========================
	private static byte[] lerArquivo(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		byte[] dados = fis.readAllBytes();
		fis.close();
		return dados;
	}

	private static void salvarArquivo(File file, byte[] dados) throws Exception {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(dados);
		fos.close();
	}

	private static void imprimirHexFormatado(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			System.out.printf("%02X", bytes[i]);
			if ((i + 1) % 8 == 0) {
				System.out.println();
			} else {
				System.out.print(" ");
			}
		}
		if (bytes.length % 8 != 0) {
			System.out.println();
		}
	}
}
