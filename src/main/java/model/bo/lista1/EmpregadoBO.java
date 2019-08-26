package model.bo.lista1;

import model.dao.lista1.EmpregadoDAO;
import model.entity.lista1.Empregado;

public class EmpregadoBO {

	public String salvar(Empregado novoEmpregado) {
		String mensagem = "";
		EmpregadoDAO empregDAO = new EmpregadoDAO();
		
		if(empregDAO.temCPFCadastrado(novoEmpregado.getCpf())) {
			mensagem = "CPF informado já pertence a outro empregado";
		}else {
			//chama o DAO para salvar o novoEmpregado
			novoEmpregado = empregDAO.salvar(novoEmpregado);
			
			if(novoEmpregado.getId() > 0) {
				mensagem = "Empregado cadastrado com sucesso!";
			}else {
				mensagem = "Erro ao cadastrar empregado";
			}
				
		}
		
		
		return mensagem;
	}

}