package gui;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.util.HibernateUtil;

import entitymanager.Teste;

public class teste {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	public static void main(String[] args) throws ParseException, IOException {
		
		 EventManager mgr = new EventManager();

	        if (args[0].equals("store")) {
	            mgr.createAndStoreEvent("My Event", new Date());
	        }

	        HibernateUtil.getSessionFactory().close();
	    
       

		Teste t = new Teste();
		
		t.setNome("ralph");
		

	}
	 private void createAndStoreEvent(String title, Date theDate) {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();

	        Event theEvent = new Event();
	        theEvent.setTitle(title);
	        theEvent.setDate(theDate);
	        session.save(theEvent);

	        session.getTransaction().commit();
	    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory(
			    new StandardServiceRegistryBuilder().build() );
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
