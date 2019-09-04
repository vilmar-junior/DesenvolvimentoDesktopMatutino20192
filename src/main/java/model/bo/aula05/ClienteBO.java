package model.bo.aula05;

import model.dao.aula05.ClienteDAO;
import model.entity.aula05.Cliente;

public class ClienteBO {

	private ClienteDAO dao = new ClienteDAO();

	public Cliente salvar(Cliente novoCliente) {
		return dao.salvar(novoCliente);
	}

}
