package br.com.mag.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;



import org.primefaces.event.RowEditEvent;

import br.com.mag.business.ContasReceber;
import br.com.mag.business.dao.ContasReceberDAO;
import br.com.mag.business.enumeration.TipoSituacaoContasReceber;


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
	
	public void cancelar(RowEditEvent ev){
         //return "/contasReceber.faces?faces-redirect=true";
	}
	
	public void salvar(RowEditEvent ev){
		
		contasReceber = new ContasReceber();    
	    contasReceber = (ContasReceber) ev.getObject();  

		if (contasReceber == null) {
//			//**enviar mensagem de alerta/erro ("Não é possivel salvar categoria nula!");
		} else{ 
			
			if (contasReceber.getValorPago()==0){
				contasReceber.setSituacaoContasReceber(TipoSituacaoContasReceber.ABERTO);
			}else{
				contasReceber.setSituacaoContasReceber(TipoSituacaoContasReceber.QUITADO);
				
				if (contasReceber.getValorParcela() > contasReceber.getValorPago()){
					List<ContasReceber> listaVendas = new ArrayList<ContasReceber>(); 
					listaVendas = contasReceberDAO.ContasVenda(contasReceber.getVenda().getCodigoVenda());
					
					for (ContasReceber lista : listaVendas) {
						if(lista.getCodigoContasReceber()==contasReceber.getCodigoContasReceber()){
							auxiliar = 1;
						}
						if (lista.getCodigoContasReceber() < contasReceber.getCodigoContasReceber()){
							auxiliar = 2;
						}else{
							if ((auxiliar) ==1 && (lista.getCodigoContasReceber()!=contasReceber.getCodigoContasReceber())){	
								lista.setValorParcela(lista.getValorParcela()+(contasReceber.getValorParcela()-contasReceber.getValorPago()));
								contasReceberDAO.editar(lista);
								break;
							}
						}
						if (auxiliar==2){
							ContasReceber contas = new ContasReceber();
				            
							Calendar cal = Calendar.getInstance();
							cal.setTime(contasReceber.getDataPrevista().getTime());
							cal.add(Calendar.MONTH, 1);
							
				            contas.setDataPrevista(cal);
				            contas.setDataPagamento(null);
				            contas.setValorPago(null);
							contas.setValorParcela(contasReceber.getValorParcela()-contasReceber.getValorPago());
							contas.setSituacaoContasReceber(TipoSituacaoContasReceber.ABERTO);
							contas.setVenda(contasReceber.getVenda());
							contasReceberDAO.salvar(contas);
							break;
							
						}
						
					}
					
					
				 }
			}
				
		}
		contasReceberDAO.editar(contasReceber);
		//contasReceber = null;
		this.listContasReceber =  contasReceberDAO.listarTodos();
		//return "/contasReceber.faces?faces-redirect=true";
	}
	
}
