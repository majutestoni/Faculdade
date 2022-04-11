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

        // 27 -> Tarefas de estcionamento
        System.out.println("Digite o dia de chegada");
        double diaChegada = scan.nextInt();
        System.out.println("Digite a hora de chegada");
        double horaChegada = scan.nextDouble();
        System.out.println("Digite o dia de saida");
        double diaSaida = scan.nextInt();
        System.out.println("Digite hora de saida");
        double horaSaida = scan.nextDouble();

        double diaFinal = diaSaida - diaChegada;
        int horaFinal = (int) Math.round(horaSaida - horaChegada);

        if (diaFinal == 0) {
            switch (horaFinal) {
                case 0:
                case 1:
                case 2:
                    System.out.println("O valor a pagar é de " + (horaFinal * 5));
                    break;
                case 3:
                case 4:
                    System.out.println("O valor a pagar é de " + (horaFinal * 7.5));
                    break;
                case 5: // precisa passar de 5
                    System.out.println("O valor a pagar é de " + (horaFinal * 10));
                    break;

                default:
                    break;
            }
        } else if (diaFinal > 0) {
            horaFinal = horaFinal * -1;
            switch (horaFinal) {
                case 0:
                case 1:
                case 2:
                    System.out.println("O valor a pagar é de " + (horaFinal * 5));
                    break;
                case 3:
                case 4:
                    System.out.println("O valor a pagar é de " + (horaFinal * 7.5));
                    break;
                case 5: // precisa passar de 5
                    System.out.println("O valor a pagar é de " + (horaFinal * 10));
                    break;

                default:
                    break;
            }
        }
