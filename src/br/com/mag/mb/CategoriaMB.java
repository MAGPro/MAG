package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

import br.com.mag.business.Categoria;
import br.com.mag.business.dao.CategoriaDAO;
import br.com.mag.business.dao.DAOException;

@ManagedBean
public class CategoriaMB implements Serializable {

	private static final long serialVersionUID = 4837148550691075343L;
	
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Categoria categoria;
	private List<Categoria> categorias;

	public CategoriaMB() {
		// categoriasDisponiveis = new ArrayList<Categoria>();
		categoria = new Categoria();
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

//	public List<Categoria> getCategorias() {
//		return categorias;
//	}

	public List<Categoria> getCategorias() {
        if (categorias == null) {
                categorias = new ArrayList<Categoria>();
        }
        
        if (categorias.isEmpty()) {
                try {
                        List<Categoria> categoriaList = categoriaDAO.listarTodos();
                        for (Categoria categoria : categoriaList) {
                                categorias.add(categoria);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        return categorias;
}

	public void salvar() throws DAOException {
		
		RequestContext context = RequestContext.getCurrentInstance();
		
		if (categoria == null) {
			//enviar mensagem de alerta/erro ("Não é possivel salvar categoria nula!");
		} else if (categoria.getCodigoCategoria() != null) {
			categoriaDAO.editar(categoria);
			//context.addCallbackParam("Categoria atualizada com sucesso", true);
			
		} else {
			categoriaDAO.salvar(categoria);
			//context.addCallbackParam("Categoria cadastrada com sucesso", true);
			context.execute("confirmation.show();");
		}

	//	return "/buscaCategoria.faces?faces-redirect=true";
	}

	public String editar() throws DAOException {

		categoria = categoriaDAO.getPrimaryKey(categoria);

		return "/cadastraCategoria.faces";
	}
	
	public String buscar() throws DAOException {

		this.categorias = categoriaDAO.listar(categoria);

		return "/buscaCategoria.faces";
	}

	public String excluir() throws DAOException {
		if (categoria == null) {
			// enviar mensagem de alerta/erro ("Não é possivel excluir categoria nula!");
		} else {
			categoriaDAO.excluir(categoria);
		}
		return "/buscaCategoria.faces?faces-redirect=true";

	}
	
	public String voltar() {
		
		return "/buscaCategoria.faces";
	}


}
