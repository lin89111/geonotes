package db;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DbParcours
 * 
 */
@Entity
@Table(name = "parcours")
public class DbParcours implements Serializable {

	@Id
	private int id;
	private String nom;
	private String createur;
	private String date_creation;
	private int stat;
	private static final long serialVersionUID = -4563248212271172837L;

	public DbParcours() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCreateur() {
		return this.createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public String getDate_creation() {
		return this.date_creation;
	}

	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}

	public int getStat() {
		return this.stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

}
