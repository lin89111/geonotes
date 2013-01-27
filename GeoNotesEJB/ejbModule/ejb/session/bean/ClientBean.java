package ejb.session.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ejb.session.remote.ClientRemote;
import entities.EntityInterface;
import entities.Note;
import entities.Route;
import entities.Step;

@Stateless(name = "ClientEJB", mappedName = "ClientBean")
public class ClientBean implements ClientRemote {

	private static final String PERSISTENCE_UNIT_NAME = "GeoNotesPU";

	private EntityManager getEntityManager() {
		EntityManagerFactory emFactory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		return emFactory.createEntityManager();
	}

	/**
	 * Execute la requete en parametre pour recherche dans la base de donnees
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
	 * Retourne la liste des parcours dans la base de donnees
	 */
	@Override
	public List<Route> getRoutes() {
		EntityManager em = this.getEntityManager();

		String query = "SELECT r FROM Route r";
		Query q = em.createQuery(query);

		return q.getResultList();
	}

	/**
	 * Retourne la liste des etapes d'un parcours
	 */
	public List<Step> getSteps(String where) {
		EntityManager em = this.getEntityManager();

		Query q = em.createQuery("SELECT s FROM Step s WHERE " + where);

		return q.getResultList();
	}

	/**
	 * Selectionne un parcours
	 */
	@Override
	public Route selectRoute(long idRoute) {
		String query = "SELECT r FROM Route r WHERE r.id = " + idRoute;

		Route route = (Route) this.find(query);

		if (route == null)
			return null;

		return route;
	}

	/**
	 * Cherche une note dans la base de donnees et la retourne
	 */
	@Override
	public Note findNote(long id) {
		String query = "SELECT n FROM Note n WHERE n.id = " + id;

		return (Note) this.find(query);
	}

}
