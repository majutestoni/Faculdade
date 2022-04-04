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


        // Ex 10 -> tres filhos -> Qual é o caçula
        System.out.println("Digite a idade dos 3 filhos");
        int idadeMarquinhos = scan.nextInt();
        int idadeZezinho = scan.nextInt();
        int idadeLuluzinha = scan.nextInt();

        if ((idadeMarquinhos < idadeZezinho) && (idadeMarquinhos < idadeLuluzinha)) {
            System.out.println("caçula: Marquinhos");
        } else if (idadeZezinho < idadeLuluzinha) {
            System.out.println("caçula: Zezinho");
        } else {
            System.out.println("caçula: Luluzinha");
        }
