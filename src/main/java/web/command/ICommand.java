package web.command;

import controle.ITransportador;
import dominio.Entidade;

public interface ICommand {
	
	public ITransportador executar(Entidade entidade);
}
