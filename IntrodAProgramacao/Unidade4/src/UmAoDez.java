import java.util.Scanner;

public class UmAoDez {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Ex01 -> Quanto um funcionario ganha com horas extras
        System.out.println("Digite as horas trabalhadas pelo funcionario: ");
        int horas = scan.nextInt();
        System.out.println("Digite o valor pago por horas: ");
        double valorHora = scan.nextDouble();

        double salarioTotal = valorHora * horas;

        if (horas > 160) {
            double salarioExtra = (horas - 160) * (valorHora / 2);
            salarioTotal = salarioTotal + salarioExtra;
        }

        System.out.println("O salario é de: " + salarioTotal);

        // Ex02 -> numero impar ou par
        System.out.println("Digite um numero inteiro: ");
        int numero = scan.nextInt();

        if (numero % 2 == 0) {
            System.out.println("O numero é par");
        } else {
            System.out.println("O numero é impar");
        }

        // Ex03 -> Qual valor é maior
        System.out.println("Digite o numero1:");
        int numero1 = scan.nextInt();
        System.out.println("Digite o numero2:");
        int numero2 = scan.nextInt();

        if (numero1 > numero2) {
            System.out.println("O numero1 é maior que o numero2");
        } else {
            System.out.println("O numero2 é maior que o numero1");
        }

        // Ex04 -. veririfcar se possui ponto flutuante
        System.out.println("Digite um numero (ponto flutuante)");
        double pontoFlutuante = scan.nextDouble();

        if (pontoFlutuante != (int) pontoFlutuante) {
            System.out.println("possui ponto flutuante");
        } else {
            System.out.println("Não possui ponto flutuante");
        }

        // EX05 -> Cor é azul: sim ou não
        System.out.println("A cor é azul? ");
        String resposta = scan.nextLine();
        System.out.println(resposta);

        if (resposta.equals("sim")) { // posso usar o equals?
            System.out.println("A resposssta é sim");
        } else {
            System.out.println("A resposta é não");
        }

        // Ex6 -> Identificar genero e converter para letra maiuscula
        System.out.println("Digite uma letra para informar o genero:");
        char teste2 = scan.nextLine().charAt(0);

        String genero2 = Character.toString(teste2).toUpperCase();

        if (genero2.equals("F")) {
            System.out.println("Genero feminino");
        } else if (genero2.equals("M")) {
            System.out.println("Genero masculino");
        } else if (genero2.equals("I")) {
            System.out.println("Genero não informado");
        } else {
            System.out.println("Entrada incorreta");
        }

        // Ex07 -> valor peso da carta
        System.out.println("Informe o peso da carta(gramas): ");
        double pesoCarta = scan.nextDouble();

        double valorPagar = 0;

        if (pesoCarta <= 50) {
            valorPagar = 0.45;

        } else {
            double pesoExcedido = pesoCarta - 0.50;
            double qtAdicional = (pesoExcedido / 20) + 1;
            valorPagar = 0.45 + (0.45 * qtAdicional);
        }
        System.out.println("O valor que vai pagar: " + valorPagar);

        // ex08 -> letra
        char letra;
        System.out.println("Digite uma letra");
        letra = scan.next().charAt(0);
        
        if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
            System.out.println("é uma vogal");
        } else {
            System.out.println("é uma consoante");
        }

        // Ex09 -> Dois valores
        System.out.println("Informe o valor 1");
        int inteiro1 = scan.nextInt();
        System.out.println("Informe o valor 2");
        int inteiro2 = scan.nextInt();

        int multiplos = inteiro1 % inteiro2;
        if (multiplos == 0) { // falta ou caso, exemplo, 2/4
            System.out.println("São multiplos");
        } else {
            System.out.println("Não são multiplos");
        }

        // Ex 10 -> tres filhos -> Qual é o caçula
        System.out.println("Digite a idade dos 3 filhos");
        int idadeMarquinhos = scan.nextInt();
        int idadeZezinho = scan.nextInt();
        int idadeLuluzinha = scan.nextInt();

        if ((idadeMarquinhos < idadeZezinho) && (idadeMarquinhos < idadeLuluzinha)) {
            System.out.println("caçula: Marquinhos");
        } else if (idadeZezinho < idadeLuluzinha) {
            System.out.println("caçula: Zezinho");
        } else {
            System.out.println("caçula: Luluzinha");
        }

        scan.close();
    }

}
