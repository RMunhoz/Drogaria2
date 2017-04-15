package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.DAO.FuncionarioDAO;
import br.com.drogaria.DAO.ItemDAO;
import br.com.drogaria.DAO.ProdutoDAO;
import br.com.drogaria.DAO.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Item;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.filter.VendaFilter;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class vendaBean {
	private List<Produto> listaProd;
	private List<Produto> listaProdFiltro;
	private List<Item> listaItens;
	private Venda vendaCadastro;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	
	private VendaFilter filtro;
	private List<Venda> listaVendas;
	
	public List<Venda> getListaVendas() {
		return listaVendas;
	}
	
	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}
	
	public VendaFilter getFiltro() {
		if (filtro == null) {
			filtro = new VendaFilter();
		}
		return filtro;
	}
	
	public void setFiltro(VendaFilter filtro) {
		this.filtro = filtro;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public Venda getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setValor_total(new BigDecimal("0.00"));
			vendaCadastro.setQtde(0);
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public List<Produto> getListaProd() {
		return listaProd;
	}

	public void setListaProd(List<Produto> listaProd) {
		this.listaProd = listaProd;
	}

	public List<Produto> getListaProdFiltro() {
		return listaProdFiltro;
	}

	public void setListaProdFiltro(List<Produto> listaProdFiltro) {
		this.listaProdFiltro = listaProdFiltro;
	}

	public List<Item> getListaItens() {
		if (listaItens == null) {
			listaItens = new ArrayList<>();
		}
		return listaItens;
	}

	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}

	public void carregarProdutos() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listaProd = dao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgError("Erro ao listar os produtos: " + ex.getMessage());
		}
	}

	public void adicionar(Produto produto) {
		int posicao = -1;
		for (int pos = 0; pos < listaItens.size() && posicao < 0; pos++) {
			Item itemTemp = listaItens.get(pos);

			if (itemTemp.getProduto().equals(produto)) {
				posicao = pos;
			}
		}

		Item item = new Item();
		item.setProduto(produto);

		if (posicao < 0) {
			item.setQuantidade(1);
			item.setValor_parcial(produto.getPreco());
			listaItens.add(item);
		} else {
			Item itemTemp = listaItens.get(posicao);
			item.setQuantidade(itemTemp.getQuantidade() + 1);
			item.setValor_parcial(produto.getPreco().multiply(new BigDecimal(item.getQuantidade())));
			listaItens.set(posicao, item);
		}
		vendaCadastro.setValor_total(vendaCadastro.getValor_total().add(produto.getPreco()));
		vendaCadastro.setQtde(vendaCadastro.getQtde() + 1);
	}

	public void remover(Item item) {
		int posicao = -1;
		for (int pos = 0; pos < listaItens.size() && posicao < 0; pos++) {
			Item itemTemp = listaItens.get(pos);

			if (itemTemp.getProduto().equals(item.getProduto())) {
				posicao = pos;
			}
		}
		if (posicao > -1) {
			listaItens.remove(posicao);
			vendaCadastro.setValor_total(vendaCadastro.getValor_total().subtract(item.getValor_parcial()));
			vendaCadastro.setQtde(vendaCadastro.getQtde() - item.getQuantidade());
		}
	}

	public void carregarDlgVenda() {
		vendaCadastro.setHorario(new Date());

		FuncionarioDAO fdao = new FuncionarioDAO();
		Funcionario funcionario = fdao.buscaPorCodigo(autenticacaoBean.getFuncionarioLogado().getCodigo());

		vendaCadastro.setFuncionario(funcionario);
	}

	public void salvar() {
		try {
			VendaDAO vdao = new VendaDAO();

			Long codigoVenda = vdao.salvar(vendaCadastro);
			Venda vendaFK = vdao.buscarPorCodigo(codigoVenda);

			for (Item item : listaItens) {
				item.setVenda(vendaFK);

				ItemDAO idao = new ItemDAO();
				idao.salvar(item);
			}

			vendaCadastro = new Venda();
			vendaCadastro.setValor_total(new BigDecimal("0.00"));

			listaItens = new ArrayList<Item>();

			FacesUtil.addMsgInfo("Venda salva com sucesso!!!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgError("Erro ao salvar a venda: " + ex.getMessage());
		}
	}
	
	public void buscar(){
		try {
			VendaDAO vdao = new VendaDAO();
			listaVendas = vdao.buscar(filtro);
			
			for (Venda venda : listaVendas) {
				System.out.println(venda);
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgError("Erro ao buscar os produtos: " + ex.getMessage());
		}
	}
}
