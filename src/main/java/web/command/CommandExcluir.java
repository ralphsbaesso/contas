package web.command;

import controle.ITransportador;
import dominio.Entidade;

public class CommandExcluir extends AbstractCommand{

	@Override
	public ITransportador executar(Entidade entidade) {
		// TODO Auto-generated method stub
		return fachada.excluir(entidade);
	}

}
