package db;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DbEtape
 * 
 */
@Entity
@Table(name = "etapes")
public class DbEtape implements Serializable {

	@Id
	private int id;
	private int id_parcours;
	private int id_note;
	private int position;
	private static final long serialVersionUID = -7727732245296974946L;

	public DbEtape() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_parcours() {
		return this.id_parcours;
	}

	public void setId_parcours(int id_parcours) {
		this.id_parcours = id_parcours;
	}

	public int getId_note() {
		return this.id_note;
	}

	public void setId_note(int id_note) {
		this.id_note = id_note;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
