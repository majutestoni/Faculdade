import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("deu certo");

        // int valor1;
        // int valor2;
        // Ex01
        /*
         * System.out.println("Leia valor 1");
         * valor1 = scan.nextInt();
         * System.out.println("Digite valor 2");
         * valor2 = scan.nextInt();
         * 
         * System.out.println(valor1 + valor2);
         * 
         * // Ex02
         * System.out.println("Valor3");
         * double valor3 = scan.nextDouble();
         * System.out.println("Valor4");
         * double valor4 = scan.nextDouble();
         * 
         * System.out.println(valor3 * valor4);
         * 
         * // Ex03
         * System.out.println("Digite o valor do raio:");
         * double raio = scan.nextDouble();
         * double area = (raio * raio) * 3.14;
         * System.out.println(area);
         */

        // Ex04
        System.out.println("Digite a nota A:");
        double notaA = scan.nextDouble();
        System.out.println("Digite a nota B:");
        double notaB = scan.nextDouble();
        double media = ((notaA * 3.5) + (notaB * 7.5)) / 11;

        if (media >= 7.0) {
            System.out.println("Parabens aprovado com " + media);
        } else {
            System.out.println("Reprovado " + media);
        }

        // Ex05
        System.out.println(" valorA: ");
        int valorA = scan.nextInt();
        System.out.println("ValorB: ");
        int valorB = scan.nextInt();
        System.out.println("valorC: ");
        int valorC = scan.nextInt();
        System.out.println("valorD: ");
        int valorD = scan.nextInt();

        double diferenca = (valorA * valorB) - (valorC * valorD);
        System.out.println(diferenca);

        // Ex06
        System.out.println("numero: ");
        double numeroF = scan.nextDouble();
        System.out.println("horas: ");
        double horasF = scan.nextDouble();
        System.out.println("valor horas: ");
        double valorHF = scan.nextDouble();

        double salario = horasF * valorHF;
        System.out.println("O salario do funcionario: " + numeroF + " é de " + salario);

        scan.close();
    }

}
