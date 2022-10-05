package Trab02;

/* 
 * Jonathan Ilchemin Ribeiro
 * Maria Julia Testoni
 * Martin Lange de Assis
 */


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Apresentacao {

	private JFrame frame;
	private JTextField tfNome;
	private JTextField tfEndereco;
	private JTextField tfData;
	private JTextField tfNumero;
	private JTextField tfRamo;
	private HashMap<String, Telefone> telefones = new HashMap<>();
	private JTextField tfTelefone;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JTextField tfTipo;
	private JTextField tfOcorrencias;
	private JTextField tfInternet;
	private Telefone telefoneGlobal = new Telefone();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apresentacao window = new Apresentacao();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Apresentacao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 549, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(6, 71, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		tfNome = new JTextField();
		tfNome.setBounds(6, 84, 145, 20);
		frame.getContentPane().add(tfNome);
		tfNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Endereço Instalação:");
		lblNewLabel_2.setBounds(6, 115, 145, 14);
		frame.getContentPane().add(lblNewLabel_2);

		tfEndereco = new JTextField();
		tfEndereco.setBounds(6, 128, 145, 20);
		frame.getContentPane().add(tfEndereco);
		tfEndereco.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Data Instalação:");
		lblNewLabel_3.setBounds(6, 159, 145, 14);
		frame.getContentPane().add(lblNewLabel_3);

		tfData = new JTextField();
		tfData.setBounds(6, 171, 145, 20);
		frame.getContentPane().add(tfData);
		tfData.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Número:");
		lblNewLabel_4.setBounds(6, 202, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);

		tfNumero = new JTextField();
		tfNumero.setBounds(6, 215, 145, 20);
		frame.getContentPane().add(tfNumero);
		tfNumero.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Ramo:");
		lblNewLabel_5.setBounds(361, 156, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);

		tfRamo = new JTextField();
		tfRamo.setEnabled(false);
		tfRamo.setBounds(371, 175, 86, 20);
		frame.getContentPane().add(tfRamo);
		tfRamo.setColumns(10);

		JButton btnAddC = new JButton("ADD TELEFONE");
		btnAddC.setEnabled(false);
		btnAddC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Telefone telefone = new Telefone();
					telefone.setTipo(tfTipo.getText());
					telefone.setCliente(tfNome.getText());
					telefone.setEndereco(tfEndereco.getText());
					telefone.setDataInstalacao(LocalDate.parse(tfData.getText(), formatter));
					telefone.setNumero(tfNumero.getText());

					if (telefone.getTipo().equals("COMERCIAL")) {
						telefone.setRamoAtividade(tfRamo.getText());
						tfRamo.setText("");
						tfRamo.setEnabled(false);
					} else if (telefone.getTipo().equals("RESIDENCIAL")) {
						telefone.setInternet(tfInternet.getText());
						tfInternet.setText("");
						tfInternet.setEnabled(false);
					} else {
						telefone.setOcorrencias(Integer.parseInt(tfOcorrencias.getText()));
						tfOcorrencias.setText("");
						tfOcorrencias.setEnabled(false);
					}

					if (telefones.containsKey(telefone.getNumero())) {
						JOptionPane.showMessageDialog(frame, "Ja possui esse telefone.");
					} else {
						JOptionPane.showMessageDialog(frame, "Telefone Adicionado");

						telefones.put(telefone.getNumero(), telefone);
						
						tfData.setText("");
						tfEndereco.setText("");
						tfNumero.setText("");
						tfNome.setText("");
						tfTipo.setText("");
						btnAddC.setEnabled(false);
					}

				} catch (DateTimeParseException data) {
					JOptionPane.showMessageDialog(frame, "Data inválida. Por favor, tente novamente.");
				} catch (IllegalArgumentException mensagem) {
					JOptionPane.showMessageDialog(frame, mensagem.getMessage());
				}
			}
		});
		btnAddC.setBounds(361, 211, 125, 21);
		frame.getContentPane().add(btnAddC);

		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setBounds(172, 211, 46, 14);
		frame.getContentPane().add(lblNewLabel_5_1);

		JButton btnConsulta = new JButton("Consultar");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Telefone t = telefones.get(tfTelefone.getText());
				String str = "";
				if (t == null) {
					str = "Número não Encontrado";
				}
				if (t != null && t.getTipo().equals("COMERCIAL")) {
					str = "Nome: " + t.getCliente() + "\n" + "Endereço: " + t.getEndereco() + "\n" + "Data: "
							+ t.getDataInstalacao() + "\n" + "Número: " + t.getNumero() + "\n" + "Ramo: "
							+ t.getRamoAtividade();
				} else if (t != null && t.getTipo().equals("RESIDENCIAL")) {
					str = "Nome: " + t.getCliente() + "\n" + "Endereço: " + t.getEndereco() + "\n" + "Data: "
							+ t.getDataInstalacao() + "\n" + "Número: " + t.getNumero() + "\n" + "Conexão Internet? "
							+ t.getInternet();
				} else if (t != null && t.getTipo().equals("ESPECIALIZADO")) {
					str = "Nome: " + t.getCliente() + "\n" + "Endereço: " + t.getEndereco() + "\n" + "Data: "
							+ t.getDataInstalacao() + "\n" + "Número: " + t.getNumero() + "\n" + "Qtde de Ocorrências: "
							+ t.getOcorrencias();
				}
				JOptionPane.showMessageDialog(frame, str);
			}
		});
		btnConsulta.setBounds(172, 284, 89, 23);
		frame.getContentPane().add(btnConsulta);

		JButton btnCalcular = new JButton("Calcular faturamento");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float cont = 0;
				for (Telefone telefone : telefones.values()) {
					cont += telefone.getValorMensal();
				}
				String str = "O potencial faturamento da  foi de: " + cont;
				JOptionPane.showMessageDialog(frame, str);
			}
		});
		btnCalcular.setBounds(283, 284, 145, 23);
		frame.getContentPane().add(btnCalcular);

		JLabel lblNewLabel = new JLabel("Telefone:");
		lblNewLabel.setBounds(6, 288, 53, 14);
		frame.getContentPane().add(lblNewLabel);

		tfTelefone = new JTextField();
		tfTelefone.setBounds(57, 285, 109, 20);
		frame.getContentPane().add(tfTelefone);
		tfTelefone.setColumns(10);

		tfTipo = new JTextField();
		tfTipo.setBounds(6, 51, 145, 19);
		frame.getContentPane().add(tfTipo);
		tfTipo.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Tipo");
		lblNewLabel_6.setBounds(6, 37, 45, 13);
		frame.getContentPane().add(lblNewLabel_6);

		tfOcorrencias = new JTextField();
		tfOcorrencias.setEnabled(false);
		tfOcorrencias.setBounds(371, 84, 96, 19);
		frame.getContentPane().add(tfOcorrencias);
		tfOcorrencias.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Quantidade de Ocorrencias");
		lblNewLabel_7.setBounds(362, 72, 144, 13);
		frame.getContentPane().add(lblNewLabel_7);

		tfInternet = new JTextField();
		tfInternet.setEnabled(false);
		tfInternet.setText("");
		tfInternet.setBounds(370, 129, 96, 19);
		frame.getContentPane().add(tfInternet);
		tfInternet.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Internet");
		lblNewLabel_8.setBounds(361, 117, 45, 13);
		frame.getContentPane().add(lblNewLabel_8);

		JButton btnTipo = new JButton("ADD TIPO");
		btnTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telefoneGlobal.setTipo(tfTipo.getText());
				btnAddC.setEnabled(true);
				if (telefoneGlobal.getTipo().equals("COMERCIAL")) {
					tfRamo.setEnabled(true);
					tfInternet.setEnabled(false);
					tfOcorrencias.setEnabled(false);
				} else if (telefoneGlobal.getTipo().equals("RESIDENCIAL")) {
					tfRamo.setEnabled(false);
					tfInternet.setEnabled(true);
					tfOcorrencias.setEnabled(false);
				} else {
					tfRamo.setEnabled(false);
					tfInternet.setEnabled(false);
					tfOcorrencias.setEnabled(true);
				}
			}
		});
		btnTipo.setBounds(161, 50, 111, 23);
		frame.getContentPane().add(btnTipo);
	}
}
