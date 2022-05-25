
Exercicios focados em estruturas de repetição (for - while - do while)


public class aulaHoje2 {
    private aulaHoje2() { // construtor
        System.out.println("teste1");
        testeAula();
        System.out.println("teste2");
    }

    private void testeAula() { // porta de entrada do metodo
        System.out.println("teste aula");
        testeAula2();
    }

    private void testeAula2() { // porta de entrada do metodo
        System.out.println("teste aula2");
    }

    public static void main(String[] args) {
        System.out.println("main'");
        new aulaHoje2();
        System.out.println("main2");
    }
}

public class aulaHoje3 {
    private aulaHoje3() { // construtor como primeiro metodo
        System.out.println("teste1");
        // Assinatura
        metodoSemSaida();
        int a = metodoComSaida(); // opcional, não é o mesmo "a", ou seja, não é a mesma variavel
        // comum usar o mesmo nome
    }

    private void metodoSemSaida() {

    }

    private int metodoComSaida() { // tirar void e colocar o tipo,o mesmo do retorno por boa pratica
        int a = 3; // após sair do metodo a varivael a morre
        return a; // precisa de return, deve ser a ultima linha
    }

    public static void main(String[] args) {
        new aulaHoje3();
    }
}

public class aulaHoje4 {
    private aulaHoje4() { // construtor como primeiro metodo
        System.out.println("teste1");
        // Assinatura -> Qunatidade e nome dos parametros
        int a = 2;
        int b = 3;
        metodoSemSaida(a, b); // não importa o nome, e sim a ordem dos valores
        metodoSemSaida(a);
    }

    private void metodoSemSaida(int a, int b) {
        metodoSemSaida(a);
        System.out.println(" b " + b);
    }

    private void metodoSemSaida(int a) {
        System.out.println("a " + a);
    }

    public static void main(String[] args) {
        new aulaHoje4();
    }
}

