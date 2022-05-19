import java.util.Scanner;

public class teste {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 24
        System.out.println("Informe o limite de peso diario (KG)");
        double pesoDePescaLimite = scan.nextDouble();
        double pesoDePescaLimiteTotal = 0, pesoPeixe = 0;
        String querContinuarAPescar = "";

        while (pesoDePescaLimiteTotal <= pesoDePescaLimite) {
            System.out.println("deseja informar o peso de mais um peixe: s (SIM) / n (NÃO)?");
            querContinuarAPescar = scan.next();
            while (querContinuarAPescar.equals("s")) {
                System.out.println("Digite o peso do peixe(Em gramas)");
                pesoPeixe = scan.nextDouble();

                System.out.println(pesoDePescaLimiteTotal);
                System.out.println("deseja informar o peso de mais um peixe: s (SIM) / n (NÃO)?");
                querContinuarAPescar = scan.next();
            }
            pesoDePescaLimiteTotal = pesoDePescaLimiteTotal + pesoPeixe;

        }

        scan.close();
    }
}
