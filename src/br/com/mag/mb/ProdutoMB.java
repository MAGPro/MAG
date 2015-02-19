package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.mag.business.Categoria;
import br.com.mag.business.Produto;
import br.com.mag.business.SubCategoria;
import br.com.mag.business.dao.CategoriaDAO;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.dao.ProdutoDAO;
import br.com.mag.business.dao.SubCategoriaDAO;
import br.com.mag.business.enumeration.TipoGenero;

@ManagedBean
//@ViewScoped
@SessionScoped
public class ProdutoMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 901114338254703798L;
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private Produto produto;
	private List<Produto> produtos;
	private Integer categoriaSelecionada;
	private Integer subCategoriaSelecionada;
	

	private List<SubCategoria> subCategorias;
	
		public ProdutoMB() {
	        produto = new Produto();
	        produtos = new ArrayList<Produto>();
	        
	    }
		
		public Integer getCategoriaSelecionada() {
			return categoriaSelecionada;
		}
		
		public Integer getSubCategoriaSelecionada() {
			return subCategoriaSelecionada;
		}
		
		public void setCategoriaSelecionada(Integer categoriaSelecionada) {
			this.categoriaSelecionada = categoriaSelecionada;
		}
		
		public void setSubCategoriaSelecionada(Integer subCategoriaSelecionada) {
			this.subCategoriaSelecionada = subCategoriaSelecionada;
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
	    
	    
	    
	    public void enviarSubCat(){
	    	System.out.println(subCategoriaSelecionada);
			produto.setSubCategoria(new SubCategoriaDAO().getPrimaryKey(subCategoriaSelecionada));	
			System.out.println(produto.getSubCategoria().getDescricao());
		}
	    
	    public void enviarCat(){
			
	    	System.out.println(categoriaSelecionada);
	    	subCategorias = null;
			getSubCategorias();
			
		}
	    
	    public List<SubCategoria> getSubCategorias() {
	    	
	    
		//	if (categoriaSelecionada != null) {
				System.out.println(categoriaSelecionada);
				if (categoriaSelecionada != null){
				try {
					List<SubCategoria> subCategoriaList = produtoDAO.listarSelecionadas(categoriaSelecionada);
				//	for (SubCategoria subCategoria : subCategoriaList) {
						subCategorias = subCategoriaList;
				//	}
				} catch (Exception e) {
					e.printStackTrace();
				}
	//		} else {
	//			this.subCategorias = subCategoriaMB.getSubCategorias();
	//		}
				}
			
			return subCategorias;
		}
	    

	 // Carregar enumerador
		public TipoGenero[] getTipoGenero() {
			return TipoGenero.values();
		}
		
	    public String salvar() throws DAOException {
			
	    	System.out.println(subCategoriaSelecionada);
	    	System.out.println(produto.getSubCategoria());
			if (produto == null) {
				//enviar mensagem de alerta/erro ("Não é possivel salvar categoria nula!");
			} else if (produto.getCodigoProduto() != null) {
				produtoDAO.editar(produto);
			
			} else {
				produtoDAO.salvar(produto);			
			}
			produto = null;
			return "/buscaProduto.faces";
		}

		public String editar() throws DAOException {

			produto = produtoDAO.getPrimaryKey(produto);
			categoriaSelecionada = produto.getSubCategoria().getCategoria().getCodigoCategoria();
			subCategoriaSelecionada = produto.getSubCategoria().getCodigoSubCategoria();		
			

			return "/cadastraProduto.faces";
		}
		
		public String cadastrar(){
			return "/cadastraProduto.faces?faces-redirect=true";
		}
		
		public String visualizar() throws DAOException {
			
			produto = produtoDAO.getPrimaryKey(produto);
			categoriaSelecionada = produto.getSubCategoria().getCategoria().getCodigoCategoria();
			subCategoriaSelecionada = produto.getSubCategoria().getCodigoSubCategoria();		
			return "/visualizaProduto.faces";
		}

		public String excluir() throws DAOException {
			if (produto == null) {
				// enviar mensagem de alerta/erro ("Não é possivel excluir categoria nula!");
			} else {
				produtoDAO.excluir(produto);
			}
		
			return "/buscaProduto.faces?faces-redirect=true";
		}
		
		public String voltar() {			
			return "/buscaProduto.faces?faces-redirect=true";
		}
}
