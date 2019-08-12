package model.entity.aula05;

public class Telefone {
	private int id;
	private int idCliente;
	private String codigoPais;
	private String ddd;
	private String numero;
	private String tipoLinha;
	private boolean ativo;

	public Telefone() {
		
	}
	
	public Telefone(int id, int idCliente, String codigoPais, String ddd, String numero, String tipoLinha,
			boolean ativo) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.codigoPais = codigoPais;
		this.ddd = ddd;
		this.numero = numero;
		this.tipoLinha = tipoLinha;
		this.ativo = ativo;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipoLinha() {
		return tipoLinha;
	}

	public void setTipoLinha(String tipoLinha) {
		this.tipoLinha = tipoLinha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return " id: " + id + " |idCliente: " + idCliente + "| +" + codigoPais + "(" + ddd + ")" + numero + " - Linha: " + tipoLinha + " "
				+ (ativo ? "Ativa" : "Inativa");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}