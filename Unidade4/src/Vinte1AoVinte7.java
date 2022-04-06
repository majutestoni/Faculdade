import java.util.Scanner;

public class Vinte1AoVinte7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Ex21 -> IMC
        System.out.println("Digite a massa em kilogramas");
        double massa = scan.nextDouble();
        System.out.println("Digite a altura");
        double altura = scan.nextDouble();

        double imc = massa / (altura * altura);

        if (imc < 18.5) {
            System.out.println("magreza");
        } else if (imc >= 18.5 && imc < 25) {
            System.out.println("saudavel");
        } else if (imc >= 25 && imc < 30) {
            System.out.println("sobrepeso");
        } else if (imc >= 30 && imc < 35) {
            System.out.println("obesidade grau I");
        } else if (imc >= 35 && imc < 40) {
            System.out.println("obesidade grau II");
        } else if (imc >= 40) {
            System.out.println("obesidade grau III");
        }

        // 22 -> switch
        System.out.println("Digite o numero do curso que voce selecionou: 1, 2 ou 3");
        int numeroDoCurso = scan.nextInt();

        switch (numeroDoCurso) {
            case 1:
                System.out.println("Bacharel em BCC");
                break;
            case 2:
                System.out.println("Licenciado em Computação");
                break;
            case 3:
                System.out.println("Bacharel em Sistemas da Informação");
                break;
            default:
                System.out.println("Valor invalido");
                break;
        }

        // 23 -> mes
        System.out.println("escreva o numero do mes");
        int numeroMes = scan.nextInt();

        switch (numeroMes) {
            case 1:
                System.out.println("Janeiro");
                break;
            case 2:
                System.out.println("Fevereiro");
                break;
            case 3:
                System.out.println("Março");
                break;
            case 4:
                System.out.println("Abril");
                break;
            case 5:
                System.out.println("Maio");
                break;
            case 6:
                System.out.println("Junho");
                break;
            case 7:
                System.out.println("Julho");
                break;
            case 8:
                System.out.println("Agosto");
                break;
            case 9:
                System.out.println("Setembro");
                break;
            case 10:
                System.out.println("Outubro");
                break;
            case 11:
                System.out.println("Novembro");
                break;
            case 12:
                System.out.println("Dezembro");
                break;

            default:
                System.out.println("Valor invalido");
                break;
        }

        // 24 -> Escolha para apresentar uma ordem
        System.out.println("Digite 1, 2 ou 3 para receber os valores em ordem");
        int escolhaOpcao = scan.nextInt();

        switch (escolhaOpcao) {
            case 1:
                System.out.println("1, 2, 3");
                break;
            case 2:
                System.out.println("3, 2, 1");
                break;
            case 3:
                System.out.println("2, 3, 1");
                break;
            default:
                break;
        }

        // 25 -> Opção abre um menu de opções
        System.out.println("Digite um valor 1");
        int valor1 = scan.nextInt();
        System.out.println("Digite um valor 2");
        int valor2 = scan.nextInt();
        System.out.println("Agora, no menu escolha uma das opções: 1, 2, 3 e 4");
        int menuOpcoes = scan.nextInt();

        switch (menuOpcoes) {
            case 1:
                System.out.println("A soma é de: " + (valor1 + valor2));
                break;
            case 2:
                System.out.println("A diferença e de: " + (valor1 - valor2));
                break;
            case 3:
                System.out.println("O produto é de: " + (valor1 * valor2));
                break;
            case 4:
                if (valor2 != 0) {
                    System.out.println("A divisão é de: " + (valor1 / valor2));
                } else {
                    System.out.println("valor 2 invalido");
                }
                break;
            default:
                break;
        }

        
        scan.close();
    }

}
