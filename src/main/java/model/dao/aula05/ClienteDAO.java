package model.dao.aula05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.entity.aula05.Cliente;
import model.entity.aula05.Endereco;
import model.entity.aula05.Telefone;

public class ClienteDAO implements BaseDAO<Cliente> {

	public Cliente salvar(Cliente novoCliente) {
		String sql = " INSERT INTO CLIENTE (NOME, SOBRENOME, CPF, IDENDERECO)" + "VALUES (?,?,?,?) ";
		ResultSet rs = null;
		
		// Exemplo de try with resources -> tudo que está dentro dos parênteses do try
		// será fechado, igual a como faziámos no finally
		try (Connection conexao = Banco.getConnection();
				PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);){
			prepStmt.setString(1, novoCliente.getNome());
			prepStmt.setString(2, novoCliente.getSobrenome());
			prepStmt.setString(3, novoCliente.getCpf());

			if (novoCliente.getEndereco() != null) {
				prepStmt.setInt(4, novoCliente.getEndereco().getId());
			} else {
				// Cliente sem endereço
				prepStmt.setInt(4, 0);
			}

			prepStmt.execute();
			rs = prepStmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				novoCliente.setId(idGerado);
			}

			TelefoneDAO telDAO = new TelefoneDAO();
			telDAO.ativarTelefones(novoCliente);
		} catch (Exception e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
		}

		return novoCliente;
	}

	public boolean excluir(int id) {

		String sql = " DELETE FROM CLIENTE WHERE ID = " + id;

		int quantidadeRegistrosExcluidos = 0;

		// Exemplo de try with resources -> tudo que está dentro dos parênteses do try
		// será fechado, igual a como faziámos no finally
		try (Connection conexao = Banco.getConnection(); 
				Statement statement = Banco.getStatement(conexao);) {
			quantidadeRegistrosExcluidos = statement.executeUpdate(sql);
			TelefoneDAO tDAO = new TelefoneDAO();
			tDAO.desativarTelefones(id);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente.");
			System.out.println("Erro: " + e.getMessage());
		}

		return quantidadeRegistrosExcluidos > 0;
	}

	public boolean alterar(Cliente cliente) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE CLIENTE " + " SET NOME=?, SOBRENOME=?, CPF=?, IDENDERECO=? " + " WHERE ID=?";
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		int quantidadeRegistrosAtualizados = 0;
		try {
			prepStmt.setString(1, cliente.getNome());
			prepStmt.setString(2, cliente.getSobrenome());
			prepStmt.setString(3, cliente.getCpf());

			if (cliente.getEndereco() != null) {
				prepStmt.setInt(4, cliente.getEndereco().getId());
			} else {
				// Cliente sem endereço
				prepStmt.setInt(4, 0);
			}
			prepStmt.setInt(5, cliente.getId());
			quantidadeRegistrosAtualizados = prepStmt.executeUpdate();

			TelefoneDAO telDAO = new TelefoneDAO();
			telDAO.ativarTelefones(cliente);
		} catch (Exception e) {
			System.out.println("Erro ao alterar cliente.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return quantidadeRegistrosAtualizados > 0;
	}

	public Cliente consultarPorId(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE WHERE ID= " + id;
		Statement stmt = Banco.getStatement(conexao);
		ResultSet rs = null;
		
		Cliente clienteConsultado = null;
		try {
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				clienteConsultado = construirClienteDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar cliente por id. Id: " + id);
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return clienteConsultado;
	}

	public ArrayList<Cliente> consultarTodos() {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE ";
		Statement stmt = Banco.getStatement(conexao);
		ResultSet rs = null;
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Cliente clienteConsultado = construirClienteDoResultSet(rs);
				clientes.add(clienteConsultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar clientes ");
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closeResultSet(rs);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return clientes;
	}

	private Cliente construirClienteDoResultSet(ResultSet rs) {
		Cliente cli = new Cliente();
		try {
			cli.setId(rs.getInt("id"));
			cli.setNome(rs.getString("nome"));
			cli.setSobrenome(rs.getString("sobrenome"));
			cli.setCpf(rs.getString("cpf"));

			EnderecoDAO enderecoDAO = new EnderecoDAO();
			Endereco endereco = enderecoDAO.consultarPorId(rs.getInt("idEndereco"));
			cli.setEndereco(endereco);

			TelefoneDAO telefDAO = new TelefoneDAO();
			ArrayList<Telefone> telefones = telefDAO.consultarPorIdCliente(rs.getInt("id"));
			cli.setTelefones(telefones);
		} catch (SQLException e) {
			System.out.println("Erro ao construir cliente do ResultSet");
			System.out.println("Erro: " + e.getMessage());
		}

		return cli;
	}
}