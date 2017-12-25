package web.command;

import controle.ITransportador;
import dominio.Entidade;

public class CommandSalvar extends AbstractCommand{

	@Override
	public ITransportador executar(Entidade entidade) {
		// TODO Auto-generated method stub
		return fachada.salvar(entidade);
	}

}
