package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

import br.com.mag.business.Cliente;
import br.com.mag.business.Endereco;
import br.com.mag.business.dao.EnderecoDAO;
import br.com.mag.business.dao.ClienteDAO;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.enumeration.TipoSituacaoCliente;

@ManagedBean
public class ClienteMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7247920166232548053L;
	
	private Cliente cliente;
	private ClienteDAO clienteDao = new ClienteDAO();
	private List<Cliente> clientes;
	
	private Endereco endereco;
	private EnderecoDAO EnderecoDao = new EnderecoDAO();
	

	public ClienteMB() {
		clientes = new ArrayList<Cliente>();
		cliente = new Cliente();
		endereco = new Endereco();
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

	public String salvar() throws DAOException {

		RequestContext context = RequestContext.getCurrentInstance();

		if (cliente == null) {
			//enviar mensagem de alerta/erro ("Não é possivel salvar categoria nula!");
		} else if (cliente.getCodigoCliente() != null) {
			clienteDao.editar(cliente);
			//context.addCallbackParam("Categoria atualizada com sucesso", true);
			
		} else {
			clienteDao.salvar(cliente);
			//context.addCallbackParam("Categoria cadastrada com sucesso", true);
			context.execute("confirmation.show();");
		}
		
		return "/buscaCliente.faces?faces-redirect=true";
	
	}

	public String editar() throws DAOException {

		cliente = clienteDao.getPrimaryKey(cliente);

		return "/cadastraCliente.faces";
	}

/*	public String buscar() throws DAOException {

		this.clientes = dao.listar(cliente);

		return "/buscaCliente.xhtml";
	}*/

	// Carregar enumerador
	public TipoSituacaoCliente[] getTipoSituacoes() {
		return TipoSituacaoCliente.values();
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
