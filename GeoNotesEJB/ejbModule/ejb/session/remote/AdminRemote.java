package ejb.session.remote;

import javax.ejb.Remote;

import entities.Note;
import entities.Route;
import entities.User;

@Remote
public interface AdminRemote {

	public User addUser(String login, String password);

	public User login(String login, String password);

	public Route addRoute(String name);

	public void deleteRoute(Route route);

	public Route findRoute(String name);

	public Note addNote(double x, double y, String description);

	public Note findNote(double x, double y, String description);

	public void deleteNote(Note note);

	public void clearDatabase();
}
