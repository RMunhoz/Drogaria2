package br.com.drogaria.domain;

import java.math.BigDecimal;

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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tb_produtos")
@NamedQueries({ @NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto"),
		@NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto WHERE produto.codigo = :codigo") })
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pro_codigo")
	private Long codigo;

	@NotEmpty(message = "Campo Descrição é obrigatório!")
	@Size(min = 5, max = 50, message = "Tamanho inválido para o campo descrição (5 - 50)")
	@Column(name = "pro_descricao", length = 50, nullable = false)
	private String descricao;

	@NotNull(message = "Campo Preço é obrigatório!")
	@DecimalMin(value = "0.00", message = "Informe um valor > ou = a 0")
	@DecimalMax(value = "99999.99", message = "Informe um valor < 100.000")
	@Digits(integer = 5, fraction = 2, message="Informe um valor válido para o campo preço!")
	@Column(name = "pro_preco", precision = 7, scale = 2, nullable = false)
	private BigDecimal preco;

	@NotNull(message = "Campo Qtde é obrigatório!")
	@Min(value = 0, message = "Informe um valor > ou = a 0")
	@Max(value = 999, message = "Informe um valor < 1.000")
	@Column(name = "pro_quantidade", nullable = false)
	private Integer quantidade;

	@NotNull(message = "Campo fabricante é Obrigatório!")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_fabricantes_fab_codigo", referencedColumnName = "fab_codigo", nullable = false)
	private Fabricante fabricante;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "Código: " + codigo + ", Descricao: " + descricao + ", Preco: " + preco + ", Qtde: " + quantidade
				+ ", Fabricante: " + fabricante;
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
