package model.entity.lista1;

import java.util.ArrayList;

public class Diretoria extends Lotacao {
	
	private Diretor diretor;
	private ArrayList<Gerencia> gerencias;
	
	public Diretoria(String nome, String sigla, Diretor d, ArrayList<Gerencia> gerencias) {
		super(nome, sigla);
		this.diretor = d;
		this.gerencias = gerencias;
	}

	public Diretor getDiretor() {
		return diretor;
	}

	public void setDiretor(Diretor diretor) {
		this.diretor = diretor;
	}

	public ArrayList<Gerencia> getGerencias() {
		return gerencias;
	}

	public void setGerencias(ArrayList<Gerencia> gerencias) {
		this.gerencias = gerencias;
	}

	@Override
	public String toString() {
		return "Diretoria [diretor=" + diretor + ", gerencias=" + gerencias + "]";
	}
}