package dominio;

import java.util.ArrayList;
import java.util.List;
import dominio.*;

public class Correntista extends Pessoa {
	
	private List<Conta> contas = new ArrayList();
	private List<Item> itens = new ArrayList();
	
	public List<Conta> getContas() {
		return contas;
	}
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

}
