package ex06list2;
public class VetorReais {
    private double[] n;

    public VetorReais(int tamanho) {
        this.n = new double[tamanho];
    }

    public double getN(int posicao) {
        return n[posicao];
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
        double teste;
        for (int i = 0; i < n.length; i++) {
            teste = n[i] / b.getN(i);
            novo.setN(i, teste);
        }

        for (int i = 0; i < n.length; i++) {
            System.out.println(novo.getN(i));
        }

        return novo;
    }

    public VetorReais multiplicacaoM(VetorReais b) {
        int tamanho = n.length;
        VetorReais novo = new VetorReais(n.length);
        tamanho--;
        double teste = 0;
        for (int i = 1; i < n.length; i++) {
            teste = n[i] * b.getN(tamanho);
            novo.setN(i, teste); 
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
