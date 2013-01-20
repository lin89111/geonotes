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
@Table(name = "routes")
public class Route implements EntityInterface, Serializable {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private long idCreator;
	private String creationDate;
	private static final long serialVersionUID = 5214740137874150185L;

	public Route() {
		super();
	}

	public Route(String name, long idCreator) {
		this();

		this.name = name;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Route [id=" + id + ", name=" + name + ", idCreator="
				+ idCreator + ", creationDate=" + creationDate + "]";
	}

}