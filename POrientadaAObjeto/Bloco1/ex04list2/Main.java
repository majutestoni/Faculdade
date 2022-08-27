package ex04list2;

public class Main {
    public static void main(String[] args) {
      Expressao exp = new Expressao("(a10)/(23(10.5b)2.59(ba))");

      System.out.println(exp.getPosicaoOperador());
      System.out.println(exp.getQtdeDivisores());
      System.out.println(exp.estaCorretaSintaticamente());
    }

}
