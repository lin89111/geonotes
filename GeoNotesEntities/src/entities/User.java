package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 * 
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue
	private long id;
	private String login;
	private String password;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
