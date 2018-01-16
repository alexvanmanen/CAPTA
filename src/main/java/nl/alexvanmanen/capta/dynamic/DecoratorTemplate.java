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
		
		String feedback = "";

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
			if (c.getName().contains(classToExecuteName)) {
				Method main = new MethodsRetriever().getMethod(c, methodToExecute);
				if (main != null) {
					MethodExecutor methodExecutor = new MethodExecutor(c, main);
					String consoleOutput = new TestHelper().getConsoleOutput(methodExecutor);
					
					if (consoleOutput.contains(outputList.get(0))) {
						list.add(criterion2);
					}	
					
					if (consoleOutput.contains(outputList.get(1))) {
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
