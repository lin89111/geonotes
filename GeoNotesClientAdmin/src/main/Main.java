package main;

import javax.naming.InitialContext;

import ejb.session.remote.AdminRemote;
import entities.Note;
import entities.Route;
import entities.User;

public class Main {

	public static void main(String[] args) {
		try {
			InitialContext ctx = new InitialContext();
			AdminRemote bean = (AdminRemote) ctx.lookup("AdminBean");
			
			String login = "jeremy", password = "argoud";
			User user = bean.login(login, password);
			
			if (user == null) {
				System.out.println("These login and password are not in the database.");
				user = bean.addUser(login, password);
			}
			
			System.out.println("You are now logged as : "+user);
			
			Route route = bean.addRoute("Test route");
			System.out.println(route);
			
			Note note1 = bean.addNote(42.1234, 4.7456, "Basilique Saint-Denis");
			System.out.println(note1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
