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
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		Template2 template = new Template2("./cases/test.adl", assignmentOutput);
		
		for(Evaluation evaluation: template.evaluate()){
			Assert.assertEquals(true, evaluation.satifies);
		}		

	}
}
