import java.util.Scanner;

public class Ex02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Deu certo 2");

        // Ex11
        System.out.println("Digite o valor A: ");
        double valorA = scan.nextDouble();
        System.out.println("Digite valor B: ");
        double valorB = scan.nextDouble();
        System.out.println("Digite valor C: ");
        double valorC = scan.nextDouble();
        // a)
        System.out.println("Valor da area triangulo A por C: " + (valorA * valorC) / 2);

        // b)
        System.out.println("Area do circulo de C como raio: " + (valorC * valorC) * 3.14);

        // c)
        double trapezio = ((valorA + valorB) * 2) / 2;
        System.out.println("O valor do trapezio de A, B  e C é: " + trapezio);

        // d)
        System.out.println("O quadro de B tem area: " + (valorB * valorB));

        // e)
        System.out.println("Retangulo de A e B: " + (valorA * valorB));

        // 12
        System.out.println("Digite o valor dos pontos x1 e y1: ");
        float p1x1 = scan.nextFloat();
        float p1y1 = scan.nextFloat();
        System.out.println("Digite o valor dos pontos x2 e y2: ");
        float p2x2 = scan.nextFloat();
        float p2y2 = scan.nextFloat();

        

    }

}
