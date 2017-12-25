package negocio;

import controle.ITransportador;

public interface IStrategy {
	public boolean processar(ITransportador transportador);

}
