package nl.alexvanmanen.capta.statics;

import java.util.HashSet;
import java.util.Set;

import nl.alexvanmanen.capta.Evaluator;
import nl.alexvanmanen.capta.Template;
import nl.alexvanmanen.capta.model.Assignment;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criterion;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.visitor.ClassExtendsVisitor;
import nl.alexvanmanen.capta.visitor.ClassMethodVisitor;
import nl.alexvanmanen.capta.visitor.VariableVisitor;

public class DecoratorTemplate implements Template {

	private String component;
	private String decorator;
	private Set<String> concreteDecorators = new HashSet<String>();
	private Set<String> concreteComponents = new HashSet<String>();
	private AssignmentOutput assignmentOutput;
	private String methodName;

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

	public Evaluations evaluate() {
		for (Class c : assignmentOutput.getClassFiles()) {
			System.out.println(c.getName());
		}

		Assignment as = new Assignment();

		Criterion criterion = new Criterion();
		criterion.description = "There is a method with the name: " + methodName;
		criterion.points = 3;
		criterion.visitor = new ClassMethodVisitor(methodName, component);
		as.add(criterion);

		Criterion criterion2 = new Criterion();
		criterion2.description = "There is a variable double with the name: myPrice";
		criterion2.points = 3;
		criterion2.visitor = new VariableVisitor("double", "myPrice");
		as.add(criterion2);

		addExtendsFromCriteria(as, concreteComponents, component);
		addExtendsFromCriteria(as, concreteDecorators, decorator);

		Evaluations evaluations = new Evaluator().evaluate(as, assignmentOutput);

		evaluations.print();
		System.out.println();
		System.out.println("total points: " + evaluations.getTotalPoints());

		return evaluations;

	}

	private void addExtendsFromCriteria(Assignment as, Set<String> classes, String extendsFrom) {
		for (String concreteComponent : classes) {
			Criterion criterion3 = new Criterion();
			criterion3.description = "There is a class " + concreteComponent + " which extends " + extendsFrom;
			criterion3.points = 1;
			criterion3.visitor = new ClassExtendsVisitor(concreteComponent, extendsFrom);
			as.add(criterion3);
		}
	}
}
