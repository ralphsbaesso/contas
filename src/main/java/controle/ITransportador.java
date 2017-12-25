package controle;

import java.util.List;

import dominio.Entidade;
import enuns.ESemafaro;

public interface ITransportador {
	
	public List<String> getMensagens();
	public void setMensagens(List<String> mensagens);
	public void setMensagens(String mensagem);
	public List<Entidade> getEntidades();
	public Entidade getEntidade();
	public void setEntidade(Entidade entidade);
	public void setEntidades(List<Entidade> entidades);
	public void setEntidades(Entidade entidade);
	public ESemafaro getSemafaro();
	public boolean setSemafaro(ESemafaro semafaro);
	public void recebeObjetoMensagem(ITransportador obj);


}
