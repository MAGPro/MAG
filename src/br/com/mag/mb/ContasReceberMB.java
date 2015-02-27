package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;


import javax.faces.bean.SessionScoped;

import org.primefaces.event.RowEditEvent;

import br.com.mag.business.ContasReceber;
import br.com.mag.business.dao.ContasReceberDAO;
import br.com.mag.business.dao.DAOException;
import br.com.mag.business.enumeration.TipoSituacaoContasReceber;


@SessionScoped
@ManagedBean
public class ContasReceberMB implements Serializable{

	private static final long serialVersionUID = 901114338254703798L;
	private ContasReceberDAO contasReceberDAO = new ContasReceberDAO();
	private ContasReceber contasReceber;
	private List<ContasReceber> listContasReceber;
	private int auxiliar = 0;
	
	public ContasReceberMB() {
        contasReceber = new ContasReceber();
        listContasReceber = new ArrayList<ContasReceber>();	 
    }
		
	public ContasReceber getContasReceber() {
		return contasReceber;
	}

	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}

	public List<ContasReceber> getListContasReceber() {		
		
		if (listContasReceber.isEmpty()) {
			try {
				List<ContasReceber> contasList = contasReceberDAO.listarTodos();
				//listContasReceber = contasReceberDAO.listarTodos();
				for (ContasReceber contas : contasList) {
					listContasReceber.add(contas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listContasReceber;
	}
	
	public TipoSituacaoContasReceber[] getTipoSituacaoContasReceber() {
		return TipoSituacaoContasReceber.values();
	}
	
	public String salvar(RowEditEvent ev) throws DAOException {
		
//		contasReceber = new ContasReceber();    
//	    contasReceber = (ContasReceber) ev.getObject();  
//	    ContasReceber contas = contasReceberDAO.getPrimaryKey(contasReceber.getId());
//	    contas.setDataPagamento(contasReceber.getDataPagamento());
//	    contas.setValorPago(contasReceber.getValorPago());
//	    System.out.println("Codigo " + contasReceber.getId()); 
//	    System.out.println("Pago: "+contasReceber.getValorPago());
//	    System.out.println("Valor a pagar"+contasReceber.getValorParcela());
//	    
//	    contasReceberDAO.editar(contasReceber);
		System.out.println("Entrouu");
		
		
//		if (contasReceber == null) {
//			//**enviar mensagem de alerta/erro ("Não é possivel salvar categoria nula!");
//		} else{ 
//			
//			if (contasReceber.getValorPago()==0){
//				contasReceber.setSituacaoContasReceber(TipoSituacaoContasReceber.ABERTO);
//			}else{
//				contasReceber.setSituacaoContasReceber(TipoSituacaoContasReceber.QUITADO);
//				
//				if (contasReceber.getValorParcela() > contasReceber.getValorPago()){
//					List<ContasReceber> listaVendas = new ArrayList<ContasReceber>(); 
//					listaVendas = contasReceberDAO.ContasVenda(contasReceber.getVenda().getCodigoVenda());
//					
//					for (ContasReceber lista : listaVendas) {
//						if(lista.getCodigoContasReceber()==contasReceber.getCodigoContasReceber()){
//							auxiliar = 1;
//						}
//						if ((auxiliar) ==1 && (lista.getCodigoContasReceber()!=contasReceber.getCodigoContasReceber())){	
//							lista.setValorParcela(lista.getValorParcela()+(contasReceber.getValorParcela()-contasReceber.getValorPago()));
//							contasReceberDAO.editar(lista);
//							break;
//						}
//					}
//				 }
//			}
//			
//			
//		}
//		contasReceberDAO.editar(contasReceber);
		return "/contasReceber.faces?faces-redirect=true";
	}
	
}
