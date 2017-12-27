package gui;

import java.io.IOException;
import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pesistente.TransacaoP;

public class teste {
	//private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("contas");

	public static void main(String[] args) throws ParseException, IOException {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("contas");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		TransacaoP t = new TransacaoP();
		
		t.setDescricao("ralph");
		
		entityManager.persist(t);
		
		
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
