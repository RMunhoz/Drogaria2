package br.com.drogaria.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.DAO.FabricanteDAO;
import br.com.drogaria.DAO.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		FabricanteDAO fdao = new FabricanteDAO();
		Fabricante fabricante = new Fabricante();
		fabricante = fdao.buscarPorCodigo(1L);

		Produto produto = new Produto();
		produto.setDescricao("Descrição two");
		produto.setPreco(new BigDecimal(12.88D));
		produto.setQuantidade(8);
		produto.setFabricante(fabricante);

		ProdutoDAO pdao = new ProdutoDAO();
		pdao.salvar(produto);
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = new Produto();

		produto = dao.buscarPorCodigo(1L);

		System.out.println(produto);
	}

	@Test
	@Ignore
	public void listar() {
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listar();

		for (Produto p : produtos) {
			System.out.println(p);
		}
	}

	@Test
	@Ignore
	public void excluir() {
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = new Produto();

		produto = dao.buscarPorCodigo(1L);
		dao.excluir(produto);
	}

	@Test
	@Ignore
	public void editar() {
		ProdutoDAO pdao = new ProdutoDAO();
		Produto produto = new Produto();

		produto = pdao.buscarPorCodigo(2L);
		produto.setDescricao("New Descrição");
		produto.setPreco(new BigDecimal(6.33D));
		produto.setQuantidade(8);

		FabricanteDAO fdao = new FabricanteDAO();
		Fabricante fabricante = new Fabricante();
		fabricante = fdao.buscarPorCodigo(1L);

		produto.setFabricante(fabricante);
		pdao.editar(produto);

	}

}
