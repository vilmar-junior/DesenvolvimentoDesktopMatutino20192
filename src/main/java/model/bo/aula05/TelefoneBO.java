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

	/**
	 * Tenta salvar um novo telefone, validando se o número não é duplicado.
	 * 
	 * @param novoTelefone
	 * @return String uma mensagem vazia caso o telefone foi salvo com sucesso, e
	 *         preenchida caso contrário.
	 */
	public String salvar(Telefone novoTelefone) {

		String mensagem = "";

		if (dao.telefoneJaCadastrado(novoTelefone)) {
			mensagem = "O telefone +" + novoTelefone.getCodigoPais() + " (" + novoTelefone.getDdd() + ") "
					+ novoTelefone.getNumero() + " já está " + "cadastrado";
		} else {
			novoTelefone = dao.salvar(novoTelefone);

			if (novoTelefone.getId() == 0) {
				mensagem = "Erro ao salvar telefone";
			}
		}

		return mensagem;
	}
}