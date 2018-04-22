package negocio;

import java.util.ArrayList;
import java.util.List;

import conexao.ControleConexao;
import controle.ITransportador;
import dominio.ListaTransferencia;
import dominio.Transacao;
import dominio.Transferencia;
import enuns.ESemafaro;

public class SalvarListaTransferencia implements IStrategy {

	@Override
	public boolean processar(ITransportador transportador) {

		ListaTransferencia listaTransferencia = (ListaTransferencia) transportador.getEntidade();
		List<String> listaErros = new ArrayList();

		if (transportador.getSemafaro().getValor() > ESemafaro.VERDE.getValor()) {

			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}

		for (int i = 0; i < listaTransferencia.getTransferencias().size(); i++) {

			Transferencia transferencia = listaTransferencia.getTransferencias().get(i);
			
			Transacao tp = transferencia.getTransacoes().get(0);
			Transacao ts = null;
			
			if(transferencia.getTransacoes().size() > 1 && transferencia.getTransacoes().get(1) != null) {
				
				ts = transferencia.getTransacoes().get(1);
			}
			
			if(transferencia.getId() > 0) {
				transferencia = ControleConexao.entityManager.merge(transferencia);
			}else {
				ControleConexao.entityManager.persist(transferencia);				
			}
				try {
					tp.setTransferencia(transferencia);
					tp = ControleConexao.entityManager.merge(tp);
					transferencia.getTransacoes().set(0, tp);
					
					if(ts != null) {
						ts.setTransferencia(transferencia);
						ts = ControleConexao.entityManager.merge(ts);
						transferencia.getTransacoes().set(1, ts);
						
					}
					
				} catch (Exception e) {
					
					listaErros.add("Erro na linha " + (i + 1));
					e.printStackTrace();
				}

		}

		if (listaErros.isEmpty()) {
			return true;
		} else {
			transportador.setMensagens(listaErros);
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
	}

}
