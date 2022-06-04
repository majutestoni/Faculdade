import java.util.Scanner;

public class Uni06ex09 {
    private Uni06ex09() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Informe N");
        int N = scan.nextInt();

        if (N >= 20) {
            N = 20;
        }

        double vetor[] = new double[N];

        ler(vetor, scan);

        scan.close();
    }

    private void ler(double vetor[], Scanner scan) {

        for (int i = 0; i < vetor.length; i++) {
            System.out.println("digite valores");
            vetor[i] = scan.nextDouble();
        }


        
    }

    public static void main(String[] args) {
        new Uni06ex09();
    }
}
