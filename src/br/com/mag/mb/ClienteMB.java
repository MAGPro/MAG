package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

import br.com.mag.business.Cliente;
import br.com.mag.business.Endereco;
import br.com.mag.business.dao.ClienteDAO;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.enumeration.TipoEndereco;
import br.com.mag.business.enumeration.TipoSituacaoCliente;
import br.com.mag.business.repository.Filtro;
import br.com.mag.business.repository.FiltroCliente;

@ManagedBean
@SessionScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 7247920166232548053L;

	private Cliente cliente;
	private ClienteDAO clienteDao;
	// private List<Cliente> clientes;
	private Endereco enderecoSelecionado;
	private Endereco endereco = new Endereco();
	private List<Cliente> clientesAtivos = new ArrayList<Cliente>();

	private FiltroCliente clientesF = new FiltroCliente();

	private Filtro filtro = new Filtro();
	private LazyDataModel<Cliente> model;
	private SelectableDataModel<Cliente> select;

	public ClienteMB() {
		model = new LazyDataModel<Cliente>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Cliente> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);

				setRowCount(clientesF.quantidadeFiltrados(filtro));

				return clientesF.filtrados(filtro);
			}
		};
		cliente = new Cliente();
		clienteDao = new ClienteDAO();
		
/*		select = new SelectableDataModel<Cliente>() {
						
			@Override
			public Object getRowKey(Cliente cliente) {
				return cliente.getEnderecos();
			}
			
			@Override
			public Cliente getRowData(String rowKey) {
		        List<Cliente> cli = (List<Cliente>) getWrappedData();

		        for(Cliente c : cli) {
		            if(c.getCodigoCliente().equals(rowKey))
		                return c;
		        }

		        return null;
			}
		};*/

	}

	public Filtro getFiltro() {
		return filtro;
	}

	public LazyDataModel<Cliente> getModel() {
		return model;
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Cliente> getClientesAtivos() {
		if(clientesAtivos == null){
			clientesAtivos = new ArrayList<Cliente>();
		}
		
		if(clientesAtivos.isEmpty()){
			try{
				List<Cliente> clienteList = clienteDao.listarAtivos();
				for (Cliente cliente : clienteList) {
					clientesAtivos.add(cliente);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return clientesAtivos;
	}

	public void adicionarEndereco() {

		cliente.adicionarEndereco(endereco);
		endereco = new Endereco();
	}

	public String salvar() throws DAOException {

		RequestContext context = RequestContext.getCurrentInstance();

		try {
			if (cliente == null) {
				// enviar mensagem de alerta/erro

			} else if (cliente.getCodigoCliente() == null) {
				clienteDao.salvar(cliente);

			} else {
				clienteDao.editar(cliente);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		cliente = new Cliente();

		return "/buscaCliente.faces?faces-redirect=true";

	}

	public String editar() throws DAOException {

		cliente = clienteDao.getPrimaryKey(cliente);
		return "/cadastraCliente.faces";
	}

	public String cadastrar() {
		cliente = new Cliente();
		return "/cadastraCliente.faces?faces-redirect=true";
	}

	public String visualizar() throws DAOException {

		cliente = clienteDao.getPrimaryKey(cliente);

		return "/visualizaCliente.faces";
	}

	// Carregar enumerador
	public TipoSituacaoCliente[] getTipoSituacoes() {
		return TipoSituacaoCliente.values();
	}

	// Carregar enumerador TipoEndereco
	public TipoEndereco[] getTipoEnderecos() {
		return TipoEndereco.values();
	}

	public String excluir() throws DAOException {

		try {

			if (cliente == null) {
				// enviar mensagem de alerta/erro
			} else {
				clienteDao.excluir(cliente);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/buscaCliente.faces?faces-redirect=true";

	}

	public void excluirEndereco() throws DAOException {
		if (this.enderecoSelecionado != null) {
			this.cliente.getEnderecos().remove(this.enderecoSelecionado);
			// enderecoSelecionado = null;
		}
	}
	
	public String desativar() throws DAOException {
		
		if (cliente.getSituacaoCliente().equals(TipoSituacaoCliente.ADIPLENTE)){
			cliente.setSituacaoCliente(TipoSituacaoCliente.INADIPLENTE);
		} else{
			cliente.setSituacaoCliente(TipoSituacaoCliente.ADIPLENTE);
		}
		clienteDao.editar(cliente);
	
		return "/buscaCliente.faces?faces-redirect=true";
	}
	

	public String voltar() {

		return "/buscaCliente.faces";
	}

}
