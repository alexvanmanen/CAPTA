package nl.alexvanmanen.capta.examples;

import nl.alexvanmanen.capta.helper.ADLReader;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.statics.Template2;

public class ExampleTemplatePrintVariable {

	public static void main(String[] args) {
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/assignments/printvariable/code");
		String adl = "./cases/assignments/printvariable/test.adl";
		test(new nl.alexvanmanen.capta.statics.Template1(assignmentOutput), assignmentOutput, adl);
		test(new nl.alexvanmanen.capta.dynamic.Template1(assignmentOutput), assignmentOutput, adl);

	}

	private static void test(nl.alexvanmanen.capta.Template1 template, AssignmentOutput assignmentOutput, String adl) {
		setParameters(template, adl);
		Evaluations evaluations = template.evaluate();
		evaluations.printTotalList();
		System.out.println("Total points: " + evaluations.getTotalPoints());
	}

	private static void setParameters(nl.alexvanmanen.capta.Template1 template1Statically, String adl) {
		ADLReader adlReader = new ADLReader(adl);
		template1Statically.setSignature(adlReader.getValue("ClassName"), adlReader.getValue("MethodName"));
		template1Statically.setVariable(adlReader.getValue("VariableType"), adlReader.getValue("VariableName"));
		template1Statically.setWhatIsBeingPrinted(adlReader.getValue("Printed"));
	}

}
