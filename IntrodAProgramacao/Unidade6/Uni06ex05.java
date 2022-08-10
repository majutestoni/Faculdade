import java.util.Scanner;

public class Uni06ex05 { // Afinidade de um casal
    private Uni06ex05() {
        System.out.println("Respostas = sim, nao e ind");

        Scanner scan = new Scanner(System.in);

        String moça[] = new String[5];

        respostaMoça(scan, moça);

        scan.close();
    }

    private void respostaMoça(Scanner scan, String moça[]) {

        System.out.println("Gosta de música sertaneja?");
        moça[0] = scan.next();
        System.out.println("Gosta de futebol?");
        moça[1] = scan.next();
        System.out.println("Gosta de seriados?");
        moça[2] = scan.next();
        System.out.println("Gosta de redes sociais?");
        moça[3] = scan.next();
        System.out.println("Gosta da Oktoberfest?");
        moça[4] = scan.next();

        String rapaz[] = new String[5];
        respostaRapaz(scan, rapaz, moça);
    }

    private void respostaRapaz(Scanner scan, String rapaz[], String moça[]) {

        System.out.println("Gosta de música sertaneja?");
        rapaz[0] = scan.next();
        System.out.println("Gosta de futebol?");
        rapaz[1] = scan.next();
        System.out.println("Gosta de seriados?");
        rapaz[2] = scan.next();
        System.out.println("Gosta de redes sociais?");
        rapaz[3] = scan.next();
        System.out.println("Gosta da Oktoberfest?");
        rapaz[4] = scan.next();

        afinidade(moça, rapaz);
    }

    private void afinidade(String moça[], String rapaz[]) {
        int contador = 0;
        for (int i = 0; i < 5; i++) {
            if (moça[i].equals(rapaz[i])) {
                contador = contador + 3;
            } else if (moça[i].equals("ind") || rapaz[i].equals("ind")) {
                contador = contador + 1;
            } else {
                contador = contador - 2;
            }
        }

        if (contador == 15) {
            System.out.println("Casem");
        } else if (contador > 9 && contador < 15) {
            System.out.println("Vocês têm muita coisa em comum!");
        } else if (contador > 4 && contador < 10) {
            System.out.println("Talvez não dê certo :(");
        } else if (contador >= 0 && contador < 5) {
            System.out.println("Vale um encontro.");
        } else if (contador < 0 && contador > -9) {
            System.out.println("Melhor não perder tempo");
        } else if (contador == -10) {
            System.out.println("Vocês se odeiam!");
        }

    }

    public static void main(String[] args) {
        new Uni06ex05();
    }
}
