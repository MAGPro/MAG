package br.com.mag.business.enumeration;

public enum TipoEndereco {
	RESIDENCIAL("Residencial"),
	COMERCIAL("Comercial");
	
	private String label;
	
	private TipoEndereco(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}

}
