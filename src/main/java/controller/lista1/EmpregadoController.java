package controller.lista1;

import model.bo.lista1.EmpregadoBO;
import model.dao.lista1.EmpregadoDAO;
import model.entity.lista1.Diretor;
import model.entity.lista1.Empregado;
import model.entity.lista1.EmpregadoOperacional;
import model.entity.lista1.Gerente;

public class EmpregadoController {

	public String salvar(String nome, String cpf, char sexo, int idade, String salarioBruto, String comissao,
			String tipoSelecionado) {
		StringBuilder mensagem = new StringBuilder();
		
		validarNome(nome, mensagem);
		validarCPF(cpf, mensagem);
		validarSexo(sexo, mensagem);
		validadeIdade(idade, mensagem);
		
		double salarioBrutoDouble = validarValorMonetario(salarioBruto, "salário bruto", mensagem);
		double comissaoDouble = 0;
		Empregado novoEmpregado = null;
		
		switch (tipoSelecionado) {
			case EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_DIRETOR:
				comissaoDouble = validarValorMonetario(comissao, "comissão", mensagem);
				novoEmpregado = new Diretor(nome, cpf, sexo, idade, salarioBrutoDouble, comissaoDouble);
				break;
			case EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_GERENTE:
				comissaoDouble = validarValorMonetario(comissao, "comissão", mensagem);
				novoEmpregado = new Gerente(nome, cpf, sexo, idade, salarioBrutoDouble, comissaoDouble);
				break;
			case EmpregadoDAO.DESCRICAO_TIPO_EMPREGADO_OPERACIONAL:
				novoEmpregado = new EmpregadoOperacional(nome, cpf, sexo, idade, salarioBrutoDouble);
				break;	
			default:
				mensagem.append("Informe o tipo do empregado \n");
				break;
		}
		
		if(mensagem.toString().isEmpty()) {
			EmpregadoBO bo = new EmpregadoBO();
			mensagem = new StringBuilder(bo.salvar(novoEmpregado));
		}
		
		
		return mensagem.toString();
	}

	private double validarValorMonetario(String valorEmString, String nomeDoValor, StringBuilder mensagem) {
		double valorDouble = 0;
		String[] partes = valorEmString.split(",");
		if(partes.length == 2) {
			if(partes[1].length() != 2) {
				mensagem.append("Informe 2 dígitos depois da vírgula para "+ nomeDoValor + "\n");
			}else {
				try {
					valorDouble = Double.parseDouble(valorEmString.replace(",", "."));
				}catch(NumberFormatException ex) {
					mensagem.append("Informe somente números para " + nomeDoValor + "\n");
				}
			}
		}else {
			mensagem.append("Informe um valor no formato '0000,00' para " + nomeDoValor + "\n");
		}
		
		return valorDouble;
	}

	private void validadeIdade(int idade, StringBuilder mensagem) {
		if(idade < 14 || idade > 65) {
			mensagem.append("Informe uma idade entre 14 e 65 anos \n");
		}
	}

	private void validarSexo(char sexo, StringBuilder mensagem) {
		if(sexo == ' ') {
			mensagem.append("Informe o sexo \n");
		}
	}

	private void validarCPF(String cpf, StringBuilder mensagem) {
		if(cpf == null || cpf.trim().length() != 11) {
			mensagem.append("CPF deve conter 11 caracteres \n");
		}else {
			try {
				Long.parseLong(cpf);
			}catch(NumberFormatException ex) {
				mensagem.append("CPF deve conter somente números \n");
			}
		}
	}

	private void validarNome(String nome, StringBuilder mensagem) {
		if(nome == null || nome.trim().length() < 3) {
			mensagem.append("Nome deve ter no mínimo 3 letras \n");
		}
	}
}