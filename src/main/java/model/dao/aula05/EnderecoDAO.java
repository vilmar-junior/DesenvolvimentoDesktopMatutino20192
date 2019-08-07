package model.dao.aula05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.entity.aula05.Endereco;

public class EnderecoDAO implements BaseDAO<Endereco> {

	public Endereco salvar(Endereco novoEndereco) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO ENDERECO(RUA, CEP, ESTADO, CIDADE, BAIRRO, NUMERO) "
				+ " VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, 
				PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			stmt.setString(1, novoEndereco.getRua());
			stmt.setString(2, novoEndereco.getCep());
			stmt.setString(3, novoEndereco.getEstado());
			stmt.setString(4, novoEndereco.getCidade());
			stmt.setString(5, novoEndereco.getBairro());
			stmt.setString(6, novoEndereco.getNumero());
			
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				int idGerado = rs.getInt(1);
				novoEndereco.setId(idGerado);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo endereço.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoEndereco;
	}

	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean alterar(Endereco entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public Endereco consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Endereco> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}