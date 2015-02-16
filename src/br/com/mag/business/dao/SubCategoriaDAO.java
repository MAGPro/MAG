package br.com.mag.business.dao;

import br.com.mag.business.SubCategoria;

public class SubCategoriaDAO extends GenericDAO <SubCategoria> {
	
	public void buscarSubCategoria(Integer id){
		getPrimaryKey(id);
		
	}
	

}
