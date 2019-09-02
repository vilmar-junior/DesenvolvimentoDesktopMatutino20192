package controller.aula05;

import java.util.ArrayList;

import model.bo.aula05.TelefoneBO;
import model.entity.aula05.Telefone;

public class TelefoneController {
	
	public static final String TIPO_TELEFONE_MOVEL = "M�vel";
	public static final String TIPO_TELEFONE_FIXO = "Fixo";
	
	private TelefoneBO bo = new TelefoneBO();

	public ArrayList<Telefone> consultarTodos() {
		return bo.consultarTodos();
	}
	
	public ArrayList<Telefone> consultarTelefonesDoCliente(int idCliente) {
		return bo.consultarTelefonesDoCliente(idCliente);
	}
}
