package Aula;

public class NameGenerator2 {
    public static String generateStarWarsName(String[] entrada) {
        String[] ex = entrada;     
        String letrasNome = ex[0].substring(0, 2);
        String letrasSobrenomeMae = ex[1].substring(0, 2);
        String letrasCidade = ex[2].substring(0, 3);
        
        String[] teste = ex[0].split(" ");
        int tamanho = teste.length - 1;
        String letrasSobrenome = teste[tamanho].substring(0,3);

        String nomeFinal = letrasSobrenome + letrasNome + " " + letrasSobrenomeMae + letrasCidade;

        return nomeFinal;
    }
}
