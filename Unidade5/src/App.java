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
        double numerador = 3;
        double denominador = 2;
        double numPar = 4;
        double termos = 0;
        for (int i = 1; i <= 20; i++) {
            termos = numerador / denominador;
            numerador += 2;
            denominador = denominador + numPar;
            numPar += 2;
        }

        System.out.println("A resposta é:" + termos);

        // Ex05
        // padrao 8 10 16 18 32 64 -> n4 = n1 + n2, n3 = n4 - 2
        System.out.println("Digite quantas vezes deve repetir ");
        int vezes = scan.nextInt();
        int padrao2 = 6;
        int padrao1 = 6;
        if (vezes > 2) {
            for (int i = 0; i <= vezes; i++) {
                padrao2 += padrao2 - 2;
                padrao1 = padrao2 - 2;
                System.out.println(padrao1);
                System.out.println(padrao2);
            }
        } else {
            System.out.println("valor invalido");
        }

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

        // Ex07
        System.out.println("Escreva o valor de n:");
        int n = scan.nextInt();
        int i = 0;
        int nMenor = 0;
        int nMaior = 0;
        while (i < n) {
            System.out.println("Digite numeros: ");
            int numerosN = scan.nextInt();
            if (numerosN < nMenor) {
                nMenor = numerosN;
            }

            if (numerosN > nMaior) {
                nMaior = numerosN;
            }
            i++;
        }
        System.out.println("O numero menor é " + nMenor + " o numero maior é " + nMaior);

        // Ex08
        System.out.println("Escreva o valor de p:");
        int p = scan.nextInt();
        int j = 0;
        int pMenor = 0;
        int contadorP = 0;
        while (j < p) {
            System.out.println("Digite numeros: ");
            int numerosP = scan.nextInt();
            if (numerosP < pMenor && numerosP < 0) {
                pMenor = numerosP;
            }
            contadorP = contadorP + numerosP;
            j++;
        }
        int mediaP = contadorP / p;
        System.out.println("O numero negativo é " + pMenor + " a media é " + mediaP);
    }
}
