import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        // Ex1
        System.out.println("Digite o comprimento: ");
        int comprimento = scan.nextInt();
        System.out.println("Digite a largura: ");
        int largura = scan.nextInt();

        System.out.println("A area do terreno retangular é: " + (largura * comprimento));

    }
}
