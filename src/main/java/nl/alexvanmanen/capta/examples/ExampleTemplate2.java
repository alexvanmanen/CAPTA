package nl.alexvanmanen.capta.examples;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.statics.Template2;

public class ExampleTemplate2 {

	public static void main (String[] args) {
		String directory = "./cases/";
		String adl = "./cases/test.adl";
		
		
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
		evaluations.print();
	}
	
	private static void dynamicTemplate(String directory, String adl) {
		AssignmentOutput assignmentOutput = new AssignmentOutput(directory);
		nl.alexvanmanen.capta.dynamic.Template2 template = new nl.alexvanmanen.capta.dynamic.Template2(
				adl, assignmentOutput);
		Evaluations evaluations = template.evaluate();
		evaluations.print();
		System.out.println("total points: " + evaluations.getTotalPoints());
	}
}
