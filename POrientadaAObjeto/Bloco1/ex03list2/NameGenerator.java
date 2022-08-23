package ex03list2;

public class NameGenerator {
	public static String generateStarWarsName(String[] entrada) {
		String primeiroNome = entrada[0].substring(0, 2);
		String sobrenomeMae = entrada[1].substring(0,2);
		String letrasCidade = entrada[2].substring(0,3);
		
		String[] nomeSeparado = entrada[0].split(" ");
		int tamanho = nomeSeparado.length - 1;
		String sobrenomeLetras = nomeSeparado[tamanho].substring(0,3);
		
		return sobrenomeLetras + primeiroNome + " " + sobrenomeMae + letrasCidade ;
	}
}
