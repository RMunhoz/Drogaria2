package br.com.drogaria.teste;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.DAO.FuncionarioDAO;
import br.com.drogaria.DAO.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.filter.VendaFilter;

public class VendaDAOTest {
	@Test
	@Ignore
	public void salvar() {
		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		funcionario = fdao.buscaPorCodigo(1L);

		Venda venda = new Venda();
		venda.setHorario(new Date());
		venda.setValor_total(new BigDecimal(100.00D));
		venda.setFuncionario(funcionario);

		VendaDAO vdao = new VendaDAO();
		vdao.salvar(venda);

	}

	@Test
	@Ignore
	public void listar() {
		VendaDAO dao = new VendaDAO();
		List<Venda> vendas = dao.listar();

		for (Venda v : vendas) {
			System.out.println(v);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		VendaDAO dao = new VendaDAO();
		Venda venda = new Venda();

		venda = dao.buscarPorCodigo(1L);

		System.out.println(venda);
	}

	@Test
	@Ignore
	public void excluir() {
		VendaDAO dao = new VendaDAO();
		Venda venda = new Venda();

		venda = dao.buscarPorCodigo(4L);
		dao.excluir(venda);
	}
	
	@Ignore
	@Test
	public void editar() {
		VendaDAO vdao = new VendaDAO();
		Venda venda = new Venda();

		venda = vdao.buscarPorCodigo(1L);
		venda.setHorario(new Date());
		venda.setValor_total(new BigDecimal(200.50));

		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		funcionario = fdao.buscaPorCodigo(4L);

		venda.setFuncionario(funcionario);
		vdao.editar(venda);

	}

	@Ignore
	@Test
	public void buscar() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		VendaFilter filtro = new VendaFilter();
		filtro.setDataIncial(formato.parse("25/03/2017"));
		filtro.setDataFinal(formato.parse("10/04/2017"));

		VendaDAO vdao = new VendaDAO();
		List<Venda> vendas = vdao.buscar(filtro);

		for (Venda venda : vendas) {
			System.out.println(venda);
		}

	}

}
