package dao.implementacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import conexao.ControleConexao;
import dao.Idao;
import dominio.Conta;
import dominio.Entidade;
import dominio.Transacao;
import dominio.Transferencia;

public class DaoTransacao implements Idao {

	@Override
	public boolean salvar(Entidade entidade) {

		Transacao transacao = (Transacao) entidade;

		try {
			ControleConexao.entityManager.persist(transacao);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean alterar(Entidade entidade) {
		
		Transacao transacao = (Transacao) entidade;

		try {
			ControleConexao.entityManager.merge(transacao);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean excluir(Entidade entidade) {

		Transacao transacao = (Transacao) entidade;

		try {
			transacao = ControleConexao.entityManager.find(Transacao.class, transacao.getId());
			
			if(transacao == null) {
				return false;
			}
			ControleConexao.entityManager.remove(transacao);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List listar(Entidade entidade) {

		Transacao transacao = (Transacao) entidade;
		Conta conta = transacao.getConta();
		
		List<Transacao> transacoes = new ArrayList();
		
		if(transacao.getId() > 0) {
			transacao = ControleConexao.entityManager.find(Transacao.class, transacao.getId());
			transacoes.add(transacao);
			
		}else if(conta != null){
			
			// Conta Primaria
			TypedQuery<Transacao> query = ControleConexao.entityManager
												.createQuery
												("SELECT t FROM Transacao t LEFT JOIN FETCH t.transferencia JOIN t.conta conta where conta.id = :id ORDER BY t.dataTransacao, t.dataCadastro",
												Transacao.class);
			
			transacoes.addAll(query.setParameter("id", conta.getId()).getResultList());
			
			for(Transacao trans : transacoes) {
				trans.getTransferencia().getTransacoes().size();
			}
		}

		return transacoes;
	}

}
