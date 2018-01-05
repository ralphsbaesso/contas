package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;

import conexao.ControleConexao;
import dominio.Conta;
import dominio.Entidade;

public class DaoConta implements Idao {

	@Override
	public boolean salvar(Entidade entidade) {

		Conta conta = (Conta) entidade;

		try {
			ControleConexao.entityManager.persist(conta);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean alterar(Entidade entidade) {
		
		Conta conta = (Conta) entidade;

		try {
			ControleConexao.entityManager.merge(conta);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean excluir(Entidade entidade) {

		Conta conta = (Conta) entidade;

		try {
			conta = ControleConexao.entityManager.find(Conta.class, conta.getId());
			
			if(conta == null) {
				return false;
			}
			ControleConexao.entityManager.remove(conta);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<?> listar(Entidade entidade) {

		Conta conta = (Conta) entidade;
		List<Conta> contas = new ArrayList();
		
		if(conta.getId() > 0) {
			conta = ControleConexao.entityManager.find(Conta.class, conta);
			contas.add(conta);
			
		}else {
			
			Query query = ControleConexao.entityManager.createQuery("FROM Conta c JOIN FETCH c.correntista");
			contas = query.getResultList();
		}

		return contas;
	}

}
