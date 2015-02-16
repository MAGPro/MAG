package br.com.mag.business;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.mag.business.enumeration.TipoSituacaoVisita;

@Entity
public class Visita {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoVisita;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataVisita;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataRealizada;
	
	@Enumerated(EnumType.STRING)
	private TipoSituacaoVisita situacaoVisita;
	
	@OneToOne
	private Cliente cliente;
		
	
	public Visita(){
		this.dataRealizada = new GregorianCalendar();
		this.dataVisita = new GregorianCalendar();
	}
}
