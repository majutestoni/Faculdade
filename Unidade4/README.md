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

//Ex01
        for (int i = 0; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.println("O numero é par " + i);
            } else {
                System.out.println("O numero é impar " + i);
            }
        }


//ex 02
  int somaPar = 0;
        int somaImpar = 0;

        for (int i = 1; i <= 5; i++) {// alterar para 100
            if (i % 2 == 0) {
                somaPar += i;
            } else {
                somaImpar += i;
            }
        }
        System.out.println("par " + somaPar);
        System.out.println("impar " + somaImpar);

