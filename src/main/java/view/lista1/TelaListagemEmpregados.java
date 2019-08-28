package view.lista1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.lista1.EmpregadoController;
import model.entity.lista1.Empregado;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaListagemEmpregados extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable tblEmpregados;
	private JButton btnExcluir;
	private JButton btnEditar;
	private String[] colunasTabelaEmpregados = {"Nome", "CPF", "Sexo", "Idade", "Salário"};
	private ArrayList<Empregado> empregados;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemEmpregados frame = new TelaListagemEmpregados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaListagemEmpregados() {
		setTitle("Consulta de empregados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarTabelaEmpregados();
			}
		});

		table = new JTable();

		tblEmpregados = new JTable();
		tblEmpregados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int indiceSelecionado = tblEmpregados.getSelectedRow();

				if(indiceSelecionado > 0) {
					habilitarBotoesEdicaoExclusao(true);
				}else {
					habilitarBotoesEdicaoExclusao(false);
				}

			}
		});
		limparTabela();

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Pegar o empregado selecionado
				int indiceSelecionadoNaTabela = tblEmpregados.getSelectedRow();
				Empregado empSelecionado = empregados.get(indiceSelecionadoNaTabela - 1);
				
				//chamar controller, passando o id do emp selecionado
				EmpregadoController controller = new EmpregadoController();
				String mensagem = controller.excluir(empSelecionado);
				
				JOptionPane.showMessageDialog(null, mensagem);
				
				atualizarTabelaEmpregados();
			}
		});
		btnExcluir.setEnabled(false);

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(344, Short.MAX_VALUE)
						.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGap(350))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(33)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(32, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(tblEmpregados, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(229)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addGap(131)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(247, Short.MAX_VALUE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(25)
						.addComponent(btnBuscar)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tblEmpregados, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnExcluir)
								.addComponent(btnEditar))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		contentPane.setLayout(gl_contentPane);
	}

	protected void habilitarBotoesEdicaoExclusao(boolean habilitar) {
		btnEditar.setEnabled(habilitar);
		btnExcluir.setEnabled(habilitar);
	}

	protected void atualizarTabelaEmpregados() {
		EmpregadoController controller = new EmpregadoController();
		empregados = controller.consultarTodos();
		
		// Limpa a tabela
		limparTabela();

		// Obtém o model da tabela
		DefaultTableModel model = (DefaultTableModel) tblEmpregados.getModel();
		// Percorre os empregados para adicionar linha a linha na tabela (JTable)
		for (Empregado empregado : empregados) {
			String[] novaLinha = new String[5];
			novaLinha[0] = empregado.getNome();
			novaLinha[1] = empregado.getCpf();
			novaLinha[2] = empregado.getSexo() == 'M' ? "Masculino" : "Feminino";
			novaLinha[3] = empregado.getIdade() + "";
			novaLinha[4] = "R$" + String.valueOf(empregado.calcularSalario()).replace(".", ",");

			// Adiciona a nova linha na tabela
			model.addRow(novaLinha);
		}
	}

	private void limparTabela() {
		tblEmpregados.setModel(new DefaultTableModel(
				new Object[][] {
					colunasTabelaEmpregados,
				},
				colunasTabelaEmpregados
				));
	}
}
