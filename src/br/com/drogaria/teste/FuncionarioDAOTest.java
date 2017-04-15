package br.com.drogaria.teste;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.DAO.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;

public class FuncionarioDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Funcionario f1 = new Funcionario();
		f1.setNome("Excluir");
		f1.setCpf("555.555.555-55");
		f1.setFuncao("Visitante");
		f1.setSenha("8907");

		FuncionarioDAO dao = new FuncionarioDAO();
		dao.salvar(f1);
	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.listar();

		for (Funcionario fun : funcionarios) {
			System.out.println(fun);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 = new Funcionario();

		f1 = dao.buscaPorCodigo(1L);

		System.out.println(f1);
	}

	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 = new Funcionario();

		f1 = dao.buscaPorCodigo(6L);

		if (f1 != null) {
			dao.excluir(f1);
		} else {
			System.out.println("Funcionario não exste!");
		}
	}
	
	@Test
	@Ignore
	public void excluirPorCodigo(){
		FuncionarioDAO dao = new FuncionarioDAO();
		
		dao.excluirComBusca(6L);
	}
	
	@Test
	@Ignore
	public void editar(){
		Funcionario f1 = new Funcionario();
		FuncionarioDAO dao = new FuncionarioDAO();
		f1 = dao.buscaPorCodigo(1L);
		f1.setCpf("888.888.888-88");
				
		dao.editar(f1);
	}
	
	@Ignore
	@Test
	public void autenticar(){
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario funcionario = fdao.autenticar("622.849.826-62", "123456");
		
		Assert.assertNotNull(funcionario);
		
	}

}
