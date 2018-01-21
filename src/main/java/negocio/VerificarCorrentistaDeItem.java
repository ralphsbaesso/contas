package negocio;

import controle.ITransportador;
import dominio.Entidade;
import dominio.Item;
import enuns.ESemafaro;

public class VerificarCorrentistaDeItem implements IStrategy {

	@Override
	public boolean processar(ITransportador transportador) {
		
		// método provisório para correntista padrão.
		if(transportador.getEntidade() instanceof Item) {
			Item item = (Item) transportador.getEntidade();
			if(item.getCorrentista().getId() < 1) {
				item.getCorrentista().setId(1);
			}
		}
		return true;
	}
}
