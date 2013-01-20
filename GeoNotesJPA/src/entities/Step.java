package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "steps")
public class Step implements EntityInterface, Serializable {

	@Id
	@GeneratedValue
	private long id;
	private long idRoute;
	private long idNote;
	private static final long serialVersionUID = -8349144664065240597L;

	public Step() {
		super();
	}

	public Step(long idNote, long idRoute) {
		this();

		this.idNote = idNote;
		this.idRoute = idRoute;
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

	@Override
	public String toString() {
		return "Step [id=" + id + ", idRoute=" + idRoute + ", idNote=" + idNote
				+ "]";
	}

}