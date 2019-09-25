package view.painel.exemplos.aula10;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.bo.exemplos.ProdutoBO;
import model.entity.exemplos.Produto;

public class InternalFrameCadastroProduto extends JInternalFrame {

	protected static final String NOME_VALOR_BARATO = "Barato";
	protected static final String NOME_VALOR_MEDIO = "Médio";
	protected static final String NOME_VALOR_CARO = "Caro";

	protected static final double PRECO_MAXIMO_VALOR_BARATO = 9.99;
	protected static final double PRECO_MAXIMO_VALOR_MEDIO = 99.9;
	protected static final double PRECO_MAXIMO_VALOR_CARO = 999999999;

	private ProdutoBO bo = new ProdutoBO();
	private Produto produto = new Produto();
	private JTextField txtNome;
	private JTextField txtFabricante;
	private JTextField txtPeso;
	private JComboBox cbValor;
	private JTextField txtId;
	private JButton btnSalvar;

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(JButton btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalFrameCadastroProduto frame = new InternalFrameCadastroProduto();
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
	public InternalFrameCadastroProduto() {

		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 47, 71, 14);
		getContentPane().add(lblNome);

		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setBounds(10, 72, 71, 14);
		getContentPane().add(lblFabricante);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(10, 97, 71, 14);
		getContentPane().add(lblValor);

		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(10, 122, 71, 14);
		getContentPane().add(lblPeso);

		txtNome = new JTextField();
		txtNome.setBounds(75, 44, 249, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		txtFabricante = new JTextField();
		txtFabricante.setBounds(75, 69, 249, 20);
		getContentPane().add(txtFabricante);
		txtFabricante.setColumns(10);

		txtPeso = new JTextField();
		txtPeso.setBounds(75, 119, 249, 20);
		getContentPane().add(txtPeso);
		txtPeso.setColumns(10);

		String[] cores = { "---Selecione---", NOME_VALOR_BARATO, NOME_VALOR_MEDIO, NOME_VALOR_CARO };
		cbValor = new JComboBox(cores);
		cbValor.setBounds(75, 94, 249, 20);
		getContentPane().add(cbValor);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Implementa a regra de negócio
				JOptionPane.showMessageDialog(null, "Salvou produto");
//				ProdutoController controlador = new ProdutoController();
//				Produto produto = construirProduto();
//
//				String mensagem = controlador.salvar(produto);
//				JOptionPane.showMessageDialog(null, mensagem);
//				limparTela();
			}
		});

		btnSalvar.setBounds(10, 183, 329, 46);
		getContentPane().add(btnSalvar);

		JLabel lblId = new JLabel("ID??:");
		lblId.setBounds(10, 11, 46, 14);
		getContentPane().add(lblId);

		txtId = new JTextField();
		txtId.setBounds(75, 8, 146, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				produto = bo.buscarProdutoPorId(txtId.getText());

				if (produto != null) {
					// Preencher os campos da tela
					txtNome.setText(produto.getNome());
					txtFabricante.setText(produto.getFabricante());
					txtPeso.setText(produto.getPeso() + "");
					if (produto.getValor() < 10) {
						cbValor.setSelectedItem(NOME_VALOR_BARATO);
					} else if (produto.getValor() < 100) {
						cbValor.setSelectedItem(NOME_VALOR_MEDIO);
					} else {
						cbValor.setSelectedItem(NOME_VALOR_CARO);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Produto não encontrado");
				}
			}
		});
		btnBuscar.setBounds(223, 7, 101, 23);
		getContentPane().add(btnBuscar);

	}

	protected void limparTela() {
		produto = new Produto();
		txtId.setText("");
		txtNome.setText("");
		txtFabricante.setText("");
		txtPeso.setText("");
		cbValor.setSelectedIndex(0);
	}

	public Produto construirProduto() {
		produto.setNome(txtNome.getText());
		produto.setFabricante(txtFabricante.getText());
		String strPeso = txtPeso.getText();

		if (strPeso.trim() != "") {
			produto.setPeso(Double.parseDouble(strPeso));
		}

//		switch (cbValor.getSelectedItem().toString()) {
//		case NOME_VALOR_BARATO:
//			produto.setValor(1.0);
//			break;
//		case NOME_VALOR_MEDIO:
//			produto.setValor(10.0);
//			break;
//		case NOME_VALOR_CARO:
//			produto.setValor(100.0);
//			break;
//
//		default:
//			produto.setValor(1.0);
//			break;
//		}
		return produto;
	}
}
