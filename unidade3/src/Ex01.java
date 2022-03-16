import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        System.out.println("oi");
        Scanner scan = new Scanner(System.in);

        // Ex01 -> Imobiliaria com terrenos retangulares
        /*
         * int comprimento;
         * int largura;
         * 
         * int i = 0;
         * while (i != 2) {
         * System.out.println("digite 1 para adionar as dimensões ou 2 para sair: ");
         * i = scan.nextInt();
         * 
         * switch (i) {
         * case 1:
         * System.out.println("Digite a comprimento: ");
         * comprimento = scan.nextInt();
         * System.out.println("Digite a largura: ");
         * largura = scan.nextInt();
         * System.out.println("A area é de: " + (comprimento * largura));
         * break;
         * }
         * if (i < 2) {
         * System.out.println("Digite 2 para finalizar e 1 para continuar: ");
         * i = scan.nextInt();
         * }
         * }
         * 
         * 
         * // Ex02
         * System.out.println("Qual valor do sapato?");
         * double sapato = scan.nextDouble();
         * double desconto = sapato * 0.12;
         * System.out.println("O valor do desconto é: " + desconto +
         * ", o valor total é: " + (sapato - desconto));
         * 
         * 
         * // Ex03 -> Preço gasolina e quando vai andar
         * System.out.println("Digite o valor do litro de gasolina: ");
         * double gasolina = scan.nextDouble();
         * System.out.println("Digite o valor que você utilozara para abastecer: ");
         * double preco = scan.nextDouble();
         * 
         * System.out.println("Você colocou " + (preco / gasolina) + " litros");
         * 
         * 
         * //Ex04
         * System.out.println("Digite as 3 notas do aluno");
         * double nota1 = scan.nextDouble();
         * double nota2 = scan.nextDouble();
         * double nota3 = scan.nextDouble();
         * 
         * double notaFinal = ((nota1 * 5) + (nota2 * 3) + (nota3 * 2)) / 10;
         * System.out.println("A nota final foi de " + notaFinal);
         * 
         * 
         * //Ex05
         * System.out.println("Digite quantos frangos precisam ser identificados: ");
         * double quantidadeFrangos = scan.nextDouble();
         * System.out.println("O valor será de " + (quantidadeFrangos * 3.50 * 4));
         

        // Ex06
        double precoKilo = 25;
        System.out.println("Digite o peso que deu em seu prato: ");
        double peso = scan.nextDouble();
        double precoFinal = (peso - 0.75) * precoKilo; // 750 não é muito para peso de prato
        System.out.println("Preço final foi de: " + precoFinal);
        

        // Ex07
        System.out.println("Digite quantas latas de refri foi comprado: ");
        double quantLatas = scan.nextDouble();
        System.out.println("Digite quantas garrafas de 600ml de refri foi comprado: ");
        double quantGarrafas6 = scan.nextDouble();
        System.out.println("Digite quantas garrafas de 2L de refri foi comprado: ");
        double quantGarrafas2 = scan.nextDouble();
        double quantidadeRefri = (quantLatas * 0.35) + (quantGarrafas6 * 0.60) + (quantGarrafas2 * 2);

        System.out.println("Total de litros de refri é de: " + quantidadeRefri);
        

//Ex08
double precoDolar = 5.2;
double valorD = 0;

System.out.println("Digite o valor em dolares para ser convertido: ");
valorD = scan.nextDouble();
System.out.println("O valor devolvido em reais foi de: " + (valorD * precoDolar));


//Ex09
double altura;
double raio;
System.out.println("Digite a altura: ");
altura = scan.nextDouble();
System.out.println("Digite o raio: ");
raio = scan.nextDouble();
System.out.println("O volume é de: " + ((raio * raio) * altura * 3.14));


//Ex10
double cateto1;
double cateto2;
System.out.println("Digite o cateto 1: ");
cateto1 = scan.nextInt();
System.out.println("digite o cateto 2");
cateto2 = scan.nextInt();

double hipotenusa = Math.sqrt((cateto1 * cateto1) + (cateto2 * cateto2)); 
System.out.println("A hipotenusa é de: " + hipotenusa);
*/

//Ex11
double tempC;
System.out.println("Digite o valor em C°: ");
tempC = scan.nextDouble();
System.out.println("A temperatura é de " + ((1.8 * tempC) + 32) + " F°");

//Ex12

        scan.close();
    }
}
