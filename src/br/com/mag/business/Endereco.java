package br.com.mag.business;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.mag.business.enumeration.TipoEndereco;

@Entity
public class Endereco extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1912505727580353217L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoEndereco;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private Integer cep;
	private String cidade;
	private String estado;
	
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;
		
	public Endereco() {

	}

	public Endereco(String logradouro, String numero, String complemento,
			String bairro, Integer cep, String cidade, String estado,
			TipoEndereco tipo,Cliente cliente) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.tipo = tipo;
		this.cliente = cliente;
	}

	public Integer getCodigoEndereco() {
		return codigoEndereco;
	}

	public void setCodigoEndereco(Integer codigoEndereco) {
		this.codigoEndereco = codigoEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoEndereco getTipo() {
		return tipo;
	}

	public void setTipo(TipoEndereco tipo) {
		this.tipo = tipo;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return codigoEndereco;
	}
}
