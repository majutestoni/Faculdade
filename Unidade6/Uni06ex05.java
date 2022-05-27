import java.util.Scanner;

public class Uni06ex05 {
    private Uni06ex05() {
        Scanner scan = new Scanner(System.in);

        String moça[] = new String[5];
        String rapaz[] = new String[5];

        respostaMoça(scan, moça);
        respostaRapaz(scan, rapaz);
        scan.close();
    }

    private void respostaMoça(Scanner scan, String moça[]) {

        System.out.println("Gosta de música sertaneja?");
        System.out.println("Gosta de futebol?");
        System.out.println("Gosta de seriados?");
        System.out.println("Gosta de redes sociais?");
        System.out.println("Gosta da Oktoberfest?");

        for (int i = 0; i < moça.length; i++) {
            moça[i] = scan.next();

            switch (moça[i]) {
                case "sim":
                    
                    break;
                case "nao":
                    
                    break;
                case "ind":
                    
                    break;            
                default:
                System.out.println("não pode");
                    break;
            }

        }
    }

    private void respostaRapaz(Scanner scan, String rapaz[]) {

        System.out.println("Gosta de música sertaneja?");
        System.out.println("Gosta de futebol?");
        System.out.println("Gosta de seriados?");
        System.out.println("Gosta de redes sociais?");
        System.out.println("Gosta da Oktoberfest?");
    }

    public static void main(String[] args) {
        new Uni06ex05();
    }
}
