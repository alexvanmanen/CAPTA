package nl.alexvanmanen.capta.examples;

import nl.alexvanmanen.capta.DecoratorTemplate;
import nl.alexvanmanen.capta.NoAssignmentDefinedException;
import nl.alexvanmanen.capta.factory.DecoratorTemplateFactory;
import nl.alexvanmanen.capta.helper.ADLReader;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;

public class ExampleTemplateDecorator {

	public static void main(String[] args) throws NoAssignmentDefinedException {
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/assignments/decorator/code/");

		DecoratorTemplateFactory factory = new DecoratorTemplateFactory();
		
		printResults(factory.getStaticTemplate(assignmentOutput));  
		System.out.println();
		printResults(factory.getDynamicTemplate(assignmentOutput));
	}

	private static void printResults(DecoratorTemplate template) throws NoAssignmentDefinedException {
		ADLReader adlReader = new ADLReader("./cases/assignments/decorator/decorator.adl");
		template.setDefinition(adlReader);
		Evaluations evaluations = template.evaluate();

		System.out.println("Evaluation type: " + template.getType());
		evaluations.printTotalList();
		System.out.println("Total points:" + evaluations.getTotalPoints());
	}

}
