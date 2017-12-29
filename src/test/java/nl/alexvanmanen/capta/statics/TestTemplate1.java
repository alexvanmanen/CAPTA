package nl.alexvanmanen.capta.statics;

import java.io.FileNotFoundException;

import junit.framework.TestCase;
import nl.alexvanmanen.capta.model.AssignmentOutput;

public class TestTemplate1 extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test() throws FileNotFoundException {

		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/assignments/Hello.java");
		Template1 template1Statically = new Template1(assignmentOutput.getCompilationUnits());
		String className = "Hello";
		String methodName = "main";
		template1Statically.setSignature(className, methodName);
		template1Statically.setVariable("String", "name");
		template1Statically.setWhatIsBeingPrinted("Hello");

		String result = template1Statically.evaluate();
		System.out.println(result);

	}
}
