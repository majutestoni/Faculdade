package ex04list2;

public class Expressao {

    private String ex;

    public Expressao(String ex) {
        setEx(ex);
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public boolean estaCorretaSintaticamente() {
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

    public int getQtdeDivisores() {
        String[] divisores = ex.split("/");
        int contador = divisores.length - 1;

        return contador;
    }

    public int getPosicaoOperador() {
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
        } else if(d > -1){
            return d;
        }else{
            return -1;
        }
    }
}
