package view.aula05;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.aula05.ClienteController;
import controller.aula05.EnderecoController;
import controller.aula05.TelefoneController;
import model.entity.aula05.Cliente;
import model.entity.aula05.Endereco;
import model.entity.aula05.Telefone;

public class TelaCadastroCliente extends JFrame {

	private static final long serialVersionUID = 4576878064669619998L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JFormattedTextField txtCPF;
	private JTextField txtCodigoPais;
	private JTextField txtDdd;
	private JTextField txtNumero;
	private JTable tblTelefones;
	private JLabel lblTelefonesCadastrados;
	private String[] colunasTabelaTelefones = {"País", "DDD", "Número", "Tipo"};
	private JComboBox cbEndereco;
	private JButton btnSalvar;
	private JComboBox cbTipo;
	private ArrayList<Telefone> telefonesDoNovoCliente;
	private ArrayList<Endereco> todosOsEnderecos;
	private String[] tipos = {TelefoneController.TIPO_TELEFONE_FIXO, TelefoneController.TIPO_TELEFONE_MOVEL};
	private Cliente novoCliente = null;
	private JButton btnAdicionarTelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente frame = new TelaCadastroCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroCliente() {
		setTitle("Cadastro de cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 10, 50, 20);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(70, 10, 95, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(180, 10, 70, 20);
		contentPane.add(lblSobrenome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(250, 10, 100, 20);
		contentPane.add(txtSobrenome);
		txtSobrenome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(370, 10, 50, 20);
		contentPane.add(lblCpf);
		
		MaskFormatter mascaraCpf;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCPF = new JFormattedTextField(mascaraCpf);
		} catch (ParseException e) {
		}
		txtCPF.setBounds(400, 10, 100, 20);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 43, 60, 14);
		contentPane.add(lblEndereco);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 100, 490, 2);
		contentPane.add(separator);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClienteController cliController = new ClienteController();
				String nomeDigitado = txtNome.getText();
				String sobrenomeDigitado = txtSobrenome.getText();
				String cpfDigitado = txtCPF.getText().replace("-", "").replace(".", "");
				Endereco enderecoSelecionado = (Endereco) cbEndereco.getSelectedItem();
				
				String mensagem = cliController.validarCamposSalvar(nomeDigitado, 
						sobrenomeDigitado, cpfDigitado, enderecoSelecionado);
				
				if(mensagem.isEmpty()) {
					novoCliente = new Cliente(nomeDigitado, sobrenomeDigitado, cpfDigitado, new ArrayList<Telefone>(), enderecoSelecionado);
					novoCliente = cliController.salvar(novoCliente);
					
					if(novoCliente.getId() > 0) {
						btnAdicionarTelefone.setEnabled(true);
						btnSalvar.setEnabled(false);
					}
				}else {
					JOptionPane.showMessageDialog(null, mensagem, "Aten��o", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(220, 70, 100, 23);
		contentPane.add(btnSalvar);
		
		JLabel lblNovoTelefone = new JLabel("Novo telefone");
		lblNovoTelefone.setForeground(Color.WHITE);
		lblNovoTelefone.setBackground(Color.DARK_GRAY);
		lblNovoTelefone.setOpaque(true);
		lblNovoTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNovoTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovoTelefone.setBounds(10, 110, 490, 20);
		contentPane.add(lblNovoTelefone);
		
		JLabel lblCodigoPais = new JLabel("C\u00F3digo pa\u00EDs:");
		lblCodigoPais.setBounds(10, 140, 70, 20);
		contentPane.add(lblCodigoPais);
		
		txtCodigoPais = new JTextField();
		txtCodigoPais.setBounds(85, 140, 30, 20);
		contentPane.add(txtCodigoPais);
		txtCodigoPais.setColumns(10);
		
		JLabel lblDdd = new JLabel("DDD:");
		lblDdd.setBounds(120, 140, 40, 20);
		contentPane.add(lblDdd);
		
		txtDdd = new JTextField();
		txtDdd.setBounds(160, 140, 30, 20);
		contentPane.add(txtDdd);
		txtDdd.setColumns(10);
		
		JLabel lblNumero = new JLabel("N\u00FAmero:");
		lblNumero.setBounds(200, 140, 50, 20);
		contentPane.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(255, 140, 78, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(343, 140, 30, 20);
		contentPane.add(lblTipo);
		
		cbTipo = new JComboBox(tipos);
		cbTipo.setBounds(383, 140, 117, 20);
		cbTipo.setSelectedIndex(-1);
		contentPane.add(cbTipo);
		
		btnAdicionarTelefone = new JButton("Adicionar telefone");
		btnAdicionarTelefone.setEnabled(false);
		btnAdicionarTelefone.setBounds(170, 170, 200, 20);
		contentPane.add(btnAdicionarTelefone);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 200, 490, 2);
		contentPane.add(separator_1);
		
		tblTelefones = new JTable();
		tblTelefones.setBounds(10, 240, 490, 200);
		contentPane.add(tblTelefones);
		
		limparTabela();
		
		lblTelefonesCadastrados = new JLabel("Telefones do cliente");
		lblTelefonesCadastrados.setForeground(Color.WHITE);
		lblTelefonesCadastrados.setBackground(Color.DARK_GRAY);
		
		//Para que a cor de fundo do label apareça
		lblTelefonesCadastrados.setOpaque(true);
		
		lblTelefonesCadastrados.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefonesCadastrados.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefonesCadastrados.setBounds(10, 208, 490, 20);
		contentPane.add(lblTelefonesCadastrados);
		
		//C�digo do construtor da tela
		consultarEnderecos();
		cbEndereco = new JComboBox(todosOsEnderecos.toArray());
		cbEndereco.setBounds(70, 40, 430, 20);
		cbEndereco.setSelectedIndex(-1);
		contentPane.add(cbEndereco);
	}
	
	private void consultarEnderecos() {
		EnderecoController controller = new EnderecoController();
		todosOsEnderecos = controller.consultarTodos();
	}
	
	protected void atualizarTabelaTelefones() {
		TelefoneController controller = new TelefoneController();
		
		if(novoCliente != null && novoCliente.getId() > 0) {
			telefonesDoNovoCliente = controller.consultarTelefonesDoCliente(novoCliente.getId());
		}
		
		limparTabela();
		
		DefaultTableModel model = (DefaultTableModel) tblTelefones.getModel();
		for (Telefone telefone : telefonesDoNovoCliente) {
			String[] novaLinha = new String[5];
			novaLinha[0] = telefone.getCodigoPais();
			novaLinha[1] = telefone.getDdd();
			novaLinha[2] = telefone.getNumero();
			novaLinha[3] = telefone.getTipoLinha();
			model.addRow(novaLinha);
		}
	}
	
	private void limparTabela() {
		tblTelefones.setModel(new DefaultTableModel(
				new Object[][] {
					colunasTabelaTelefones,
				},
				colunasTabelaTelefones
				));
	}
}
