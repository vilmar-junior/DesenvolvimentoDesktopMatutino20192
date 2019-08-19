package model.entity.lista1;

public class Gerente extends Empregado {

	private double comissao;
	
	public Gerente(String nome, String cpf, char sexo, int idade, double salarioBruto, double comissao) {
		super(nome, cpf, sexo, idade, salarioBruto);
		this.comissao = comissao;
	}

	@Override
	public double calcularSalario() {
		return this.getSalarioBase() * 0.9 + this.comissao;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	@Override
	public String toString() {
		return "Gerente [comissao=" + comissao + "]";
	}
}