package conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ControleConexao {
	
	public ControleConexao() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("contas");
	}
	
	private static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;
	
	public static EntityTransaction getTransaction() {
		entityManager  = entityManagerFactory.createEntityManager();
		return entityManager.getTransaction();
	}
	
	public static void finishTransaction() {
		entityManager.close();
	}

}
