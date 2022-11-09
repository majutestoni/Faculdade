package bloco3.list05ex02;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Abc {

	public static void main(String[] args) {
		FileReader entrada;
		FileWriter saida;
		int chave = 0;
		try {
			entrada = new FileReader("Nomearquivo.txt");
			saida = new FileWriter("Nomearquivo.txt-saida");
			int lido = entrada.read();
			int convertido;
			while (lido != -1) {
				convertido = converter(lido, chave);
				saida.write(convertido);
				lido = entrada.read();
			}

			entrada.close();
			saida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static int converter(int lido, int chave) {
		int valor = lido + chave;
		if(valor > 255) {
			valor = valor - 256;
		}
		return valor;
	}
	
}
