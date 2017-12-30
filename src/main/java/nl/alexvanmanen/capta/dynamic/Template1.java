package nl.alexvanmanen.capta.dynamic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criterion;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.model.Evaluations;

public class Template1 implements nl.alexvanmanen.capta.Template1 {

	private AssignmentOutput assignmentOutput;
	private String className;
	private String methodName;
	private String type;
	private String name;
	private String printed;

	public Template1(AssignmentOutput assignmentOutput) {
		this.assignmentOutput = assignmentOutput;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		
		
		Template1 template = new Template1(assignmentOutput);
		template.setSignature("Hello", "main");
		template.setVariable("String", "naam");
		template.setWhatIsBeingPrinted("Helo");
		Evaluations evaluations = template.evaluate();
		evaluations.print();
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

		
		for (Class c : 	assignmentOutput.getClassFiles()) {
			if (c.getName().contains(className)) {
				list.add(criterion1);
				Method main = new MethodsRetriever().getMethod(c, methodName);
				if (main != null) {
					list.add(criterion2);

					MethodExecutor methodExecutor = new MethodExecutor(c, main);
					String consoleOutput = new TestHelper().getConsoleOutput(methodExecutor);

					if (consoleOutput.contains(printed)) {
						list.add(criterion3);
					}
				}
			}
		}
		

		Evaluations evaluations = new Evaluations();
		evaluations.add(new Evaluation(criterion1, false));
		evaluations.add(new Evaluation(criterion2, false));
		evaluations.add(new Evaluation(criterion3, false));
		
		evaluations.checkIfCriteriaAreMet(list);
		
		return evaluations;
	}
	
	public void setSignature(String className, String methodName) {
		this.className = className;
		this.methodName = methodName;

	}

	public void setVariable(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public void setWhatIsBeingPrinted(String printed) {
		this.printed = printed;
	}
}
