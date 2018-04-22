package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controle.ITransportador;
import dao.Idao;
import dao.implementacao.DaoTransacao;
import dominio.ListaTransferencia;
import dominio.Transacao;
import dominio.Transferencia;
import enuns.ESemafaro;

public class VerificarTransacoesJaPersistidas implements IStrategy {

	@Override
	public boolean processar(ITransportador transportador) {

		if (transportador.getEntidade() instanceof ListaTransferencia) {

			ListaTransferencia listaTransferencia = (ListaTransferencia) transportador.getEntidade();
			
//			//verifica se existe arquivo anexo
//			if(listaTransferencia.getCaminhoArquivo() == null || listaTransferencia.getCaminhoArquivo().isEmpty()) {
//				//vazio, para somente este processo.
//				return true;
//			}

			Calendar maiorData = Calendar.getInstance();
			Calendar menorData = Calendar.getInstance();

			maiorData.set(1990, 0, 0);
			menorData.set(3000, 0, 0);

			// pegar a maior e menor data da lista
			for (Transferencia transferencia : listaTransferencia.getTransferencias()) {

				Calendar dataAtual = transferencia.getTransacoes().get(0).getDataTransacao();

				if (dataAtual.getTimeInMillis() > maiorData.getTimeInMillis()) {
					maiorData = (Calendar) dataAtual.clone();
				}

				if (dataAtual.getTimeInMillis() < menorData.getTimeInMillis()) {
					menorData = (Calendar) dataAtual.clone();
				}

			}

			Transacao transacao = new Transacao();

			transacao.getFiltros().put("maiorData", maiorData);
			transacao.getFiltros().put("menorData", menorData);

			Idao dao = new DaoTransacao();

			List<Transacao> transDB = dao.listar(transacao);
			
			List<Transferencia> removesT = new ArrayList();

			for (Transferencia transferencia : listaTransferencia.getTransferencias()) {

				Transacao t = transferencia.getTransacoes().get(0);

				for (Transacao tDB : transDB) {
					
					//verifica o docto(número do doc ou ted)
					if(t.getDocto().equals(tDB.getDocto())) {
						removesT.add(transferencia);
						continue;
					}

					// verificar data
					if (t.getDataTransacao().equals(tDB.getDataTransacao())) {

						// verificar valor
						if(Double.compare(t.getValor(), tDB.getValor()) == 0) {
								
							//verificar descrição
							if(t.getDescricao().trim().equals(tDB.getDescricao().trim())) {
								
								// transação repetida, adicionar a lista de remoção.
								removesT.add(transferencia);
							}
						}
					}
				}
			}
			
			listaTransferencia.getTransferencias().removeAll(removesT);
			
			if(listaTransferencia.getTransferencias().isEmpty()) {
				transportador.setMensagens("Transações repetidas, não seão salvas");
				return transportador.setSemafaro(ESemafaro.VERMELHO);
			}
		}

		return true;
	}

}
