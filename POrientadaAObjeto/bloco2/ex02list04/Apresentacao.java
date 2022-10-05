package ex02list04;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
	private JTextField tfNomeCurso;
	private JTextField tfSiglaCurso;
	private JTextField tfFormaIngresso;
	private JTextField tfAno;
	private boolean teste;

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
		frame.setBounds(100, 100, 550, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(41, 84, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		tfNome = new JTextField();
		tfNome.setBounds(41, 97, 146, 20);
		frame.getContentPane().add(tfNome);
		tfNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data de nascimento");
		lblNewLabel_1.setBounds(40, 128, 120, 14);
		frame.getContentPane().add(lblNewLabel_1);

		tfDataNascimento = new JTextField();
		tfDataNascimento.setBounds(41, 141, 146, 20);
		frame.getContentPane().add(tfDataNascimento);
		tfDataNascimento.setColumns(10);

		JButton bntAddAluno = new JButton("Adiciona Aluno");
		bntAddAluno.setEnabled(false);
		bntAddAluno.setBounds(186, 240, 146, 23);
		frame.getContentPane().add(bntAddAluno);

		tfNomeCurso = new JTextField();
		tfNomeCurso.setEnabled(false);
		tfNomeCurso.setBounds(326, 97, 147, 20);
		frame.getContentPane().add(tfNomeCurso);
		tfNomeCurso.setColumns(10);

		tfSiglaCurso = new JTextField();
		tfSiglaCurso.setEnabled(false);
		tfSiglaCurso.setBounds(326, 140, 147, 20);
		frame.getContentPane().add(tfSiglaCurso);
		tfSiglaCurso.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nome do curso");
		lblNewLabel_2.setBounds(325, 84, 92, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Sigla do Curso");
		lblNewLabel_3.setBounds(326, 128, 91, 14);
		frame.getContentPane().add(lblNewLabel_3);

		tfFormaIngresso = new JTextField();
		tfFormaIngresso.setEnabled(false);
		tfFormaIngresso.setBounds(326, 186, 147, 20);
		frame.getContentPane().add(tfFormaIngresso);
		tfFormaIngresso.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Forma de ingresso");
		lblNewLabel_4.setBounds(326, 173, 130, 14);
		frame.getContentPane().add(lblNewLabel_4);

		tfAno = new JTextField();
		tfAno.setEnabled(false);
		tfAno.setBounds(41, 185, 146, 20);
		frame.getContentPane().add(tfAno);
		tfAno.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Ano");
		lblNewLabel_5.setBounds(41, 172, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);

		JButton btnUniversidade = new JButton("Aluno Universidade");
		btnUniversidade.setBounds(267, 50, 130, 23);
		frame.getContentPane().add(btnUniversidade);

		JButton btnMedio = new JButton("Aluno Ensino Medio");
		btnMedio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				btnUniversidade.setEnabled(false);
				bntAddAluno.setEnabled(true);
				tfAno.setEnabled(true);
			}
		});
		btnMedio.setBounds(101, 50, 130, 23);
		frame.getContentPane().add(btnMedio);

		JButton btnConsultar = new JButton("Consultar alunos");
		btnConsultar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String str = "";
				for (Aluno aluno : alunos) {
					str += aluno.mostra() + "\n";
				}

				JOptionPane.showMessageDialog(frame, str);
			}
		});
		btnConsultar.setBounds(384, 240, 120, 23);
		frame.getContentPane().add(btnConsultar);

		btnUniversidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teste = true;
				btnMedio.setEnabled(false);
				bntAddAluno.setEnabled(true);
				tfNomeCurso.setEnabled(true);
				tfSiglaCurso.setEnabled(true);
				tfFormaIngresso.setEnabled(true);
			}
		});

		bntAddAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = tfNome.getText();
					LocalDate dataNascimento = LocalDate.parse(tfDataNascimento.getText(), formatter);
					int ano = 0;
					if (teste == false) {
						ano = Integer.parseInt(tfAno.getText());
					}
					String formaIngresso = tfFormaIngresso.getText();
					String nomeCurso = tfNomeCurso.getText();
					String siglaCurso = tfSiglaCurso.getText();
					Curso curso = new Curso(nomeCurso, siglaCurso);

					Aluno novoAluno;
					if (teste == false) {
						novoAluno = new AlunoMedio(nome, dataNascimento, ano);
						tfAno.setText("");
						tfAno.setEnabled(false);
						btnUniversidade.setEnabled(true);
					} else {
						novoAluno = new AlunoUniversitario(nome, dataNascimento, formaIngresso, curso);
						tfNomeCurso.setEnabled(false);
						tfNomeCurso.setText("");
						tfSiglaCurso.setEnabled(false);
						tfSiglaCurso.setText("");
						tfFormaIngresso.setEnabled(false);
						tfFormaIngresso.setText("");
						btnMedio.setEnabled(true);
					}

					alunos.add(novoAluno);
					JOptionPane.showMessageDialog(frame, "Aluno adicionado!");
					tfNome.setText("");
					tfDataNascimento.setText("");
					bntAddAluno.setEnabled(false);
				} catch (DateTimeParseException data) {
					JOptionPane.showMessageDialog(frame, "Data inválida. Por favor, tente novamente.");

				} catch (IllegalArgumentException mensagem) {
					JOptionPane.showMessageDialog(frame, mensagem.getMessage());
				}

			}
		});
	}
}