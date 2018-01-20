package nl.alexvanmanen.capta.statics;

import java.io.FileNotFoundException;

import junit.framework.TestCase;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;

public class TestTemplate1 extends TestCase {

	private String assignmentDirectory = "./cases/assignments/printvariable/code";

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test() throws FileNotFoundException {

		AssignmentOutput assignmentOutput = new AssignmentOutput(assignmentDirectory);
		Template1 template1Statically = new Template1(assignmentOutput.getCompilationUnits());
		String className = "Hello";
		String methodName = "main";
		template1Statically.setSignature(className, methodName);
		template1Statically.setVariable("String", "name");
		template1Statically.setWhatIsBeingPrinted("Hello");

		Evaluations evaluations = template1Statically.evaluate();
		evaluations.print();

	}
}
