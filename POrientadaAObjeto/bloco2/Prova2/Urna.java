package Prova2;

// Maria Júlia Testoni

import java.time.LocalDate;

public class Urna extends Equipamento {
    private int secao;
    private int qtdEleitores;
    private String versaoSoft;

    public Urna(int zonaEleitoral, LocalDate dataFabri, String codPat, int secao, int qtdEleitores, String versaoSoft) {
        super(zonaEleitoral, dataFabri, codPat);
        setSecao(secao);
        setQtdEleitores(qtdEleitores);
        setVersaoSoft(versaoSoft);
        setTipo("Urna");
    }

    public String getChave() {
        String gerada = "UE" + getVersaoSoft();
        LocalDate a = LocalDate.of(2020, 10, 31);
        if (getDataFabri().isAfter(a)) {
            gerada += "ATU";
        } else {
            gerada += "ANT";
        }

        gerada += getZonaEleitoral() + "" + getSecao();

        return gerada;
    }

    public int getSecao() {
        return secao;
    }

    public void setSecao(int secao) {
        this.secao = secao;
    }

    public int getQtdEleitores() {
        return qtdEleitores;
    }

    public void setQtdEleitores(int qtdEleitores) {
        this.qtdEleitores = qtdEleitores;
    }

    public String getVersaoSoft() {
        return versaoSoft;
    }

    public void setVersaoSoft(String versaoSoft) {
        this.versaoSoft = versaoSoft;
    }

}
