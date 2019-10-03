package controller.exemplos;

import java.util.List;

import model.bo.exemplos.ProdutoBO;
import model.entity.exemplos.Produto;
import model.seletor.exemplos.ProdutoSeletor;
import model.util.GeradorPlanilha;

public class ProdutoController {

	public static final String TIPO_RELATORIO_XLS = "xls";
	public static final String TIPO_RELATORIO_PDF = "pdf";

	private ProdutoBO bo = new ProdutoBO();

	public List<Produto> listarTodosProdutos() {
		return bo.listarProdutos();
	}

	public List<Produto> listarProdutosAtePreco(double maximo) {
		return bo.listarProdutosPorFaixaDePreco(maximo);
	}

	public String salvar(Produto produto) {
		String validacao = validarProduto(produto);

		if (validacao == "") {
			if (produto.getId() > 0) {
				// INSERT
				if (bo.atualizar(produto)) {
					validacao = "Produto atualizado com sucesso!";
				} else {
					validacao = "Erro ao atualizar produto";
				}
			} else {
				// INSERT
				if (bo.inserir(produto)) {
					validacao = "Produto salvo com sucesso!";
				} else {
					validacao = "Erro ao salvar produto";
				}
			}
		}

		return validacao;
	}

	private String validarProduto(Produto produto) {
		String validacao = "";

		if (produto == null) {
			validacao = "Produto está NULO!";
		} else {
			// Validar o preenchimento
			if (produto.getNome().trim().equals("")) {
				validacao += "- Nome é obrigatório \n";
			}
			if (produto.getFabricante().trim().equals("")) {
				validacao += "- Fabricante é obrigatório \n";
			}
		}
		return validacao;
	}

	public void gerarRelatorio(List<Produto> produtos, String caminhoEscolhido, String tipoRelatorio) {
		if (tipoRelatorio.equals(TIPO_RELATORIO_XLS)) {
			bo.gerarPlanilha(produtos, caminhoEscolhido);
		}
	}

	public List<Produto> listarProdutos(ProdutoSeletor seletor) {
		return bo.listarProdutos(seletor);
	}

	public String gerarPlanilha(List<Produto> produtosConsultados, String caminho) {
		GeradorPlanilha geradorExcel = new GeradorPlanilha();
		return geradorExcel.gerarPlanilhaProdutos(caminho, produtosConsultados);
	}
}