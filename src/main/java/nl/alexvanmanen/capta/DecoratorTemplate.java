package nl.alexvanmanen.capta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;

public abstract class DecoratorTemplate {

	protected String component;
	protected String decorator;
	protected Set<String> concreteDecorators = new HashSet<String>();
	protected Set<String> concreteComponents = new HashSet<String>();
	protected AssignmentOutput assignmentOutput;
//	protected String methodName;
	protected Map<String, String[]> executionList = new HashMap<String, String[]>();
	protected String classToExecuteName;// = "Start";
	protected String methodToExecute;
//	protected String printed1 = "8.75";
//	protected String printed2 = "9.3";
	protected List<String> outputList = new ArrayList<String>();
	protected String methodName;// = "main";
	
	
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
	
	
	public void setExecutionClass(String classToExecute) {
		this.classToExecuteName = classToExecuteName;
	}
	
	public void setExecutionMethod(String methodToExecute) {
		this.methodToExecute = methodToExecute;
	}
	
	public void addExpectedOutput(List<String> expectedOutput){
		outputList.addAll(expectedOutput);
	}

	public abstract Evaluations evaluate() throws NoAssignmentDefinedException;
}
