package nl.alexvanmanen.capta.statics;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criterion;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.visitor.ConsoleVisitor;
import nl.alexvanmanen.capta.visitor.MethodVisitor;
import nl.alexvanmanen.capta.visitor.VariableVisitor;

public class Template1 implements nl.alexvanmanen.capta.Template1 {

	private String className;
	private String methodName;
	List<CompilationUnit> compilationUnits;

	private String type;
	private String name;
	private String printed;
	private Evaluations evaluations = new Evaluations();

	public Template1(List<CompilationUnit> list) {
		this.compilationUnits = list;
	}

	public Template1(AssignmentOutput assignmentOutput) {
		this(assignmentOutput.getCompilationUnits());
	}

	public static void main(String[] args) {

		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/assignments/Hello.java");
		Template1 template1Statically = new Template1(assignmentOutput);
		String className = "Hello";
		String methodName = "main";
		template1Statically.setSignature(className, methodName);
		template1Statically.setVariable("String", "name");
		template1Statically.setWhatIsBeingPrinted("Hello");

		Evaluations evaluations = template1Statically.evaluate();
		evaluations.print();

	}

	public Evaluations evaluate() {

		String feedback = "";

		Criterion criterion1 = new Criterion();
		criterion1.visitor = new VariableVisitor(type, name);
		criterion1.description = "+2 There is a variable " + name + " of the type " + type + "\n";
		criterion1.points = 2;

		Criterion criterion2 = new Criterion();
		criterion2.visitor = new MethodVisitor(methodName, className);
		criterion2.description = "+1 There is a method " + methodName + " in the class " + className + "\n";
		criterion2.points = 1;

		Criterion criterion3 = new Criterion();
		criterion3.visitor = new ConsoleVisitor(printed);
		criterion3.description = "+2 " + printed + " is being printed\n";
		criterion3.points = 2;

		Criterion[] list = { criterion1, criterion2, criterion3 };

		return evaluate(feedback, list);

	}

	private Evaluations evaluate(String feedback, Criterion[] list) {
		for (CompilationUnit compilationUnit : compilationUnits) {
			for (Criterion criteria : list) {
				compilationUnit.accept(criteria.visitor, null);
				if (criteria.visitor.isFound()) {
					evaluations.add(new Evaluation(criteria, true));
					feedback += criteria.description;
				}
			}
		}
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

	public void printEvaluatedCode() {
		for (CompilationUnit compilationUnit : compilationUnits)
			System.out.println(compilationUnit.toString());

	}

	public Evaluations getEvaluations() {
		return evaluations;
	}
}
