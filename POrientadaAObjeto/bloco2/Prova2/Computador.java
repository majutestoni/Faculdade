package Prova2;

// Maria Júlia Testoni

import java.time.LocalDate;

public class Computador extends Equipamento {
    private String endApu;
    private int qtdUrnas;
    private String sistema;
    private String versaoSO;

    public Computador(String endApu, int zonaEleitoral, LocalDate dataFabri, String codPat, String sistema,
            String versaoSO, int qtdUrnas) {
        super(zonaEleitoral, dataFabri, codPat);
        setEndApu(endApu);
        setQtdUrnas(qtdUrnas);
        setSistema(sistema);
        setVersaoSO(versaoSO);
        setTipo("Computador");
    }

    public String getChave() {
        return "CA" + getEndApu().charAt(4) + getZonaEleitoral() + getQtdUrnas() + getCodPat().length();
    }

    public String getEndApu() {
        return endApu;
    }

    public void setEndApu(String endApu) {
        this.endApu = endApu;
    }

    public int getQtdUrnas() {
        return qtdUrnas;
    }

    public void setQtdUrnas(int qtdUrnas) {
        this.qtdUrnas = qtdUrnas;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getVersaoSO() {
        return versaoSO;
    }

    public void setVersaoSO(String versaoSO) {
        this.versaoSO = versaoSO;
    }

}
