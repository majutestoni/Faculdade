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

