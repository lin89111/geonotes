package entities;

import java.io.Serializable;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Note implements EntityInterface, Serializable {

	@Id
	@GeneratedValue
	private long id;
	private double x;
	private double y;
	private String description;
	private long idCreator;
	private String creationDate;
	private static final long serialVersionUID = 3066372552313588248L;

	public Note() {
		super();
	}

	public Note(double x, double y, String description, long idCreator) {
		this();
		
		this.x = x;
		this.y = y;
		this.description = description;
		this.idCreator = idCreator;
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.creationDate = format.format(date);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
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

	@Override
	public String toString() {
		return "Note [id=" + id + ", x=" + x + ", y=" + y + ", description="
				+ description + ", idCreator=" + idCreator + ", creationDate="
				+ creationDate + "]";
	}

}