package negocio;

import controle.ITransportador;
import controle.TransportadorFachada;
import dao.Idao;
import dominio.Entidade;
import enuns.ESemafaro;

public class RegraExcluirEntidade implements IStrategy {

	@Override
	public boolean processar(ITransportador transportador) {
		
		if(transportador.getSemafaro().getValor() > ESemafaro.VERDE.getValor()) {
			
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		
		Entidade entidade = (Entidade) transportador.getEntidade();
		TransportadorFachada transportadorFachada = (TransportadorFachada) transportador;
		
		if(entidade.getId() == 0) {
			transportador.setMensagens("Erro em excluir. Não foi encontrado o id!");
			
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		
		Idao dao = transportadorFachada.getDao();
		if(!dao.excluir(entidade)) {
			transportador.setMensagens("Ocorreu um erro em excluir!");
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		
		return true;
	}

}
