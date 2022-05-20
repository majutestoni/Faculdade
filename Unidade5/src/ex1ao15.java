import java.util.Scanner;

public class ex1ao15 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

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
                teste = teste + nomeT + ", ";
            }
            if (idadeT > 20) {
                contadorT = contadorT + 1;
            }
        }
        System.out.println("Alunos com 18: " + teste);
        System.out.println("A quantidade de alunos que tem 20 é " + contadorT);

        // 10
        int r = 0, s = 0, v = 0;
        int contador10 = 0;
        while (contador10 < 10) {
            s++;
            if (s == 100) {
                r++;
                s = 0;
            }
            v = 0;
            v = r + s;
            int d = (int) Math.pow(v, 2);
            String aString = Integer.toString(r);
            String bString = Integer.toString(s);
            String dString = Integer.toString(d);

            if (r > 0 && s < 10) {

                if (dString.equals(aString + "0" + bString)) {
                    contador10++;
                    System.out.println(dString);
                    System.out.println(contador10);
                }
            } else {
                if (dString.equals(aString + bString)) {
                    contador10++;
                    System.out.println(dString);
                    System.out.println(contador10);
                }
            }
        }

        // Ex11
        int biscoitosQuebrados = 1;
        int biscoitosQuebradosTotal = 1;
        int biscoitosQuebradosUltimaHora = 1;
        for (int tempo = 1; tempo <= 15; tempo++) { // considerar um tempo a mais
            biscoitosQuebrados = biscoitosQuebradosUltimaHora * 3;
            biscoitosQuebradosUltimaHora = biscoitosQuebrados;
            biscoitosQuebradosTotal = biscoitosQuebradosTotal + biscoitosQuebrados;

        }
        System.out.println(biscoitosQuebradosTotal);

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

        // Ex13
        System.out.println("Digite o valor do tanque cheio(em litros)");
        int tanqueCheio = scan.nextInt();
        int tanqueParada = 0, totalDeReabastecimento = 0;
        int tanqueAbastecidoParada = 0;
        int kmPercorrido = 0;
        int kmMedia = 0, kmMediaTotal = 0;

        System.out.println("Digite quantas paradas ocorreram :");
        int paradas = scan.nextInt();
        for (int d = 0; d < paradas; d++) {
            System.out.println("Informe em quanto está o tanque");
            tanqueParada = scan.nextInt();
            tanqueAbastecidoParada = tanqueCheio - tanqueParada;
            totalDeReabastecimento = totalDeReabastecimento + tanqueAbastecidoParada;

            System.out.println("Informe quantos KM foi percorrido até a parada");
            kmPercorrido = scan.nextInt();
            kmMedia = kmPercorrido / tanqueAbastecidoParada;
            kmMediaTotal = kmMediaTotal + kmMedia;

        }
        System.out.println("A km média total é de " + kmMediaTotal);
        System.out.println("E o total abastecido " + totalDeReabastecimento);

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
        scan.close();
    }
}
