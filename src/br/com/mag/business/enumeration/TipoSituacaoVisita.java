package br.com.mag.business.enumeration;

public enum TipoSituacaoVisita {

	REALIZADA("Realizada"),
	CANCELADA("Cancelada"),
	REMARCADA("Remarcada");
	private String label;
	
	private TipoSituacaoVisita(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}

}
