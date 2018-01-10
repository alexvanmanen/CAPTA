package nl.alexvanmanen.capta.dynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criterion;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.model.Evaluations;

public class DecoratorTemplate extends nl.alexvanmanen.capta.DecoratorTemplate {

	public DecoratorTemplate(AssignmentOutput assignmentOutput) {
		super(assignmentOutput);
	}

	public Evaluations evaluate() {


//		Object o = execute("Italian", "getPrice");
//		System.out.println(o);

		// Object o = m.invoke(t, new Locale(args[1], args[2], args[3]));

		// Method getPrice = new MethodsRetriever().getMethod(c, "getPrice");

		// MethodExecutor methodExecutor = new MethodExecutor(c, getPrice);
		//
		//
		// String consoleOutput = new
		// TestHelper().getConsoleOutput(methodExecutor);
		// System.out.println(consoleOutput);

		// for (Class c : assignmentOutput.getClassFiles()) {
		// if (c.getName().contains(className)) {
		// list.add(criterion1);
		// Method main = new MethodsRetriever().getMethod(c, methodName);
		// if (main != null) {
		// list.add(criterion2);
		//
		// MethodExecutor methodExecutor = new MethodExecutor(c, main);
		// String consoleOutput = new
		// TestHelper().getConsoleOutput(methodExecutor);
		//
		// if (consoleOutput.contains(printed)) {
		// list.add(criterion3);
		// }
		// }
		// }
		// }
		
		String feedback = "";



		String className = "Start";
		String printed1 = "8.75";
		String printed2 = "9.3";
		String methodName = "main";
		
		Criterion criterion2 = new Criterion();
		criterion2.description = "+5 First pizza price is printed\n";
		criterion2.points = 5;

		Criterion criterion3 = new Criterion();
		criterion3.description = "+6 Second pizza price is printed\n";
		criterion3.points = 6;

		List<Criterion> list = new ArrayList<Criterion>();


		Evaluations evaluations = new Evaluations();
		evaluations.add(new Evaluation(criterion2, false));
		evaluations.add(new Evaluation(criterion3, false));
		

		
		for (Class c : 	assignmentOutput.getClassFiles()) {
			
			if (c.getName().contains(className)) {
				Method main = new MethodsRetriever().getMethod(c, methodName);
				if (main != null) {
					//list.add(criterion2);

					MethodExecutor methodExecutor = new MethodExecutor(c, main);
					String consoleOutput = new TestHelper().getConsoleOutput(methodExecutor);
					if (consoleOutput.contains(printed2)) {
						list.add(criterion2);
					}	
					
					if (consoleOutput.contains(printed1)) {
						list.add(criterion3);
					}
				}
			}
		}

		evaluations.checkIfCriteriaAreMet(list);

		return evaluations;
	}

	private Object execute(String className, String methodName) {
		Class c = getClass(className);
		Object t = null;
		Object invoke = null;
		try {
			t = c.newInstance();
			Method[] allMethods = c.getMethods();
			
			for (Method m : allMethods) {
				if (m.getName().equalsIgnoreCase(methodName)) {
					invoke = m.invoke(t, null);
				}
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return invoke;
	}

	private Class getClass(String name) {
		for (Class c : assignmentOutput.getClassFiles()) {
			if (c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}

}
