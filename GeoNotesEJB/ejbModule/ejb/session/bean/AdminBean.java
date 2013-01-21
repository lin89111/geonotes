package ejb.session.bean;

import java.util.List;

import ejb.session.remote.AdminRemote;
import entities.EntityInterface;
import entities.Note;
import entities.Route;
import entities.Step;
import entities.User;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Stateful(name = "AdminEJB", mappedName = "AdminBean")
public class AdminBean implements AdminRemote {

	private static final String PERSISTENCE_UNIT_NAME = "GeoNotesPU";

	private long userId;
	private Route route;

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

	private void deleteObject(EntityInterface object) {
		EntityManager em = this.getEntityManager();

		em.getTransaction().begin();
		em.remove(object);
		em.getTransaction().commit();
	}

	@Override
	public User addUser(String login, String password) {
		User user = new User(login, password);
		String query = "SELECT u FROM User u WHERE u.login = '" + login + "'";

		if (this.find(query) == null) {
			user = (User) this.addObject(user);
			this.userId = user.getId();
			return user;
		}

		return null;
	}

	@Override
	public User login(String login, String password) {
		String query = "SELECT u FROM User u WHERE u.login = '" + login + "'";

		User user = (User) this.find(query);
		this.userId = user.getId();
		System.out.println(userId);

		return user;
	}

	@Override
	public Route addRoute(String name) {
		System.out.println(this.userId);

		if (this.userId != 0)
			this.route = (Route) this.addObject(new Route(name, this.userId));

		return this.route;
	}

	@Override
	public Note addNote(double x, double y, String description) {
		Note note = null;

		if (this.userId != 0)
			note = (Note) this.addObject(new Note(x, y, description,
					this.userId));

		if (note != null && this.route != null)
			this.addNoteToRoute(note.getId(), this.route.getId());

		return note;
	}

	private void addNoteToRoute(long idNote, long idRoute) {
		this.addObject(new Step(idNote, idRoute));
	}

	@Override
	public void deleteRoute(Route route) {
		if (this.route.getId() == route.getId())
			this.route = null;

		this.deleteSteps("idRoute = " + route.getId());
		this.deleteObject(route);
	}

	@Override
	public Route findRoute(String name) {
		String query = "SELECT r FROM Route r WHERE r.name = '" + name + "'";

		return (Route) this.find(query);
	}

	@Override
	public Note findNote(double x, double y, String description) {
		String query = "SELECT n FROM Note n WHERE n.x = " + x + " AND n.y = "
				+ y + " AND description = '" + description + "'";

		return (Note) this.find(query);
	}

	@Override
	public void deleteNote(Note note) {
		this.deleteSteps("idNote = " + note.getId());
		this.deleteObject(note);
	}

	private List<Step> getSteps(String where) {
		EntityManager em = this.getEntityManager();
		String query = "SELECT s FROM Step s WHERE " + where;

		Query q = em.createQuery(query);

		return q.getResultList();
	}

	private void deleteSteps(String where) {
		List<Step> steps = this.getSteps(where);

		for (int i = 0; i < steps.size(); i++) {
			this.deleteObject(steps.get(i));
		}

	}
}
