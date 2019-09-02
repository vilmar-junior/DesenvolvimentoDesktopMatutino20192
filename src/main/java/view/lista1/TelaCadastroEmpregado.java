package view.lista1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;

import controller.lista1.EmpregadoController;
import model.dao.lista1.EmpregadoDAO;
import model.entity.lista1.Diretor;
import model.entity.lista1.Empregado;
import model.entity.lista1.EmpregadoOperacional;
import model.entity.lista1.Gerente;

import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class TelaCadastroEmpregado {

	private static final Integer IDADE_MINIMA_EMPREGADO = 14;
	private JFrame frmCadastroDeEmpregado;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtSalarioBruto;
	private JTextField txtComissao;
	private JComboBox cbTipo;
	private JRadioButton rbSexoM;
	private JRadioButton rbSexoF;
	private JSpinner spnIdade;
	private JLabel lblComissao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEmpregado window = new TelaCadastroEmpregado();
					window.frmCadastroDeEmpregado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroEmpregado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeEmpregado = new JFrame();
		frmCadastroDeEmpregado.setTitle("Cadastro de empregado");
		frmCadastroDeEmpregado.setBounds(100, 100, 450, 285);
		frmCadastroDeEmpregado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeEmpregado.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(22, 56, 46, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(22, 81, 46, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblCpf);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(22, 106, 46, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblSexo);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(22, 131, 46, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblIdade);
		
		JLabel lblSalarioBruto = new JLabel("Salário Bruto:");
		lblSalarioBruto.setBounds(22, 156, 83, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblSalarioBruto);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(22, 181, 46, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblTipo);
		
		lblComissao = new JLabel("Comissão:");
		lblComissao.setBounds(22, 206, 67, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblComissao);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = txtNome.getText();
				String cpf = txtCPF.getText();
				
				int idade = (Integer) spnIdade.getValue();
				
				char sexo = ' ';
				if(rbSexoM.isSelected()) {
					sexo = 'M';
				}else if(rbSexoF.isSelected()) {
					sexo = 'F';
				}
				
				String tipoSelecionado = (String) cbTipo.getSelectedItem(); 
				
				EmpregadoController controlador = new EmpregadoController();
				String mensagem = controlador.salvar(nome, cpf, sexo, idade, 
						txtSalarioBruto.getText(), txtComissao.getText(), tipoSelecionado);
				
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		btnSalvar.setBounds(265, 52, 136, 174);
		frmCadastroDeEmpregado.getContentPane().add(btnSalvar);
		
		txtNome = new JTextField();
		txtNome.setBounds(111, 56, 120, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(111, 81, 120, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtCPF);
		txtCPF.setColumns(10);
		
		rbSexoM = new JRadioButton("M");
		rbSexoM.setBounds(110, 105, 40, 20);
		frmCadastroDeEmpregado.getContentPane().add(rbSexoM);
		
		rbSexoF = new JRadioButton("F");
		rbSexoF.setBounds(185, 105, 40, 20);
		frmCadastroDeEmpregado.getContentPane().add(rbSexoF);
		
		ButtonGroup bgSexo = new ButtonGroup();
		bgSexo.add(rbSexoM);
		bgSexo.add(rbSexoF);
		
		txtSalarioBruto = new JTextField();
		txtSalarioBruto.setBounds(111, 156, 120, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtSalarioBruto);
		txtSalarioBruto.setColumns(10);
		
		String[] tipos = {"Selecione", 
						  EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_DIRETOR, 
						  EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_GERENTE, 
						  EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_OPERACIONAL };
		cbTipo = new JComboBox(tipos);
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tipoSelecionado = (String) cbTipo.getSelectedItem();
				
				if(tipoSelecionado.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_DIRETOR)
						|| tipoSelecionado.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_GERENTE)) {
					lblComissao.setVisible(true);
					txtComissao.setVisible(true);
				}else {
					lblComissao.setVisible(false);
					txtComissao.setVisible(false);
					txtComissao.setText("");
				}
			}
		});
		cbTipo.setBounds(111, 181, 120, 20);
		frmCadastroDeEmpregado.getContentPane().add(cbTipo);
		
		txtComissao = new JTextField();
		txtComissao.setBounds(111, 206, 120, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtComissao);
		txtComissao.setColumns(10);
		
		spnIdade = new JSpinner();
		spnIdade.setValue(IDADE_MINIMA_EMPREGADO);
		spnIdade.setBounds(111, 131, 120, 20);
		frmCadastroDeEmpregado.getContentPane().add(spnIdade);
	}
}