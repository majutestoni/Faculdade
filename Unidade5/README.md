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


        // 18
        System.out.println("Digite o canal: ");
        int canal = scan.nextInt();
        int quantasPessoas = 0;
        int pessoas4 = 0;
        int pessoas5 = 0;
        int pessoas9 = 0;
        int pessoas12 = 0;
        while (canal != 0) {
            System.out.println("Informe quantas pessoas estão assistindo");
            quantasPessoas = scan.nextInt();
            switch (canal) {
                case 4:
                    pessoas4 = +quantasPessoas;
                    break;
                case 5:
                    pessoas5 = +quantasPessoas;
                    break;
                case 9:
                    pessoas9 = +quantasPessoas;
                    break;
                case 12:
                    pessoas12 = +quantasPessoas;
                    break;
                default:
                    System.out.println("Canal invalido");
                    break;
            }
            System.out.println("Informe o canal");
            canal = scan.nextInt();
        }
        double pessoasTotal = pessoas4 + pessoas5 + pessoas9 + pessoas12;
        double audiencia4 = (pessoas4 / pessoasTotal) * 100;
        double audiencia5 = (pessoas5 / pessoasTotal) * 100;
        double audiencia9 = (pessoas9 / pessoasTotal) * 100;
        double audiencia12 = (pessoas12 / pessoasTotal) * 100;
        System.out.println(
                "A audiencia do canal 4 é " + audiencia4 + "%, canal 5 " + audiencia5 + "%, canal 9 " + audiencia9
                        + "% e canal 12 " + audiencia12 + "%");
