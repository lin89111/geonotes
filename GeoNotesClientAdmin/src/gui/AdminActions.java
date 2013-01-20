package gui;

import javax.naming.InitialContext;

import ejb.session.remote.AdminRemote;
import entities.Note;
import entities.Route;
import entities.User;

public class AdminActions {

	public static void clearDatabase() {
		System.out.println("I know you don't really want to do it...");
	}

	public static void initializeDatabase() {
		try {
			InitialContext ctx = new InitialContext();
			AdminRemote bean = (AdminRemote) ctx.lookup("AdminBean");

			String login = "tintin", password = "milou";
			User user = bean.addUser(login, password);

			if (user == null)
				System.out
						.println("The database already contains init values.");
			else {
				System.out.println("Adding a sample to the database...");
				Route route = bean.addRoute("Promenade a Paris");
				Note note1 = bean.addNote(48.86958, 2.30825,
						"Les champs Elysee");
				Note note2 = bean.addNote(48.85828, 2.29425, "La tour Eiffel");
				Note note3 = bean.addNote(48.93546, 2.35984,
						"La basilique Saint-Denis");
				System.out.println(route + "\n" + note1 + "\n" + note2 + "\n"
						+ note3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void login(String login, String password) {
		try {
			InitialContext ctx = new InitialContext();
			AdminRemote bean = (AdminRemote) ctx.lookup("AdminBean");
			User user = bean.login(login, password);

			if (user == null)
				System.out
						.println("These login and password are not in the database.");
			else
				System.out.println("You are now logged as : " + user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addUser(String login, String password) {
		try {
			InitialContext ctx = new InitialContext();
			AdminRemote bean = (AdminRemote) ctx.lookup("AdminBean");
			User user = bean.addUser(login, password);

			if (user == null)
				System.out
						.println("These login and password are already in the database.");
			else
				System.out.println("You are now registered as : " + user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addRoute(String name) {
		try {
			InitialContext ctx = new InitialContext();
			AdminRemote bean = (AdminRemote) ctx.lookup("AdminBean");
			Route route = bean.addRoute(name);

			if (route != null)
				System.out.println("Route added ! " + route);
			else
				System.out.println("You must be logged to add a route.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteRoute(String name) {
		try {
			InitialContext ctx = new InitialContext();
			AdminRemote bean = (AdminRemote) ctx.lookup("AdminBean");
			Route route = bean.findRoute(name);
			bean.deleteRoute(route);
			System.out.println(route + " deleted !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addNote(String description, String latitude,
			String longitude) {
		try {
			InitialContext ctx = new InitialContext();
			AdminRemote bean = (AdminRemote) ctx.lookup("AdminBean");
			Note note = bean.addNote(Double.parseDouble(latitude),
					Double.parseDouble(longitude), description);

			if (note != null)
				System.out.println("Note added ! " + note);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteNote(String description, String latitude,
			String longitude) {
		try {
			InitialContext ctx = new InitialContext();
			AdminRemote bean = (AdminRemote) ctx.lookup("AdminBean");
			Note note = bean.findNote(Double.parseDouble(latitude),
					Double.parseDouble(longitude), description);
			bean.deleteNote(note);
			System.out.println(note + " deleted !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
