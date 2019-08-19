package model.entity.lista1;

public class EmpregadoOperacional extends Empregado {

	public EmpregadoOperacional(String nome, String cpf, char sexo, int idade, double salarioBruto) {
		super(nome, cpf, sexo, idade, salarioBruto);
	}

	@Override
	public double calcularSalario() {
		return this.getSalarioBase() * 0.85;
	}

}
