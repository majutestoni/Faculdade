import java.util.Scanner;

public class Teste2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Ex15 -> While
        System.out.println("Digite o nome do aluno");
        String nome = scan.next();
        while (nome == "fim") {
            System.out.println("teste");
            System.out.println("nota1");
            int nota1 = scan.nextInt();
            System.out.println("nota2");
            int nota2 = scan.nextInt();

            int media = (nota1 + nota2) / 2;
            System.out.println(media);

            System.out.println("Digite o nome do aluno");
            nome = scan.nextLine();

        }

        scan.close();

    }
}
