package ejb.session.remote;

import java.util.List;

import javax.ejb.Remote;

import entities.Note;
import entities.Route;
import entities.Step;

@Remote
public interface ClientRemote {

	public List<Route> getRoutes();

	public Route selectRoute(long idRoute);

	public List<Step> getSteps(String where);

	public Note findNote(long id);

}
