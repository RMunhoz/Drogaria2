package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.DAO.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {
	private Fabricante fabCadastro;
	private List<Fabricante> listaFab;
	private List<Fabricante> listaFabFiltro;
	private String acao;	
	private Long codigo;

	public Fabricante getFabCadastro() {
		return fabCadastro;
	}

	public void setFabCadastro(Fabricante fabCadastro) {
		this.fabCadastro = fabCadastro;
	}

	public List<Fabricante> getListaFab() {
		return listaFab;
	}

	public void setListaFab(List<Fabricante> listaFab) {
		this.listaFab = listaFab;
	}

	public List<Fabricante> getListaFabFiltro() {
		return listaFabFiltro;
	}

	public void setListaFabFiltro(List<Fabricante> listaFabFiltro) {
		this.listaFabFiltro = listaFabFiltro;
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
			FabricanteDAO dao = new FabricanteDAO();
			dao.salvar(fabCadastro);

			fabCadastro = new Fabricante();

			FacesUtil.addMsgInfo("Fabricante Salvo com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao incluir fabricante: " + ex.getMessage());
		}
	}

	public void novo() {
		fabCadastro = new Fabricante();
	}

	public void carregarPesquisa() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			listaFab = dao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgError("Erro ao listar os fabricantes: " + ex.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				FabricanteDAO dao = new FabricanteDAO();
				fabCadastro = dao.buscarPorCodigo(codigo);
			} else {
				fabCadastro = new Fabricante();
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgError("Erro ao obter dados do fabricante " + ex.getMessage());
		}
	}

	public void excluir() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(fabCadastro);

			FacesUtil.addMsgInfo("Fabricante removido com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao remover fabricante: " + ex.getMessage());
		}
	}

	public void editar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.editar(fabCadastro);

			FacesUtil.addMsgInfo("Fabricante Editado com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao Editarr fabricante: " + ex.getMessage());
		}
	}

}
