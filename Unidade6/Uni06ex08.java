import java.util.Arrays;
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

        ler(vetor, scan, vetAuxiliar);
        mostrar(vetAuxiliar, vetor);
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

    private void mostrar(int vetAuxiliar[], double vetor[]) {
        int contador = 0;
        double vetFinal[] = new double[vetor.length];
        int vetAuxiliar2[] = new int[vetAuxiliar.length];

        // reordenar
        for (int i = 0; i < vetor.length; i++) {
            boolean repetidos = false;
            for (int j = 0; j < contador; j++) {
                if (vetor[i] == vetFinal[j]) {
                    repetidos = true;
                    break;
                }
            }
            if (!repetidos) {
                vetFinal[contador++] = vetor[i];
                vetAuxiliar2[contador++] = vetAuxiliar[i];
            }

        }
        vetFinal = Arrays.copyOf(vetor, contador);
        vetAuxiliar2 = Arrays.copyOf(vetAuxiliar, contador);
        // apresentar
        for (int i = 0; i < vetFinal.length; i++) {
            System.out.println("Numero " + vetFinal[i] + " frequencia " + vetAuxiliar2[i]);
        }
    }

    public static void main(String[] args) {
        new Uni06ex08();
    }
}
