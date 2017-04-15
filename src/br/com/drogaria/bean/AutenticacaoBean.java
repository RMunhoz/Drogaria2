package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.DAO.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
	
	public String autenticar(){
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
//			funcionarioLogado = fdao.autenticar(funcionarioLogado.getCpf(), funcionarioLogado.getSenha());
			funcionarioLogado = fdao.autenticar(funcionarioLogado.getCpf(), DigestUtils.md5Hex(funcionarioLogado.getSenha()));
			
			if (funcionarioLogado == null) {
				FacesUtil.addMsgError("CPF e/ou senha inválido!");
				return null;
			}else {
				FacesUtil.addMsgInfo("Funcionario autenticado com sucesso");
				return "/pages/principal.xhtml?faces-redirect=true";
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgError("Erro ao autenticar no sistema " + ex.getMessage());
			return null;
		}
	}
	
	public String logout(){
		funcionarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}
}
