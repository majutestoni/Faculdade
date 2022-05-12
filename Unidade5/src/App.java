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

        // Ex07
        System.out.println("Escreva o valor de n:");
        int n = scan.nextInt();
        int nMenor = 0;
        int nMaior = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("Digite numeros: ");
            int numerosN = scan.nextInt();
            if (numerosN < nMenor) {
                nMenor = numerosN;
            }

            if (numerosN > nMaior) {
                nMaior = numerosN;
            }
        }
        System.out.println("O numero menor é " + nMenor + " o numero maior é " + nMaior);

        // Ex08
        System.out.println("Escreva o valor de p:");
        int p = scan.nextInt();
        int pMenor = 0;
        int contadorP = 0;
        for (int j = 0; j < p; j++) {
            System.out.println("Digite numeros: ");
            int numerosP = scan.nextInt();
            if (numerosP < pMenor && numerosP < 0) {
                pMenor = numerosP;
            }
            contadorP = contadorP + numerosP;
        }
        int mediaP = contadorP / p;
        System.out.println("O numero negativo é " + pMenor + " a media é " + mediaP);

        // Ex09
        System.out.println("Informe t: ");
        int t = scan.nextInt();
        String teste = "";
        int contadorT = 0;
        for (int k = 0; k < t; k++) {
            System.out.println("Digite o nome: ");
            String nomeT = scan.next();
            System.out.println("Digite a idade");
            int idadeT = scan.nextInt();

            if (idadeT == 18) {
                teste = teste + ", " + nomeT;
            }
            if (idadeT > 20) {
                contadorT = contadorT + 1;
            }
        }
        System.out.println("Alunos com 18: " + teste);
        System.out.println("A quantidade de alunos que tem 20 é " + contadorT);

        // Ex12 -> Triangulo de floyd
        System.out.println("Digite o w");
        int w = scan.nextInt();
        int a, b;
        int c = 1;
        for (a = 1; a <= w; a++) {
            for (b = 1; b <= a; b++) {
                System.out.print(c + " ");
                c++;
            }
            System.out.println();
        }

        // Ex14
        double lucro1 = 0;
        double lucro2 = 0;
        double lucro3 = 0;
        for (int e = 1; e <= 5; e++) {
            System.out.println("Escreva o preço de compra: ");
            double PC = scan.nextDouble();
            System.out.println("Digite o preço de venda");
            double PV = scan.nextDouble();

            double lucro = (PV - PC) / PC * 100;
            if (lucro < 10) {
                lucro1 = lucro1 + 1;
            }
            if (lucro <= 10 && lucro <= 20) {
                lucro2 = lucro2 + 1;
            }
            if (lucro > 20) {
                lucro3 = lucro3 + 1;
            }
        }
        System.out.println("Mercadorias com menos de 10% de lucro: " + lucro1);
        System.out.println("Mercadorias com mais de 10% e menos de 20% de lucro: " + lucro2);
        System.out.println("Mercadorias com mais de 20% de lucro: " + lucro3);

        // Ex15 -> While
        String nome = "";
        System.out.println("Digite o nome do aluno");
        nome = scan.next();
        while (!nome.equals("fim")) {
            System.out.println("nota1");
            int nota1 = scan.nextInt();
            System.out.println("nota2");
            int nota2 = scan.nextInt();

            int media = (nota1 + nota2) / 2;
            System.out.println(media);
            System.out.println("Digite o nome do aluno");
            nome = scan.next();
        }

        // Ex16
        System.out.println("Digite a altura");
        double altura16 = scan.nextInt();
        char sexo;
        double alturaMulheres = 0;
        double alturaTotal = 0;
        int contadorAltura = 0;
        int contadorAlturaMulheres = 0;
        while (altura16 != 0) {
            contadorAltura = contadorAltura + 1;
            System.out.println("Digite m para masculino e f para feminino:");
            sexo = scan.next().charAt(0);
            alturaTotal = alturaTotal + altura16;
            if (sexo == 'f' || sexo == 'F') {
                alturaMulheres = alturaMulheres + altura16;
                contadorAlturaMulheres = contadorAlturaMulheres + 1;
            }
            System.out.println("Digite a altura");
            altura = scan.nextInt();
        }

        System.out.println("Altura media das mulheres é " + (alturaMulheres / contadorAlturaMulheres));
        System.out.println("A altura media do grupo é de: " + (alturaTotal / contadorAltura));

        // 17
        System.out.println("Digite o numero de inscrição: ");
        int inscricao = scan.nextInt();
        double alturaAtleta, atletasTotal = 0;
        int contadorAtleta = 0;
        double atletaAlto = 0;
        double atletaBaixo = 0;
        int inscricaoAlto = 0;
        int inscricaoBaixo = 0;
        while (inscricao != 0) {
            contadorAtleta = contadorAtleta + 1;
            System.out.println("Qual a altura");
            alturaAtleta = scan.nextInt();
            atletasTotal = atletasTotal + alturaAtleta;

            if (alturaAtleta > atletaAlto) {
                atletaAlto = alturaAtleta;
                inscricaoAlto = inscricao;
            }
            if (alturaAtleta < atletaBaixo) {
                atletaBaixo = alturaAtleta;
                inscricaoBaixo = inscricao;
            }

            System.out.println("Digite o numero de inscrição: ");
            inscricao = scan.nextInt();
        }
        System.out.println("Altura atleta alto " + atletaAlto + " inscrção " + inscricaoAlto);
        System.out.println("Altura atleta baixo " + atletaBaixo + " inscrção " + inscricaoBaixo);
        System.out.println("A altura media do grupo: " + (atletasTotal / contadorAtleta));

        scan.close();
    }
}
