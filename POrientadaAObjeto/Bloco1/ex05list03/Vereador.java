package ex05list03;

public class Vereador {
    private String nome;
    private Partido partido;
    private int qntProjAprovado;
    private int qntProjApresentados;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && nome.isBlank()) {
            this.nome = nome;
        }
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        if (partido != null) {
            this.partido = partido;
            partido.addVereador(this);
        }
    }

    public int getQntProjAprovado() {
        return qntProjAprovado;
    }

    public void setQntProjAprovado(int qntProjAprovado) {
        this.qntProjAprovado++;
    }

    public int getQntProjApresentados() {
        return qntProjApresentados;
    }

    public void setQntProjApresentados(int qntProjApresentados) {
        this.qntProjApresentados++;
    }

    public double getIndiceTrabalho() {
        if (qntProjApresentados < 1) {
            return 0;
        } else if (qntProjApresentados > 0 && qntProjApresentados <= 5) {
            return 0.80;
        } else if (qntProjApresentados >= 6 && qntProjApresentados <= 10) {
            return 1;
        } else if (qntProjApresentados >= 11 && qntProjApresentados <= 17) {
            return 1.08;
        } else {
            return 1.22;
        }
    }

    public double getDesempenho() {
        if (getQntProjApresentados() == 0) {
            return 0;
        } else {
            return (qntProjAprovado / qntProjApresentados) * getIndiceTrabalho();
        }
    }
}
