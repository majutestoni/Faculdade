package lex08list2;

import java.util.Arrays;

public class PassageirorHora {
private int[][] matriz = new int[30][12];

    public void adiciona(int dia, int mes, int hora) {

    }

    public int quantosPAssageiros() {
        int a = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = 1;
            }
        }

        System.out.println(Arrays.toString(matriz));
        return a;
    }
}
