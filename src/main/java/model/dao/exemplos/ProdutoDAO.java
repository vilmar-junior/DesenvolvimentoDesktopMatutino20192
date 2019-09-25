package model.dao.exemplos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.entity.exemplos.Produto;
import model.seletor.exemplos.ProdutoSeletor;

public class ProdutoDAO {

	public int inserir(Produto p) {
		int novoId = -1;

		String sql = " INSERT INTO PRODUTO (NOME, FABRICANTE, VALOR, PESO) " + " VALUES (?,?,?,?) ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setString(1, p.getNome());
			prepStmt.setString(2, p.getFabricante());
			prepStmt.setDouble(3, p.getValor());
			prepStmt.setDouble(4, p.getPeso());

			prepStmt.execute();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir produto. Causa: \n: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return novoId;
	}

	public boolean atualizar(Produto p) {
		boolean sucessoUpdate = false;

		String sql = " UPDATE PRODUTO P SET NOME=?, FABRICANTE=?, VALOR=?, PESO=? " + " WHERE P.ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setString(1, p.getNome());
			prepStmt.setString(2, p.getFabricante());
			prepStmt.setDouble(3, p.getValor());
			prepStmt.setDouble(4, p.getPeso());
			prepStmt.setDouble(5, p.getId());

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {
				sucessoUpdate = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar produto. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}

		return sucessoUpdate;
	}

	public boolean remover(int id) {
		boolean sucessoDelete = false;

		String sql = " DELETE FROM PRODUTO " + " WHERE ID = ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setInt(1, id);

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {// 1 - sucesso na execução
				sucessoDelete = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao remover produto. Id = " + id + ". Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return sucessoDelete;
	}

	public ArrayList<Produto> listarPorFaixaDePreco(double valorMaximo) {
		String sql = " SELECT * FROM PRODUTO WHERE VALOR < ? ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Produto> produtos = new ArrayList<Produto>();

		try {
			prepStmt.setDouble(1, valorMaximo);
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				Produto p = construirProdutoDoResultSet(result);
				produtos.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar produtos por faixa de preço. Causa: " + e.getMessage());
		}
		return produtos;
	}

	private Produto construirProdutoDoResultSet(ResultSet result) {
		Produto p = new Produto();

		// Obtendo valores pelo NOME DA COLUNA
		try {
			p.setId(result.getInt("ID"));
			p.setNome(result.getString("NOME"));
			p.setFabricante(result.getString("FABRICANTE"));
			p.setValor(result.getDouble("VALOR"));
			p.setPeso(result.getDouble("PESO"));
			p.setDataCadastro(result.getDate("DATACADASTRO").toLocalDate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public ArrayList<Produto> listarComSeletor(ProdutoSeletor seletor) {
		String sql = " SELECT * FROM PRODUTO p";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		if (seletor.temPaginacao()) {
			// TODO continuar...
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Produto> produtos = new ArrayList<Produto>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				Produto p = construirProdutoDoResultSet(result);
				produtos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;

	}

	/**
	 * Cria os filtros de consulta (cláusulas WHERE/AND) de acordo com o que foi
	 * preeenchido no seletor.
	 * 
	 * ATENÇÃO: a ordem de criação dos filtros e posterior preenchimentos é
	 * relevante, logo este método é intimamente ligado ao método
	 * preencherParametrosConsulta
	 * 
	 * @param seletor o seletor de produtos
	 * @param jpql    a consulta que será preenchida
	 */
	private String criarFiltros(ProdutoSeletor seletor, String sql) {

		// Tem pelo menos UM filtro
		sql += " WHERE ";
		boolean primeiro = true;

		if (seletor.getIdProduto() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.id = " + seletor.getIdProduto();
			primeiro = false;
		}

		if ((seletor.getNomeProduto() != null) && (seletor.getNomeProduto().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.nome LIKE '%" + seletor.getNomeProduto() + "%'";
			primeiro = false;
		}

		if ((seletor.getCorProduto() != null) && (seletor.getCorProduto().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.cor = '" + seletor.getCorProduto() + "'";
			primeiro = false;
		}

		if (seletor.getPesoProduto() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.peso = " + seletor.getPesoProduto();
			primeiro = false;
		}

		if ((seletor.getDataInicioCadastro() != null) && (seletor.getDataFimCadastro() != null)) {
			// Regra composta, olha as 3 opções de preenchimento do período

			// Todo o período preenchido (início E fim)
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.dataCadastro BETWEEN" + seletor.getDataInicioCadastro() + " AND " + seletor.getDataFimCadastro();
			primeiro = false;
		} else if (seletor.getDataInicioCadastro() != null) {
			// só o início
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.dataCadastro >= " + seletor.getDataInicioCadastro();
			primeiro = false;
		} else if (seletor.getDataFimCadastro() != null) {
			// só o fim
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.dataCadastro <= " + seletor.getDataFimCadastro();
			primeiro = false;
		}

		return sql;

	}

	public ArrayList<Produto> listarTodos() {
		String sql = " SELECT * FROM PRODUTO ";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Produto> produtos = new ArrayList<Produto>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				Produto p = new Produto();

				// Obtendo valores pelo NOME DA COLUNA
				p.setId(result.getInt("ID"));
				p.setNome(result.getString("NOME"));
				p.setFabricante(result.getString("FABRICANTE"));

				// Outra forma de obter (POSICIONAL)
				p.setValor(result.getDouble(4));
				p.setPeso(result.getDouble(5));
				produtos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

	/**
	 * Retorna um produto dado um id.
	 * 
	 * @param id o identificador do produto
	 * @return um produto caso o id exista na tabela Produto null caso contrário
	 */
	public Produto obterPorId(int id) {
		String sql = " SELECT * FROM PRODUTO " + " WHERE ID=?";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		Produto p = null;

		try {
			prepStmt.setInt(1, id);
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				p = new Produto();

				// Obtendo valores pelo NOME DA COLUNA
				p.setId(result.getInt("ID"));
				p.setNome(result.getString("NOME"));
				p.setFabricante(result.getString("FABRICANTE"));

				// Outra forma de obter (POSICIONAL)
				p.setValor(result.getDouble(4));
				p.setPeso(result.getDouble(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
}