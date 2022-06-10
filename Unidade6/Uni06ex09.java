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
        int soma = 0, somaTotal = 0;
        int vetorAuxiliar[] = new int[auxMulher];
        int mulheresAcima50 = 0;

        for (int i = 0; i < vetorIdade.length; i++) {
            somaTotal = somaTotal + vetorNota[i];
            homem = false;
            mulher = false;

            if (vetorGenero[i] == 1) {
                homem = true;
            } else if (vetorGenero[i] == 2) {
                mulher = true;
            }

            if (homem) {
                soma = soma + vetorNota[i];
                auxHomem++;
            } else if (mulher) {
                for (int k = 0; k < vetorIdade.length; k++) {
                    if (vetorIdade[i] < vetorIdade[k]) {
                        notaMulherMaisNova = vetorNota[i];
                    }
                    if (vetorIdade[i] > 50) {
                        auxMulher++;
                        for (int g = 0; g < auxMulher; g++) {
                            vetorAuxiliar[auxMulher] = vetorNota[i];
                        }
                    }
                }
            }
        }

        double mediaHomens = soma / auxHomem;
        double mediaTotal = somaTotal / vetorNota.length;

        for (int i = 0; i < vetorAuxiliar.length; i++) {
            System.out.println("a " + vetorAuxiliar[i]);
            if (vetorAuxiliar[i] > mediaTotal) {
                mulheresAcima50++;
            }
        }
        System.out.println("nota media do cinema " + mediaTotal);
        System.out.println("media total homem " + mediaHomens);
        System.out.println("Nota mulher mais nova " + notaMulherMaisNova);
        System.out.println("Mulheres acima dos 50 com nota maior que a media " + mulheresAcima50);

    }

    public static void main(String[] args) {
        new Uni06ex09();
    }
}
