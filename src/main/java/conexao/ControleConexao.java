package conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ControleConexao {
	
	public ControleConexao() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("contas");
		this.entityManager  = entityManagerFactory.createEntityManager();
	}
	
	private static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;

}
