package controle;

import dominio.Entidade;

public interface IFachada {

	public ITransportador salvar(Entidade entidade);
	public ITransportador alterar(Entidade entidade);
	public ITransportador excluir(Entidade entidade);
	public ITransportador listar(Entidade entidade);
}
