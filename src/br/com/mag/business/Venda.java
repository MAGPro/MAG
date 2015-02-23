package br.com.mag.business;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.mag.business.enumeration.TipoFormaPagamento;

@Entity
public class Venda extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoVenda;
	private double valorTotal;
	private double desconto;
	@Enumerated(EnumType.STRING)
	private TipoFormaPagamento formaPagamento;
	private Integer numeroParcelas;
	private Calendar dataVenda = Calendar.getInstance();
	private double valorTotalCobrar;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy="venda", fetch= FetchType.LAZY)
	private List<ContasReceber> contasReceber;
	
	@OneToMany(mappedBy="venda",targetEntity = ItemVenda.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemVenda> itens = new ArrayList<ItemVenda>();
	
	
	public Venda() {
		super();
		itens = new ArrayList<ItemVenda>();
	}

	public Venda(Integer codigoVenda, double valorTotal, double desconto,
			TipoFormaPagamento formaPagamento, Integer numeroParcelas,
			Calendar dataVenda, Cliente cliente,
			List<ContasReceber> contasReceber, List<ItemVenda> itens) {
		super();
		this.codigoVenda = codigoVenda;
		this.valorTotal = valorTotal;
		this.desconto = desconto;
		this.formaPagamento = formaPagamento;
		this.numeroParcelas = numeroParcelas;
		this.dataVenda = dataVenda;
		this.cliente = cliente;
		this.contasReceber = contasReceber;
		this.itens = itens;
	}

	public void adicionarItem(ItemVenda item) {
		this.itens.add(item);
	}
	public Integer getCodigoVenda() {
		return codigoVenda;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public TipoFormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(TipoFormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}
	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ContasReceber> getContasReceber() {
		return contasReceber;
	}
	public void setContasReceber(List<ContasReceber> contasReceber) {
		this.contasReceber = contasReceber;
	}
	public Calendar getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}
	public void setCodigoVenda(Integer codigoVenda) {
		this.codigoVenda = codigoVenda;
	}
	public List<ItemVenda> getItens() {
		return itens;
	}
	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}
	public double getValorTotalCobrar() {
		return valorTotalCobrar;
	}
	public void setValorTotalCobrar(double valorTotalCobrar) {
		this.valorTotalCobrar = valorTotalCobrar;
	}
	@Override
	public Integer getId() {
		return codigoVenda;
	}

	public boolean equals(Object obj) {
		return cliente.equals(obj);
	}

	public int hashCode() {
		return cliente.hashCode();
	}
	
}
