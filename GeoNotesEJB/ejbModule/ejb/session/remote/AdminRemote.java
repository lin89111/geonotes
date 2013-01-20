package ejb.session.remote;

import javax.ejb.Remote;

import entities.Note;
import entities.Route;
import entities.Step;
import entities.User;

@Remote
public interface AdminRemote {

	public User addUser(String login, String password);

	public User login(String login, String password);

	public Route addRoute(String name);

	// public boolean deleteRoute();

	public Note addNote(double x, double y, String description);
	
	// public boolean deleteNote();

	public Step addNoteToRoute(long idNote, long idRoute);
	
	// public boolean removeNoteForNote();
}
