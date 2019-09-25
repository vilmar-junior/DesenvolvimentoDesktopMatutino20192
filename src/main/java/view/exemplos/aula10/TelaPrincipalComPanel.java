package view.exemplos.aula10;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import view.painel.exemplos.aula10.PanelListagemClientes;

/**
 * Exemplo de menu com componentes do tipo JPanel (Aula 10)
 * 
 * @author Vilmar César Pereira Júnior
 *
 */
public class TelaPrincipalComPanel extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalComPanel frame = new TelaPrincipalComPanel();
					// Inicializa a tela principal MAXIMIZADA
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipalComPanel() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaPrincipalComPanel.class.getResource("/icones/icons8-сharlie-сhaplin.png")));
		setTitle("Tela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar mbPrincipal = new JMenuBar();
		setJMenuBar(mbPrincipal);

		JMenu mnProdutos = new JMenu("Produtos");
		mnProdutos.setIcon(new ImageIcon(TelaPrincipalComPanel.class.getResource("/icones/icons8-comprar.png")));
		mbPrincipal.add(mnProdutos);

		JMenuItem mntmCadastrarProduto = new JMenuItem("Cadastrar");
		mntmCadastrarProduto.setIcon(new ImageIcon(
				TelaPrincipalComPanel.class.getResource("/icones/icons8-adicionar-usuário-masculino.png")));
		mnProdutos.add(mntmCadastrarProduto);

		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmListar.setIcon(new ImageIcon(TelaPrincipalComPanel.class.getResource("/icones/icons8-cardápio.png")));
		mnProdutos.add(mntmListar);

		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setIcon(new ImageIcon(TelaPrincipalComPanel.class.getResource("/icones/icons8-usuário.png")));
		mbPrincipal.add(mnClientes);

		JMenuItem mntmCadastrarCliente = new JMenuItem("Cadastrar");
		mntmCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// troca o PAINEL
				PanelListagemClientes telaListagemClientes = new PanelListagemClientes();

				// Registra um listener para clicar no botão do painel
				telaListagemClientes.getBtnChamarPai().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// Lógica executada quando o botão do filho (o painel) for chamado
						JOptionPane.showMessageDialog(null, "Olá, eu sou o pai das telas!");
					}
				});

				setContentPane(telaListagemClientes);
			}
		});
		mntmCadastrarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mntmCadastrarCliente.setIcon(new ImageIcon(
				TelaPrincipalComPanel.class.getResource("/icones/icons8-adicionar-usuário-masculino.png")));
		mnClientes.add(mntmCadastrarCliente);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public void chamarPai() {
		JOptionPane.showMessageDialog(null, "Olá, eu sou o pai das telas!");
	}
}