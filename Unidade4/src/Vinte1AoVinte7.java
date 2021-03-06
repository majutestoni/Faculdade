import java.util.Arrays;
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
        System.out.println("Escolha tres numeros inteiros: ");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        System.out.println("Escolha, conforme um numero, a opção que deseja: ");
        int escolhaOpcao = scan.nextInt();

        switch (escolhaOpcao) {
            case 1:
                int[] numeros = { a, b, c };
                Arrays.sort(numeros);
                for (int numero : numeros)
                    System.out.println(numero);
                break;
            case 2:
                if (a > b && a > c && b > c) {
                    System.out.println(String.format("%s ; %s ; %s", a, b, c));
                } else if (b > a && b > c && c > a) {
                    System.out.println(String.format("%s ; %s ; %s", b, c, a));
                } else if (b > a && b > c && a > c) {
                    System.out.println(String.format("%s ; %s ; %s", b, a, c));
                } else if (c > a && c > b && b > a) {
                    System.out.println(String.format("%s ; %s ; %s", c, b, a));
                } else if (c > a && c > b && a > b) {
                    System.out.println(String.format("%s ; %s ; %s", c, a, b));
                } else if (a > b && a > c && c > b) {
                    System.out.println(String.format("%s ; %s ; %s", a, c, b));
                }
                break;
            case 3:
                if (a > b && a > c && b > c) {
                    System.out.println(String.format("%s ; %s ; %s", b, a, c));
                } else if (b > a && b > c && c > a) {
                    System.out.println(String.format("%s ; %s ; %s", c, b, a));
                } else if (b > a && b > c && a > c) {
                    System.out.println(String.format("%s ; %s ; %s", a, b, c));
                } else if (c > a && c > b && b > a) {
                    System.out.println(String.format("%s ; %s ; %s", b, c, a));
                } else if (c > a && c > b && a > b) {
                    System.out.println(String.format("%s ; %s ; %s", a, c, b));
                } else if (a > b && a > c && c > b) {
                    System.out.println(String.format("%s ; %s ; %s", c, a, b));
                }
                break;
            default:
            System.out.println("invalido");
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

        // Ex26 -> utilizando caracter
        System.out.println("Escolha uma opção: T -> triangulo, Q -> quadrado, R -> Retangulo, C -> Circulo");
        char opcaoAreas = scan.nextLine().charAt(0);
        char convertido = Character.toUpperCase(opcaoAreas);
        switch (convertido) {
            case 'T':
                System.out.println("Digite a base e a altura: ");
                int base = scan.nextInt();
                int alturaT = scan.nextInt();
                System.out.println("A area do triangulo é de: " + ((base * alturaT) / 2));
                break;

            case 'Q':
                System.out.println("Digite o lado do quadrado: ");
                int lado = scan.nextInt();
                System.out.println("A area do quadrado é de: " + (lado * lado));
                break;

            case 'R':
                System.out.println("Digite o lado e a base do retangulo: ");
                int ladoR = scan.nextInt();
                int baser = scan.nextInt();
                System.out.println("A area do retangulo é de: " + (ladoR * baser));
                break;

            case 'C':
                System.out.println("Digite o raio do circulo: ");
                double raio = scan.nextDouble();
                System.out.println("A area do circulo é de: " + ((raio * raio) * 3.14));
                break;

            default:
                break;
        }

        scan.close();
    }

}
