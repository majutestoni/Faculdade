import java.util.Scanner;

public class Uni06ex03 {
    private Uni06ex03() {
        Scanner scan = new Scanner(System.in);
        int vetor[] = new int[3]; // alterar para 12

        lerValores(scan, vetor);
        scan.close();
    }

    private void lerValores(Scanner scan, int vet[]) {
        for (int i = 0; i < vet.length; i++) {
            System.out.println("leia");
            vet[i] = scan.nextInt();
        }
        aumentoValores(vet);
    }

    private void aumentoValores(int vetor[]) {
        double aumento = 0;
        double novoVetor[] = new double[3];
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] % 2 == 0) {
                aumento = vetor[i] + (vetor[i] * 0.02);
            } else {
                aumento = vetor[i] + (vetor[i] * 0.05);
            }

            novoVetor[i] = aumento;

        }
        apresentar(novoVetor);
    }

    private void apresentar(double vet[]) {
        for (int i = 0; i < vet.length; i++) {
            System.out.println("os valores ajustados " + vet[i]);
        }
    }

    public static void main(String[] args) {
        new Uni06ex03();
    }
}