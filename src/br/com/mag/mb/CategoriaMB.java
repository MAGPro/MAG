package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.SortOrder;
import org.primefaces.model.LazyDataModel;

import br.com.mag.business.Categoria;
import br.com.mag.business.dao.CategoriaDAO;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.repository.Filtro;
import br.com.mag.business.repository.FiltroCategoria;


@ManagedBean
public class CategoriaMB implements Serializable {

	private static final long serialVersionUID = 4837148550691075343L;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Categoria categoria = new Categoria();
	private List<Categoria> categoriasAtivas = new ArrayList<Categoria>();
	
	//@Inject
	private FiltroCategoria categorias = new FiltroCategoria();

	private Filtro filtro = new Filtro();
	private LazyDataModel<Categoria> model;
	
	public CategoriaMB() {
		model = new LazyDataModel<Categoria>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Categoria> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);
				
				setRowCount(categorias.quantidadeFiltrados(filtro));
				
				return categorias.filtrados(filtro);
			}
			
		};
	}
	
	public Filtro getFiltro() {
		return filtro;
	}

	public LazyDataModel<Categoria> getModel() {
		return model;
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

	public List<Categoria> getCategoriasAtivas() {
        if (categoriasAtivas == null) {
                categoriasAtivas = new ArrayList<Categoria>();
        }
        
        if (categoriasAtivas.isEmpty()) {
                try {
                        List<Categoria> categoriaList = categoriaDAO.listarAtivas();
                        for (Categoria categoria : categoriaList) {
                                categoriasAtivas.add(categoria);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        return categoriasAtivas;
}

	public String salvar() throws DAOException {
		
		if (categoria == null) {
			//enviar mensagem de alerta/erro ("Não é possivel salvar categoria nula!");
		} else if (categoria.getCodigoCategoria() != null) {
			categoriaDAO.editar(categoria);
		
		} else {
			categoriaDAO.salvar(categoria);			
		}
		categoria = null;
		return "/buscaCategoria.faces";
	}

	public String editar() throws DAOException {

		categoria = categoriaDAO.getPrimaryKey(categoria);

		return "/cadastraCategoria.faces";
	}
	
	public String cadastrar(){

		return "/cadastraCategoria.faces?faces-redirect=true";
	}
	
	public String visualizar() throws DAOException {

		categoria = categoriaDAO.getPrimaryKey(categoria);

		return "/visualizaCategoria.faces";
	}
	
	public String desativar() throws DAOException {
		
		if (categoria.isAtivo()){
			categoria.setAtivo(false);
		} else{
			categoria.setAtivo(true);
		}
		categoriaDAO.editar(categoria);
	
		return "/buscaCategoria.faces?faces-redirect=true";
	}
	
	
	public String voltar() {
		
		return "/buscaCategoria.faces?faces-redirect=true";
	}

}
