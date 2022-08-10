import java.util.Scanner;

public class Uni06ex10 {
    private Uni06ex10() {
        Scanner scan = new Scanner(System.in);

        int vetor[] = new int[50];

        System.out.println("Digite a opção");
        int opção = scan.nextInt();

        switch (opção) {
            case 1:
                opcao1(vetor, scan);
                break;
            case 2:
                opcao2(vetor, scan);
                break;
            case 3:
                opcao3(vetor, scan);
                break;
            case 4:
                opcao4(vetor, scan);
                break;
            case 5:
                opcao5(vetor);
                break;
            case 6:
                opcao6(vetor);
                break;
            case 7:
                // desafio
                break;
            case 8:

                break;
            default:
                break;
        }

        scan.close();
    }

    private void opcao1(int vetor[], Scanner scan) {
        if (vetor[49] == 0) {
            System.out.println("Digite o ultimo valor que deseja inserir");
            int valor = scan.nextInt();
            vetor[49] = valor;
            System.out.println("valor inserido");
        } else {
            System.out.println("Sem espaço no vetor");
        }
    }

    private void opcao2(int vetor[], Scanner scan) {
        System.out.println("Digite o valor que que desesa pesquisar");
        int pesquisa = scan.nextInt();

        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == pesquisa) {
                System.out.println("valor encontrador");
            }
        }
    }

    private void opcao3(int vetor[], Scanner scan) {
        System.out.println("Informe o valor que deseja alterar");
        int alterar = scan.nextInt();
        System.out.println("informe o novo valor");
        int alterado = scan.nextInt();

        for (int j = 0; j < vetor.length; j++) {
            if (vetor[j] == alterar) {
                vetor[j] = alterado;
            }
        }
    }

    private void opcao4(int vetor[], Scanner scan) {
        System.out.println("Escolha o valor que deseja excluir");
        int excluir = scan.nextInt();
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == excluir) {
            }
        }

    }

    private void opcao5(int vetor[]) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(" " + vetor[i]);
        }
    }

    private void opcao6(int vetor[]) {
        int aux = 0;

        for (int i = 0; i < vetor.length; i++) {
            for (int j = 0; j < vetor.length; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                }
            }
        }

        for (int e = 0; e < vetor.length; e++) {
            System.out.print(" " + vetor[e]);
        }
    }

    public static void main(String[] args) {
        new Uni06ex10();
    }
}
