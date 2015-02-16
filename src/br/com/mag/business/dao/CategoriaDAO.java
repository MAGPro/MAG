package br.com.mag.business.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.mag.business.Categoria;

public class CategoriaDAO extends GenericDAO <Categoria> {
	
	public List<Categoria> listar(Categoria categoria){
		List<Categoria> categorias = new ArrayList<Categoria>();
		StringBuffer sql = new StringBuffer("Select c from Categoria c ");
		
		sql.append(" WHERE c.ativo = ").append(categoria.isAtivo());
		
		if (categoria.getDescricao() != null) {
			sql.append(" AND c.descricao LIKE '%").append(categoria.getDescricao()).append("%'");
		}
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		categorias = query.getResultList();
		
		return categorias;
	} 
	
	public void buscarCategoria(Integer id){
		getPrimaryKey(id);
		
	}


}
