package ejb.session.remote;

import javax.ejb.Remote;

@Remote
public interface AdminRemote {
	public long addUser(String login, String password);
}
