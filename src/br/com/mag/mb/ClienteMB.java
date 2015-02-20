package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import br.com.mag.business.Cliente;
import br.com.mag.business.Endereco;
import br.com.mag.business.dao.ClienteDAO;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.enumeration.TipoEndereco;
import br.com.mag.business.enumeration.TipoSituacaoCliente;

@ManagedBean
@SessionScoped
public class ClienteMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7247920166232548053L;
	
	private Cliente cliente = new Cliente();
	private ClienteDAO clienteDao = new ClienteDAO();
	private List<Cliente> clientes;
	
	private Endereco endereco = new Endereco();
	

	public ClienteMB() {
		clientes = new ArrayList<Cliente>();
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

	public List<Cliente> getClientes() {
		if (clientes.isEmpty()) {
			try {
				List<Cliente> clienteList = clienteDao.listarTodos();
				for (Cliente cliente : clienteList) {
					clientes.add(cliente);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return clientes;
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
				context.execute("confirmation.show();");

			} else {
				if (clienteDao.getPrimaryKey(cliente) == null) {
					throw new Exception(
							"Erro ao atualizar, registro não encontrado");
				}

				clienteDao.editar(cliente);

			}

		} catch (Exception e) {
			e.printStackTrace();
			}

		return "/buscaCliente.faces?faces-redirect=true";

	}

	public String editar() throws DAOException {

		cliente = clienteDao.getPrimaryKey(cliente);
		return "/cadastraCliente.faces";
	}
	
	public String cadastrar(){

		return "/cadastraCliente.faces?faces-redirect=true";
	}
	
	public String visualizar() throws DAOException {

		cliente = clienteDao.getPrimaryKey(cliente);

		return "/visualizaCliente.faces";
	}

/*	public String buscar() throws DAOException {

		this.clientes = dao.listar(cliente);

		return "/buscaCliente.xhtml";
	}*/

	// Carregar enumerador
	public TipoSituacaoCliente[] getTipoSituacoes() {
		return TipoSituacaoCliente.values();
	}
	
	// Carregar enumerador TipoEndereco
	public TipoEndereco[] getTipoEnderecos() {
		return TipoEndereco.values();
	}
	
	public String excluir() throws DAOException {
		if (cliente == null) {
			// enviar mensagem de alerta/erro ("Não é possivel excluir categoria nula!");
		} else {
			clienteDao.excluir(cliente);
		}
		return "/buscaCliente.faces?faces-redirect=true";

	}
	
	public String voltar() {
		
		return "/buscaCliente.faces";
	}

}
