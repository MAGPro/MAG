package br.com.mag.business.enumeration;

public enum TipoSituacaoCliente {
	ADIPLENTE("Adiplente"),
	INADIPLENTE("Inadiplente");
	private String label;
	
	private TipoSituacaoCliente(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}

}
