package nl.alexvanmanen.capta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import nl.alexvanmanen.capta.model.Criteria;
import nl.alexvanmanen.capta.visitor.ConsoleVisitor;
import nl.alexvanmanen.capta.visitor.MethodVisitor;
import nl.alexvanmanen.capta.visitor.VariableVisitor;

public class Template1Statically {

	private String className;
	private String methodName;
	private CompilationUnit cu;
	private String type;
	private String name;
	private String printed;

	public Template1Statically(String fileName) throws FileNotFoundException {

		FileInputStream in = new FileInputStream(fileName);
		cu = JavaParser.parse(in);
	}

	public String evaluate() throws FileNotFoundException {
		// prints the resulting compilation unit to default system output
		System.out.println(cu.toString());

		String feedback = "";

		Criteria s1 = new Criteria();
		s1.visitor = new VariableVisitor(type, name);
		s1.feedback = "+2 There is a variable " + name + " of the type " + type + "\n";

		Criteria s2 = new Criteria();
		s2.visitor = new MethodVisitor(methodName, className);
		s2.feedback = "+1 There is a method " + methodName + " in the class " + className + "\n";

		Criteria s3 = new Criteria();
		s3.visitor = new ConsoleVisitor(printed);
		s3.feedback = "+2 " + printed + " is being printed\n";

		Criteria[] list = { s1, s2, s3 };
		for (Criteria s : list) {
			cu.accept(s.visitor, null);
			if (s.visitor.isFound()) {
				feedback += s.feedback;
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
