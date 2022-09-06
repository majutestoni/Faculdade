package ex01list2;

public

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Apresentacao {

	private JFrame frame;
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfUF;
	private JTextField tfRenda;
	private ArrayList<Contribuinte> contribuintes = new ArrayList<>();
	private HashMap<String, Contribuinte> contriHashMap = new HashMap<>();
	private JLabel lblNewLabel_4;
	private JTextField tfConsulta;
	private JButton btnNewButton_1;
	private JTextField tfCpfConsulta;

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
		frame.setBounds(100, 100, 429, 283);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome do contribuinte:");
		lblNewLabel.setBounds(10, 11, 123, 14);
		frame.getContentPane().add(lblNewLabel);

		tfNome = new JTextField();
		tfNome.setBounds(143, 8, 189, 20);
		frame.getContentPane().add(tfNome);
		tfNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		tfCPF = new JTextField();
		tfCPF.setBounds(143, 33, 108, 20);
		frame.getContentPane().add(tfCPF);
		tfCPF.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Estado (UF):");
		lblNewLabel_2.setBounds(10, 61, 78, 14);
		frame.getContentPane().add(lblNewLabel_2);

		tfUF = new JTextField();
		tfUF.setBounds(143, 58, 46, 20);
		frame.getContentPane().add(tfUF);
		tfUF.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Renda anual: R$");
		lblNewLabel_3.setBounds(10, 86, 108, 14);
		frame.getContentPane().add(lblNewLabel_3);

		tfRenda = new JTextField();
		tfRenda.setBounds(143, 83, 108, 20);
		frame.getContentPane().add(tfRenda);
		tfRenda.setColumns(10);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contribuinte contrib;
				contrib = new Contribuinte();
				contrib.setNome(tfNome.getText());
				contrib.setCpf(tfCPF.getText());
				contrib.setUf(tfUF.getText());
				contrib.setRendaAnual(Double.parseDouble(tfRenda.getText()));
				double imposto = contrib.calcularImposto();
				String str = "O contribuinte " + contrib.getNome() + " pagarÃ¡ R$" + imposto;
				JOptionPane.showMessageDialog(frame, str);
				contribuintes.add(contrib);
				contriHashMap.put(contrib.getCpf(), contrib);
			}
		});
		btnNewButton.setBounds(143, 114, 89, 23);
		frame.getContentPane().add(btnNewButton);

		lblNewLabel_4 = new JLabel("Imposto a consultar R$");
		lblNewLabel_4.setBounds(11, 175, 122, 13);
		frame.getContentPane().add(lblNewLabel_4);

		tfConsulta = new JTextField();
		tfConsulta.setBounds(133, 172, 118, 19);
		frame.getContentPane().add(tfConsulta);
		tfConsulta.setColumns(10);

		btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double valorConsulta = Double.parseDouble(tfConsulta.getText());
				String str = "Contribuites com imposto acima de R$" + valorConsulta + "\n";
				for (int i = 0; i < contribuintes.size(); i++) {
					Contribuinte c = contribuintes.get(i);
					if (c.calcularImposto() > valorConsulta) {
						str += "\n" + c.getNome() + " - " + c.getCpf() + " " + c.getRendaAnual() + " "
								+ c.calcularImposto();
					}
				}
				JOptionPane.showMessageDialog(frame, str);
			}
		});
		btnNewButton_1.setBounds(262, 171, 85, 21);
		frame.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_5 = new JLabel("CPF a consultar");
		lblNewLabel_5.setBounds(10, 211, 123, 13);
		frame.getContentPane().add(lblNewLabel_5);

		tfCpfConsulta = new JTextField();
		tfCpfConsulta.setBounds(136, 208, 96, 19);
		frame.getContentPane().add(tfCpfConsulta);
		tfCpfConsulta.setColumns(10);

		JButton btnConsultaCpf = new JButton("OK");
		btnConsultaCpf.addActionListener(new ActionListener() {
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
		});
		btnConsultaCpf.setBounds(247, 207, 85, 21);
		frame.getContentPane().add(btnConsultaCpf);
	}
}Apresentacao{

}
