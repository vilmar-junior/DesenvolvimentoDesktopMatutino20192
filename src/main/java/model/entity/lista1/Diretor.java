package model.entity.lista1;

public class Diretor extends Empregado {

	private double comissao;
	
	public Diretor(String nome, String cpf, char sexo, int idade, double salarioBruto, double comissao) {
		super(nome, cpf, sexo, idade, salarioBruto);
		this.comissao = comissao;
	}

	@Override
	public double calcularSalario() {
		return this.getSalarioBase() * 3 + this.comissao;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
}