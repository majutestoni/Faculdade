			public void actionPerformed(ActionEvent e) {
				String str = "CPF nao encontrado";

				// usando forEach
				for (Contribuinte c : contribuintes) {
					if (c.getCpf().equals(tfCpfConsulta.getText())) {
						str = "\n" + c.getNome() + " - " + c.getCpf() + " " + c.getRendaAnual() + " "
								+ c.calcularImposto();
					}
				}

				JOptionPane.showMessageDialog(frame, str);

				// usando HashMap

				Contribuinte c = contriHashMap.get(tfCpfConsulta.getText());
				if (c != null) {
					str = "\n" + c.getNome() + " - " + c.getCpf() + " " + c.getRendaAnual() + " " + c.calcularImposto();
				}
			}