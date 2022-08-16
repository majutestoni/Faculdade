public class InteiroPostivo {
    private int valor;

    public void setValor(int valor) {
        if (valor > 0) {
            this.valor = valor;
        } else {
            this.valor = 0;
        }
    }

    public int getValor() {
        return valor;
    }

    public int multiplicaInteiro(InteiroPostivo outro) {
        return valor * outro.getValor();
    }

    public int fatorial(int termos) {
        int resultado = 1;
        for (int i = termos; i > 0; i--) {
            resultado = resultado * i;
        }

        return resultado;
    }

    public String divisores() {
        int contador = 1;
        String todosDiivisores = "";

        for (int i = 1; i <= (valor / 2); i++) {
            if (valor % i == 0) {
                contador++;
                todosDiivisores = todosDiivisores + i + ", ";
            }
        }

        todosDiivisores = todosDiivisores + valor + " e a quantidade de divisores é " + contador;

        return todosDiivisores;
    }

    public int[] fibonacci() {
        int[] vetor = new int[valor];
        int prox = 0;
        int prox2 = 0;

        for (int i = 0; i < valor; i++) {
            if (i == 0 ) {
                prox = 1;
                prox2 = 0;
            } else {
                prox += prox2;
                prox2 = prox - prox2;
            }
            vetor[i] = prox;
        }

        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i]);
        }
        return vetor;
    }

    public float valorH() {
        float resultado = 1;
        for (float i = 2; i <= valor; i++) {
            resultado = resultado + 1 / i;
        }
        return resultado;
    }

    public float valorI() {
        float resultado = 0;
        for (float i = 0; i <= valor; i++) {
            resultado = resultado - ((valor - i) / (i + 1));
        }
        return resultado;
    }

    public double valorS() {
        double resultado = 0;
        int termos = 20;
        for (int i = 0; i < 20; i++) {
            resultado = Math.pow(valor, (termos - i)) / fatorial(termos); // fatorial revisar
        }

        return resultado;
    }

    public float valorP() {
        float resultado = 0;
        int termos = valor;
        for (int i = 1; i <= valor; i++) {
            resultado = resultado + ((fatorial(termos + 2)) / i); // fatorial revisar
        }

        return resultado;
    }

}
