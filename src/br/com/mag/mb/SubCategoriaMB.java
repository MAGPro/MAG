package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.mag.business.SubCategoria;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.dao.SubCategoriaDAO;

@ManagedBean
public class SubCategoriaMB implements Serializable{
	
	private SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
	private SubCategoria subCategoria;
	private List<SubCategoria> subCategorias;


	public SubCategoriaMB() {
		// categoriasDisponiveis = new ArrayList<Categoria>();
		subCategoria = new SubCategoria();
		subCategorias = new ArrayList<SubCategoria>();
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public List<SubCategoria> getSubCategorias() {
		if (subCategorias.isEmpty()) {
			try {
				List<SubCategoria> subCategoriaList = subCategoriaDAO.listarTodos();
				for (SubCategoria subCategoria : subCategoriaList) {
					subCategorias.add(subCategoria);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return subCategorias;
	}

	public String salvar() throws DAOException {

		subCategoriaDAO.salvar(subCategoria);

		return "Salvando subcategoria..";
	}

	public String excluir() throws DAOException {

		subCategoriaDAO.excluir(subCategoria);

		return "Excluindo subcategoria..";
	}


}
