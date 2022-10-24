package list04ex04;

import java.util.ArrayList;

public class Partido {
    private ArrayList<Vereador> vereadores;

    public ArrayList<Vereador> getVereadors() {
        return vereadores;
    }

    private String nome;
    private int numero;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && nome.isBlank()) {
            this.nome = nome;
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero > 9 && numero < 100) {
            this.numero = numero;
        }
    }

    public double mediaDesempenho() {
        int contador = 0;
        for (Vereador vereador : vereadores) {
            contador += vereador.getDesempenho();
        }
        return contador / vereadores.size();
    }

    public int totalProjAprova() {
        int contador = 0;
        for (Vereador vereador : vereadores) {
            contador += vereador.getQntProjAprovado();
        }
        return contador;
    }

    public int totalProjApresen() {
        int contador = 0;
        for (Vereador vereador : vereadores) {
            contador += vereador.getQntProjApresentados();
        }
        return contador;
    }

    public Vereador maisProjAprov() {
        if (vereadores.isEmpty()) {
            return null;
        }
        Vereador retornar = new Vereador();
        for (Vereador vereador : vereadores) {
            if (vereador.getQntProjAprovado() > retornar.getQntProjAprovado()) {
                retornar = vereador;
            }
        }
        return retornar;
    }

    public Vereador menorDesempenho() {
        if (vereadores.isEmpty()) {
            return null;
        }
        Vereador retornar = vereadores.get(0);
        for (Vereador vereador : vereadores) {
            if (vereador.getDesempenho() < retornar.getDesempenho()) {
                retornar = vereador;
            }
        }
        return retornar;
    }

    public void addVereador(Vereador vereador) {
        vereadores.add(vereador);
    }

    public int qntVereadores() {
        return vereadores.size();
    }

}
