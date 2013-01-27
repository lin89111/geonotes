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

/**
 * Bean des actions accessibles pour un client de type Admin
 */
@Stateful(name = "AdminEJB", mappedName = "AdminBean")
public class AdminBean implements AdminRemote {

	private static final String PERSISTENCE_UNIT_NAME = "GeoNotesPU";

	private User user;
	private Route route;

	/**
	 * Recupere un entity manager
	 * 
	 * @return
	 */
	private EntityManager getEntityManager() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		return emFactory.createEntityManager();
	}

	/**
	 * Ajoute un objet dans la base de donnees. Tous les beans Entity
	 * implementent la classe EntityInterface.
	 * 
	 * @param object
	 * @return
	 */
	private EntityInterface addObject(EntityInterface object) {
		EntityManager em = this.getEntityManager();

		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();

		return object;
	}

	/**
	 * Trouve un objet dans la base de donnees. Tous les beans Entity
	 * implementent la classe EntityInterface.
	 * 
	 * @param query
	 * @return
	 */
	private EntityInterface find(String query) {
		EntityManager em = this.getEntityManager();

		Query q = em.createQuery(query);

		if (q.getResultList().size() > 0)
			return (EntityInterface) q.getResultList().get(0);

		return null;
	}

	/**
	 * Supprime un objet de la base de donnees. Tous les beans Entity
	 * implementent la classe EntityInterface.
	 * 
	 * @param object
	 */
	private void deleteObject(EntityInterface object) {
		EntityManager em = this.getEntityManager();

		em.getTransaction().begin();
		em.remove(em.merge(object));
		em.getTransaction().commit();
	}

	/**
	 * Ajoute un nouvel utilisateur dans la base de donnees
	 */
	@Override
	public User addUser(String login, String password) {
		User user = new User(login, password);
		String query = "SELECT u FROM User u WHERE u.login = '" + login + "'";

		if (this.find(query) == null)
			return this.user = (User) this.addObject(user);

		return null;
	}

	/**
	 * Identifie un utilisateur
	 */
	@Override
	public User login(String login, String password) {
		String query = "SELECT u FROM User u WHERE u.login = '" + login + "'";

		this.user = (User) this.find(query);

		return this.user;
	}

	/**
	 * Ajoute un parcours dans la base de donnees
	 */
	@Override
	public Route addRoute(String name) {
		if (this.user != null)
			this.route = (Route) this.addObject(new Route(name, this.user
					.getId()));

		return this.route;
	}

	/**
	 * Ajoute une note dans la base de donnees
	 */
	@Override
	public Note addNote(double x, double y, String description) {
		Note note = null;

		if (this.user != null)
			note = (Note) this.addObject(new Note(x, y, description, this.user
					.getId()));

		if (note != null && this.route != null)
			this.addNoteToRoute(note.getId(), this.route.getId());

		return note;
	}

	/**
	 * Ajoute une etape, ie. une correspondance dans la base de donnees entre un
	 * parcours et une note
	 * 
	 * @param idNote
	 * @param idRoute
	 */
	private void addNoteToRoute(long idNote, long idRoute) {
		Step step = (Step) this.addObject(new Step(idNote, idRoute));
	}

	/**
	 * Supprime de la base de donnees le parcours passe en parametre
	 */
	@Override
	public void deleteRoute(Route route) {
		if (route == null)
			return;

		if (this.route != null && this.route.getId() == route.getId())
			this.route = null;

		this.deleteSteps("s.idRoute = " + route.getId());
		this.deleteObject(route);
	}

	/**
	 * Retourne le parcours de la base de donnees ayant pour valeurs les
	 * parametres indiques
	 */
	@Override
	public Route findRoute(String name) {
		String query = "SELECT r FROM Route r WHERE r.name = '" + name + "'";

		return (Route) this.find(query);
	}

	/**
	 * Retourne la note de la base de donnees ayant pour valeurs les parametres
	 * indiques
	 */
	@Override
	public Note findNote(double x, double y, String description) {
		String query = "SELECT n FROM Note n WHERE n.x = " + x + " AND n.y = "
				+ y + " AND n.description = '" + description + "'";

		return (Note) this.find(query);
	}

	/**
	 * Supprime la note passee en parametre
	 */
	@Override
	public void deleteNote(Note note) {
		if (note == null)
			return;

		this.deleteSteps("s.idNote = " + note.getId());
		this.deleteObject(note);
	}

	/**
	 * Retourne la liste de toutes les etapes de la base de donnees
	 * correspondant a la clause where passee en parametre
	 * 
	 * @param where
	 * @return
	 */
	private List<Step> getSteps(String where) {
		EntityManager em = this.getEntityManager();

		Query q = em.createQuery("SELECT s FROM Step s WHERE " + where);

		return q.getResultList();
	}

	/**
	 * Supprime de la base de donnees l'etape correspondant a la clause where
	 * indiquee en parametre
	 * 
	 * @param where
	 */
	private void deleteSteps(String where) {
		List<Step> steps = this.getSteps(where);

		for (int i = 0; i < steps.size(); i++) {
			this.deleteObject(steps.get(i));
		}

	}

	/**
	 * Vide la base de donnees
	 */
	@Override
	public void clearDatabase() {
		EntityManager em = this.getEntityManager();
		em.getTransaction().begin();
		
		Query q = em.createQuery("SELECT r FROM Route r");
		List<Route> routes = q.getResultList();
		
		for (Route route : routes)
			this.deleteRoute(route);
		
		q = em.createQuery("SELECT n FROM Note n");
		List<Note> notes = q.getResultList();
		
		for (Note note : notes)
			this.deleteNote(note);
		
		q = em.createQuery("SELECT u FROM User u");
		List<User> users = q.getResultList();
		
		for (User user : users)
			this.deleteObject(user);
		
		em.getTransaction().commit();
	}
}
