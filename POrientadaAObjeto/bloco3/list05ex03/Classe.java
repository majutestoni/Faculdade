package bloco3.list05ex03;

import java.io.FileInputStream;
import java.nio.file.Path;

public class Classe {

	public static void main(String[] args) {

	}

	public int[] histograma(Path p) {

		int vetor[] = new int[256];
		try (FileInputStream fileInputStream = new FileInputStream(p.toString())) {
			int lido = fileInputStream.read();
			while (lido != -1) {
				vetor[lido]++;
				lido = fileInputStream.read();
			}

		} catch (Exception e) {
		}
		return vetor;
	}

}
