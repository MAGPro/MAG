package br.com.mag.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.mag.business.enumeration.TipoSituacaoContasReceber;

@Entity
public class ContasReceber {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoContasReceber;
	@Enumerated(EnumType.STRING)
	private TipoSituacaoContasReceber situacaoContasReceber;
	private Calendar dataPagamento;
	private BigDecimal valorPago;
	
	@ManyToOne
	private Venda venda;
	
	public ContasReceber(TipoSituacaoContasReceber situacaoContasReceber,
			Calendar dataPagamento, BigDecimal valorPago, Venda venda) {
		super();
		this.situacaoContasReceber = situacaoContasReceber;
		this.dataPagamento = dataPagamento;
		this.valorPago = valorPago;
		this.venda = venda;
	}
	
	public ContasReceber(){
		this.dataPagamento = new GregorianCalendar();
	}

	public Integer getCodigoContasReceber() {
		return codigoContasReceber;
	}

	public void setCodigoContasReceber(Integer codigoContasReceber) {
		this.codigoContasReceber = codigoContasReceber;
	}

	public TipoSituacaoContasReceber getSituacaoContasReceber() {
		return situacaoContasReceber;
	}

	public void setSituacaoContasReceber(TipoSituacaoContasReceber situacaoContasReceber) {
		this.situacaoContasReceber = situacaoContasReceber;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	

	
	
	
}
