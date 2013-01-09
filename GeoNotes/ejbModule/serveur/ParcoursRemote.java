package serveur;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ParcoursRemote {
	public void creer(String nom, List<Integer> idEtapes, String createur);
	public void modifier(String nom, List<Integer> idEtapes);
	public void modifier(String nom, String nouveauNom, List<Integer> idEtapes);
	public void ajouterEtape(String nom, int idEtape);
	public void supprimer(String nom);
	public ParcoursBean recuperer(String nom);
	public List<ParcoursBean> recupererTous();
}
