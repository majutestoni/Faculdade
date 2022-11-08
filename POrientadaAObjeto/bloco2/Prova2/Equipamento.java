package Prova2;

// Maria Júlia Testoni

import java.time.LocalDate;

public abstract class Equipamento {

    private String tipo;
    private String codPat;

    private int zonaEleitoral;

    private LocalDate dataFabri;

    public Equipamento(int zonaEleitoral, LocalDate dataFabri, String codPat) {
        super();

        setZonaEleitoral(zonaEleitoral);

        setDataFabri(dataFabri);
        setCodPat(codPat);
    }

    public abstract String getChave();

    public String getCodPat() {
        return codPat;
    }

    public void setCodPat(String codPat) {
        this.codPat = codPat;
    }

    public int getZonaEleitoral() {
        return zonaEleitoral;
    }

    public void setZonaEleitoral(int zonaEleitoral) {
        if (zonaEleitoral >= 1 && zonaEleitoral <= 106) {
            this.zonaEleitoral = zonaEleitoral;
        } else {
            IllegalArgumentException exc = new IllegalArgumentException("Zona eleitoral inválida");
            throw exc;
        }
    }

    public LocalDate getDataFabri() {
        return dataFabri;
    }

    public void setDataFabri(LocalDate dataFabri) {
        this.dataFabri = dataFabri;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        ;
        this.tipo = tipo;
    }

}
