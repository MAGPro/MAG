package br.com.mag.business;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.mag.business.enumeration.TipoEndereco;
import br.com.mag.business.enumeration.TipoSituacaoCliente;

@Entity
public class Cliente extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3164037012965263022L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoCliente = null;
	private String nome;
	private String cpf;
	private Integer rg;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;
	
	private String telResidencial;
	private String telComercial;
	private String telMovel;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private TipoSituacaoCliente situacaoCliente;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_codigoCliente")
	private List<Endereco> enderecos;
		
	public Cliente(){
		this.dataNascimento = new GregorianCalendar();
	}
	
	public Integer getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getRg() {
		return rg;
	}
	public void setRg(Integer rg) {
		this.rg = rg;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelResidencial() {
		return telResidencial;
	}
	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}
	public String getTelComercial() {
		return telComercial;
	}
	public void setTelComercial(String telComercial) {
		this.telComercial = telComercial;
	}
	public String getTelMovel() {
		return telMovel;
	}
	public void setTelMovel(String telMovel) {
		this.telMovel = telMovel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TipoSituacaoCliente getSituacaoCliente() {
		return situacaoCliente;
	}
	public void setSituacaoCliente(TipoSituacaoCliente situacaoCliente) {
		this.situacaoCliente = situacaoCliente;
	}
		
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return codigoCliente;
	}

}
