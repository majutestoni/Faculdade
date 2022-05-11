import java.util.Scanner;

public class Teste2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        //12 e 14 feitos
        // Ex10

        // Ex11
        int intermediario = 1;
        int biscoitosQuebrados = 0;
        for (int i = 0; i < 3; i++) {
            biscoitosQuebrados = biscoitosQuebrados + intermediario;
            intermediario += 1;
        }
        System.out.println(biscoitosQuebrados);

        // Ex13
        System.out.println("Digite quantas paradas ocorreram :");
        int paradas = scan.nextInt();
        for (int d = 0; d < paradas; d++) {
            System.out.println("Digite o odometro");
            int odometro = scan.nextInt();
            System.out.println("digite a quantidade de combustivel adicionada: ");
            int combustivelAdicionado = scan.nextInt();

            // Calculos

        }

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
