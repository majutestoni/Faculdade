package ex06list2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        VetorReais ex = new VetorReais(5);
        VetorReais ot = new VetorReais(5);

        ex.setN(0, 2);
        ex.setN(1, 4);
        ex.setN(2, 5);
        ex.setN(3, 10);
        ex.setN(4, 10);
        ot.setN(0, 2);
        ot.setN(1, 4);
        ot.setN(2, 5);
        ot.setN(3, 10);
        ot.setN(4, 5);

        // System.out.println(ex.pares());
        System.out.println(Arrays.toString(ex.invertendoVetor()));
         ex.divisao(ot);
         System.out.println(ex.diferenca());
    }
}
