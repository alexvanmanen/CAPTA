package nl.alexvanmanen.capta.examples;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.statics.Template2;

public class ExampleTemplateConditions {

	public static void main (String[] args) {
		String directory = "./cases/assignments/conditions/code/";
		String adl = "./cases/assignments/conditions/test.adl";
		
		System.out.println("static");
		staticTemplate(directory, adl);
		System.out.println("");
		System.out.println("dynamic");
		dynamicTemplate(directory, adl);
	}

	private static void staticTemplate(String directory, String adl) {
		AssignmentOutput assignmentOutput = new AssignmentOutput(directory);
		Template2 template = new Template2(adl, assignmentOutput);
		Evaluations evaluations = template.evaluate();
		evaluations.printTotalList();
		System.out.println("total points: " + evaluations.getTotalPoints());

		//evaluations.print();
	}
	
	private static void dynamicTemplate(String directory, String adl) {
		AssignmentOutput assignmentOutput = new AssignmentOutput(directory);
		nl.alexvanmanen.capta.dynamic.Template2 template = new nl.alexvanmanen.capta.dynamic.Template2(
				adl, assignmentOutput);
		Evaluations evaluations = template.evaluate();
		
		evaluations.printTotalList();
		System.out.println("total points: " + evaluations.getTotalPoints());
	}
}
