package view.lista1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

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

public class TelaCadastroEmpregado {

	private JFrame frmCadastroDeEmpregado;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtIdade;
	private JTextField txtSalarioBruto;
	private JTextField txtComissao;
	private JComboBox cbTipo;
	private JRadioButton rbSexoM;
	private JRadioButton rbSexoF;

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
		
		JLabel lblComisso = new JLabel("Comissão:");
		lblComisso.setBounds(22, 206, 67, 14);
		frmCadastroDeEmpregado.getContentPane().add(lblComisso);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//TODO é apenas um EXEMPLO, JAMAIS CHAMAR DAO DIRETO DA TELA!
				EmpregadoDAO dao = new EmpregadoDAO();
				Empregado emp = null;
				String nome = txtNome.getText();
				String cpf = txtCPF.getText();
				
				//TODO cuidado, pois pode gerar exceção
				int idade = Integer.parseInt(txtIdade.getText());
				
				char sexo = ' ';
				if(rbSexoM.isSelected()) {
					sexo = 'M';
				}else if(rbSexoF.isSelected()) {
					sexo = 'F';
				}
				double salarioBruto = Double.parseDouble(txtSalarioBruto.getText());
				double comissao = Double.parseDouble(txtComissao.getText());
				
				String tipoSelecionado = (String) cbTipo.getSelectedItem(); 
				
				if(tipoSelecionado.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_DIRETOR)) {
					emp = new Diretor(nome, cpf, sexo, idade, salarioBruto, comissao);
				}else if(tipoSelecionado.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_GERENTE)) {
					emp = new Gerente(nome, cpf, sexo, idade, salarioBruto, comissao);
				}else if (tipoSelecionado.equals(EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_OPERACIONAL)) {
					emp = new EmpregadoOperacional(nome, cpf, sexo, idade, salarioBruto);
				}
				
				emp = dao.salvar(emp);
				
				if(emp.getId() > 0) {
					JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar!", "Atenção", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(201, 52, 200, 169);
		frmCadastroDeEmpregado.getContentPane().add(btnSalvar);
		
		txtNome = new JTextField();
		txtNome.setBounds(95, 53, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(95, 78, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtCPF);
		txtCPF.setColumns(10);
		
		rbSexoM = new JRadioButton("M");
		rbSexoM.setBounds(95, 102, 39, 23);
		frmCadastroDeEmpregado.getContentPane().add(rbSexoM);
		
		rbSexoF = new JRadioButton("F");
		rbSexoF.setBounds(149, 102, 46, 23);
		frmCadastroDeEmpregado.getContentPane().add(rbSexoF);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(95, 128, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtIdade);
		txtIdade.setColumns(10);
		
		txtSalarioBruto = new JTextField();
		txtSalarioBruto.setBounds(95, 153, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtSalarioBruto);
		txtSalarioBruto.setColumns(10);
		
		String[] tipos = {EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_DIRETOR, 
						  EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_GERENTE, 
						  EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_OPERACIONAL };
		cbTipo = new JComboBox(tipos);
		cbTipo.setSelectedIndex(-1);
		cbTipo.setBounds(95, 178, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(cbTipo);
		
		txtComissao = new JTextField();
		txtComissao.setBounds(95, 203, 86, 20);
		frmCadastroDeEmpregado.getContentPane().add(txtComissao);
		txtComissao.setColumns(10);
	}
}