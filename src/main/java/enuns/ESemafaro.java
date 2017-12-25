package enuns;

public enum ESemafaro {
	
	VERDE(1), AMARELO(2), VERMELHO(3), STOP(5);
	
	private int valor;
	
	ESemafaro(int valor){
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

}
