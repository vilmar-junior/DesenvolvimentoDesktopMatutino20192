package executavel.aula05;

import java.util.ArrayList;
import java.util.Random;

import model.dao.aula05.ClienteDAO;
import model.dao.aula05.EnderecoDAO;
import model.dao.aula05.TelefoneDAO;
import model.dao.lista1.EmpregadoDAO;
import model.entity.aula05.Cliente;
import model.entity.aula05.Endereco;
import model.entity.aula05.Telefone;
import model.entity.lista1.Diretor;
import model.entity.lista1.Empregado;
import model.entity.lista1.EmpregadoOperacional;
import model.entity.lista1.Gerente;

public class Executavel {

	private static final String CODIGO_PAIS_BRASIL = "55";

	public static void main(String[] args) {
		Diretor d = new Diretor("Edson Arantes", "12345678901", 'M', 75, 12000.5, 500); 
		Gerente g = new Gerente("Artur Antunes", "11111111111", 'M', 60, 8000.5, 200);
		EmpregadoOperacional op = new EmpregadoOperacional("José das Couves", "99999999999", 'M', 20, 1000.5);
		
		EmpregadoDAO dao = new EmpregadoDAO();
//		dao.salvar(d);
//		dao.salvar(g);
//		dao.salvar(op);
		
		Empregado emp = dao.consultarPorId(2);
		System.out.println(emp.toString());
		
		// exercício 1
		// criarClientesMostrarNoConsole();

		// Métodos de testes da aula 2
		//criarEndereco();
		//excluirEndereco(1);
		//atualizarEndereco();
		//consultarEndereco(25);
		//consultarEnderecos();

		// salvarTelefonesAleatorios();
		// excluirTelefone();
		// atualizarTelefone();
		// consultarTelefones();
		
		//salvarNovoCliente();
		//alterarCliente(3);
		//excluirCliente(1);
		//consultarCliente(2);
		//consultarClientes();
	}
	
	private static void consultarCliente(int id) {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente clienteConsultado = clienteDAO.consultarPorId(id);
		
		System.out.println(clienteConsultado);
	}
	
	private static void excluirCliente(int id) {
		ClienteDAO clienteDAO = new ClienteDAO();
		if (clienteDAO.excluir(id)) {
			System.out.println("Excluiu");
		} else {
			System.out.println("Não excluiu");
		}
	}

	private static void alterarCliente(int id) {
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cli = clienteDAO.consultarPorId(id);
		cli.setNome("Pedro");
		cli.setSobrenome("Alterado de Souza");
		
		if(clienteDAO.alterar(cli)) {
			System.out.println("Alterou o cliente");
		}else {
			System.out.println("Não alterou o cliente");
		}
	}

	private static void consultarClientes() {
		ClienteDAO clienteDAO = new ClienteDAO();
		System.out.println("************* Todos os clientes *************");
		System.out.println("");
		ArrayList<Cliente> todosOsClientes = clienteDAO.consultarTodos();

		for (Cliente cli : todosOsClientes) {
			System.out.println(cli);
		}
		System.out.println("");
		System.out.println("**********************************************");
		
	}

	private static void salvarNovoCliente() {
		Cliente c = new Cliente("José", "da Silva Sauro", "55577788811", 
				criarTelefones(), 
				criarEndereco());
		ClienteDAO cliDAO = new ClienteDAO();
		c = cliDAO.salvar(c);
		
		if (c.getId() > 0) {
			System.out.println("Cliente salvo com sucesso. ID: " + c.getId());
			System.out.println("************* Telefones do cliente *************");
			System.out.println("");
			for(Telefone t: c.getTelefones()) {
				System.out.println(t);
			}
			System.out.println("");
			System.out.println("**********************************************");
		} else {
			System.out.println("Não salvou cliente");
		}
	}

	private static void consultarEnderecos() {
		EnderecoDAO endDAO = new EnderecoDAO();
		System.out.println("************* Todos os endereços *************");
		System.out.println("");
		ArrayList<Endereco> todosOsEnderecos = endDAO.consultarTodos();

		for (Endereco e : todosOsEnderecos) {
			System.out.println(e);
		}
		System.out.println("");
		System.out.println("**********************************************");
	}
	
	private static void consultarEndereco(int id) {
		EnderecoDAO endDAO = new EnderecoDAO();
		Endereco enderecoConsultado = endDAO.consultarPorId(id);
		
		System.out.println(enderecoConsultado);
	}

	private static void atualizarEndereco() {
		Endereco enderecoQueSeraAtualizado = new Endereco("Rua Nova", "88032000", "PR", "Curitiba", "Centro", "10");
		enderecoQueSeraAtualizado.setId(2);
		
		EnderecoDAO endDAO = new EnderecoDAO();

		if (endDAO.alterar(enderecoQueSeraAtualizado)) {
			System.out.println("Alterou endereço");
		} else {
			System.out.println("Não alterou endereço");
		}
		
	}

