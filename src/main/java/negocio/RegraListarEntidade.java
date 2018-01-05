package negocio;

import java.util.List;

import controle.ITransportador;
import controle.TransportadorFachada;
import dao.Idao;
import dominio.Entidade;
import enuns.ESemafaro;

public class RegraListarEntidade implements IStrategy {

	@SuppressWarnings("unchecked")
	@Override
	public boolean processar(ITransportador transportador) {
		
		Entidade entidade = (Entidade) transportador.getEntidade();
		TransportadorFachada transportadorFachada = (TransportadorFachada) transportador;
		Idao dao = transportadorFachada.getDao();
		
		if(transportador.getSemafaro().getValor() > ESemafaro.VERDE.getValor()) {
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		
		List entidades = dao.listar(entidade);
		
		try {
			transportador.setEntidades(entidades);
			
		}catch(Exception e) {	
			e.printStackTrace();
			transportador.setMensagens("Erro em listar entidades!");
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		
		if(entidades.size() == 0) {
			transportador.setMensagens("A lista está vázia");
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		return true;
		
	}

}
