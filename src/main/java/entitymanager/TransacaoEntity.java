package entitymanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import dominio.Conta;
import dominio.Transacao;

@Entity
@Table(name="trasacoes")
public class TransacaoEntity extends Transacao{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	
	@Override
	public Conta getContaPrimaria() {
		// TODO Auto-generated method stub
		return super.getContaPrimaria();
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
