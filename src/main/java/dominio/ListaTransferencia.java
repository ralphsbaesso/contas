package dominio;

import java.util.ArrayList;
import java.util.List;

public class ListaTransferencia extends Entidade {
	
	private List<Transferencia> transferencias = new ArrayList();

	public List<Transferencia> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(List<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}
}
