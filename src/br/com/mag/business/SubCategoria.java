package br.com.mag.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class SubCategoria extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoSubCategoria;
	private String descricao;
	@ManyToOne
	private Categoria categoria;
	private boolean ativo = true;
	
	
	public SubCategoria(){
		
	}
		
	public SubCategoria(String descricao, Categoria categoria) {
		super();
		this.descricao = descricao;
		this.categoria = categoria;
	}
	
	public Integer getCodigoSubCategoria() {
		return codigoSubCategoria;
	}
	public void setCodigoSubCategoria(Integer codigoCategoria) {
		this.codigoSubCategoria = codigoCategoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return codigoSubCategoria;
	}	
}
