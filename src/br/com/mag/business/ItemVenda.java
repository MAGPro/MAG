package br.com.mag.business;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoItemVenda;
	@ManyToOne
	private Produto produto;
	private Integer quantidade;
	private Double valorVenda;
	@ManyToOne
	@JoinColumn(name ="venda_codigoVenda" )
	private Venda venda;
	
	public ItemVenda() {
		super();
	}
	
	public ItemVenda(Integer codigoItemVenda, Produto produto,
			Integer quantidade, Double valorVenda, Venda venda) {
		super();
		this.codigoItemVenda = codigoItemVenda;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorVenda = valorVenda;
		this.venda = venda;
	}
	
	public Double getTotal(){
		return valorVenda * quantidade;
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
	public Integer getCodigoItemVenda() {
		return codigoItemVenda;
	}
	public void setCodigoItemVenda(Integer codigoItemVenda) {
		this.codigoItemVenda = codigoItemVenda;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	@Override
	public Integer getId() {
		return codigoItemVenda;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
}
