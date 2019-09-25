package model.seletor.exemplos;

import java.time.LocalDate;
import java.util.Date;

public class ProdutoSeletor {

	//Atributos que servirão de filtros
	private int idProduto;
	private String nomeProduto;
	private String corProduto;
	private Double pesoProduto;
	
	//Filtragem de datas por período (início, fim)
	private LocalDate dataInicioCadastro;
	private LocalDate dataFimCadastro;

	//Atributos para possível paginação dos resultados (intervalo)
	private int limite;
	private int pagina;
	
	public ProdutoSeletor() {
		//Default: traz os resultados sem limite e sem página
		this.limite = 0;
		this.pagina = -1;
	}
	
	/**
	 * Verifica se este seletor tem algum campo preenchido
	 *
	 * @return verdadeiro se existe algum campo de filtro preenchido
	 */
	public boolean temFiltro() {
		if (this.idProduto > 0) {
			return true;
		}
		if ((this.nomeProduto != null) && (this.nomeProduto.trim().length() > 0)) {
			return true;
		}
		if ((this.corProduto != null) && (this.corProduto.trim().length() > 0)) {
			return true;
		}
		if ((this.pesoProduto != null) && (this.pesoProduto > 0)) {
			return true;
		}
		if (this.dataInicioCadastro != null) {
			return true;
		}
		if (this.dataFimCadastro != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se os campos de paginacao estao preenchidos
	 *
	 * @return verdadeiro se os campos limite e pagina estao preenchidos
	 */
	public boolean temPaginacao() {
		return ((this.limite > 0) && (this.pagina > -1));
	}

	/**
	 * Calcula deslocamento (offset) a partir da pagina e do limite
	 *
	 * @return offset
	 */
	public int getOffset() {
		return (this.limite * (this.pagina - 1));
	}

	//Getters e setters
	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int id) {
		this.idProduto = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nome) {
		this.nomeProduto = nome;
	}

	public String getCorProduto() {
		return corProduto;
	}

	public void setCorProduto(String corProduto) {
		this.corProduto = corProduto;
	}

	public Double getPesoProduto() {
		return pesoProduto;
	}

	public void setPesoProduto(Double pesoProduto) {
		this.pesoProduto = pesoProduto;
	}

	public LocalDate getDataInicioCadastro() {
		return dataInicioCadastro;
	}

	public void setDataInicioCadastro(LocalDate dataInicioCadastro) {
		this.dataInicioCadastro = dataInicioCadastro;
	}

	public LocalDate getDataFimCadastro() {
		return dataFimCadastro;
	}

	public void setDataFimCadastro(LocalDate dataFimCadastro) {
		this.dataFimCadastro = dataFimCadastro;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
}

