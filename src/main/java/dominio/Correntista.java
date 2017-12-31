package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "correntistas")
public class Correntista extends Pessoa {
	
	private List<Conta> contas = new ArrayList();
	private List<Item> itens = new ArrayList();
	
	
	@OneToMany(mappedBy = "correntista", cascade = CascadeType.ALL)
	public List<Conta> getContas() {
		return contas;
	}
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	@OneToMany(mappedBy = "correntista")
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

}
