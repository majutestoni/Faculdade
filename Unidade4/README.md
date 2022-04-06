## Unidade 4

Exercicios das aulas sobre a Unidade 4 -> Referente a condições de desvios

{
    if;
    if else;
    switch case.
}

*{
    ==
    !=
    >
    <
}

*{
    &&
    ||
    !
}

        // ex08 -> letra
        char letra;
        System.out.println("Digite uma letra");
        letra = scan.next().charAt(0);

        if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
            System.out.println("é uma vogal");
        } else {
            System.out.println("é uma consoante");
        }
        
        //Ex 12 -> Triangulo se pode ser, se não é e qual é
System.out.println("Digite o lado 1: ");
int lado1 = scan.nextInt();
System.out.println("Digite o lado 2: ");
int lado2 = scan.nextInt();
System.out.println("Digite o lado 3: ");
int lado3 = scan.nextInt();

if(lado1 < (lado2 + lado3) && lado2 < (lado1 + lado3) && lado3 < (lado1 + lado2)){
    if(lado1 == lado3 && lado1 == lado2){
System.out.println("é equilatero");
    }else{
        if(lado1 == lado2 || lado2 == lado3 || lado1 == lado3){
System.out.println("é isoceles");
        }else{
            System.out.println("é escaleno");
        }
    }
}else{
    System.out.println("não é um triangulo");
}

        // Ex13 -> truco
        System.out.println("informe 3 cartas");
        int carta1 = scan.nextInt();
        int carta2 = scan.nextInt();
        int carta3 = scan.nextInt();

        int qCartaBoas = 0;

        if (carta1 == 1 || carta1 == 2 || carta1 == 3) {
            qCartaBoas += 1;
        }
        if (carta2 == 1 || carta2 == 2 || carta2 == 3) {
            qCartaBoas = qCartaBoas + 1;
        }
        if (carta3 == 1 || carta3 == 2 || carta3 == 3) {
            qCartaBoas++;
        }

        if (qCartaBoas == 1) {
            System.out.println("Truco");
        } else if (qCartaBoas == 2) {
            System.out.println("seis");
        } else if (qCartaBoas == 3) {
            System.out.println("nove");
        }
        
                // Ex14 -> data é valida
        System.out.println("Digite o dia");
        int dia = scan.nextInt();
        System.out.println("Digite o mes");
        int mes = scan.nextInt();
        System.out.println("Digite o ano");
        int ano = scan.nextInt();

        if (dia > 0 && dia < 32 && mes > 0 && mes < 13 && ano > 0) {
            if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                System.out.println("Data valida");
            }else if(mes != 2 && dia < 31){
                System.out.println("Data valida");
            }else if(mes == 2 && dia < 29){
                System.out.println("Data valida");
            }else if(dia == 29 && ano % 4 == 0 & !(ano % 100 == 0 && ano % 400 !=0)){
                System.out.println("Data valida");
            }else{
                System.out.println("Data não é valida");
            }
        } else {
            System.out.println("Data não valida");
        }
        
                // Ex 15 -> reajuste salarial
        System.out.println("Digite o salario do funcionario");
        double salarioFuncionario = scan.nextInt();
        System.out.println("Digite a quantidade de meses que o funcionario está na empresa");
        int mesFuncionario = scan.nextInt();
        double reajuste;

        if (mesFuncionario <= 12) {
            reajuste = salarioFuncionario * 0.05;
            System.out.println("o valor fica com " + (reajuste + salarioFuncionario));
        } else if (mesFuncionario > 12 && mesFuncionario < 49) {
            reajuste = salarioFuncionario * 0.07;
            System.out.println("o valor fica com " + (reajuste + salarioFuncionario));
        }
        
        //Ex16 -> idades de homens e mulheres
System.out.println("Digite as idades dos homens");
int idadeHomem1 = scan.nextInt();
int idadeHomem2 = scan.nextInt();
System.out.println("Digite as idades das mulheres");
int idadeMulher1 = scan.nextInt();
int idadeMulher2 = scan.nextInt();

int homemMaisVelho = 0;
int homemMaisNovo = 0;
int MulherMaisVelha = 0;
int MulherMaisNova = 0;

