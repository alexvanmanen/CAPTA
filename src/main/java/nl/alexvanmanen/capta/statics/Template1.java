package nl.alexvanmanen.capta.statics;

import java.io.FileNotFoundException;

import com.github.javaparser.ast.CompilationUnit;

import nl.alexvanmanen.capta.model.Criteria;
import nl.alexvanmanen.capta.visitor.ConsoleVisitor;
import nl.alexvanmanen.capta.visitor.MethodVisitor;
import nl.alexvanmanen.capta.visitor.VariableVisitor;

public class Template1 {

	private String className;
	private String methodName;
	CompilationUnit compilationUnit;

	private String type;
	private String name;
	private String printed;

	public Template1(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public String evaluate() throws FileNotFoundException {
		// prints the resulting compilation unit to default system output
		System.out.println(compilationUnit.toString());

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
		for (Criteria s : list) {
			compilationUnit.accept(s.visitor, null);
			if (s.visitor.isFound()) {
				feedback += s.description;
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

}
