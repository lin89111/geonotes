package ejb.session.bean;

import ejb.session.remote.AdminRemote;
import entities.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless(name="AdminEJB", mappedName="AdminBean")
@LocalBean
public class AdminBean implements AdminRemote {

	private static final String PERSISTENCE_UNIT_NAME = "GeoNotesPU";
	private EntityManagerFactory emFactory;

	@Override
	public long addUser(String login, String password) {
		emFactory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emFactory.createEntityManager();

		em.getTransaction().begin();
		User user = new User(login, password);
		em.persist(user);
		em.getTransaction().commit();

		return user.getId();
	}

}
