package br.com.mag.mb;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import br.com.mag.business.Cliente;
import br.com.mag.business.ContasReceber;
import br.com.mag.business.ItemVenda;
import br.com.mag.business.Produto;
import br.com.mag.business.Venda;
import br.com.mag.business.dao.ClienteDAO;
import br.com.mag.business.dao.ContasReceberDAO;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.dao.ProdutoDAO;
import br.com.mag.business.dao.VendaDAO;
import br.com.mag.business.enumeration.TipoFormaPagamento;
import br.com.mag.business.enumeration.TipoSituacaoContasReceber;

@ManagedBean
@SessionScoped
public class VendaMB {

	private ItemVenda item = new ItemVenda();
	private ContasReceber contaReceber = new ContasReceber();
	private VendaDAO vendaDAO = new VendaDAO();
	private ContasReceberDAO contasReceberDAO =  new ContasReceberDAO();
	private Venda venda = new Venda();
	private Produto produto;
	private List<Venda> vendas;
	private double valorTotal;
	private Integer qtdEstoqueAtualizado;
	private Integer idProduto;
	private Integer idCliente;
	private Integer qtdParcelas;
	private double valorParcela;
 	private Calendar vencimento; 
    private int diaVencimento;
	private ItemVenda  itemSelecionado; 
	private List<Cliente> clientes =  new ArrayList<Cliente>();
	//private double totalAtual;
	
	
	/*terminar de implementar 
	 public List<Produto> completeProduto(String descricao) {
	        return  produtoDAO.buscaPelaDescricao(descricao); 
	    }
	   */
	   
		public void calculaTotal() {
			this.valorTotal=0;
			
			if(!venda.getItens().isEmpty()){
				for(ItemVenda item: venda.getItens()){
					valorTotal += item.getTotal();
					
				}if(venda.getDesconto() >0.0){
					this.valorTotal = valorTotal - venda.getDesconto();
				}
			}
		}
		
	public void atualizaEstoque(Produto produto) {
		qtdEstoqueAtualizado = item.getProduto().getQtdEstoque() - item.getQuantidade();
		produto.setQtdEstoque(qtdEstoqueAtualizado);
	}
	
	public double calculaValorParcela(Integer qtdParcela){
		return valorTotal/qtdParcela;
		
	}

	public String voltar() {
		return "/buscaVenda.faces";
	}

	public void adicionarItem() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		produto = produtoDAO.getPrimaryKey(idProduto);
		Cliente cliente = clienteDAO.getPrimaryKey(idCliente);

		item.setProduto(produto);
		item.setValorVenda(produto.getValorVenda());
		venda.adicionarItem(item);
		venda.setCliente(cliente);
		calculaTotal();
		item = new ItemVenda();
	}
	public void adicionaContaReceberAprazo(){
		 contaReceber.setSituacaoContasReceber(TipoSituacaoContasReceber.ABERTO);
		 contaReceber.setDataPrevista(vencimento);
         contaReceber.setValorParcela(valorParcela);
         contaReceber.setVenda(venda);
         contaReceber = new ContasReceber();
         
	}
	public void adicionaContaReceberAvista(){
		contaReceber.setDataPagamento(venda.getDataVenda());
		contaReceber.setDataPrevista(venda.getDataVenda());
		contaReceber.setSituacaoContasReceber(TipoSituacaoContasReceber.QUITADO);
		contaReceber.setValorPago(valorTotal);
		contaReceber.setValorParcela(valorTotal);
        contaReceber.setVenda(venda);
        venda.adicionarContaReceber(contaReceber);
        contaReceber = new ContasReceber();
        
	}
	
	public void verificaFormaPagamento(){
		calculaTotal();
		qtdParcelas = venda.getNumeroParcelas();
		System.out.println(qtdParcelas);
	
		if (venda.getFormaPagamento().equals(TipoFormaPagamento.AVISTA)) {
			adicionaContaReceberAvista();
			
			
		} else if (venda.getFormaPagamento().equals(TipoFormaPagamento.APRAZO)) {
			valorParcela = calculaValorParcela(qtdParcelas);
			vencimento = contaReceber.getDataPagamento();
			geraParcelaPorDiaVencimento();
		}
	}
	
	 public  void geraParcelaPorDiaVencimento(){   
	        
	        System.out.println( "Entrada: "+vencimento.getTime() );
	        int diaVencimento = vencimento.get(Calendar.DAY_OF_MONTH);
	          for(int i = 1; i < qtdParcelas; i++) {
	        	  
	              vencimento.roll( Calendar.MONTH, true );    
	              vencimento.set( Calendar.DAY_OF_MONTH, diaVencimento );
	             
	              if(vencimento.get( Calendar.MONTH) == 0 ) {    
	                vencimento.roll( Calendar.YEAR, true );   
	              }
	              System.out.println(" Parcela " + (i) + ": " + vencimento.getTime() );
	              contaReceber.setDataPrevista(vencimento);
	              adicionaContaReceberAprazo();
	              
	          }   
	    }  
	 
	 public String salvar() {
			
			RequestContext context = RequestContext.getCurrentInstance();
			try {
				if (venda == null) {

				} else if (venda.getCodigoVenda() == null) {
					venda.setValorTotal(valorTotal);
					ProdutoDAO produtoDAO = new ProdutoDAO();
					venda.setValorTotalCobrar(valorTotal);
					venda.setValorTotal(valorTotal);
					vendaDAO.salvar(venda);
					contasReceberDAO.salvar(contaReceber);
					
					
					for (ItemVenda item : venda.getItens()) {
						atualizaEstoque(item.getProduto());
						produtoDAO.atualizaEstoque(item.getProduto());
						System.out.println(item.getProduto().getDescricao());
					}
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
			venda = new Venda();
			return "/buscaVenda.faces";
		}

	public String editar() throws DAOException {

		venda = vendaDAO.getPrimaryKey(venda);
		return "/cadastraVenda.faces";
	}

	public String cadastrar() {
		venda = new Venda();
		return "/cadastraVenda.faces?faces-redirect=true";
	}

	public String visualizar() throws DAOException {
		venda = vendaDAO.getPrimaryKey(venda);
		return "/visualizaVenda.faces";
				}
		public String deleteItem() {
			if(this.venda.getItens() != null && !this.venda.getItens().isEmpty()){
				if(this.itemSelecionado!= null){
					this.venda.getItens().remove(this.itemSelecionado);
					calculaTotal();
				}
			}
			return null;
		}
		
     void setItem(ItemVenda item) {
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

	public Integer getQtdEstoqueAtualizado() {
		return qtdEstoqueAtualizado;
	}

	public void setQtdEstoqueAtualizado(Integer qtdEstoqueAtualizado) {
		this.qtdEstoqueAtualizado = qtdEstoqueAtualizado;
	}

	public ContasReceber getContaReceber() {
		return contaReceber;
	}

	public void setContaReceber(ContasReceber contaReceber) {
		this.contaReceber = contaReceber;
	}

	public Integer getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public int getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(int diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public ItemVenda getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(ItemVenda itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Calendar getVencimento() {
		return vencimento;
	}

	public void setVencimento(Calendar vencimento) {
		this.vencimento = vencimento;
	}

	public List<Cliente> getClientes() {
		ClienteDAO clienteDao =  new ClienteDAO();
		
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
	
}








