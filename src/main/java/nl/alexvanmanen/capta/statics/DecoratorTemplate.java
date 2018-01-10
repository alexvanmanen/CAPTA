package nl.alexvanmanen.capta.statics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import nl.alexvanmanen.capta.Evaluator;
import nl.alexvanmanen.capta.model.Assignment;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criterion;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.visitor.ClassExtendsVisitor;
import nl.alexvanmanen.capta.visitor.ClassMethodVisitor;
import nl.alexvanmanen.capta.visitor.DecoratorExecutionVisitor;

public class DecoratorTemplate extends nl.alexvanmanen.capta.DecoratorTemplate {



	public DecoratorTemplate(AssignmentOutput assignmentOutput) {
		super(assignmentOutput);
	}

	public Evaluations evaluate() {
		
		
		Assignment as = new Assignment();

		Criterion criterion = new Criterion();
		criterion.description = "In the component class "+ component+" there is a method with the name: " + methodName;
		criterion.points = 1;
		criterion.visitor = new ClassMethodVisitor(methodName, component);
		as.add(criterion);
		
		for(String executionKey: executionList.keySet()){
			addExecutionCriteria(as, executionKey, executionList.get(executionKey));

		}
		
		addExtendsFromCriteria(as, decorator, component, "is a valid decorator");
		addExtendsFromCriteria(as, concreteComponents, component, "is a valid concrete component");
		addExtendsFromCriteria(as, concreteDecorators, decorator, "is a valid concrete decorator");

		Evaluations evaluations = new Evaluator().evaluate(as, assignmentOutput);
		return evaluations;

	}

	private void addExecutionCriteria(Assignment as, String concreteComponent, String... concreteComponents) {
		Criterion criterion2 = new Criterion();
		
		criterion2.description = "new "+concreteComponent+"() is made with "+ Arrays.toString(concreteComponents);
		criterion2.points = 1;
		criterion2.visitor = new DecoratorExecutionVisitor(concreteDecorators, concreteComponent);
		as.add(criterion2);
	}

	private void addExtendsFromCriteria(Assignment as, String className, String extendsFrom, String description) {
		Set<String> set = new HashSet<String>();
		set.add(className);
		addExtendsFromCriteria(as, set, extendsFrom, "is a valid Decorator");
	}

	private void addExtendsFromCriteria(Assignment as, Set<String> classes, String extendsFrom, String description) {
		for (String className : classes) {
			Criterion criterion3 = new Criterion();
			criterion3.description = className + ": "+ description;
			criterion3.points = 1;
			criterion3.visitor = new ClassExtendsVisitor(className, extendsFrom);
			as.add(criterion3);
		}
	}
	

}
