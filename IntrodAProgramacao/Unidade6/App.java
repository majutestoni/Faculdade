import java.util.Scanner;

public class App {
    private App() {
        Scanner scan = new Scanner(System.in);
        int vet[] = new int[10];
        vetorLer(scan, vet);
        vetorEscrever(vet);
        scan.close();
    }

    private void vetorLer(Scanner scan, int vet[]) {
        for (int i = 0; i < vet.length; i++) {
            vet[i] = scan.nextInt();
        }
    }

    private void vetorEscrever(int vet[]) {
        for (int i = vet.length - 1; i >= 0; i--) {
            System.out.println("vet[" + i + "]:" + vet[i]);
        }
    }

    public static void main(String[] args) {
        new App();
    }
}