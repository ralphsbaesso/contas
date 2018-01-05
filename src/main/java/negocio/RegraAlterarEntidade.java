package negocio;

import controle.ITransportador;
import controle.TransportadorFachada;
import dao.Idao;
import dominio.Entidade;
import enuns.ESemafaro;

public class RegraAlterarEntidade implements IStrategy {

	@Override
	public boolean processar(ITransportador transportador) {
		
		Entidade entidade = (Entidade) transportador.getEntidade();
		TransportadorFachada transportadorFachada = (TransportadorFachada) transportador;
		
		if(transportador.getSemafaro().getValor() > ESemafaro.VERDE.getValor()) {
			
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		
		Idao dao = transportadorFachada.getDao();
		if(!dao.alterar(entidade)) {
			transportador.setMensagens("Ocorreu um erro em alterar!");
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		
		return true;
	}

}
