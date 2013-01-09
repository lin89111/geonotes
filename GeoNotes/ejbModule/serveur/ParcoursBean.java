package serveur;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import db.DbEtape;
import db.DbNote;
import db.DbParcours;

/**
 * Session Bean implementation class ParcoursBean
 */
@Stateful
@LocalBean
public class ParcoursBean implements ParcoursRemote, ParcoursLocal {

    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;

	@Override
	public void creer(String nom, List<Integer> idEtapes, String createur) {
		DbParcours parcours = this.ajouterParcours(nom, createur);
		this.ajouterEtapes(parcours, idEtapes);
	}
	
	private DbParcours ajouterParcours(String nom, String createur) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		DbParcours parcours = new DbParcours();
		parcours.setNom(nom);
		parcours.setCreateur(createur);
		parcours.setDateCreation(format.format(date));
		parcours.setStat(0);
		
		this.entityManager.persist(parcours);
		this.entityManager.flush();
		
		return parcours;

	}
	
	private void ajouterEtapes(DbParcours parcours, List<Integer> idEtapes) {
		DbEtape etape;
		int position = 1;
		
		for (Integer id : idEtapes) {
			etape = new DbEtape();
			etape.setIdNote(id);
			etape.setIdParcours(parcours.getId());
			etape.setPosition(position++);
			
			this.entityManager.persist(etape);
		}
	}

	@Override
	public void modifier(String nom, List<Integer> idEtapes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifier(String nom, String nouveauNom, List<Integer> idEtapes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterEtape(String nom, int idEtape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimer(String nom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ParcoursBean recuperer(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParcoursBean> recupererTous() {
		// TODO Auto-generated method stub
		return null;
	}

}
