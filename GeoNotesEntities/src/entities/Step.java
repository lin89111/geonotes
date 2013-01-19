package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Step
 * 
 */
@Entity
@Table(name = "steps")
public class Step implements Serializable {

	@Id
	@GeneratedValue
	private long id;
	private long idRoute;
	private long idNote;
	private static final long serialVersionUID = 1L;

	public Step() {
		super();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdRoute() {
		return this.idRoute;
	}

	public void setIdRoute(long idRoute) {
		this.idRoute = idRoute;
	}

	public long getIdNote() {
		return this.idNote;
	}

	public void setIdNote(long idNote) {
		this.idNote = idNote;
	}

}
