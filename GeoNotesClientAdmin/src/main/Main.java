package main;

import javax.naming.InitialContext;

import ejb.session.remote.AdminRemote;

public class Main {

	public static void main(String[] args) {
		try {
			InitialContext ctx = new InitialContext();
			AdminRemote bean = (AdminRemote) ctx.lookup("AdminBean");
			System.out.println(bean.addUser("jeremy", "argoud"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
