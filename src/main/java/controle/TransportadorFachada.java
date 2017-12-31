package controle;

import javax.persistence.EntityManager;

public class TransportadorFachada extends ATransportador {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
