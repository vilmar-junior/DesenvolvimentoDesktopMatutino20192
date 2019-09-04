package controller.aula05;

import java.util.ArrayList;

import model.bo.aula05.TelefoneBO;
import model.entity.aula05.Telefone;

public class TelefoneController {

	public static final String TIPO_TELEFONE_MOVEL = "Móvel";
	public static final String TIPO_TELEFONE_FIXO = "Fixo";

	private TelefoneBO bo = new TelefoneBO();

	public ArrayList<Telefone> consultarTodos() {
		return bo.consultarTodos();
	}

	public ArrayList<Telefone> consultarTelefonesDoCliente(int idCliente) {
		return bo.consultarTelefonesDoCliente(idCliente);
	}

	public String validarNovoTelefone(String codigoPais, String ddd, String numero, String tipoLinha) {
		String mensagem = "";

		if (codigoPais.isEmpty() || codigoPais.trim().length() != 2) {
			mensagem += "Código do país deve possuir 2 números \n";
		}

		if (ddd.isEmpty() || codigoPais.trim().length() != 2) {
			mensagem += "DDD deve possuir 2 números \n";
		}

		if (numero.isEmpty() || numero.trim().length() != 11) {
			mensagem += "Número deve possuir de 8 a 12 números \n";
		}

		if (tipoLinha.isEmpty()) {
			mensagem += "Informe o tipo da linha \n";
		}

		return mensagem;
	}

	public String salvar(Telefone novoTelefone) {
		return bo.salvar(novoTelefone);
	}
}
