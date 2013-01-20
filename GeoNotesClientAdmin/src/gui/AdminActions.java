package gui;

import javax.naming.InitialContext;

import ejb.session.remote.AdminRemote;
import entities.User;

public class AdminActions {

	public static void clearDatabase() {
		System.out.println("I know you don't really want to do it...");
	}

	public static void initializeDatabase() {
		System.out.println("Adding some values in the database...");

		try {
			InitialContext ctx = new InitialContext();
			AdminRemote bean = (AdminRemote) ctx.lookup("AdminBean");
			
			
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

	public static void addRoute(String text) {
		// TODO Auto-generated method stub

	}

	public static void deleteRoute(String text) {
		// TODO Auto-generated method stub

	}

	public static void addNote(String text, String text2, String text3) {
		// TODO Auto-generated method stub

	}

	public static void deleteNote(String text) {
		// TODO Auto-generated method stub

	}

}
