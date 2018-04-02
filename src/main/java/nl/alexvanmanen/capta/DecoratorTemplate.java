package nl.alexvanmanen.capta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.alexvanmanen.capta.helper.ADLReader;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;

public abstract class DecoratorTemplate {

	protected String component;
	protected String decorator;
	protected Set<String> concreteDecorators = new HashSet<String>();
	protected Set<String> concreteComponents = new HashSet<String>();
	protected AssignmentOutput assignmentOutput;
	protected Map<String, String[]> executionList = new HashMap<String, String[]>();
	protected String classToExecuteName;
	protected String methodToExecute;
	protected List<String> outputList = new ArrayList<String>();
	protected String methodName;
	
	
	public DecoratorTemplate(AssignmentOutput assignmentOutput) {
		this.assignmentOutput = assignmentOutput;
	}

	public void setComponent(String component, String methodName) {
		this.component = component;
		this.methodName = methodName;
	}

	public void setDecorator(String decorator) {
		this.decorator = decorator;
	}

	public void addConcreteDecarator(String concreteDecorator) {
		this.concreteDecorators.add(concreteDecorator);
	}
	
	public void addConcreteDecarators(List<String> concreteDecorators) {
		this.concreteDecorators.addAll(concreteDecorators);
	}

	public void addConcreteComponent(String concreteComponent) {
		this.concreteComponents.add(concreteComponent);
	}
	
	public void addConcreteComponents(List<String> concreteComponents) {
		
		this.concreteComponents.addAll(concreteComponents);
	}
	
	public void addExecutions(String concreteComponent, String... concreteDecorators) {
		executionList.put(concreteComponent, concreteDecorators);
	}
	
	public void addExecutions(List<String> list) {
		String concreteComponent = list.get(0);
		list.remove(0);
		
		/* convert to array */
		String[] concreteDecorators = new String[list.size()];
		concreteDecorators = list.toArray(concreteDecorators);
		
		executionList.put(concreteComponent, concreteDecorators);
	}
	
	
	public void setExecutionClass(String classToExecuteName) {
		this.classToExecuteName = classToExecuteName;
	}
	
	public void setExecutionMethod(String methodToExecute) {
		this.methodToExecute = methodToExecute;
	}
	
	public void addExpectedOutput(List<String> expectedOutput){
		outputList.addAll(expectedOutput);
	}
	
	public void setDefinition(ADLReader adlReader) {
		this.setComponent(adlReader.getValue("Component"), adlReader.getValue("ComponentMethod"));
		this.addConcreteComponents(adlReader.getValues("ConcreteComponents"));
		this.setDecorator(adlReader.getValue("Decorator"));
		this.addConcreteDecarators(adlReader.getValues("ConcreteDecorators"));
		
		for(List<String> execution: adlReader.getListOfValues("Executions")){
			this.addExecutions(execution);
		}

		this.setExecutionClass(adlReader.getValue("ExecutionClass"));
		this.setExecutionMethod(adlReader.getValue("ExecutionMethod"));
		this.addExpectedOutput(adlReader.getValues("ExpectedOutput"));
	}

	public abstract Evaluations evaluate() throws NoAssignmentDefinedException;
	
	public abstract String getType();
}
