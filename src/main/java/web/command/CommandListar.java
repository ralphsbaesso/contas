package web.command;

import controle.ITransportador;
import dominio.Entidade;

public class CommandListar extends AbstractCommand{

	@Override
	public ITransportador executar(Entidade entidade) {
		
		return fachada.listar(entidade);
	}

}
