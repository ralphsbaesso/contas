package dominio.leitorarquivo;

import dominio.Conta;
import dominio.ListaTransferencia;

public abstract class AbstractCarregarArquivo implements ICarregadorArquivo {
	
	protected ListaTransferencia lista = new ListaTransferencia();
	protected Conta conta;
	
	public AbstractCarregarArquivo(Conta conta) {
		this.conta = conta;
	}

	

}
