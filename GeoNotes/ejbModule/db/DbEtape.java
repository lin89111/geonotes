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
	@GeneratedValue
	private int id;
	@Column(name = "id_parcours")
	private int idParcours;
	@Column(name = "id_note")
	private int idNote;
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

	public int getIdParcours() {
		return this.idParcours;
	}

	public void setIdParcours(int idParcours) {
		this.idParcours = idParcours;
	}

	public int getIdNote() {
		return this.idNote;
	}

	public void setIdNote(int idNote) {
		this.idNote = idNote;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
