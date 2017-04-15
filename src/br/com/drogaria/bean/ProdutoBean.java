package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.DAO.FabricanteDAO;
import br.com.drogaria.DAO.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	private Produto prodCadastro;
	private List<Produto> listaProd;
	private List<Produto> listaProdFiltro;
	private String acao;
	private Long codigo;
	private List<Fabricante> listaFab;

	public List<Fabricante> getListaFab() {
		return listaFab;
	}

	public void setListaFab(List<Fabricante> listaFab) {
		this.listaFab = listaFab;
	}

	public Produto getProdCadastro() {
		return prodCadastro;
	}

	public void setProdCadastro(Produto prodCadastro) {
		this.prodCadastro = prodCadastro;
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

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void salvar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.salvar(prodCadastro);

			prodCadastro = new Produto();

			FacesUtil.addMsgInfo("Produto Salvo com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao incluir produto: " + ex.getMessage());
		}
	}

	public void novo() {
		prodCadastro = new Produto();
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listaProd = dao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgError("Erro ao listar os produtos: " + ex.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				ProdutoDAO dao = new ProdutoDAO();
				prodCadastro = dao.buscarPorCodigo(codigo);
			} else {
				prodCadastro = new Produto();
			}
			FabricanteDAO fdao = new FabricanteDAO();
			listaFab = fdao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgError("Erro ao obter dados do produto " + ex.getMessage());
		}
	}

	public void excluir() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(prodCadastro);

			FacesUtil.addMsgInfo("Produto removido com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao remover produto: " + ex.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.editar(prodCadastro);

			FacesUtil.addMsgInfo("Produto Editado com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao Editar produto: " + ex.getMessage());
		}
	}

}
