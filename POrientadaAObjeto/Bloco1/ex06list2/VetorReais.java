package ex06list2;

public class VetorReais {
    private double[] n;

    public VetorReais(int tamanho) {
        this.n = new double[tamanho];
    }

    public void setN(int posicao, double n) {
        this.n[posicao] = n;

    }

    public int pares() {
        int contador = 0;
        for (int i = 0; i < n.length; i++) {
            if (((int) n[i] % 2) == 0) {
                contador++;
            }
        }

        return contador;
    }

    public VetorReais divisao(VetorReais b) {
        VetorReais novo = new VetorReais(n.length);
        for (int i = 0; i < n.length; i++) {
            novo[i] = n[i] / b[i];
        }

        return novo;
    }

    public double[] multiplicacaoM(VetorReais b) {
        int tamanho = b.length - 1;
        double[] novo = new double[n.length];
        novo[0] = n[0] * b[tamanho];
        tamanho--;
        for (int i = 1; i < b.length; i++) {
            novo[i] = n[i] * b[tamanho];
            tamanho--;
        }

        return novo;
    }

    public double[] invertendoVetor() {
        int tamanho = n.length - 1;
        double[] novo = new double[n.length];
        for (int i = 0; i < n.length; i++) {
            novo[i] = n[tamanho];
            tamanho--;
        }
        return novo;
    }

    public double diferenca() {
        double teste = 0;
        double maior = 0;
        for (int i = 1; i < n.length; i++) {
            teste = n[i] - n[i - 1];
            if (teste > maior) {
                maior = teste;
            }
        }
        return maior;
    }

}
