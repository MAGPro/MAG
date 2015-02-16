package br.com.mag.business.enumeration;

public enum TipoEndereco {
	RESIDENCIAL("Residencial"),
	COMERCIAL("Comercial"),
	ENTREGAMERCADORIA("Entrega de Produtos"),
	PAGAMENTO("Pagamento");
	
	private String label;
	
	private TipoEndereco(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}

}
