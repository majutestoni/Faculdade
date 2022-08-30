package lex08list2;

public class Main {
    public static void main(String[] args) {

        PassageirorHora ph = new PassageirorHora();
        ph.adiciona(24, 2, 5);
        ph.adiciona(24, 2, 5);
        ph.adiciona(24, 2, 5);
        ph.adiciona(24, 2, 5);
        ph.adiciona(30, 12, 5);
        ph.adiciona(30, 12, 5);
        ph.adiciona(30, 12, 5);
        ph.adiciona(30, 12, 5);
        ph.adiciona(30, 12, 5);
        ph.adiciona(30, 12, 5);
        ph.adiciona(30, 12, 5);
        ph.adiciona(30, 12, 5);
        ph.adiciona(30, 12, 5);
        System.out.println(ph.quantosPAssageiros(24, 2));
        System.out.println(ph.mesMenorFlux());
        System.out.println(ph.picoTransporte());
        
    }

}
