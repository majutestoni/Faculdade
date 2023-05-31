package Trabalho;

import java.io.IOException;
import java.util.Scanner;

import Trabalho.Calculadoras.CalculadoraDinamica;
import Trabalho.Calculadoras.CalculadoraVetor;

public class Main {

	public static void main(String[] args) throws IOException {

		try (Scanner scanner = new Scanner(System.in)) {
			
			while (true) {
				System.out.println("======= Bem vindo a calculadora ========");
				System.out.println("======    de notação pós-fixa    =======");
				System.out.println();
				System.out.println("==== Que tipo de pilha deseja usar? ====");
				System.out.println();
				System.out.println("1- Vetor   2- Dinâmica");
				
				int opcao = scanner.nextInt();
				if (opcao != 1 && opcao != 2) {
					System.out.println("Opção Inválida");
					System.out.println();
					main(args);
				}

				scanner.nextLine();
				System.out.println("==== Digite a expressão ====");
				String entrada = scanner.nextLine();
				System.out.println();				
				String[] vetor = entrada.split("\\s");			

				if (opcao == 1) {
					CalculadoraVetor estatico = new CalculadoraVetor(entrada);
					System.out.println("RESULTADO: " + estatico.resultado());
				} else {
					CalculadoraDinamica dinamica = new CalculadoraDinamica(vetor);
					System.out.println("RESULTADO: " + dinamica.resultado());
				}

				System.out.println();
				System.out.println("==================================================");
				System.out.println();
			}
		}
	}
}
