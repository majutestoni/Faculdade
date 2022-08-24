package ex04list2;

public class Expressao {

    public static boolean estaCorretaSintaticamente(String ex) {
        int contador1 = 0;
        int contador2 = 0;

        for (int i = 0; i < ex.length(); i++) {
            if (ex.charAt(i) == '(') {
                contador1++;
            }
            if (ex.charAt(i) == ')') {
                contador2++;
            }
        }

        if (contador1 == contador2) {
            return true;
        } else {
            return false;
        }
    }

    public static int getQtdeDivisores(String ex) {
        String[] teste = ex.split("/");
        int contador = teste.length - 1;

        return contador;
    }

    public static int getPosicaoOperador(String ex) {
        int a = ex.indexOf("+");
        int b = ex.indexOf("-");
        int c = ex.indexOf("*");
        int d = ex.indexOf("/");

        if (a < b && a < c && a < d) {
            return a;
        } else if (b < c && b < d) {
            return b;
        } else if (c < d) {
            return c;
        } else {
            return d;
        }
    }
}
