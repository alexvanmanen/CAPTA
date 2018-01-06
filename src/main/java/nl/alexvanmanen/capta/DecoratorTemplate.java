package nl.alexvanmanen.capta;

import java.util.HashMap;
import java.util.HashSet;
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
	protected String methodName;
	protected Map<String, String[]> executionList = new HashMap<String, String[]>();

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

	public void addConcreteComponent(String concreteComponent) {
		this.concreteComponents.add(concreteComponent);
	}
	
	public void addExecutions(String concreteComponent, String... concreteComponents) {
		executionList.put(concreteComponent, concreteComponents);
	}

	public abstract Evaluations evaluate() throws NoAssignmentDefinedException;
}