if(idadeHomem1 > idadeHomem2){
homemMaisVelho = idadeHomem1;
homemMaisNovo = idadeHomem2;
}else if(idadeHomem2 > idadeHomem1){
    homemMaisVelho = idadeHomem2;
    homemMaisNovo = idadeHomem1;
}
if(idadeMulher1 > idadeMulher2){
MulherMaisVelha = idadeHomem1;
MulherMaisNova = idadeMulher2;
}else if(idadeMulher2 > idadeMulher1){
    MulherMaisVelha = idadeMulher2;
    MulherMaisNova = idadeMulher1;
}

System.out.println("A soma das idades mais velho e mais nova " + (homemMaisVelho + MulherMaisNova));
System.out.println("O produto do mais novo com a mais velha " + (homemMaisNovo * MulherMaisVelha));

        // Ex17 -> imposto de renda
        System.out.println("Digite a renda anual");
        double rendaAnual = scan.nextDouble();
        System.out.println("Digite o numero de dependentes");
        int dependentesRenda = scan.nextInt();

        double rendaLiquida = rendaAnual - (rendaAnual * 0.02 * dependentesRenda); // duvida
        double imposto;

        if (rendaLiquida > 2000) {
            if (rendaLiquida >= 200 && rendaLiquida <= 5000) {
                imposto = rendaLiquida * 0.05;
                System.out.println("O imposto é de " + (imposto));
            } else if (rendaLiquida > 5000 && rendaLiquida <= 10000) {
                imposto = rendaLiquida * 0.10;
                System.out.println("O imposto é de " + (imposto));
            }else if(rendaLiquida > 10000){
                imposto = rendaLiquida * 0.15;
                System.out.println("O imposto é de " + (imposto));
            }
        } else {
            System.out.println("Não paga imposto");
        }
        
        
        // Ex18 -> pagamento com vencimento e desconto
        System.out.println("Digite o valor da prestação");
        double valorPrestacao = scan.nextInt();
        System.out.println("Digite a data de pagamento");
        int dataPagamento = scan.nextInt();

        if (dataPagamento <= 10) {
            double valorPagamentoDesconto = valorPrestacao * 0.10;
            System.out.println("O valor pagoi foi " + (valorPrestacao - valorPagamentoDesconto));
        }else if(dataPagamento > 10 && dataPagamento <= 15){
            System.out.println("O valor pago foi de " + (valorPrestacao));
        }else if(dataPagamento > 15){
            double valorMulta = valorPrestacao * 0.02 * (dataPagamento - 15);
            System.out.println("O valor pago com multa foi de " + (valorPrestacao + valorMulta));
        }
        
                // Ex 19 -> X e Y
        System.out.println("Digite X");
        int x = scan.nextInt();
        System.out.println("Digite Y");
        int y = scan.nextInt();

        if (x == 0 && y == 0) {
            System.out.println("Quadrante 0");
        } else if (x > 0 && y > 0) {
            System.out.println("Quadrando 1");
        } else if (x > 0 && y < 0) {
            System.out.println("Quadrante 2");
        } else if (x < 0 && y > 0) {
            System.out.println("Quadrante 3");
        }else if(x < 0 && y < 0){
            System.out.println("Quadrante 4");
        }
        
                // Ex20 -> Notas aluno
        System.out.println("Digite as 3 notas dos alunos");
        double notaConceito1 = scan.nextDouble();
        double notaConceito2 = scan.nextDouble();
        double notaConceito3 = scan.nextDouble();

        double media = (notaConceito1 + notaConceito2 + notaConceito3) / 3;
        if (media >= 9) {
            System.out.println("Aprovado com conceito A");
        } else if (media >= 7.5 && media < 9) {
            System.out.println("Aprovado com conceito B");
        } else if (media >= 6 && media < 7.5) {
            System.out.println("Aprovado com conceito C");
        } else if (media >= 4 && media < 6) {
            System.out.println("Reprovado com conceito D");
        } else if (media < 4) {
            System.out.println("Reprovado com conceito E");
        }
        
        

