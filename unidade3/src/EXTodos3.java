
    import java.util.Scanner;

public class EXTodos3 {

 public static void main(String[] args) { System.out.println("oi"); Scanner scan = new Scanner(System.in);

    // Ex01 -> Imobiliaria com terrenos retangulares 
    int comprimento;
    int largura;

    System.out.println("Digite o comprimento do terreno: ");
    comprimento = scan.nextInt();
    System.out.println("Digite a largura do terreno:");
    largura = scan.nextInt();

    System.out.println("A area do terreno é de: " + (largura * comprimento));

    // Ex02
    System.out.println("Qual valor do sapato?");
    double sapato = scan.nextDouble();
    double desconto = sapato * 0.12;
    System.out.println("O valor do desconto é: " + desconto +
            ", o valor total é: " + (sapato - desconto));

    // Ex03 -> Preço gasolina e quando vai andar
    System.out.println("Digite o valor do litro de gasolina: ");
    double gasolina = scan.nextDouble();
    System.out.println("Digite o valor que você utilizara para abastecer: ");
    double preco = scan.nextDouble();

    System.out.println("Você colocou " + (preco / gasolina) + " litros");

    // Ex04
    System.out.println("Digite as 3 notas do aluno");
    double nota1 = scan.nextDouble();
    double nota2 = scan.nextDouble();
    double nota3 = scan.nextDouble();

    double notaFinal = ((nota1 * 5) + (nota2 * 3) + (nota3 * 2)) / 10;
    System.out.println("A nota final foi de " + notaFinal);

    // Ex05
    System.out.println("Digite quantos frangos precisam ser identificados: ");
    double quantidadeFrangos = scan.nextDouble();
    System.out.println("O valor será de " + (quantidadeFrangos * 3.50 * 4));

    // Ex06
    double precoKilo = 25;
    System.out.println("Digite o peso que deu em seu prato em  Kilo: ");
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

    // Ex08
    double precoDolar = 5.2;
    double valorD = 0;

    System.out.println("Digite o valor em dolares para ser convertido: ");
    valorD = scan.nextDouble();
    System.out.println("O valor devolvido em reais foi de: " + (valorD * precoDolar));

    // Ex09
    double altura;
    double raio;
    System.out.println("Digite a altura: ");
    altura = scan.nextDouble();
    System.out.println("Digite o raio: ");
    raio = scan.nextDouble();
    System.out.println("O volume é de: " + ((raio * raio) * altura * 3.14));

    // Ex10
    double cateto1;
    double cateto2;
    System.out.println("Digite o cateto 1: ");
    cateto1 = scan.nextInt();
    System.out.println("digite o cateto 2");
    cateto2 = scan.nextInt();

    double hipotenusa = Math.sqrt((cateto1 * cateto1) + (cateto2 * cateto2));
    System.out.println("A hipotenusa é de: " + hipotenusa);

    // Ex11
    double tempC;
    System.out.println("Digite o valor em C°: ");
    tempC = scan.nextDouble();
    System.out.println("A temperatura é de " + ((1.8 * tempC) + 32) + " F°");

    // Ex12
    System.out.println("Digite o nome do funcionario: ");
    String nomeFuncionario = scan.next();
    System.out.println("Quantas horas ele trabalhou? ");
    int horas = scan.nextInt();
    System.out.println("Digite o numero de dependentes: ");
    int dependentes = scan.nextInt();

    int salarioTrab = horas * 10;
    int salarioFamilia = dependentes * 60;
    double salarioDescontos1 = salarioTrab * 0.085;
    double salarioDescontos2 = salarioTrab * 0.05;
    double salarioLiquido = salarioDescontos1 + salarioDescontos2 + salarioFamilia;

    System.out.println("O funcionario " + nomeFuncionario + " tem o salario bruto de "
            + (salarioFamilia + salarioTrab) + " e o salario liquido de " + salarioLiquido);

    // Ex13
    System.out.println("Digite a altura da parede: ");
    int alturaA = scan.nextInt();
    System.out.println("Digite o comprimento: ");
    int comprimentoA = scan.nextInt();

    int azulejos = (alturaA * comprimentoA) / 9;
    System.out.println("O preço total dos azulejos " + (azulejos * 12.5));

    // 14
    System.out.println("Digite a distacia percorrida(em km): ");
    int distancia = scan.nextInt();
    System.out.println("Digite o tempo gasto (horas): ");
    int tempoGasto = scan.nextInt();
    double velocidadeMedia = distancia / tempoGasto;
    double gastoLitro = distancia / 12;

    System.out.println("A velocidade media do veiculo foi de: " + velocidadeMedia
            + " e a gastode combustivel foi de " + gastoLitro);

    // 15
    System.out.println("Digite ate 3 numeros: ");
    int numeros = scan.nextInt();
    int centena = numeros / 100;
    int dezena = numeros % 100 / 10;
    int unidade = numeros % 100 % 10 / 1;
    System.out.println("A centena é: " + centena + ", a dezena: " + dezena + " e a unidade: " + unidade);

    // 16
    System.out.println("Descreva o valor a ser pago: ");
    int valorPago = scan.nextInt();

    int notas100 = valorPago / 100;
    int notas10 = valorPago % 100 / 10;
    int notas1 = valorPago % 100 % 10 / 1;

    int totalNotas = notas1 + notas10 + notas100;
    System.out.println("Total de notas: " + totalNotas + ", sendo de 100: " + notas100 + ",notas de 10: " + notas10
            + " e notas 1: " + notas1);

    scan.close();
}
}
