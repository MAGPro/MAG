package br.com.mag.business;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.mag.business.enumeration.TipoGenero;

@Entity
public class Produto extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoProduto;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoGenero genero;
	private String complemento;
	private double valorVenda;
	private double valorCusto;
	private String unidade;
	private Integer estoqueMinimo;
	private Integer qtdEstoque;
	private boolean ativo;
	
	@OneToOne
	private SubCategoria subCategoria;
	
	public Produto(){
		
	}
	
	public Produto(String descricao, TipoGenero genero, String complemento,
			double valorVenda, double valorCusto, String unidade,
			Integer estoqueMinimo, Integer qtdEstoque, SubCategoria subcategoria) {
		super();
		this.descricao = descricao;
		this.genero = genero;
		this.complemento = complemento;
		this.valorVenda = valorVenda;
		this.valorCusto = valorCusto;
		this.unidade = unidade;
		this.estoqueMinimo = estoqueMinimo;
		this.qtdEstoque = qtdEstoque;
		this.subCategoria = subcategoria;
	}
	
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public TipoGenero getGenero() {
		return genero;
	}
	public void setGenero(TipoGenero genero) {
		this.genero = genero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public SubCategoria getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}
	public Integer getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public double getValorCusto() {
		return valorCusto;
	}
	public void setValorCusto(double valorCusto) {
		this.valorCusto = valorCusto;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public double getEstoqueMinimo() {
		return estoqueMinimo;
	}
	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
	public double getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return codigoProduto;
	}
		
}
