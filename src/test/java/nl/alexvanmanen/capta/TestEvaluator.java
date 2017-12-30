package nl.alexvanmanen.capta;

import junit.framework.Assert;
import junit.framework.TestCase;
import nl.alexvanmanen.capta.model.Assignment;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criterion;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.model.Exp;
import nl.alexvanmanen.capta.visitor.BinaryExpressionVisitor;
import nl.alexvanmanen.capta.visitor.ConsoleVisitor;
import nl.alexvanmanen.capta.visitor.MethodVisitor;
import nl.alexvanmanen.capta.visitor.VariableVisitor;

public class TestEvaluator extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testEvaluationCriteria() {
		
		Criterion criterion = new Criterion();
		String type = "String";
		String name = "name";
		criterion.visitor = new VariableVisitor(type, name);
		criterion.description = "There is a variable " + name + " of the type " + type + "\n";
		criterion.points = 2;

		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		Evaluation actual = new Evaluator().evaluate(criterion, assignmentOutput);
		Assert.assertEquals(true, actual.satifies);
	}
	
	public void testEvaluationAssignment() {
		Assignment assignment = new Assignment();
		
		Criterion criterion = new Criterion();
		
		String type = "String";
		String name = "name";
		criterion.visitor = new VariableVisitor(type, name);
		criterion.description = "There is a variable " + name + " of the type " + type + "\n";
		criterion.points = 2;

		assignment.add(criterion);
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		Evaluations actual = new Evaluator().evaluate(assignment, assignmentOutput);
		
		
		Assert.assertEquals(true, actual.getFirst().satifies);
	}
	
	
	public void testEvaluationWrongAssignment() {
		Assignment assignment = new Assignment();
		
		Criterion criterion = new Criterion();
		
		String type = "String";
		String name = "AAA";
		criterion.visitor = new VariableVisitor(type, name);
		criterion.description = "There is a variable " + name + " of the type " + type + "\n";
		criterion.points = 2;

		assignment.add(criterion);
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		
		Evaluations actual = new Evaluator().evaluate(assignment, assignmentOutput);
		Assert.assertEquals(false, actual.getFirst().satifies);
	}
	
	public void testEvaluationAssignments() {
		Assignment assignment = new Assignment();
		
		Criterion criterion1 = new Criterion();
		
		String type = "String";
		String name = "name";
		criterion1.visitor = new VariableVisitor(type, name);
		criterion1.description = "There is a variable " + name + " of the type " + type + "\n";
		criterion1.points = 2;

		
		String methodName = "main";
		String className = "Hello";
		Criterion criterion2 = new Criterion();
		criterion2.visitor = new MethodVisitor(methodName, className);
		criterion2.description = "+1 There is a method " + methodName + " in the class " + className + "\n";

		String printed = "Hello";
		Criterion criterion3 = new Criterion();
		criterion3.visitor = new ConsoleVisitor(printed);
		criterion3.description = "+2 " + printed + " is being printed\n";

		
		assignment.add(criterion1);
		assignment.add(criterion2);
		assignment.add(criterion3);

		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		Evaluations actual = new Evaluator().evaluate(assignment, assignmentOutput);
		
		Assert.assertEquals(true, actual.areAllEvaluationsSatisfied());
		
		
	}
	
	public void testEvaluationCriteriaBinaryExpression() {
		
		Criterion criterion = new Criterion();

		Exp expression = new Exp("INPUT","!=", "50",2);
		criterion.visitor = new BinaryExpressionVisitor(expression);
		criterion.description = "There is a expression "+ expression +"\n";
		criterion.points = 2;

		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		Evaluation actual = new Evaluator().evaluate(criterion, assignmentOutput);
		Assert.assertEquals(true, actual.satifies);
	}
	

	
}
