import java.util.Scanner;

public class Uni06ex08 {
    private Uni06ex08() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Informe N");
        int N = scan.nextInt();

        if (N >= 20) {
            N = 20;
        }

        

        scan.close();
    }

    public static void main(String[] args) {
        new Uni06ex08();
    }
}
