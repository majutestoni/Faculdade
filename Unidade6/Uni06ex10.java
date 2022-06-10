import java.util.Scanner;

public class Uni06ex10 {
    private Uni06ex10() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Informe N");
        int N = scan.nextInt();

        if (N > 50) {
            N = 50;
        }

        double vetor[] = new double[N];
        
        scan.close();
    }

    public static void main(String[] args) {
        new Uni06ex10();
    }
}
