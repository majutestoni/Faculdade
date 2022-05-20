import java.util.Scanner;

public class teste {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 29
        System.out.println("Informe o valor que deseja");
        int valorDesejado = scan.nextInt();
        int valorDesejadoFinal = 0, notasFinais = 0;
        int cedulas20 = 0, cedulas10 = 0, cedulas5 = 0, cedulas2 = 0, cedulas1 = 0;
        int notas20 = 0, notas10 = 0, notas5 = 0, notas2 = 0, notas1 = 0;

        while (valorDesejado != valorDesejadoFinal) {

            if (valorDesejado % 20 == 0) {

            }

            valorDesejadoFinal = 0;
            notasFinais = notasFinais + cedulas20 + cedulas10 + cedulas5 + cedulas2 + cedulas1;
            System.out.println(notasFinais);
        }

        scan.close();
    }
}
