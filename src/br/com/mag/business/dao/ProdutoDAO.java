package br.com.mag.business.dao;


import java.util.List;




import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.mag.business.Produto;
import br.com.mag.business.SubCategoria;
import br.com.mag.util.JPAUtil;



public class ProdutoDAO extends GenericDAO <Produto>{
	
	
	public List<SubCategoria> listarSelecionadas(Integer categoriaSelecionada){
		
		StringBuffer sql = new StringBuffer ("Select s from SubCategoria s where categoria_codigoCategoria = " + categoriaSelecionada);
		Query query = getEntityManager().createQuery(sql.toString());
		List<SubCategoria> lista = query.getResultList();
		return lista;
	}
	
	EntityManager entityManager = new JPAUtil().getEntityManager();
    
    
    public void atualizaEstoque(Produto produto){
        String ATUALIZA_ESTOQUE = "UPDATE Produto  SET  qtdEstoque = ? WHERE codigoProduto = ?";
        Query query=null;
        query=entityManager.createQuery(ATUALIZA_ESTOQUE);
        query.setParameter(1, produto.getQtdEstoque());
        query.setParameter(2, produto.getCodigoProduto());
        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();
    //    entityManager.close();
    
    }
	

}