	private static void excluirEndereco(int id) {
		EnderecoDAO endDAO = new EnderecoDAO();

		if (endDAO.excluir(id)) {
			System.out.println("Excluiu endereço " + id);
		} else {
			System.out.println("Não excluiu endereço " + id);
		}
	}

	private static void consultarTelefones() {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		System.out.println(telefoneDAO.consultarPorId(2));
		System.out.println("");
		System.out.println("************* Todos os telefones *************");
		ArrayList<Telefone> todosOsTelefones = telefoneDAO.consultarTodos();

		for (Telefone t : todosOsTelefones) {
			System.out.println(t);
		}
		System.out.println("");
		System.out.println("**********************************************");
	}

	private static void atualizarTelefone() {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		Telefone telefoneQueSeraAtualizado = new Telefone(2, 0, "88", "66", "1232-3211", "Móvel", false);
		if (telefoneDAO.alterar(telefoneQueSeraAtualizado)) {
			System.out.println("Alterou");
		} else {
			System.out.println("Não alterou");
		}
	}

	private static void excluirTelefone() {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		if (telefoneDAO.excluir(3)) {
			System.out.println("Excluiu");
		} else {
			System.out.println("Não excluiu");
		}
	}

	private static void salvarTelefonesAleatorios() {
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		ArrayList<Telefone> telefones = criarTelefones();

		for (Telefone t : telefones) {
			telefoneDAO.salvar(t);
		}
	}

	private static void criarClientesMostrarNoConsole() {
		Endereco endereco1 = new Endereco("Mauro Ramos", "88000-123", "SC", "Florianópolis", "Centro", "10A");

		// Construção da lista de telefones
		ArrayList<Telefone> telefonesCliente1 = new ArrayList<Telefone>();
		Telefone tel1 = new Telefone(0, 0, "55", "048", "2020-5555", "Fixa", true);
		telefonesCliente1.add(tel1);

		// Listas de telefones são criadas por um método auxiliar criarTelefones()
		// criado na classe Executavel
		Cliente cliente1 = new Cliente("Edson", "Arantes do Nascimento", "010.010.100-10", telefonesCliente1,
				endereco1);
		Cliente cliente2 = new Cliente("Artur", "Antunes Coimbra", "000.000.100-10", criarTelefones(), endereco1);
		Cliente cliente3 = new Cliente("Manoel", "dos Santos", "777.010.100-10", criarTelefones(), endereco1);
		Cliente cliente4 = new Cliente("Roberto", "Rivellino", "011.010.100-10", criarTelefones(), endereco1);
		Cliente cliente5 = new Cliente("Eduardo", "Gonçalves de Andrade", "009.010.100-10", criarTelefones(),
				endereco1);

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);

		for (Cliente c : clientes) {
			System.out.println("************************************************************************************");
			System.out.println(c);
		}
		System.out.println("************************************************************************************");

		// TODO exercício 2 -> salvar os clientes no banco
	}

	private static Endereco criarEndereco() {
		Random ran = new Random();
		String cep = "" + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10)
				+ ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10);

		String numero = "" + ran.nextInt(1000);
		Endereco novoEndereco = new Endereco("Novo endereço " + ran.nextInt(100), cep, "SC", "Florianópolis", "Centro",
				numero);

		EnderecoDAO dao = new EnderecoDAO();
		novoEndereco = dao.salvar(novoEndereco);

//		if (novoEndereco.getId() > 0) {
//			System.out.println("Endereço salvo com sucesso.");
//		} else {
//			System.out.println("Endereço não foi salvo.");
//		}
		
		return novoEndereco;
	}

	/**
	 * Cria uma lista de telefones randômicos.
	 * 
	 * @return uma lista de telefones.
	 */
	private static ArrayList<Telefone> criarTelefones() {
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(new Telefone(0, 0, CODIGO_PAIS_BRASIL, criarDdd(), criarNumeroTelefone(), "Fixa", true));
		telefones.add(new Telefone(0, 0, CODIGO_PAIS_BRASIL, criarDdd(), criarNumeroTelefone(), "Fixa", true));
		telefones.add(new Telefone(0, 0, CODIGO_PAIS_BRASIL, criarDdd(), criarNumeroTelefone(), "Móvel", true));

		return telefones;
	}

	/**
	 * Cria um DDD randômico
	 * 
	 * @return uma String com o DDD, com 2 caracteres.
	 */
	private static String criarDdd() {
		Random ran = new Random();
		return "" + ran.nextInt(10) + ran.nextInt(10);
	}

	/**
	 * Cria um número de telefone randômico, no formato ####-####, onde # é um
	 * número de 0 a 9
	 * 
	 * @return o número gerado.
	 */
	private static String criarNumeroTelefone() {
		Random ran = new Random();
		String prefixo = "" + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10);
		String ramal = "" + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10);

		return prefixo + "-" + ramal;
	}
}