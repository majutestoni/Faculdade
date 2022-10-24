package list04ex04;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Apresentacao {

	private JFrame frame;
	private JTextField tfTitulo;
	private JTextField tfApresentar;
	private JTextField tfAprovar;
	private JTextField tfNumero;
	private JTextField tfLO;
	private JTextField tfVotos;
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private HashMap<String, ProjetoDeLei> projetos = new HashMap<>();

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
		frame.setBounds(100, 100, 544, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JComboBox<String> cbTipoProjeto = new JComboBox<String>();
		cbTipoProjeto.setModel(new DefaultComboBoxModel<String>(new String[] { "Projeto de Lei", "PL Complementar" }));
		cbTipoProjeto.setToolTipText("Projeto de Lei\r\nPL Complementar");
		cbTipoProjeto.setBounds(386, 8, 117, 25);
		frame.getContentPane().add(cbTipoProjeto);
		cbTipoProjeto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (cbTipoProjeto.getSelectedItem().toString().equals("PL Complementar")) {
					tfLO.setEnabled(true);
					tfVotos.setEnabled(true);

				}else {
					tfLO.setEnabled(false);
					tfVotos.setEnabled(false);
				}

			}
		});

		JLabel lblNewLabel = new JLabel("Título do Projeto:");
		lblNewLabel.setBounds(12, 12, 100, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Data de Apresentação:");
		lblNewLabel_1.setBounds(12, 40, 138, 16);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Data de Aprovação:");
		lblNewLabel_2.setBounds(12, 68, 117, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Nº do Projeto:");
		lblNewLabel_3.setBounds(12, 96, 85, 16);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Artigo Lei Orgânica:");
		lblNewLabel_4.setBounds(12, 122, 117, 16);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Qtd. de Votos Favoráveis:");
		lblNewLabel_5.setBounds(12, 150, 150, 16);
		frame.getContentPane().add(lblNewLabel_5);

		tfTitulo = new JTextField();
		tfTitulo.setBounds(113, 10, 150, 20);
		frame.getContentPane().add(tfTitulo);
		tfTitulo.setColumns(10);

		tfApresentar = new JTextField();
		tfApresentar.setBounds(149, 38, 94, 20);
		frame.getContentPane().add(tfApresentar);
		tfApresentar.setColumns(10);

		tfAprovar = new JTextField();
		tfAprovar.setColumns(10);
		tfAprovar.setBounds(134, 68, 94, 20);
		frame.getContentPane().add(tfAprovar);

		tfNumero = new JTextField();
		tfNumero.setBounds(93, 96, 57, 20);
		frame.getContentPane().add(tfNumero);
		tfNumero.setColumns(10);

		tfLO = new JTextField();
		tfLO.setColumns(10);
		tfLO.setBounds(135, 120, 75, 20);
		frame.getContentPane().add(tfLO);
		tfLO.setEnabled(false);

		tfVotos = new JTextField();
		tfVotos.setColumns(10);
		tfVotos.setBounds(168, 148, 75, 20);
		frame.getContentPane().add(tfVotos);
		tfVotos.setEnabled(false);

		JLabel lblNewLabel_6 = new JLabel("Tipo de Projeto:");
		lblNewLabel_6.setBounds(293, 12, 94, 16);
		frame.getContentPane().add(lblNewLabel_6);

		JButton btnCadastrar = new JButton("Cadastrar Projeto");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProjetoDeLei pl = null;
					if (cbTipoProjeto.getSelectedIndex() == 0) {
						pl = new ProjetoDeLei(tfTitulo.getText(), LocalDate.parse(tfApresentar.getText(), df),
								LocalDate.parse(tfAprovar.getText(), df), Integer.parseInt(tfNumero.getText()));
					} else if (cbTipoProjeto.getSelectedIndex() == 1) {
						pl = new ProjetoDeLeiComplementar(tfTitulo.getText(),
								LocalDate.parse(tfApresentar.getText(), df), LocalDate.parse(tfAprovar.getText(), df),
								Integer.parseInt(tfNumero.getText()), Integer.parseInt(tfLO.getText()),
								Integer.parseInt(tfVotos.getText()));
					}
					projetos.put(pl.getTitulo(), pl); // ADICIONA OS PROJETOS DE LEI AO HASH.NÃO TENHO CTTZ SE A CHAVE É
														// A MAIS APROPRIADA... - MARTIN
					JOptionPane.showMessageDialog(frame, "Projeto Adicionado");
				} catch (DateTimeException e2) {
					JOptionPane.showMessageDialog(frame, e2);
				} catch (IllegalArgumentException e3) {
					JOptionPane.showMessageDialog(frame, e3.getMessage());
				}
			}
		});
		btnCadastrar.setBounds(31, 178, 179, 26);
		frame.getContentPane().add(btnCadastrar);
	}
}
