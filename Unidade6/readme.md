import java.util.Scanner;

public class Uni06ex09 {
    private Uni06ex09() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Informe N");
        int N = scan.nextInt();

        int vetorNota[] = new int[N];
        int vetorGenero[] = new int[N];
        int vetorIdade[] = new int[N];

        ler(vetorNota, scan, vetorGenero, vetorIdade);
        reorganizar(vetorNota, vetorGenero, vetorIdade);

        scan.close();
    }

    private void ler(int vetorNota[], Scanner scan, int vetorGenero[], int vetorIdade[]) {

        for (int i = 0; i < vetorNota.length; i++) {
            System.out.println("digite a nota");
            vetorNota[i] = scan.nextInt();
            System.out.println("digite o genero");
            vetorGenero[i] = scan.nextInt();
            System.out.println("digite a idade");
            vetorIdade[i] = scan.nextInt();

        }

    }

    private void reorganizar(int vetorNota[], int vetorGenero[], int vetorIdade[]) {
        int auxHomem = 0, auxMulher = 0;
        boolean homem = false, mulher = false;
        int notaMulherMaisNova = 0;
        int vetorHomem[] = new int[auxHomem];

        for (int i = 0; i < vetorIdade.length; i++) {
            homem = false;
            mulher = false;

            if (vetorGenero[i] == 1) {
                homem = true;
            } else if (vetorGenero[i] == 2) {
                mulher = true;
            }

            if (homem) {
                auxHomem++;
                for (int j = 0; j < auxHomem; j++) {
                    vetorHomem[auxHomem] = vetorNota[i];
                }
            } else if (mulher) {
                for (int j = 0; j < vetorIdade.length; j++) {
                    if (vetorIdade[i] < vetorIdade[j]) {
                        notaMulherMaisNova = vetorNota[i];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Uni06ex09();
    }
}
