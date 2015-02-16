package br.com.mag.business.enumeration;

public enum TipoFormaPagamento {
	AVISTA("A vista"),
	APRAZO("A prazo");
	private String label;

	private TipoFormaPagamento(String label) {
		this.label = label;
	}


	public String getLabel() {
		return label;
	}	
}
