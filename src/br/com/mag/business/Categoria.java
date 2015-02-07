package br.com.mag.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Categoria extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoCategoria;
	
	private String descricao;
	
	@OneToMany(mappedBy="categoria",fetch=FetchType.LAZY)
	private List<SubCategoria> subCategorias;
	
	
	public Categoria(String descricao) {
		super();
		this.descricao = descricao;
	}
	
	
	public List<SubCategoria> getSubCategorias() {
		return subCategorias;
	}
	
	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return codigoCategoria;
	}
	
	

}
