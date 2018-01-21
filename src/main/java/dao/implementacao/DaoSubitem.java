package dao.implementacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import conexao.ControleConexao;
import dao.Idao;
import dominio.Entidade;
import dominio.Subitem;

public class DaoSubitem implements Idao{

	@Override
	public boolean salvar(Entidade entidade) {

		Subitem subitem = (Subitem) entidade;

		try {
			ControleConexao.entityManager.persist(subitem);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean alterar(Entidade entidade) {

		Subitem subitem = (Subitem) entidade;

		try {
			ControleConexao.entityManager.merge(subitem);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean excluir(Entidade entidade) {

		Subitem subitem = (Subitem) entidade;

		try {
			subitem = ControleConexao.entityManager.find(Subitem.class, subitem.getId());

			if (subitem == null) {
				return false;
			}
			ControleConexao.entityManager.remove(subitem);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List listar(Entidade entidade) {

		Subitem subitem = (Subitem) entidade;
		List<Subitem> itens = new ArrayList();

		if (subitem.getId() > 0) {
			subitem = ControleConexao.entityManager.find(Subitem.class, subitem);
			itens.add(subitem);

		} else {

			Query query = ControleConexao.entityManager.createQuery("FROM Subitem");
			itens = query.getResultList();
		}

		return itens;
	}

}
