package br.com.mag.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ItemVenda {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoItemVenda;
	@ManyToOne
	private Produto produto;
	private Integer quantidade;
	@ManyToOne
	private Venda venda;
	
	
	public ItemVenda(Produto produto, Integer quantidade, Venda venda) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
		this.venda = venda;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
