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

    public int divisores() { // verificar como retorna valores
        int contador = 0;
        for (int i = 1; i <= valor; i++) {
            if (valor % i == 0) {
                contador++;
            }
        }
        return contador;
    }

    public String fibonacci() {
        int prox = 0;
        String resultado = "";
        int prox2 = 0;
        for (int i = 1; i <= valor; i++) {
            if (i == 1) {
                prox = 1;
                prox2 = 0;
            } else {
                prox += prox2;
                prox2 = prox - prox2;
            }

            resultado = resultado + " " + prox + " ";
        }

        return resultado;
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
