package enuns;

public enum ELeitorArquivo {
	
	TWO_COLUMN_VALUE("[data, descricao, docto, , + credito, - debito, saldo]"), ONE_COLUMN_VALUE("[data, , descricao, docto, credito/debito, saldo]"), CCREDITO("-");
	
	ELeitorArquivo(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

}
