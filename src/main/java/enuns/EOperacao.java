package enuns;

public enum EOperacao {
	
	SALVAR("salvar"), ALTERAR("alterar"), LISTAR("listar"), EXCLUIR("excluir");
	
	EOperacao(String valor) {
		this.valor = valor;
	}

	private String valor;

	public String getValor() {
		return valor;
	}
	
	
}
