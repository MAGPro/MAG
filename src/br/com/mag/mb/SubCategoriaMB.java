package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mag.business.SubCategoria;
import br.com.mag.business.dao.CategoriaDAO;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.dao.SubCategoriaDAO;

@ManagedBean
//@ViewScoped
@SessionScoped
public class SubCategoriaMB implements Serializable{


	private static final long serialVersionUID = 1060409736908492398L;
	private SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
	private SubCategoria subCategoria;
	private List<SubCategoria> subCategorias;
	
	private Integer categoriaSelecionada;
	
	public Integer getCategoriaSelecionada() {
		return categoriaSelecionada;
	} 
	
	public void setCategoriaSelecionada(Integer categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public SubCategoriaMB() {
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
	
	
	public void enviarCat(){
		
		subCategoria.setCategoria(new CategoriaDAO().getPrimaryKey(categoriaSelecionada));	
		
	}
	
	public String salvar() throws DAOException {
	
		if (subCategoria.getDescricao() == null) {
			//enviar mensagem de alerta/erro ("Não é possivel salvar categoria nula!");
		} else if (subCategoria.getCodigoSubCategoria() != null) {
			//System.out.println(" Salvando "+ subCategoria.getDescricao());
			subCategoriaDAO.editar(subCategoria);
		
			} else {
				subCategoriaDAO.salvar(subCategoria);				
			}
		subCategoria = null;
		return "/buscaSubCategoria.faces";
	}

	public String editar() throws DAOException {
		
		categoriaSelecionada = subCategoria.getCategoria().getCodigoCategoria(); 
		return "/cadastraSubCategoria.faces";
	}
	
	public String cadastrar(){
				
		return "/cadastraSubCategoria.faces?faces-redirect=true";
	}
	
	public String visualizar() throws DAOException {

		subCategoria = subCategoriaDAO.getPrimaryKey(subCategoria);
		categoriaSelecionada = subCategoria.getCategoria().getCodigoCategoria(); 
		return "/visualizaSubCategoria.faces";
	}

	public String excluir() throws DAOException {
		if (subCategoria == null) {
			// enviar mensagem de alerta/erro ("Não é possivel excluir categoria nula!");
		} else {
			subCategoriaDAO.excluir(subCategoria);
		}
	
		return "/buscaSubCategoria.faces?faces-redirect=true";
	}
	

	public String voltar() {
		
		return "/buscaSubCategoria.faces?faces-redirect=true";
	}

}
