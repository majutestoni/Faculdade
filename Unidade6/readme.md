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
        double vetFinal[] = new double[N];

        ler(vetor, scan, vetAuxiliar);
        mostrar(vetAuxiliar, vetor, vetFinal);
        scan.close();
    }

    private void ler(double vetor[], Scanner scan, int vetAuxiliar[]) {

        int aux = 0;

        for (int i = 0; i < vetor.length; i++) {
            aux = 0;
            System.out.println("digite valores");
            vetor[i] = scan.nextDouble();
            for (int j = 0; j < vetor.length; j++) {
                if (vetor[i] == vetor[j]) {
                    aux++;
                    vetAuxiliar[i] = aux;
                }
            }
        }
    }

    private void mostrar(int vetAuxiliar[], double vetor[], double vetFinal[]) {
        for (int i = 0; i < vetor.length; i++) {
            for (int j = 0; j < vetor.length; j++) {
                if (vetor[i] == vetor[j]) {

                }
            }
            System.out.println("Numero " + vetor[i] + " frequencia " + vetAuxiliar[i]);
        }

    }

    public static void main(String[] args) {
        new Uni06ex08();
    }
}
