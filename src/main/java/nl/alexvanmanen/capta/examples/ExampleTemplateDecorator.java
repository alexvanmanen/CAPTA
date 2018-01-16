package nl.alexvanmanen.capta.examples;

import java.util.List;

import nl.alexvanmanen.capta.DecoratorTemplate;
import nl.alexvanmanen.capta.NoAssignmentDefinedException;
import nl.alexvanmanen.capta.dynamic.DecoratorTemplateDynamic;
import nl.alexvanmanen.capta.helper.ADLReader;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.statics.DecoratorTemplateStatic;

public class ExampleTemplateDecorator {

	public static void main(String[] args) throws NoAssignmentDefinedException {
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/assignments/decorator/code/");

		printResults("Static", new DecoratorTemplateStatic(assignmentOutput));
		System.out.println();
		printResults("Dynamic", new DecoratorTemplateDynamic(assignmentOutput));
	}
	
	private static void printResults(String type, DecoratorTemplate template) throws NoAssignmentDefinedException{
		ADLReader adlReader = new ADLReader("./cases/assignments/decorator/decorator.adl");

		setDefinition(template, adlReader);
		Evaluations dynamicEvaluations = template.evaluate();
		System.out.println(type + " evaluation");
		dynamicEvaluations.printTotalList();
		System.out.println("Total points:" + dynamicEvaluations.getTotalPoints());
	}
	

	private static void setDefinition(DecoratorTemplate decoratorTemplate, ADLReader adlReader) {
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
