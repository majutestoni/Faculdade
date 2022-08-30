package lex08list2;

public class PassageirorHora {
    private int[][][] matriz = new int[12][30][24];

    public void adiciona(int dia, int mes, int hora) {
        matriz[mes - 1][dia - 1][hora]++;
    }

    public int quantosPAssageiros(int dia, int mes) {
        int contador = 0;
        for (int i = 0; i < matriz[mes][dia].length; i++) {
            contador = contador + matriz[mes][dia][i];
        }

        return contador;
    }

    public int mesMenorFlux() {

        int contador = 0;
        int mesMenor = 100;

        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                for (int j2 = 0; j2 < this.matriz[i][j].length; j2++) {
                    contador = contador + this.matriz[i][j][j2];
                }
            }
            if (contador < mesMenor) {
                mesMenor = i;
            }
            contador = 0;
        }

        return mesMenor + 1;
    }

    public String picoTransporte() {
        int contador = 0;
        int mes = 0;
        int dia = 0;
        int hora = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                for (int j2 = 0; j2 < matriz[i][j].length; j2++) {
                    if (matriz[i][j][j2] > contador) {
                        contador = matriz[i][j][j2];
                        hora = j2;
                        dia = j;
                        mes = i;
                    }
                }
            }
        }

        String teste = "mes: " + (mes + 1) + " dia: " + (dia + 1) + " e hora: " + hora;

        return teste;
    }
}
