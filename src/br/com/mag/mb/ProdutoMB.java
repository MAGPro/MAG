package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ManagedBean;

import br.com.mag.business.Categoria;
import br.com.mag.business.Produto;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.dao.ProdutoDAO;

@ManagedBean
public class ProdutoMB implements Serializable{
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private Produto produto;
	//private List<Categoria> categoriasDisponiveis;
	private List<Produto> produtos;
	
	
		public ProdutoMB() {
	       // categoriasDisponiveis = new ArrayList<Categoria>();
	        produto = new Produto();
	        produtos = new ArrayList<Produto>();
	    }

	    public void setProduto(Produto produto) {
	        this.produto = produto;
	    }

	    public Produto getProduto() {
	        return produto;
	    }
	    
	    public List<Produto> getProdutos() {
			if (produtos.isEmpty()) {
				try {
					List<Produto> produtoList = produtoDAO.listarTodos();
					for (Produto produto : produtoList) {
						produtos.add(produto);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return produtos;
		}

	
	    public String salvar() throws DAOException {
		
	    	produtoDAO.salvar(produto);
	    	
	    	return "Salvando produto..";
	    }
	
	   

}
