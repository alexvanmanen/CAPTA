package nl.alexvanmanen.capta.statics;

import java.io.FileNotFoundException;

import junit.framework.Assert;
import junit.framework.TestCase;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluation;

public class TestTemplate2 extends TestCase {

	
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test() throws FileNotFoundException {
		String directory = "./cases/assignments/conditions/code/";
		String adl = "./cases/assignments/conditions/test.adl";
		
		AssignmentOutput assignmentOutput = new AssignmentOutput(directory);
		Template2 template = new Template2(adl, assignmentOutput);
		
		
		Assert.assertEquals(true, template.evaluate().areAllEvaluationsSatisfied());
				

	}
}
