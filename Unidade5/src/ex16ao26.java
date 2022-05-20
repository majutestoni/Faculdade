import java.util.Scanner;

public class ex16ao26 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Ex16
        System.out.println("Digite a altura");
        double altura16 = scan.nextInt();
        char sexo;
        double alturaMulheres = 0;
        double alturaTotal = 0;
        int contadorAltura = 0;
        int contadorAlturaMulheres = 0;
        while (altura16 != 0) {
            contadorAltura = contadorAltura + 1;
            System.out.println("Digite m para masculino e f para feminino:");
            sexo = scan.next().charAt(0);
            alturaTotal = alturaTotal + altura16;
            if (sexo == 'f' || sexo == 'F') {
                alturaMulheres = alturaMulheres + altura16;
                contadorAlturaMulheres = contadorAlturaMulheres + 1;
            }
            System.out.println("Digite a altura");
            altura16 = scan.nextInt();
        }

        System.out.println("Altura media das mulheres é " + (alturaMulheres / contadorAlturaMulheres));
        System.out.println("A altura media do grupo é de: " + (alturaTotal / contadorAltura));

        // 17
        System.out.println("Digite o numero de inscrição: ");
        int inscricao = scan.nextInt();
        double alturaAtleta, atletasTotal = 0;
        int contadorAtleta = 0;
        double atletaAlto = 0;
        double atletaBaixo = 0;
        int inscricaoAlto = 0;
        int inscricaoBaixo = 0;
        while (inscricao != 0) {
            contadorAtleta = contadorAtleta + 1;
            System.out.println("Qual a altura");
            alturaAtleta = scan.nextInt();
            atletasTotal = atletasTotal + alturaAtleta;

            if (alturaAtleta > atletaAlto) {
                atletaAlto = alturaAtleta;
                inscricaoAlto = inscricao;
            }
            if (alturaAtleta < atletaBaixo) {
                atletaBaixo = alturaAtleta;
                inscricaoBaixo = inscricao;
            }

            System.out.println("Digite o numero de inscrição: ");
            inscricao = scan.nextInt();
        }
        System.out.println("Altura atleta alto " + atletaAlto + " inscrção " + inscricaoAlto);
        System.out.println("Altura atleta baixo " + atletaBaixo + " inscrção " + inscricaoBaixo);
        System.out.println("A altura media do grupo: " + (atletasTotal / contadorAtleta));

        // 18
        System.out.println("Digite o canal: ");
        int canal = scan.nextInt();
        int quantasPessoas = 0;
        int pessoas4 = 0;
        int pessoas5 = 0;
        int pessoas9 = 0;
        int pessoas12 = 0;
        while (canal != 0) {
            System.out.println("Informe quantas pessoas estão assistindo");
            quantasPessoas = scan.nextInt();
            switch (canal) {
                case 4:
                    pessoas4 = +quantasPessoas;
                    break;
                case 5:
                    pessoas5 = +quantasPessoas;
                    break;
                case 9:
                    pessoas9 = +quantasPessoas;
                    break;
                case 12:
                    pessoas12 = +quantasPessoas;
                    break;
                default:
                    System.out.println("Canal invalido");
                    break;
            }
            System.out.println("Informe o canal");
            canal = scan.nextInt();
        }
        double pessoasTotal = pessoas4 + pessoas5 + pessoas9 + pessoas12;
        double audiencia4 = (pessoas4 / pessoasTotal) * 100;
        double audiencia5 = (pessoas5 / pessoasTotal) * 100;
        double audiencia9 = (pessoas9 / pessoasTotal) * 100;
        double audiencia12 = (pessoas12 / pessoasTotal) * 100;
        System.out.println(
                "A audiencia do canal 4 é " + audiencia4 + "%, canal 5 " + audiencia5 + "%, canal 9 " + audiencia9
                        + "% e canal 12 " + audiencia12 + "%");

        // 19
        System.out.println("Informe o valor da compra: ");
        int valorCompra = scan.nextInt();
        double valorClientePagar = 0;
        double contadorLucroLoja = 0;
        while (valorCompra != 0) {
            if (valorCompra > 500) {
                valorClientePagar = valorCompra - (valorCompra * 0.20);
            } else {
                valorClientePagar = valorCompra - (valorCompra * 0.15);
            }
            System.out.println("O valor que o cliente pagará é de " + valorClientePagar);
            contadorLucroLoja = contadorLucroLoja + valorClientePagar;
            System.out.println("Informe o valor da compra: ");
            valorCompra = scan.nextInt();
        }
        System.out.println("A loja ganhará no final do dia " + contadorLucroLoja);

        // 20
        System.out.println("Digite a quantidade de massa em kg");
        double massaRadioativo = scan.nextDouble();
        double massaVidaFim = massaRadioativo / 2;
        double contadorTempovida = 50;
        while (massaVidaFim > 0.5) {
            contadorTempovida = contadorTempovida + 50;
            massaVidaFim = massaVidaFim / 2;
        }
        System.out.println("A massa final de " + massaVidaFim);
        System.out.println("O tempo de vida foi " + contadorTempovida + " segundos");

        // 21
        double alturaChico = 1.50;
        double alturaZe = 1.10;
        int contadoAnosChicoZe = 0;
        while (alturaChico > alturaZe) {
            contadoAnosChicoZe = contadoAnosChicoZe + 1;
            alturaChico = alturaChico + 0.20;
            alturaZe = alturaZe + 0.30;
        }
        System.out.println("O tempo em anos foi de " + contadoAnosChicoZe);

        // 22
        System.out.println("Digite o ano que voce quer saber o salario");
        int anoSalario = scan.nextInt();
        int anoContratado = 1995;
        double salarioFuncionario = 2000;
        double aumentoDeSalario = 0.015;
        while (anoContratado != anoSalario) {
            salarioFuncionario = salarioFuncionario + (salarioFuncionario * aumentoDeSalario);
            aumentoDeSalario = aumentoDeSalario * 2;
            anoContratado = anoContratado + 1;
        }
        System.out.println("O salario finalizou com " + salarioFuncionario);

        // 23
        System.out.println("deseja digitar os dados de mais um vendedor: s (SIM) / n (NÃO)");
        String maisUmVendedor = scan.nextLine();

        String nomeFuncionarios = "";
        int itensVendidos = 1;
        double valorDoProduto = 0, valorTotalDeVenda = 0, salarioFuncionarioTotal = 0;
        while (maisUmVendedor.equals("s")) {
            System.out.println("Digite o nome do funcionario");
            nomeFuncionarios = scan.next();

            System.out.println("digite quantos itens");
            itensVendidos = scan.nextInt();

            for (int h = 1; h <= itensVendidos; h++) {
                System.out.println("Digite o valor do produto");
                valorDoProduto = scan.nextDouble();
                valorTotalDeVenda = valorTotalDeVenda + valorDoProduto;
            }
            salarioFuncionarioTotal = 0.3 * valorTotalDeVenda;

            System.out.println("O funcionario " + nomeFuncionarios + " teve um total de venda de " + valorTotalDeVenda
                    + " de " + itensVendidos + " itens vendidos " + " e o salario de " + salarioFuncionarioTotal);

            System.out.println("deseja digitar os dados de mais um vendedor: s (SIM) / n (NÃO)");
            maisUmVendedor = scan.next();
        }

        // 24 -> Quer adicionar outro peixe
        System.out.println("Informe o limite de peso diario (KG)");
        double pesoDePescaLimite = scan.nextDouble();
        double pesoDePescaLimiteTotal = 0, pesoPeixe = 0;
        // String querContinuarAPescar = "";

        while (pesoDePescaLimite >= pesoDePescaLimiteTotal) {
            // System.out.println("deseja informar o peso de mais um peixe: s (SIM) / n
            // (NÃO)?");
            // querContinuarAPescar = scan.next();
            System.out.println("Digite o peso do peixe(Em gramas)");
            pesoPeixe = scan.nextDouble();

            pesoDePescaLimiteTotal = pesoDePescaLimiteTotal + pesoPeixe;
        }

        System.out.println("Limite excedido");

        // 25 -> Só falta a diferença ser ajustada
        int jogadorD = 0, jogadorE = 0;
        int jogadorDTotal = 0, jogadorETotal = 0;
        long diferenca = 0;
        String jogadorFezPonto = "";
        while (jogadorDTotal < 21 && jogadorETotal < 21 || diferenca < 2 || diferenca < -2) {
            System.out.println("Qual jogador fez ponto? ");
            jogadorFezPonto = scan.next();
            if (jogadorFezPonto.equals("d")) {
                System.out.println("JogadorD fez quantos Pontos");
                jogadorD = scan.nextInt();
                jogadorDTotal = jogadorDTotal + jogadorD;
            } else if (jogadorFezPonto.equals("e")) {
                System.out.println("JogadorE fez quantos Pontos");
                jogadorE = scan.nextInt();
                jogadorETotal = jogadorETotal + jogadorE;
            } else {
                System.out.println("Invalido");
            }
            diferenca = jogadorD - jogadorE;
            System.out.println(diferenca);
        }

        System.out.println(jogadorDTotal);
        System.out.println(jogadorETotal);

        // 26
        System.out.println("Informe o valor maximo que Astolfo aceita pagar");
        int valorMaximoAstolfo = scan.nextInt();
        int valorPedagioInformado = 0, distanciaKmAstolfo = 0;
        int contadorAstolfoNaoPaga = 0, contadorAstolfoAcima150 = 0, contadorTrechosInformados = 0;
        System.out.println("informe o valor do pedagio");
        valorPedagioInformado = scan.nextInt();
        while (valorPedagioInformado > 0) {
            System.out.println("Informe a distancia");
            distanciaKmAstolfo = scan.nextInt();
            contadorTrechosInformados = contadorTrechosInformados + 1;
            if (valorPedagioInformado == valorMaximoAstolfo) {
                contadorAstolfoNaoPaga = contadorAstolfoNaoPaga + 1;
            }

            if (distanciaKmAstolfo >= 150) {
                contadorAstolfoAcima150 = contadorAstolfoAcima150 + 1;
            }
            System.out.println("informe o valor do pedagio");
            valorPedagioInformado = scan.nextInt();
        }
        System.out.println("trechos com valor acima do qual ele nega-se a pagar " + contadorAstolfoNaoPaga);
        System.out.println("quantidade de trechos informados " + contadorTrechosInformados);
        System.out.println("trechos acima de 150km com valor aceito por ele " + contadorAstolfoAcima150);

        scan.close();
    }
}
