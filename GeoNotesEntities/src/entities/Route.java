package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Route
 * 
 */
@Entity
@Table(name = "routes")
public class Route implements Serializable {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private long idCreator;
	private String creationDate;
	private static final long serialVersionUID = 1L;

	public Route() {
		super();
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

}
