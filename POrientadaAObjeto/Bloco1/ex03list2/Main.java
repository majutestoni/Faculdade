package ex03list2;

public class Main {
	public static void main(String[] args) {
		String[] teste = new String[3];
		
		teste[0] = "João Aparecido da Silva";
		teste[1] = "Dores";
		teste[2] = "Blumenau";
		
		System.out.println(NameGenerator.generateStarWarsName(teste));
	}
}
