package pesistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import dominio.Conta;

@Entity
@Table(name="trasacoes")
public class TransacaoP extends dominio.Transacao{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	
	

	@Column(name="descricao")
	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return super.getDescricao();
	}
	
	@Column(name="valor")
	@Override
	public Double getValor() {
		// TODO Auto-generated method stub
		return super.getValor();
	}
}
