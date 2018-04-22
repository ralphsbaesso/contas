package dominio;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class ListaTransferencia extends Entidade {
	
	private List<Transferencia> transferencias = new ArrayList();
	private BufferedReader reader;

	public List<Transferencia> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(List<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
}
