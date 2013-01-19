package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Note
 * 
 */
@Entity
@Table(name = "notes")
public class Note implements Serializable {

	@Id
	@GeneratedValue
	private long id;
	private float x;
	private float y;
	private String description;
	private long idCreator;
	private String creationDate;
	private static final long serialVersionUID = 1L;

	public Note() {
		super();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

	public long getIdCreator() {
		return this.idCreator;
	}

	public void setIdCreator(long idCreator) {
		this.idCreator = idCreator;
	}

	public String getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
