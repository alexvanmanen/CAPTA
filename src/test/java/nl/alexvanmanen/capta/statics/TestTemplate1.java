package nl.alexvanmanen.capta.statics;

import java.io.FileNotFoundException;

import junit.framework.TestCase;
import nl.alexvanmanen.capta.helper.ADLReader;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;

public class TestTemplate1 extends TestCase {


	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test() throws FileNotFoundException {

		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/assignments/printvariable/code");
		String adl = "./cases/assignments/printvariable/test.adl";
		ADLReader adlReader = new ADLReader(adl);
		
		
		Template1 template = new Template1(assignmentOutput);
		template.setSignature(adlReader.getValue("ClassName"), adlReader.getValue("MethodName"));
		template.setVariable(adlReader.getValue("VariableType"), adlReader.getValue("VariableName"));
		template.setWhatIsBeingPrinted(adlReader.getValue("Printed"));
		Evaluations evaluations = template.evaluate();
		assertEquals(5, evaluations.getTotalPoints());
	}
}
