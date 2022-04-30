import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello, World!");

        // Ex01
        for (int i = 0; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.println("O numero é par " + i);
            } else {
                System.out.println("O numero é impar " + i);
            }
        }

        // ex 02
        int somaPar = 0;
        int somaImpar = 0;

        for (int i = 1; i <= 5; i++) {// alterar para 100
            if (i % 2 == 0) {
                somaPar += i;
            } else {
                somaImpar += i;
            }
        }
        System.out.println("par " + somaPar);
        System.out.println("impar " + somaImpar);

        // ex03
        double somaTermos = 0;
        double fracao;
        for (int i = 1; i <= 100; i++) {
            fracao = 1.0 / i;
            somaTermos += fracao;
        }
        System.out.println("A soma dos termos é " + somaTermos);

        // Ex04 -> Verificar a questão do denominador
        double numrador = 1;
        double denominador = 0;
        double respostaS = 0;
        double termos;
        for (int i = 1; i <= 20; i++) {
            numrador += 2;
            denominador += 2 + denominador;
            termos = numrador / denominador;
            respostaS += termos;
            System.out.println(numrador);
            System.out.println(denominador);
        }

        System.out.println(respostaS);

        // EX06
        int altura;
        int contador = 0;
        for (int i = 1; i <= 20; i++) {
            System.out.println("Digite sua altura: ");
            altura = scan.nextInt();

            contador += altura;
        }
        double mediaAltura = contador / 20;
        System.out.println(mediaAltura);

        scan.close();
    }
}
