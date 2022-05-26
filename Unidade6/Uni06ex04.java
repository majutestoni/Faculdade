import java.util.Scanner;

public class Uni06ex04 {
    private Uni06ex04() {
        Scanner scan = new Scanner(System.in);
        int vetor1[] = new int[3];
        int vetor2[] = new int[3];

        lerValores(scan, vetor1, vetor2);
        scan.close();
    }

    private void lerValores(Scanner scan, int vetor1[], int vetor2[]) {
        for (int i = 0; i < 3; i++) {
            System.out.println("ler valores 1");
            vetor1[i] = scan.nextInt();
            System.out.println("ler valores 2");
            vetor2[i] = scan.nextInt();

        }
        somaValores(vetor1, vetor2);
    }

    private void somaValores(int vetor1[], int vetor2[]) {
        int vetor3[] = new int[3];

        for (int i = 0; i < 3; i++) {
            vetor3[i] = vetor1[i] + vetor2[i];
        }
        apresentar(vetor3);
    }

    private void apresentar(int vetor3[]) {
        for (int i = 0; i < 3; i++) {
            System.out.println(vetor3[i]);
        }
    }

    public static void main(String[] args) {
        new Uni06ex04();
    }
}
