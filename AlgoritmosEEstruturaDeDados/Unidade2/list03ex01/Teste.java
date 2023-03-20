package lista03ex03;

public class Main {

	public static void main(String[] args) {
		PilhaVetor<Integer> teste = new PilhaVetor<Integer>(10);
		
		System.out.println(teste.estaVazia());
		teste.push(10);
		teste.push(5);
		teste.push(9);
		teste.push(7);
		System.out.println(teste.toString());
		System.out.println(teste.estaVazia());
		System.out.println(teste.pop());
		System.out.println(teste.toString());
		System.out.println(teste.peek());
		teste.push(4);
		teste.push(2);
		teste.push(1);
		teste.push(8);
		teste.push(9);
		teste.push(3);
		System.out.println(teste.toString());
		teste.liberar();
		System.out.println(teste.toString());
		System.out.println(teste.estaVazia());
		
		System.out.println("MUDA LISTA");
		PilhaLista<Integer> lista = new PilhaLista<Integer>();
		
		System.out.println(lista.estaVazia());
		teste.push(10);
		teste.push(5);
		teste.push(9);
		teste.push(7);
		System.out.println(teste.toString());
		System.out.println(teste.estaVazia());
		System.out.println(teste.pop());
		System.out.println(teste.toString());
		System.out.println(teste.peek());
		


	}
}
