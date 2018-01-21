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
	
	@OneToOne
	@JoinColumn(name = "transacao_principal", referencedColumnName = "id", unique = true)
	public Transacao getTransacaoPrincipal() {
		return transacaoPrincipal;
	}
	public void setTransacaoPrincipal(Transacao transacaoPrincipal) {
		this.transacaoPrincipal = transacaoPrincipal;
	}
	
	@OneToOne
	@JoinColumn(name = "transacao_secundaria", referencedColumnName = "id", unique = true)
	public Transacao getTransacaoSecundaria() {
		return transacaoSecundaria;
	}
	public void setTransacaoSecundaria(Transacao transacaoSecundaria) {
		this.transacaoSecundaria = transacaoSecundaria;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transacaoPrincipal == null) ? 0 : transacaoPrincipal.hashCode());
		result = prime * result + ((transacaoSecundaria == null) ? 0 : transacaoSecundaria.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transferencia other = (Transferencia) obj;
		if (transacaoPrincipal == null) {
			if (other.transacaoPrincipal != null)
				return false;
		} else if (!transacaoPrincipal.equals(other.transacaoPrincipal))
			return false;
		if (transacaoSecundaria == null) {
			if (other.transacaoSecundaria != null)
				return false;
		} else if (!transacaoSecundaria.equals(other.transacaoSecundaria))
			return false;
		return true;
	}
	
}
