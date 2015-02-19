package br.com.mag.business.dao;


import java.util.List;


import javax.persistence.Query;

import br.com.mag.business.Produto;
import br.com.mag.business.SubCategoria;



public class ProdutoDAO extends GenericDAO <Produto>{
	
	
	public List<SubCategoria> listarSelecionadas(Integer categoriaSelecionada){
		
		StringBuffer sql = new StringBuffer ("Select s from SubCategoria s where categoria_codigoCategoria = " + categoriaSelecionada);
		Query query = getEntityManager().createQuery(sql.toString());
		List<SubCategoria> lista = query.getResultList();
		return lista;
	}
	

}
