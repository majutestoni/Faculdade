import java.util.Scanner;

public class Uni06ex08 {
    private Uni06ex08() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Informe N");
        int N = scan.nextInt();

        if (N >= 20) {
            N = 20;
        }

        double vetor[] = new double[N];
        int vetAuxiliar[] = new int[N];
        boolean auxiliar[] = new boolean[N];
        for (int i = 0; i < auxiliar.length; i++) {
            auxiliar[i] = true;
        }

        ler(vetor, scan);
        mostrar(vetAuxiliar, vetor, auxiliar);
        apresentar(vetor, vetAuxiliar, auxiliar);
        scan.close();
    }

    private void ler(double vetor[], Scanner scan) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.println("digite valores");
            vetor[i] = scan.nextDouble();
        }
    }

    private void mostrar(int vetAuxiliar[], double vetor[], boolean auxiliar[]) {
        int contador = 0;

        // reordenar
        for (int i = 0; i < vetor.length; i++) {
            for (int j = 0; j < vetor.length; j++) {
                if (vetor[i] == vetor[j]) {
                    vetAuxiliar[i]++;
                    if (i < j) {
                        auxiliar[i] = false;
                    }
                }
            }

        }
    }

    private void apresentar(double vetor[], int vetAuxiliar[], boolean auxiliar[]) {
        for (int i = 0; i < vetAuxiliar.length; i++) {
            if (auxiliar[i] == true) {
                System.out.println("numer " + vetor[i] + " frequencia " + vetAuxiliar[i]);
            }
        }
    }

    public static void main(String[] args) {
        new Uni06ex08();
    }
}
