package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.mag.business.Categoria;
import br.com.mag.business.dao.CategoriaDAO;
import br.com.mag.business.dao.DAOException;

@ManagedBean
public class CategoriaMB implements Serializable {

	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Categoria categoria;
	private List<Categoria> categorias;

	public CategoriaMB() {
		// categoriasDisponiveis = new ArrayList<Categoria>();
		categoria = new Categoria();
		categorias = new ArrayList<Categoria>();
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public List<Categoria> getCategorias() {
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

	public String salvar() throws DAOException {

		categoriaDAO.salvar(categoria);

		return "Salvando categoria..";
	}

	public String excluir() throws DAOException {

		categoriaDAO.excluir(categoria);

		return "Excluindo categoria..";
	}

}
