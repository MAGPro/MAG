package br.com.mag.business.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.mag.business.Categoria;
import br.com.mag.business.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {
	
	public List<Cliente> pesquisar(String nome){
		
		StringBuffer sql = new StringBuffer ("Select c from Cliente c where c.nome like '%" + nome +"%'");
		Query query = getEntityManager().createQuery(sql.toString());
		List<Cliente> lista = query.getResultList();
		return lista;
	}
	
	public List<Cliente> listar(Cliente cliente){
		List<Cliente> clientes = new ArrayList<Cliente>();
		StringBuffer sql = new StringBuffer("Select c from Cliente c ");
		
//		sql.append(" WHERE c.ativo = ").append(cliente.isAtivo());
		
		if (cliente.getNome() != null) {
			sql.append(" AND c.nome LIKE '%").append(cliente.getNome()).append("%'");
		}
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		clientes = query.getResultList();
		
		return clientes;
	} 
	
/*	public List<Categoria> listarAtivas(){
		List<Categoria> categorias = new ArrayList<Categoria>();
		StringBuffer sql = new StringBuffer("Select c from Categoria c ");
		
		sql.append(" WHERE c.ativo = TRUE");
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		categorias = query.getResultList();
		
		return categorias;
	} */
	
	public void buscarCategoria(Integer id){
		getPrimaryKey(id);
		
	}

}
