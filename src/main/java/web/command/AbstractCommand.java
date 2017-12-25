package web.command;

import controle.Fachada;
import controle.IFachada;

public abstract class AbstractCommand implements ICommand {

	protected IFachada fachada = new Fachada();

}
