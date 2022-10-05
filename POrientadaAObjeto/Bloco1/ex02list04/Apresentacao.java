package ex02list04;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Apresentacao {

	private JFrame frame;
	private JTextField tfNome;
	private JTextField tfDataNascimento;
	public ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
		frame.setBounds(100, 100, 617, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(10, 23, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		tfNome = new JTextField();
		tfNome.setBounds(10, 36, 146, 20);
		frame.getContentPane().add(tfNome);
		tfNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data de nascimento");
		lblNewLabel_1.setBounds(10, 88, 120, 14);
		frame.getContentPane().add(lblNewLabel_1);

		tfDataNascimento = new JTextField();
		tfDataNascimento.setBounds(10, 103, 146, 20);
		frame.getContentPane().add(tfDataNascimento);
		tfDataNascimento.setColumns(10);

		JButton bntAddAluno = new JButton("Adiciona Aluno");
		bntAddAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Aluno novoAluno = new AlunoMedio(tfNome.getText(), LocalDate.parse(tfDataNascimento.getText(), formatter));
					alunos.add(novoAluno);
				} catch (DateTimeParseException data) {
					JOptionPane.showMessageDialog(frame, "Data inválida. Por favor, tente novamente.");
				
				} catch (IllegalArgumentException mensagem) {
				JOptionPane.showMessageDialog(frame, mensagem.getMessage());
				}

			}
		});
		bntAddAluno.setBounds(10, 134, 146, 23);
		frame.getContentPane().add(bntAddAluno);
	}
}