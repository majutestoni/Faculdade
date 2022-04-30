import java.util.Scanner;

public class Teste2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Ex04 -> Verificar a questão do denominador
        double numrador = 1;
        double denominador = 0;
        double respostaS = 0;
        double termos;
        for (int i = 1; i <= 20; i++) {
            numrador += 2;
            denominador += 2 + denominador;
            termos = numrador / denominador;
            respostaS += termos;
            System.out.println(numrador);
            System.out.println(denominador);
        }

        System.out.println(respostaS);

        scan.close();

    }
}
