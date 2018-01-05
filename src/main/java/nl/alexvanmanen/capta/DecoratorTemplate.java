package nl.alexvanmanen.capta;

import java.util.HashSet;
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

	public abstract Evaluations evaluate() throws NoAssignmentDefinedException;
}
