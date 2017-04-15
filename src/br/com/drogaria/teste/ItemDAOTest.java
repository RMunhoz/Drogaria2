package br.com.drogaria.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.DAO.ItemDAO;
import br.com.drogaria.DAO.ProdutoDAO;
import br.com.drogaria.DAO.VendaDAO;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;

public class ItemDAOTest {
	@Test
	@Ignore
	public void salvar() {
		ProdutoDAO pdao = new ProdutoDAO();
		Produto produto = new Produto();

		produto = pdao.buscarPorCodigo(2L);

		VendaDAO vdao = new VendaDAO();
		Venda venda = new Venda();

		venda = vdao.buscarPorCodigo(1L);

		Item item = new Item();
		item.setQuantidade(5);
		item.setValor_parcial(new BigDecimal(220.00D));
		item.setVenda(venda);
		item.setProduto(produto);

		ItemDAO dao = new ItemDAO();
		dao.salvar(item);
	}

	@Test
	@Ignore
	public void listar() {
		ItemDAO dao = new ItemDAO();
		List<Item> itens = dao.listar();

		for (Item i : itens) {
			System.out.println(i);
		}
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ItemDAO dao = new ItemDAO();
		Item item = new Item();

		item = dao.buscarPorCodigo(1L);

		System.out.println(item);
	}

	@Test
	@Ignore
	public void excluir() {
		ItemDAO dao = new ItemDAO();
		Item item = new Item();

		item = dao.buscarPorCodigo(2L);
		dao.excluir(item);
	}
	
	@Test
	@Ignore
	public void editar(){
		ItemDAO dao = new ItemDAO();
		Item item = new Item();
		
		item = dao.buscarPorCodigo(1L);
		item.setQuantidade(15);
		item.setValor_parcial(new BigDecimal(88.00D));
			
		ProdutoDAO pdao = new ProdutoDAO();
		Produto produto = new Produto();
		
		produto = pdao.buscarPorCodigo(2L);
		item.setProduto(produto);

		VendaDAO vdao = new VendaDAO();
		Venda venda = new Venda();
		
		venda = vdao.buscarPorCodigo(1L);
		item.setVenda(venda);
		
		dao.editar(item);
	}

}
