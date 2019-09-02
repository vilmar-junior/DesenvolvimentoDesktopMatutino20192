package model.bo.aula05;

import java.util.ArrayList;

import model.dao.aula05.TelefoneDAO;
import model.entity.aula05.Telefone;

public class TelefoneBO {

	private TelefoneDAO dao = new TelefoneDAO();
	
	public ArrayList<Telefone> consultarTodos() {
		return dao.consultarTodos();
	}

	public ArrayList<Telefone> consultarTelefonesDoCliente(int idCliente) {
		return dao.consultarTodosPorIdCliente(idCliente);
	}
}