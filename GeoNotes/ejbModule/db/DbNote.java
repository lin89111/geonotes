package db;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DbNote
 * 
 */
@Entity
@Table(name = "notes")
public class DbNote implements Serializable {

	@Id
	private int id;
	private float x;
	private float y;
	private String description;
	private String commentaire;
	private String createur;
	@Column(name = "date_creation")
	private String dateCreation;
	private int stat;
	private static final long serialVersionUID = -4816613731579229595L;

	public DbNote() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getX() {
		return this.x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getCreateur() {
		return this.createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getStat() {
		return this.stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

}
