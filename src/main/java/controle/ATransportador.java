package controle;

import java.util.ArrayList;
import java.util.List;

import dominio.Entidade;
import enuns.ESemafaro;

public abstract class ATransportador implements ITransportador{
	
	protected Entidade entidade = new Entidade();
	protected List<String> mensagens = new ArrayList();
	protected List<Entidade> entidades = new ArrayList();
	protected ESemafaro semafaro = ESemafaro.VERDE;

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		if(mensagens == null)
			return;
		this.mensagens = mensagens;
	}
	public void setMensagens(String mensagem){
		if(mensagem == null || mensagem == "")
			return;
		this.mensagens.add(mensagem);
	}

	public List<Entidade> getEntidades() {
		return entidades;
	}
	
	public Entidade getEntidade() {
		return entidade;
	}
	
	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public void setEntidades(List<Entidade> entidades) {
		this.entidades = entidades;
	}

	public void setEntidades(Entidade entidade) {

		this.entidades.add(entidade);
	}

	public ESemafaro getSemafaro() {
		return semafaro;
	}

	public boolean setSemafaro(ESemafaro semafaro) {
		this.semafaro = semafaro;
		
		return semafaro.getValor() > ESemafaro.AMARELO.getValor()? false : true;
	}
	
	public void recebeObjetoMensagem(ITransportador obj){
		this.entidade = obj.getEntidade();
		this.entidades = obj.getEntidades();
		this.mensagens = obj.getMensagens();
		this.semafaro = obj.getSemafaro();
	}

}
