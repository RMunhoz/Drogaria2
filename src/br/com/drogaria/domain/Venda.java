package br.com.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_vendas")
@NamedQueries({
	@NamedQuery(name="Venda.listar", query="SELECT venda FROM Venda venda"),
	@NamedQuery(name="Venda.buscarPorCodigo", query="SELECT venda FROM Venda venda WHERE venda.codigo = :codigo")
})
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ven_codigo")
	private Long codigo;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "ven_horario", nullable = false)
	private Date horario;

	@Column(name = "ven_valor_total", precision = 7, scale = 2, nullable = false)
	private BigDecimal valor_total;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_funcionarios_fun_codigo", referencedColumnName = "fun_codigo", nullable = false)
	private Funcionario funcionario;
	
	@Transient
	private Integer qtde;
	
	public Integer getQtde() {
		return qtde;
	}
	
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getHorario() {
		return horario;
	}
	
	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Venda - codigo: " + codigo + ", horario: " + horario + ", valor: " + valor_total + ", funcionario: "
				+ funcionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
