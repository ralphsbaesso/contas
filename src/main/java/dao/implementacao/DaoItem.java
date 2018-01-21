package dao.implementacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import conexao.ControleConexao;
import dao.Idao;
import dominio.Entidade;
import dominio.Item;

public class DaoItem implements Idao {

	@Override
	public boolean salvar(Entidade entidade) {

		Item item = (Item) entidade;

		try {
			ControleConexao.entityManager.persist(item);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean alterar(Entidade entidade) {

		Item item = (Item) entidade;

		try {
			ControleConexao.entityManager.merge(item);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean excluir(Entidade entidade) {

		Item item = (Item) entidade;

		try {
			item = ControleConexao.entityManager.find(Item.class, item.getId());

			if (item == null) {
				return false;
			}
			ControleConexao.entityManager.remove(item);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List listar(Entidade entidade) {

		Item item = (Item) entidade;
		List<Item> itens = new ArrayList();

		if (item.getId() > 0) {
			item = ControleConexao.entityManager.find(Item.class, item);
			itens.add(item);

		} else {

			Query query = ControleConexao.entityManager.createQuery("FROM Item");
			itens = query.getResultList();
		}

		return itens;
	}

}
