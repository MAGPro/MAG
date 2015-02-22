package br.com.mag.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.text.html.ListView;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import br.com.mag.business.Cliente;
import br.com.mag.business.ItemVenda;
import br.com.mag.business.Produto;
import br.com.mag.business.Venda;
import br.com.mag.business.dao.ClienteDAO;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.dao.ProdutoDAO;
import br.com.mag.business.dao.VendaDAO;
import br.com.mag.business.enumeration.TipoFormaPagamento;

@ManagedBean
@SessionScoped
public class VendaMB {

	private ItemVenda item = new ItemVenda();
	private Venda venda = new Venda();
	private double valorTotalVenda;
	private double valorTotalCobrar;
	private Integer idProduto;
	private Integer idCliente;
	private List<Venda> vendas;
	private double qtdEstoqueAtualizado;
	private Produto produto;
	private VendaDAO vendaDAO = new VendaDAO();

	
	
	/*
	 public List<Produto> completeProduto(String descricao) {
	        return  produtoDAO.buscaPelaDescricao(descricao); 
	    }
	   */
	public double getTotalVenda() {
		valorTotalVenda += item.getTotal();
		venda.setValorTotal(valorTotalVenda);
		return valorTotalVenda;
	}

	public double getTotalCobrar() {
		valorTotalCobrar = valorTotalVenda - venda.getDesconto();
		System.out.println(valorTotalVenda + "-" + venda.getDesconto()
				+ "valor a cobrar " + valorTotalCobrar);
		venda.setValorTotalCobrar(valorTotalCobrar);
		return valorTotalCobrar;
	}

	public void atualizaEstoque(Produto produto) {
		qtdEstoqueAtualizado = item.getProduto().getQtdEstoque()
				- item.getQuantidade();
		System.out.println(qtdEstoqueAtualizado);
		produto.setQtdEstoque(qtdEstoqueAtualizado);
	}

	public String salvar() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			if (venda == null) {

			} else if (venda.getCodigoVenda() == null) {
				ProdutoDAO produtoDAO = new ProdutoDAO();
				vendaDAO.salvar(venda);

				for (ItemVenda item : venda.getItens()) {
					produtoDAO.atualizaEstoque(item.getProduto());
					System.out.println(item.getProduto().getDescricao());
				}
				venda = null;
				context.execute("confirmation.show();");
				
			} else {
				if (vendaDAO.getPrimaryKey(venda) == null) {
					throw new Exception(
							"Erro ao atualizar, registro não encontrado");
				}

				vendaDAO.editar(venda);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/buscaVenda.faces";
	}

	public String voltar() {

		return "/buscaVenda.faces";
	}



	public void adicionarItem() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();

		// Carregando dados do produto
		produto = produtoDAO.getPrimaryKey(idProduto);
		// carregando dados do cliente
		Cliente cliente = clienteDAO.getPrimaryKey(idCliente);

		item.setProduto(produto);
		item.setValorVenda(produto.getValorVenda());

		// bidirecional
		item.setVenda(venda);
		venda.adicionarItem(item);

		atualizaEstoque(produto);
		venda.setCliente(cliente);
		venda.setValorTotal(getTotalVenda());

		item = new ItemVenda();
	}
	
	public String editar() throws DAOException {

		venda = vendaDAO.getPrimaryKey(venda);
		return "/cadastraVenda.faces";
	}

	public String cadastrar() {

		return "/cadastraVenda.faces?faces-redirect=true";
	}

	public String visualizar() throws DAOException {

		venda = vendaDAO.getPrimaryKey(venda);

		return "/visualizaVenda.faces";
	}
	public void setItem(ItemVenda item) {
		this.item = item;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public List<Venda> getVendas() {
		if (vendas == null) {
			vendas = new ArrayList<Venda>();
		}
		if (vendas.isEmpty()) {
			try {
				VendaDAO vendaDAO = new VendaDAO();
				List<Venda> vendaList = vendaDAO.listarTodos();
				for (Venda venda : vendaList) {
					vendas.add(venda);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vendas;
	}
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public ItemVenda getItem() {
		return item;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Venda getVenda() {
		return venda;
	}

	public TipoFormaPagamento[] getTipos() {
		return TipoFormaPagamento.values();
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public double getQtdEstoqueAtualizado() {
		return qtdEstoqueAtualizado;
	}

	public void setQtdEstoqueAtualizado(double qtdEstoqueAtualizado) {
		this.qtdEstoqueAtualizado = qtdEstoqueAtualizado;
	}

	

}
