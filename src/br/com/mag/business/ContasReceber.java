package br.com.mag.business;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.mag.business.enumeration.TipoSituacaoContasReceber;

@Entity
public class ContasReceber extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7798822491274378375L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoContasReceber;
	@Enumerated(EnumType.STRING)
	private TipoSituacaoContasReceber situacaoContasReceber;
	private Date dataPagamento;
	private Calendar dataPrevista;
	private Double valorPago;
	private Double valorParcela;
	
	
	@ManyToOne
	private Venda venda;
	
	public ContasReceber(TipoSituacaoContasReceber situacaoContasReceber,
			Date dataPagamento, Double valorPago, Venda venda) {
		super();
		this.situacaoContasReceber = situacaoContasReceber;
		this.dataPagamento = dataPagamento;
		this.valorPago = valorPago;
		this.venda = venda;
	}
	
	public ContasReceber(){
//		this.dataPagamento = new GregorianCalendar();
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

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Calendar getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Calendar dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(Double valorParcela) {
		this.valorParcela = valorParcela;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return codigoContasReceber;
	}

}
