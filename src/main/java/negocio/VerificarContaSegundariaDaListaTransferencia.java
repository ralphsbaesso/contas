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
				
				Conta contaSecundaria;
				
				if(transferencia.getTransacoes().size() > 1 && transferencia.getTransacoes().get(1) != null) {
					
					contaSecundaria = transferencia.getTransacoes().get(1).getConta();
				}else {
					continue;
				}
				
				if(contaSecundaria.getId() == Integer.MIN_VALUE || contaSecundaria.getId() == 0) {
					transferencia.getTransacoes().remove(1);
					continue;
				}
				
				List<Conta> contas = dao.listar(contaSecundaria);
				
				if(!contas.isEmpty()) {
					
					contaSecundaria = contas.get(0);
					
					Transacao tp = transferencia.getTransacoes().get(0);
					Transacao ts = transferencia.getTransacoes().get(1);
					
					ts.setDataTransacao(tp.getDataTransacao());
					ts.setDescricao(tp.getDescricao());
					ts.setDetalhamento(tp.getDetalhamento());
					ts.setTitulo(tp.getTitulo());
					ts.setValor(tp.getValor() * -1);
					ts.setSubitem(tp.getSubitem());
					ts.setQtdeItem(tp.getQtdeItem());
				}
			}
		}
		
		return true;
	}

}
