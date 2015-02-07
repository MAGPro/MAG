package br.com.mag.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import br.com.mag.business.enumeration.FormaPagamento;
import br.com.mag.business.enumeration.SituacaoContasReceber;

@Entity
public class Venda {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoVenda;
	private BigDecimal valorTotal;
	private BigDecimal desconto;
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;
	private Integer numeroParcelas;
	private Calendar dataVenda;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy="venda", fetch= FetchType.LAZY)
	private List<ContasReceber> contasReceber;
	
	@OneToMany(mappedBy="venda")
	private List<ItemVenda> itensVenda;
		
	public Venda(BigDecimal valorTotal, BigDecimal desconto,
			FormaPagamento formaPagamento, Integer numeroParcelas,
			Calendar dataVenda, Cliente cliente,
			List<ContasReceber> contasReceber, List<ItemVenda> itensVenda) {
		super();
		this.valorTotal = valorTotal;
		this.desconto = desconto;
		this.formaPagamento = formaPagamento;
		this.numeroParcelas = numeroParcelas;
		this.dataVenda = dataVenda;
		this.cliente = cliente;
		this.contasReceber = contasReceber;
		this.itensVenda = itensVenda;
	}
	
	public Venda(){
		this.dataVenda = new GregorianCalendar();
	}
	
	public Integer getCodigoVenda() {
		return codigoVenda;
	}
	public void setCodigoCompra(Integer codigoVenda) {
		this.codigoVenda = codigoVenda;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
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
	
}
