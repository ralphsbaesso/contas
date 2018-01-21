package negocio;

import java.util.List;

import controle.ITransportador;
import dao.Idao;
import dao.implementacao.DaoConta;
import dominio.Conta;
import dominio.ListaTransferencia;
import dominio.Transacao;
import dominio.Transferencia;

public class VerificarContaSegundariaDaListaTransferencia implements IStrategy {

	@Override
	public boolean processar(ITransportador transportador) {
		
		if(transportador.getEntidade() instanceof ListaTransferencia) {
			
			ListaTransferencia listaTransferencia =  (ListaTransferencia) transportador.getEntidade();
			Idao dao = new DaoConta();
			
			for(Transferencia transferencia : listaTransferencia.getTransferencias()) {
				
				Conta contaSegundaria = transferencia.getTransacaoSecundaria().getConta();
				
				if(contaSegundaria.getId() == Integer.MIN_VALUE) {
					transferencia.setTransacaoSecundaria(null);
					continue;
				}
				
				List<Conta> contas = dao.listar(contaSegundaria);
				
				if(!contas.isEmpty()) {
					
					contaSegundaria = contas.get(0);
					
					Transacao tp = transferencia.getTransacaoPrincipal();
					Transacao ts = transferencia.getTransacaoSecundaria();
					
					ts.setDataTransacao(tp.getDataTransacao());
					ts.setDescricao(tp.getDescricao());
					ts.setDetalhamento(tp.getDetalhamento());
					ts.setTitulo(tp.getTitulo());
					ts.setValor(tp.getValor());
					ts.setSubitem(tp.getSubitem());
				}else {
					transferencia.setTransacaoSecundaria(null);
				}
			}
		}
		
		return true;
	}

}
