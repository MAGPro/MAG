package br.com.mag.business;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.mag.business.enumeration.TipoSituacaoVisita;

@Entity
public class Visita extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7846684840801768125L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoVisita;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataVisita;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataRealizada;
	
	@Enumerated(EnumType.STRING)
	private TipoSituacaoVisita situacaoVisita;
	
	@OneToOne(optional = false, fetch = FetchType.EAGER)
	private Endereco endereco;
		
	
	public Visita(){
		this.dataRealizada = new GregorianCalendar();
		this.dataVisita = new GregorianCalendar();
	}
	
	


	public Integer getCodigoVisita() {
		return codigoVisita;
	}


	public void setCodigoVisita(Integer codigoVisita) {
		this.codigoVisita = codigoVisita;
	}


	public Calendar getDataVisita() {
		return dataVisita;
	}


	public void setDataVisita(Calendar dataVisita) {
		this.dataVisita = dataVisita;
	}


	public Calendar getDataRealizada() {
		return dataRealizada;
	}


	public void setDataRealizada(Calendar dataRealizada) {
		this.dataRealizada = dataRealizada;
	}


	public TipoSituacaoVisita getSituacaoVisita() {
		return situacaoVisita;
	}


	public void setSituacaoVisita(TipoSituacaoVisita situacaoVisita) {
		this.situacaoVisita = situacaoVisita;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return codigoVisita;
	}
}
