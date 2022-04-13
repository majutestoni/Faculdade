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

        // 27 -> Pedagio estacionamento
        System.out.println("Digite a hora de chegada");
        int horaChegada = scan.nextInt();
        System.out.println("Digite a minuto de chegada");
        int minutoChegada = scan.nextInt();
        System.out.println("Digite hora de saida");
        int horaSaida = scan.nextInt();
        System.out.println("Digite minuto de saida");
        int minutoSaida = scan.nextInt();
        int qtdHoras = 0;

        if (horaChegada > horaSaida) {
            qtdHoras = 24 - (horaChegada - horaSaida);
        } else {
            qtdHoras = horaSaida - horaChegada;
        }
        int minuto = 60 - minutoChegada + minutoSaida;
        if (minuto >= 30) {
            qtdHoras++;
        }

        double precoCobrado = 0;
        switch (qtdHoras) {
            case 0:
                System.out.println("Não precisa pagar");
                break;
            case 1:
                precoCobrado = 5.00;
                break;
            case 2:
                precoCobrado = 10.00;
                break;
            case 3:
                precoCobrado = 17.5;
                break;
            case 4:
                precoCobrado = 25.00;
                break;
            default:
                precoCobrado = 25 + (10 * (qtdHoras - 4));
                break;
        }
        System.out.println("quatidade de horas " + qtdHoras);
        System.out.println("O preço cobrado será de " + precoCobrado);
