import java.util.Scanner;

public class ex27ao34 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 28
        System.out.println("mais um voto: s (SIM) / n (NÃO)");
        String votoRadio = scan.next();
        int codigoBanda;
        int codigoBanda1 = 0, codigoBanda2 = 0, codigoBanda3 = 0, codigoBanda4 = 0;
        int contadorTotalBandas = 0;
        while (votoRadio.equals("s")) {
            System.out.println("Qual codígo da sua banda favorita");
            codigoBanda = scan.nextInt();
            contadorTotalBandas = contadorTotalBandas + 1;
            switch (codigoBanda) {
                case 1:
                    codigoBanda1 = codigoBanda1 + 1;
                    break;
                case 2:
                    codigoBanda2 = codigoBanda2 + 1;
                    break;
                case 3:
                    codigoBanda3 = codigoBanda3 + 1;
                    break;
                case 4:
                    codigoBanda4 = codigoBanda4 + 1;
                    break;
                default:
                    System.out.println("invalido");
                    break;
            }
            System.out.println("mais um voto: s (SIM) / n (NÃO)");
            votoRadio = scan.next();
        }
        System.out.println(codigoBanda1);
        System.out.println(codigoBanda2);
        System.out.println(codigoBanda3);
        System.out.println(codigoBanda4);

        System.out.println("percentual banda 1" + codigoBanda1 / contadorTotalBandas);
        System.out.println("percentual banda 2" + codigoBanda2 / contadorTotalBandas);
        System.out.println("percentual banda 3" + codigoBanda3 / contadorTotalBandas);
        System.out.println("percentual banda 4" + codigoBanda4 / contadorTotalBandas);

        if (codigoBanda1 > codigoBanda2 && codigoBanda1 > codigoBanda3 && codigoBanda1 > codigoBanda4) {
            System.out.println("banda vencedora: 1");
        } else if (codigoBanda2 > codigoBanda3 && codigoBanda2 > codigoBanda4) {
            System.out.println("banda vencedora: 2");
        } else if (codigoBanda3 > codigoBanda4) {
            System.out.println("banda vencedora: 3");
        } else {
            System.out.println("banda vencedora: 4");
        }

        // 31
        System.out.println("Digite seu número inteiro");
        int numeroInteiro = scan.nextInt();
        while (numeroInteiro % 2 == 0) {
            numeroInteiro = numeroInteiro / 2;
            System.out.println(numeroInteiro);
            if (numeroInteiro % 2 != 0) {
                while (numeroInteiro % 3 == 0) {
                    numeroInteiro = numeroInteiro / 3;
                    System.out.println(numeroInteiro);
                    if (numeroInteiro % 3 != 0) {
                        while (numeroInteiro % 5 == 0) {
                            numeroInteiro = numeroInteiro / 5;
                            System.out.println(numeroInteiro);
                        }
                    }
                }
            }
        }

        scan.close();
    }
}
