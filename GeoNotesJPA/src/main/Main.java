package main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.User;

public class Main {

	private static final String PERSISTENCE_UNIT_NAME = "GeoNotesPU";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		// Read the existing entries and write to console
		Query q = em.createQuery("select t from User t");
		List<User> userList = q.getResultList();
		for (User user : userList) {
			System.out.println(user);
		}
		System.out.println("Size: " + userList.size());

		// Create new user
		em.getTransaction().begin();
		User user = new User();
		user.setLogin("jeremy");
		user.setPassword("argoud");
		em.persist(user);
		em.getTransaction().commit();

		System.out.println(user);
		
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		
		em.close();
		factory.close();
	}
}
