import java.util.Scanner;

public class Vinte1AoVinte7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Ex21 -> IMC
        System.out.println("Digite a massa em kilogramas");
        double massa = scan.nextDouble();
        System.out.println("Digite a altura");
        double altura = scan.nextDouble();

        double imc = massa / (altura * altura);

        if (imc < 18.5) {
            System.out.println("magreza");
        } else if (imc >= 18.5 && imc < 25) {
            System.out.println("saudavel");
        } else if (imc >= 25 && imc < 30) {
            System.out.println("sobrepeso");
        } else if (imc >= 30 && imc < 35) {
            System.out.println("obesidade grau I");
        } else if (imc >= 35 && imc < 40) {
            System.out.println("obesidade grau II");
        } else if (imc >= 40) {
            System.out.println("obesidade grau III");
        }

        // 22 -> switch
        System.out.println("Digite o numero do curso que voce selecionou: 1, 2 ou 3");
        int numeroDoCurso = scan.nextInt();

        switch (numeroDoCurso) {
            case 1:
                System.out.println("Bacharel em BCC");
                break;
            case 2:
                System.out.println("Licenciado em Computação");
                break;
            case 3:
                System.out.println("Bacharel em Sistemas da Informação");
                break;
            default:
                System.out.println("Valor invalido");
                break;
        }

        // 23 -> mes
        System.out.println("escreva o numero do mes");
        int numeroMes = scan.nextInt();

        switch (numeroMes) {
            case 1:
                System.out.println("Janeiro");
                break;
            case 2:
                System.out.println("Fevereiro");
                break;
            case 3:
                System.out.println("Março");
                break;
            case 4:
                System.out.println("Abril");
                break;
            case 5:
                System.out.println("Maio");
                break;
            case 6:
                System.out.println("Junho");
                break;
            case 7:
                System.out.println("Julho");
                break;
            case 8:
                System.out.println("Agosto");
                break;
            case 9:
                System.out.println("Setembro");
                break;
            case 10:
                System.out.println("Outubro");
                break;
            case 11:
                System.out.println("Novembro");
                break;
            case 12:
                System.out.println("Dezembro");
                break;

            default:
                System.out.println("Valor invalido");
                break;
        }

        scan.close();
    }

}
