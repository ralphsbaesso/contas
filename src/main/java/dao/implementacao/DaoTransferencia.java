package dao.implementacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import conexao.ControleConexao;
import dao.Idao;
import dominio.Conta;
import dominio.Entidade;
import dominio.Transferencia;

public class DaoTransferencia implements Idao {

	@Override
	public boolean salvar(Entidade entidade) {

		Transferencia transferencia = (Transferencia) entidade;

		try {
			ControleConexao.entityManager.persist(transferencia);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean alterar(Entidade entidade) {
		
		Transferencia transferencia = (Transferencia) entidade;

		try {
			ControleConexao.entityManager.merge(transferencia);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean excluir(Entidade entidade) {

		Transferencia transferencia = (Transferencia) entidade;

		try {
			transferencia = ControleConexao.entityManager.find(Transferencia.class, transferencia.getId());
			
			if(transferencia == null) {
				return false;
			}
			ControleConexao.entityManager.remove(transferencia);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List listar(Entidade entidade) {

		Transferencia transferencia = (Transferencia) entidade;
		Conta conta = transferencia.getTransacaoPrincipal().getConta();
		
		List<Transferencia> transferencias = new ArrayList();
		
		if(transferencia.getId() > 0) {
			transferencia = ControleConexao.entityManager.find(Transferencia.class, transferencia.getId());
			transferencias.add(transferencia);
			
		}else if(conta != null){
			
			TypedQuery<Transferencia> query = ControleConexao.entityManager
												.createQuery
												("SELECT t FROM Transferencia t JOIN t.transacaoPrincipal tp JOIN tp.conta conta where conta.id = :id",
												Transferencia.class);
			
			transferencias = query.setParameter("id", conta.getId()).getResultList();
		}

		return transferencias;
	}

}
