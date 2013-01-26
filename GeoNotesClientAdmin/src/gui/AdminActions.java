package gui;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.session.remote.AdminRemote;
import entities.Note;
import entities.Route;
import entities.User;

public class AdminActions {

	private static AdminRemote bean;

	public static void init() {
		try {
			InitialContext ctx = new InitialContext();
			bean = (AdminRemote) ctx.lookup("AdminBean");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static void clearDatabase() {
		System.out.println("I know you don't really want to do it...");
	}

	public static void initializeDatabase() {
		String login = "tintin", password = "milou";
		User user = bean.addUser(login, password);

		if (user == null)
			System.out.println("The database already contains init values.");
		else {
			System.out.println("Adding a sample to the database...");
			Route route = bean.addRoute("Promenade a Paris");
			Note note1 = bean.addNote(48.86958, 2.30825, "Les champs Elysee");
			Note note2 = bean.addNote(48.85828, 2.29425, "La tour Eiffel");
			Note note3 = bean.addNote(48.93546, 2.35984,
					"La basilique Saint-Denis");
			System.out.println(route + "\n" + note1 + "\n" + note2 + "\n"
					+ note3);
			User user1 = bean.addUser("jeremy", "argoud");
			User user2 = bean.addUser("samuel", "collins");
			System.out.println(user1 + "\n" + user2);
		}
	}

	public static void login(String login, String password) {
		User user = bean.login(login, password);

		if (user == null)
			System.out
					.println("These login and password are not in the database.");
		else
			System.out.println("You are now logged as : " + user);
	}

	public static void addUser(String login, String password) {
		User user = bean.addUser(login, password);

		if (user == null)
			System.out
					.println("These login and password are already in the database.");
		else
			System.out.println("You are now registered as : " + user);
	}

	public static void addRoute(String name) {
		Route route = bean.addRoute(name);

		if (route != null)
			System.out.println("Route added ! " + route);
		else
			System.out.println("You must be logged to add a route.");
	}

	public static void deleteRoute(String name) {
		Route route = bean.findRoute(name);

		if (route != null) {
			bean.deleteRoute(route);
			System.out.println(route + " deleted !");
		} else
			System.out.println("Nothing has been deleted.");
	}

	public static void addNote(String description, String latitude,
			String longitude) {
		Note note = bean.addNote(Double.parseDouble(latitude),
				Double.parseDouble(longitude), description);

		if (note != null)
			System.out.println("Note added ! " + note);
	}

	public static void deleteNote(String description, String latitude,
			String longitude) {
		Note note = bean.findNote(Double.parseDouble(latitude),
				Double.parseDouble(longitude), description);

		if (note != null) {
			bean.deleteNote(note);
			System.out.println(note + " deleted !");
		} else
			System.out.println("Nothing has been deleted.");

	}
}
