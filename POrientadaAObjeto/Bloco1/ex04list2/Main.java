package ex04list2;

public class Main {
    public static void main(String[] args) {
        String ex = new String("(a+10)/(23*(10.5-b)-2.59/(b*a))");

        System.out.println(Expressao.estaCorretaSintaticamente(ex));
    System.out.println(Expressao.getQtdeDivisores(ex));
    System.out.println(Expressao.getPosicaoOperador(ex));
    }

}
