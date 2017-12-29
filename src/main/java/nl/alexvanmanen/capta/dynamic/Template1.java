package nl.alexvanmanen.capta.dynamic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criteria;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.model.Evaluations;

public class Template1 {

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
		Evaluations evaluations = template.start();
		evaluations.print();
	}

	public Evaluations start() {

		String feedback = "";

		Criteria criteria1 = new Criteria();
		criteria1.description = "+1 There is a class Hello\n";
		criteria1.points = 1;

		Criteria criteria2 = new Criteria();
		criteria2.description = "+1 There is a method main\n";
		criteria2.points = 1;
		
		Criteria criteria3 = new Criteria();
		criteria3.description = "+3 Hello is being printed\n";
		criteria3.points = 3;
		
		List<Criteria> list = new ArrayList<Criteria>();

		
		for (Class c : 	assignmentOutput.getClassFiles()) {
			if (c.getName().contains(className)) {
				list.add(criteria1);
				Method main = new MethodsRetriever().getMethod(c, methodName);
				if (main != null) {
					list.add(criteria2);

					MethodExecutor methodExecutor = new MethodExecutor(c, main);
					String consoleOutput = new TestHelper().getConsoleOutput(methodExecutor);

					if (consoleOutput.contains(printed)) {
						list.add(criteria3);
					}
				}
			}
		}
		

		Evaluations evaluations = new Evaluations();
		evaluations.add(new Evaluation(criteria1, false));
		evaluations.add(new Evaluation(criteria2, false));
		evaluations.add(new Evaluation(criteria3, false));
		
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
