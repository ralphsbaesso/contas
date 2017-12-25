package web.command;

import controle.ITransportador;
import dominio.Entidade;

public class CommandAlterar extends AbstractCommand{

	@Override
	public ITransportador executar(Entidade entidade) {
		// TODO Auto-generated method stub
		return fachada.alterar(entidade);
	}

}
