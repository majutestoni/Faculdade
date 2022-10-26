package list04ex04;

import java.util.ArrayList;

public class Vereador {
    private String nome;
    private Partido partido;
    private ArrayList<ProjetoDeLei> projetosLei;

    public void addProjetodeLei9(ProjetoDeLei p) {
        projetosLei.add(p);
    }

    public ArrayList<ProjetoDeLei> getProjetoDeLeis() {
        return projetosLei;
    }

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
        int conta = 0;

        for (ProjetoDeLei projeto : this.projetosLei) {
            if (projeto.getDataAprovacao() != null) {
                conta++;
            }
        }
        return conta;
    }

    public double getIndiceTrabalho() {
        if (projetosLei.size() < 1) {
            return 0;
        } else if (projetosLei.size() > 0 && projetosLei.size() <= 5) {
            return 0.80;
        } else if (projetosLei.size() >= 6 && projetosLei.size() <= 10) {
            return 1;
        } else if (projetosLei.size() >= 11 && projetosLei.size() <= 17) {
            return 1.08;
        } else {
            return 1.22;
        }
    }

    public double getDesempenho() {
        if (projetosLei.size() == 0) {
            return 0;
        } else {
            return (getQntProjAprovado() / projetosLei.size()) * getIndiceTrabalho();
        }
    }
}
