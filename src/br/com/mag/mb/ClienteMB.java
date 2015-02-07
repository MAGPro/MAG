package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.mag.business.Cliente;
import br.com.mag.business.dao.ClienteDAO;
import br.com.mag.business.dao.DAOExcpetion;

@ManagedBean
public class ClienteMB implements Serializable {

	private Cliente cliente;
	private ClienteDAO dao = new ClienteDAO();
	private List<Cliente> clientes;

	public ClienteMB() {
		clientes = new ArrayList<Cliente>();
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		if (clientes.isEmpty()) {
			try {
				List<Cliente> clienteList = dao.listarTodos();
				for (Cliente cliente : clienteList) {
					clientes.add(cliente);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return clientes;
	}

	public String salvar() throws DAOExcpetion {
		dao.salvar(cliente);
		return "Salvou";
	}

}
