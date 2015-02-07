package br.com.mag.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;




import br.com.mag.business.Categoria;
import br.com.mag.business.Produto;
import br.com.mag.business.SubCategoria;
//import br.com.mag.enumeration.TipoGenero;
import br.com.mag.util.JPAUtil;

public class TesteCriacao {

	public static void main(String[] args) {
		
	/*	Produto produto = new Produto();
		Categoria categoria = new Categoria();
		SubCategoria subCategoria = new SubCategoria();
		
//		produto.setComplemento("teste");
//		produto.setDescricao("Batom");
//		produto.setEstoqueMinimo(5);
//		produto.setGenero(TipoGenero.FEMININO);
//		produto.setQtdEstoque(15);
//		produto.setUnidade("alj");
//		produto.setValorCusto(new BigDecimal("20.00"));
//		produto.setValorVenda(new BigDecimal("35.00"));

	//	produto.setSubcategoria(subcategoria);
		
//		categoria.setDescricao("Maquiagem");
		
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();


		manager.persist(produto);
		manager.persist(subCategoria);
		manager.persist(categoria);

		manager.getTransaction().commit();
		manager.close();*/
	}
	
}
