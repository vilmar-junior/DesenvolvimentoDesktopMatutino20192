package model.entity.lista1;

import java.util.ArrayList;

public class Gerencia extends Lotacao {
	
	private Gerente gerente;
	private ArrayList<EmpregadoOperacional> funcionariosOperacionais;
	
	public Gerencia(String nome, String sigla, Gerente gerente, ArrayList<EmpregadoOperacional> operacionais) {
		super(nome, sigla);
		this.gerente = gerente;
		this.funcionariosOperacionais = operacionais;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public ArrayList<EmpregadoOperacional> getFuncionariosOperacionais() {
		return funcionariosOperacionais;
	}

	public void setFuncionariosOperacionais(ArrayList<EmpregadoOperacional> funcionariosOperacionais) {
		this.funcionariosOperacionais = funcionariosOperacionais;
	}

	@Override
	public String toString() {
		return "Gerencia [gerente=" + gerente + ", funcionariosOperacionais=" + funcionariosOperacionais + "]";
	}
}