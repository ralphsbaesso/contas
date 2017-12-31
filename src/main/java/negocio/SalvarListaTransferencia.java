package negocio;

import conexao.ControleConexao;
import controle.ITransportador;
import dominio.Conta;
import dominio.ListaTransferencia;
import dominio.Transferencia;

public class SalvarListaTransferencia implements IStrategy {

	@Override
	public boolean processar(ITransportador transportador) {
		
		ListaTransferencia listaTransferencia =  (ListaTransferencia) transportador.getEntidade();
		
		
		for(Transferencia trans : listaTransferencia.getTransferencias()) {
			
			// salva conta principal
			Conta contaP = trans.getTransacaoPrincipal().getConta();
			
			ControleConexao.entityManager.persist(contaP);
			
			// salvar conta secundária
			Conta contaS = trans.getTransacaoSecundaria().getConta();
			
			ControleConexao.entityManager.persist(contaS);
			
			// salvar transferencia
			ControleConexao.entityManager.persist(trans);
		}
		
		ControleConexao.entityManager.getTransaction().commit();
		
		return false;
	}

}
