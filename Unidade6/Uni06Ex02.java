import java.util.Scanner;

public class Uni06Ex02 {
    private Uni06Ex02() {
        Scanner scan = new Scanner(System.in);

    
        int vet[] = new int[12]; // alterar para 12

        lerVetor(scan, vet);

        scan.close();
    }

    private void lerVetor(Scanner scan, int vet[]) {
        int contador = 0;
        for (int i = 0; i < vet.length; i++) {
            vet[i] = scan.nextInt();
            contador = contador + vet[i];
        }
        mediaVetor(contador, vet);
    }

    private void mediaVetor(int contador, int vet[]) {
        int media = contador / 12;
        apresentarValor(media, vet);
    }

    private void apresentarValor(int media, int vet[]) {
        for (int i = 0; i < vet.length; i++) {
            if (vet[i] > media) {
                System.out.println(vet[i]);
            }
        }

    }

    public static void main(String[] args) {

        new Uni06Ex02();

    }
}
