package list01ex01;

import java.util.Arrays;

public class main {

	public static void main(String[] args) {
		List teste = new List();

		System.out.println(teste.listaVazia());
		teste.inserir(10);
		teste.inserir(5);
		teste.inserir(1);
		teste.inserir(9);
		teste.inserir(7);
		System.out.println(Arrays.toString(teste.exibir()));
		System.out.println(teste.listaVazia());

		System.out.println(teste.buscar(5));
		teste.retirar(10);
		System.out.println(Arrays.toString(teste.exibir()));
		
		teste.inserir(11);
		teste.inserir(6);
		teste.inserir(4);
		teste.inserir(3);
		teste.inserir(2);
		System.out.println(Arrays.toString(teste.exibir()));
	}

}
