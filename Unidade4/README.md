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

