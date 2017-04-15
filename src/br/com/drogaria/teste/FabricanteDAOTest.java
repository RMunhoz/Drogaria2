package br.com.drogaria.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.DAO.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	@Ignore
	@Test
	public void salvar() {
		Fabricante f1 = new Fabricante();
		f1.setDescricao("Descrição A");

		Fabricante f2 = new Fabricante();
		f2.setDescricao("Descrição B");

		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(f1);
		dao.salvar(f2);
	}

	@Ignore
	@Test
	public void listar() {
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar();

		for (Fabricante f : fabricantes) {
			System.out.println(f);
		}
	}

	@Ignore
	@Test
	public void buscarPorCodigo() {
		FabricanteDAO dao = new FabricanteDAO();

		Fabricante f1 = dao.buscarPorCodigo(2L);
		Fabricante f2 = dao.buscarPorCodigo(3L);

		System.out.println(f1);
		System.out.println(f2);
	}

	@Ignore
	@Test
	public void excluir() {
		FabricanteDAO dao = new FabricanteDAO();

		Fabricante f1 = dao.buscarPorCodigo(2L);

		if (f1 != null) {
			dao.excluir(f1);
		}
		System.out.println("Este fabricante não existe!");
		
		// Fabricante f1 = new Fabricante();
		// f1.setCodigo(1L);
		// f1.setDescricao("Descrição A");
		//
		// FabricanteDAO dao = new FabricanteDAO();
		// dao.excluir(f1);

	}

	@Ignore
	@Test
	public void excluirPorCodigo() {
		FabricanteDAO dao = new FabricanteDAO();

		dao.excluirComBusca(2L);

	}
	
	@Ignore
	@Test
	public void editar(){
		Fabricante f1 = new Fabricante();
		f1.setCodigo(1L);
		f1.setDescricao("xxx");
		
		FabricanteDAO dao = new FabricanteDAO();
		dao.editar(f1);
	}
}
