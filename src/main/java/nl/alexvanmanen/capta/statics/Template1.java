package nl.alexvanmanen.capta.statics;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criteria;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.visitor.ConsoleVisitor;
import nl.alexvanmanen.capta.visitor.MethodVisitor;
import nl.alexvanmanen.capta.visitor.VariableVisitor;

public class Template1 {

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

	public static void main(String[] args){
		
		
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/assignments/Hello.java");
		Template1 template1Statically = new Template1(assignmentOutput);
		String className = "Hello";
		String methodName = "main";
		template1Statically.setSignature(className, methodName);
		template1Statically.setVariable("String", "name");
		template1Statically.setWhatIsBeingPrinted("Hello");

		String result = template1Statically.evaluate();
		System.out.println(result);

	}
	
	
	public String evaluate()  {
		
		String feedback = "";

		Criteria criteria1 = new Criteria();
		criteria1.visitor = new VariableVisitor(type, name);
		criteria1.description = "+2 There is a variable " + name + " of the type " + type + "\n";
		criteria1.points = 2;
		
		Criteria criteria2 = new Criteria();
		criteria2.visitor = new MethodVisitor(methodName, className);
		criteria2.description = "+1 There is a method " + methodName + " in the class " + className + "\n";
		criteria2.points = 1;

		Criteria s3 = new Criteria();
		s3.visitor = new ConsoleVisitor(printed);
		s3.description = "+2 " + printed + " is being printed\n";
		s3.points = 2;
		
		
		
		Criteria[] list = { criteria1, criteria2, s3 };
		return evaluate(feedback, list);

	}

	private String evaluate(String feedback, Criteria[] list) {
		for(CompilationUnit compilationUnit: compilationUnits){
			for (Criteria criteria : list) {
				compilationUnit.accept(criteria.visitor, null);
				if (criteria.visitor.isFound()) {
					evaluations.add(new Evaluation(criteria, true));
					feedback += criteria.description;
				}
			}
		}
		return feedback;
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

	
	public void printEvaluatedCode(){
		for(CompilationUnit compilationUnit: compilationUnits)
			System.out.println(compilationUnit.toString());

	}
	
	public Evaluations getEvaluations(){
		return evaluations;
	}
}
