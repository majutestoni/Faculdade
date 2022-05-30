import java.util.Scanner;

public class Uni06ex06 { // Afinidade de um casal
    private Uni06ex06() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Informe N");
        final int N = scan.nextInt();
        float vetor[] = new float[N];

        leia(scan, vetor);
        scan.close();
    }

    private void leia(Scanner scan, float vetor[]) {
        for (int index = 0; index < vetor.length; index++) {
            vetor[index] = scan.nextFloat();
        }

        System.out.println("informe outro valor");
        final int valor = scan.nextInt();

        resultador(valor, vetor);
    }

    private void resultador(int valor, float vetor[]) {
        for (int i = 0; i < vetor.length; i++) {
            if (valor == vetor[i]) {
                System.out.println("valor cadastrado");
            }
        }
    }

    public static void main(String[] args) {
        new Uni06ex06();
    }
}
