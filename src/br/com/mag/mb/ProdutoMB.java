package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;








import javax.faces.bean.ManagedBean;

import br.com.mag.business.Categoria;
import br.com.mag.business.Produto;
import br.com.mag.business.dao.CategoriaDAO;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.dao.ProdutoDAO;
import br.com.mag.business.dao.SubCategoriaDAO;
import br.com.mag.business.enumeration.TipoGenero;
import br.com.mag.business.enumeration.TipoSituacaoCliente;

@ManagedBean
public class ProdutoMB implements Serializable{
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private Produto produto;
	//private List<Categoria> categoriasDisponiveis;
	private List<Produto> produtos;
	private Integer categoriaSelecionada;
	private Integer subCategoriaSelecionada;
	
	
		public ProdutoMB() {
	       // categoriasDisponiveis = new ArrayList<Categoria>();
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

	 // Carregar enumerador
		public TipoGenero[] getTipoGenero() {
			return TipoGenero.values();
		}
		
		public void enviarSubCat(){
			
			produto.setSubCategoria(new SubCategoriaDAO().getPrimaryKey(subCategoriaSelecionada));	
			
		}
		
	    public String salvar() throws DAOException {
			
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

			return "/cadastraProduto.faces";
		}
		
		public String cadastrar(){

			return "/cadastraProduto.faces?faces-redirect=true";
		}
		
		public String visualizar() throws DAOException {

			produto = produtoDAO.getPrimaryKey(produto);

			return "/visualizaProduto.faces";
		}
		
//		public String buscar() throws DAOException {
//
//			this.produtos = produtoDAO.listar(produto);
//
//			return "/buscaProduto.faces";
//		}

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
