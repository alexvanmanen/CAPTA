package nl.alexvanmanen.capta.dynamic;

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

		Criterion criterion1 = new Criterion();
		criterion1.description = "+1 There is a class Hello\n";
		criterion1.points = 1;

		Criterion criterion2 = new Criterion();
		criterion2.description = "+1 There is a method main\n";
		criterion2.points = 1;
		
		Criterion criterion3 = new Criterion();
		criterion3.description = "+3 Hello is being printed\n";
		criterion3.points = 3;
		
		List<Criterion> list = new ArrayList<Criterion>();

		
//		for (Class c : 	assignmentOutput.getClassFiles()) {
//			if (c.getName().contains(className)) {
//				list.add(criterion1);
//				Method main = new MethodsRetriever().getMethod(c, methodName);
//				if (main != null) {
//					list.add(criterion2);
//
//					MethodExecutor methodExecutor = new MethodExecutor(c, main);
//					String consoleOutput = new TestHelper().getConsoleOutput(methodExecutor);
//
//					if (consoleOutput.contains(printed)) {
//						list.add(criterion3);
//					}
//				}
//			}
//		}
		

		Evaluations evaluations = new Evaluations();
		evaluations.add(new Evaluation(criterion1, false));
		evaluations.add(new Evaluation(criterion2, false));
		evaluations.add(new Evaluation(criterion3, false));
		
		evaluations.checkIfCriteriaAreMet(list);
		
		return evaluations;
	}
	



}
