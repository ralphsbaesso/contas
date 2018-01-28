package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "transferencias")
public class Transferencia extends Entidade {
	
	private List<Transacao> transacoes = new ArrayList<>();

	@OneToMany(mappedBy = "transferencia", cascade = CascadeType.ALL)
	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transcoes) {
		this.transacoes = transcoes;
	}

	
}
