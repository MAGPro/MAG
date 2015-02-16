package 
br.com.mag.mb;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.mag.business.Cliente;
import br.com.mag.business.ItemVenda;
import br.com.mag.business.Produto;
import br.com.mag.business.Venda;
import br.com.mag.business.dao.ClienteDAO;
import br.com.mag.business.dao.ItemVendaDAO;
import br.com.mag.business.dao.ProdutoDAO;
import br.com.mag.business.dao.VendaDAO;
import br.com.mag.business.enumeration.TipoFormaPagamento;

@ManagedBean
@ViewScoped
public class VendaMB{
	
	private ItemVenda item = new ItemVenda();
	private Venda venda = new Venda();
	private Integer idProduto;
	private double valorTotalVenda;
	private double valorTotalCobrar;
	private Integer idCliente;
	private List<Venda> vendas;
	private double qtdEstoqueAtualizado;
	private Produto produto;
	
	public double getTotalVenda(){
			valorTotalVenda+=item.getTotal()-venda.getDesconto();;
		return valorTotalVenda;	
	}
	public double getTotalCobrar(){
			valorTotalCobrar = valorTotalVenda-venda.getDesconto();
		return valorTotalCobrar;
	}
	
	public void adicionar() {
		VendaDAO vendaDAO = new VendaDAO();
		ItemVendaDAO itemDAO = new ItemVendaDAO();	
		vendaDAO.salvar(venda);
		atualizaEstoque(produto);
		
		for (ItemVenda item : venda.getItens()) {
			itemDAO.salvar(item);
		}

		this.venda = new Venda();
	}
	public void atualizaEstoque(Produto produto){
		qtdEstoqueAtualizado = produto.getQtdEstoque()-item.getQuantidade();
		System.out.println(produto.getQtdEstoque());
		
	}
	public void adicionarItem() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		
		//Carregando dados do produto
		produto = produtoDAO.getPrimaryKey(idProduto);
		//carregando dados do cliente
		Cliente cliente = clienteDAO.getPrimaryKey(idCliente);
		
		item.setProduto(produto);
		item.setValorVenda(produto.getValorVenda());
		
		//Mapeamento bidirecional
		item.setVenda(venda);
		venda.adicionarItem(item);
		
		
		venda.setCliente(cliente);
		venda.setValorTotal(getTotalVenda());
		//venda.setValorTotalCobrar(getValorTotalCobrar());

		
		item = new ItemVenda();
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
	
	public TipoFormaPagamento[] getTipos(){
		return TipoFormaPagamento.values();
	}
	
	public Integer getIdCliente(){
		return idCliente;
	}
	
	public void setIdCliente(Integer idCliente){
		this.idCliente = idCliente;
	}
	public List<Venda> getVendas() {
		if (vendas == null) {
			vendas = new ArrayList<Venda>();
		}

		if (vendas.isEmpty()) {
			try {
				VendaDAO vendaDAO =  new VendaDAO();
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
	
}
