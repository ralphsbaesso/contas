package dominio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transferencias")
public class Transferencia extends Entidade {
	
	private Transacao transacaoPrincipal = new Transacao();
	private Transacao transacaoSecundaria = new Transacao();
	
	@OneToOne(mappedBy = "transferencia", cascade = CascadeType.ALL)
	@JoinColumn(name = "transacao_principal")
	public Transacao getTransacaoPrincipal() {
		return transacaoPrincipal;
	}
	public void setTransacaoPrincipal(Transacao transacaoPrincipal) {
		this.transacaoPrincipal = transacaoPrincipal;
	}
	
	@OneToOne(mappedBy = "transferencia", cascade = CascadeType.ALL)
	public Transacao getTransacaoSecundaria() {
		return transacaoSecundaria;
	}
	public void setTransacaoSecundaria(Transacao transacaoSecundaria) {
		this.transacaoSecundaria = transacaoSecundaria;
	}
}
