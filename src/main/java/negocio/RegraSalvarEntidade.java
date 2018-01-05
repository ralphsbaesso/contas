package negocio;

import controle.ITransportador;
import controle.TransportadorFachada;
import dao.Idao;
import dominio.Entidade;
import enuns.ESemafaro;

public class RegraSalvarEntidade implements IStrategy {

	@Override
	public boolean processar(ITransportador transportador) {
		
		
		if(transportador.getSemafaro().getValor() > ESemafaro.VERDE.getValor()) {
			
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		
		TransportadorFachada transportadorFachada = (TransportadorFachada) transportador;
		Entidade entidade = (Entidade) transportadorFachada.getEntidade();
		Idao dao = transportadorFachada.getDao();
		if(!dao.salvar(entidade)) {
			transportadorFachada.setMensagens("Erro no salvar");
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		
		return true;
	}
}
