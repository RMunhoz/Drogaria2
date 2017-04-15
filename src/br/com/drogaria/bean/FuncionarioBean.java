package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.DAO.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {
	private Funcionario funCadastro;
	private List<Funcionario> listaFun;
	private List<Funcionario> listaFunFiltro;
	private String acao;
	private Long codigo;

	public Funcionario getFunCadastro() {
		return funCadastro;
	}

	public void setFunCadastro(Funcionario funCadastro) {
		this.funCadastro = funCadastro;
	}

	public List<Funcionario> getListaFun() {
		return listaFun;
	}

	public void setListaFun(List<Funcionario> listaFun) {
		this.listaFun = listaFun;
	}

	public List<Funcionario> getListaFunFiltro() {
		return listaFunFiltro;
	}

	public void setListaFunFiltro(List<Funcionario> listaFunFiltro) {
		this.listaFunFiltro = listaFunFiltro;
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
			FuncionarioDAO dao = new FuncionarioDAO();
//			funCadastro.setSenha(funCadastro.getSenha());
			funCadastro.setSenha(DigestUtils.md5Hex(funCadastro.getSenha()));
			dao.salvar(funCadastro);

			funCadastro = new Funcionario();

			FacesUtil.addMsgInfo("Fabricante Salvo com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao incluir fabricante: " + ex.getMessage());
		}
	}

	public void novo() {
		funCadastro = new Funcionario();
	}

	public void carregarPesquisa() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			listaFun = dao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgError("Erro ao listar os funcionários: " + ex.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				FuncionarioDAO dao = new FuncionarioDAO();
				funCadastro = dao.buscaPorCodigo(codigo);
			} else {
				funCadastro = new Funcionario();
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgError("Erro ao obter dados do funcionários " + ex.getMessage());
		}
	}

	public void excluir() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.excluir(funCadastro);

			FacesUtil.addMsgInfo("Funcionário removido com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao remover funcionário: " + ex.getMessage());
		}
	}

	public void editar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
//			funCadastro.setSenha(funCadastro.getSenha());
			funCadastro.setSenha(DigestUtils.md5Hex(funCadastro.getSenha()));
			dao.editar(funCadastro);

			FacesUtil.addMsgInfo("Funcionário Editado com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao Editar funcionário: " + ex.getMessage());
		}
	}

}
