package negocio;

import controle.ITransportador;
import dominio.ListaTransferencia;
import dominio.Transacao;
import enuns.ESemafaro;

public class VerificarCamposTransacoes implements IStrategy {

	@Override
	public boolean processar(ITransportador transportador) {
		
		if(transportador.getEntidade() instanceof ListaTransferencia) {
			
			ListaTransferencia listaTransferencia =  (ListaTransferencia) transportador.getEntidade();	
			
			for (int i = 0; i < listaTransferencia.getTransferencias().size(); i++) {
				
				String mensagem = "";
				
				Transacao transacao = listaTransferencia.getTransferencias().get(i).getTransacaoPrincipal();
				
				int contaSegundariaId = listaTransferencia.getTransferencias().get(i).getTransacaoSecundaria().getConta().getId();
				
				if(transacao.getDataTransacao() == null) {
					mensagem += "Data no formato errado; ";
				}
				
				if(transacao.getValor() == Double.MIN_VALUE) {
					mensagem += "Valor no formato errado; ";
				}
				
				if(transacao.getConta().getId() == Integer.MIN_VALUE) {
					mensagem += "Não foi selecionado a Conta; "; 
				}
				
				if(transacao.getSubitem().getId() == Integer.MIN_VALUE) {
					mensagem += "Não foi selecionado o item ou/e subitem; "; 
				}
				
				// se conta principal igual a conta segundário
				if(transacao.getConta().getId() == contaSegundariaId) {
					mensagem += "Não é possivel fazer tranferencia para a mesma conta; ";
				}
				
				if(transacao.getQtdeItem() == Integer.MIN_VALUE) {
					transacao.setQtdeItem(0);
				}else if(transacao.getQtdeItem() < 0) {
					mensagem += "Não pode colocar valor negativo na quantidade de item!;";
				}
			
				if(mensagem != "") {
					mensagem = "Erro na transação da linha " + (i + 1) + ": " + mensagem;
					transportador.setMensagens(mensagem);
					transportador.setSemafaro(ESemafaro.AMARELO);
				}
			
			}
		}
		
		return true;
	}

}
