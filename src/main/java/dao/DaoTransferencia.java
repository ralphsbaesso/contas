package dao;

import java.util.List;

import javax.persistence.EntityTransaction;

import conexao.ControleConexao;
import dominio.Entidade;

public class DaoTransferencia implements Idao{

	@Override
	public boolean salvar(Entidade entidade) {
		EntityTransaction transaction = ControleConexao.entityManager.getTransaction();
		return false;
	}

	@Override
	public boolean alterar(Entidade entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<?> listar(Entidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
