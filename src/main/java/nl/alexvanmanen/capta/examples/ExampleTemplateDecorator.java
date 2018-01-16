package nl.alexvanmanen.capta.examples;

import java.util.List;

import nl.alexvanmanen.capta.helper.ADLReader;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.statics.DecoratorTemplateStatic;

public class ExampleTemplateDecorator {

	public static void main(String[] args) {
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/assignments/decorator/code/");
		DecoratorTemplateStatic decoratorTemplate = new DecoratorTemplateStatic(assignmentOutput);
		
		ADLReader adlReader = new ADLReader("./cases/assignments/decorator/decorator.adl");
		setDefinition(decoratorTemplate, adlReader);


		Evaluations evaluations = decoratorTemplate.evaluate();
		System.out.println("Static evaluation");
		evaluations.printTotalList();
		System.out.println("Total points:" + evaluations.getTotalPoints());
	}

	private static void setDefinition(DecoratorTemplateStatic decoratorTemplate, ADLReader adlReader) {
		decoratorTemplate.setComponent(adlReader.getValue("Component"), adlReader.getValue("ComponentMethod"));
		decoratorTemplate.addConcreteComponents(adlReader.getValues("ConcreteComponents"));
		decoratorTemplate.setDecorator(adlReader.getValue("Decorator"));
		decoratorTemplate.addConcreteDecarators(adlReader.getValues("ConcreteDecorators"));
		
		for(List<String> execution: adlReader.getListOfValues("Executions")){
			decoratorTemplate.addExecutions(execution);
		}

		decoratorTemplate.setExecutionClass(adlReader.getValue("ExecutionClass"));
		decoratorTemplate.setExecutionMethod(adlReader.getValue("ExecutionMethod"));
		decoratorTemplate.addExpectedOutput(adlReader.getValues("ExpectedOutput"));
	}

	
}
