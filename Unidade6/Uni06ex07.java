
import java.util.Scanner;

public class Uni06ex07 {
    private Uni06ex07() {
        Scanner scan = new Scanner(System.in);

        System.out.println("leia N");
        int N = scan.nextInt();

        if (N >= 20) {
            N = 20;
        }

        int vetor[] = new int[N];

        ler(vetor, scan);
        scan.close();
    }

    private void ler(int vetor[], Scanner scan) {

        for (int i = 0; i < vetor.length; i++) {
            System.out.println("Digite valor");
            vetor[i] = scan.nextInt();
            for (int j = 0; j < vetor.length; j++) {
                if (vetor[i] == vetor[j]) {
                    System.out.println("Valor ja existente");
                    System.out.println("Digite outro valor");
                    vetor[i] = scan.nextInt();
                }
            }
        }
        reordenar(vetor);
    }

    private void reordenar(int vetor[]) {
        int aux = 0;

        for (int i = 0; i < vetor.length; i++) {
            for (int j = 0; j < vetor.length - 1; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                }
            }
        }

        reorganizado(vetor);
    }

    private void reorganizado(int vetor[]) {
        for (int e = 0; e < vetor.length; e++) {
            System.out.print(" " + vetor[e]);
        }
    }

    public static void main(String[] args) {
        new Uni06ex07();
    }
}
