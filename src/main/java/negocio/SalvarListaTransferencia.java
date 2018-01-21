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

			Transacao tp = transferencia.getTransacaoPrincipal();
			Transacao ts = transferencia.getTransacaoSecundaria();

			try {

				transferencia.setTransacaoPrincipal(ControleConexao.entityManager.merge(tp));
				
				if(ts != null) {
					transferencia.setTransacaoSecundaria(ControleConexao.entityManager.merge(ts));
				}

				ControleConexao.entityManager.persist(transferencia);

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
