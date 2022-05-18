        //23
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

            for (int w = 1; w <= itensVendidos; w++) {
                System.out.println("Digite o valor do produto");
                valorDoProduto = scan.nextDouble();
                valorTotalDeVenda = valorTotalDeVenda + valorDoProduto;
            }
            salarioFuncionarioTotal = 0.3 * valorTotalDeVenda;

            System.out.println("O funcionario " + nomeFuncionarios + " teve um total de venda de " + valorTotalDeVenda
                    + " e o salario de " + salarioFuncionarioTotal);

            System.out.println("deseja digitar os dados de mais um vendedor: s (SIM) / n (NÃO)");
            maisUmVendedor = scan.next();
        }


