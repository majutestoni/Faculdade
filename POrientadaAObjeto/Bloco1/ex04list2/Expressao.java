package ex04list2;

public class Expressao {

    public static boolean estaCorretaSintaticamente(String ex) {
        //não
        String[] teste = ex.split("/");
        String[] teste2 = ex.split("+");

        System.out.println(teste);
        System.out.println(teste2);
        if (teste == teste2) {
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
