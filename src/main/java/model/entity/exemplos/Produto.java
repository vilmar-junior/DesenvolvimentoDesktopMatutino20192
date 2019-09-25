package model.entity.exemplos;

import java.time.LocalDate;

public class Produto {

	private int id;
	private String nome;
	private String fabricante;
	private double valor;
	private double peso;
	private LocalDate dataCadastro;

	public Produto(String nome, String fabricante, double valor, double peso, LocalDate dataCadastro) {
		super();
		this.nome = nome;
		this.fabricante = fabricante;
		this.valor = valor;
		this.peso = peso;
		this.dataCadastro = dataCadastro;
	}

	public Produto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", fabricante=" + fabricante + "]";
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}