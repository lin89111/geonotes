package gui;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.session.remote.ClientRemote;
import entities.Note;
import entities.Route;
import entities.Step;

public class ClientActions {

	private static ClientRemote bean;
	private static List<Step> steps;
	private static int currentStep;

	public static void init() {
		try {
			InitialContext ctx = new InitialContext();
			bean = (ClientRemote) ctx.lookup("ClientBean");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Object[][] getRoutes() {
		List<Route> routes = bean.getRoutes();

		Object[][] data;

		data = new Object[routes.size()][2];

		Route route;

		for (int i = 0; i < routes.size(); i++) {
			route = routes.get(i);

			data[i][0] = route.getId();
			data[i][1] = route.getName();
		}

		return data;
	}

	public static String[] selectRoute(long idRoute) {
		Route route = bean.selectRoute(idRoute);
		steps = bean.getSteps("s.idRoute = " + route.getId());
		currentStep = 0;

		Step step = getNextStep();
		String[] data;

		if (step == null) {
			data = new String[2];

			data[0] = "" + route.getId();
			data[1] = route.getName();
		} else {
			Note note = bean.findNote(step.getIdNote());

			data = new String[6];

			data[0] = "" + route.getId();
			data[1] = route.getName();
			data[2] = "" + currentStep;
			data[3] = "" + note.getX();
			data[4] = "" + note.getY();
			data[5] = note.getDescription();
		}

		return data;
	}

	private static Step getNextStep() {
		if (steps.size() < currentStep + 1)
			return null;

		return steps.get(currentStep++);
	}

	public static String[] getNextStepData() {
		Step step = getNextStep();

		if (step == null)
			return null;

		Note note = bean.findNote(step.getIdNote());

		String data[] = { "" + currentStep, "" + note.getX(), "" + note.getY(),
				note.getDescription() };

		return data;
	}

}
