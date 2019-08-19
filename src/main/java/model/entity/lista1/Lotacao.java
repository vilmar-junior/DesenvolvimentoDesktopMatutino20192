package model.entity.lista1;

public abstract class Lotacao {
	private int id;
	private String nome;
	private String sigla;
	
	public Lotacao(String nome, String sigla) {
		super();
		this.nome = nome;
		this.sigla = sigla;
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
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}