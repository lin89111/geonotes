package ejb.session.bean;

import ejb.session.remote.AdminRemote;
import entities.EntityInterface;
import entities.Note;
import entities.Route;
import entities.Step;
import entities.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Stateful(name = "AdminEJB", mappedName = "AdminBean")
@LocalBean
public class AdminBean implements AdminRemote {

	private static final String PERSISTENCE_UNIT_NAME = "GeoNotesPU";

	private User user;

	private EntityManager getEntityManager() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		return emFactory.createEntityManager();
	}

	private EntityInterface addObject(EntityInterface object) {
		EntityManager em = this.getEntityManager();

		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();

		return object;
	}

	private EntityInterface find(String query) {
		EntityManager em = this.getEntityManager();

		Query q = em.createQuery(query);

		if (q.getResultList().size() > 0)
			return (EntityInterface) q.getResultList().get(0);

		return null;
	}

	@Override
	public User addUser(String login, String password) {
		User user = new User(login, password);
		String query = "SELECT u FROM User u WHERE u.login = '" + login + "'";

		if (this.find(query) == null) {
			this.user = (User) this.addObject(user);
			return this.user;
		}
		
		return null;
	}

	@Override
	public User login(String login, String password) {
		String query = "SELECT u FROM User u WHERE u.login = '" + login + "'";
		User user = (User) this.find(query);

		if (user != null)
			return this.user = user;

		return null;
	}

	@Override
	public Route addRoute(String name) {
		Route route = new Route(name, this.user.getId());

		return (Route) this.addObject(route);
	}

	@Override
	public Note addNote(double x, double y, String description) {
		Note note = new Note(x, y, description, this.user.getId());

		return (Note) this.addObject(note);
	}

	@Override
	public Step addNoteToRoute(long idNote, long idRoute) {
		Step step = new Step(idNote, idRoute);

		return (Step) this.addObject(step);
	}

}
