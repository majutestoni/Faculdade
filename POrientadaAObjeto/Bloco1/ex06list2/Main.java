package ex06list2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        VetorReais ex = new VetorReais(5);

        ex.setN(0, 15);
        ex.setN(1, 20);
        ex.setN(2, 10.4);
        ex.setN(3, 5);
        ex.setN(4, 2.2);

     //   System.out.println(ex.pares());
        System.out.println(Arrays.toString(ex.invertendoVetor()));
    }
}
