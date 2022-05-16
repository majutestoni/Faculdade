        // Ex09
        System.out.println("Informe t: ");
        int t = scan.nextInt();
        String teste = "";
        int contadorT = 0;
        for (int k = 0; k < t; k++) {
            System.out.println("Digite o nome: ");
            String nomeT = scan.next();
            System.out.println("Digite a idade");
            int idadeT = scan.nextInt();

            if (idadeT == 18) {
                teste = teste + nomeT + ", ";
            }
            if (idadeT > 20) {
                contadorT = contadorT + 1;
            }
        }
        System.out.println("Alunos com 18: " + teste);
        System.out.println("A quantidade de alunos que tem 20 é " + contadorT);
