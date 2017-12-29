package nl.alexvanmanen.capta;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import nl.alexvanmanen.capta.helper.ADLReader;
import nl.alexvanmanen.capta.model.Assignment;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criteria;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.model.Exp;
import nl.alexvanmanen.capta.statics.Template2;
import nl.alexvanmanen.capta.visitor.BinaryExpressionVisitor;
import nl.alexvanmanen.capta.visitor.ConsoleVisitor;
import nl.alexvanmanen.capta.visitor.MethodVisitor;
import nl.alexvanmanen.capta.visitor.VariableVisitor;

public class TestEvaluator extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testEvaluationCriteria() {
		
		Criteria criteria = new Criteria();
		String type = "String";
		String name = "name";
		criteria.visitor = new VariableVisitor(type, name);
		criteria.description = "There is a variable " + name + " of the type " + type + "\n";
		criteria.points = 2;

		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		Evaluation actual = new Evaluator().evaluate(criteria, assignmentOutput);
		Assert.assertEquals(true, actual.satifies);
	}
	
	public void testEvaluationAssignment() {
		Assignment assignment = new Assignment();
		
		Criteria criteria = new Criteria();
		
		String type = "String";
		String name = "name";
		criteria.visitor = new VariableVisitor(type, name);
		criteria.description = "There is a variable " + name + " of the type " + type + "\n";
		criteria.points = 2;

		assignment.add(criteria);
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		List<Evaluation> actual = new Evaluator().evaluate(assignment, assignmentOutput);
		
		
		Assert.assertEquals(true, actual.get(0).satifies);
	}
	
	
	public void testEvaluationWrongAssignment() {
		Assignment assignment = new Assignment();
		
		Criteria criteria = new Criteria();
		
		String type = "String";
		String name = "AAA";
		criteria.visitor = new VariableVisitor(type, name);
		criteria.description = "There is a variable " + name + " of the type " + type + "\n";
		criteria.points = 2;

		assignment.add(criteria);
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		List<Evaluation> actual = new Evaluator().evaluate(assignment, assignmentOutput);
		
		Assert.assertEquals(false, actual.get(0).satifies);

	}
	
	public void testEvaluationAssignments() {
		Assignment assignment = new Assignment();
		
		Criteria criteria1 = new Criteria();
		
		String type = "String";
		String name = "name";
		criteria1.visitor = new VariableVisitor(type, name);
		criteria1.description = "There is a variable " + name + " of the type " + type + "\n";
		criteria1.points = 2;

		
		String methodName = "main";
		String className = "Hello";
		Criteria criteria2 = new Criteria();
		criteria2.visitor = new MethodVisitor(methodName, className);
		criteria2.description = "+1 There is a method " + methodName + " in the class " + className + "\n";

		String printed = "Hello";
		Criteria criteria3 = new Criteria();
		criteria3.visitor = new ConsoleVisitor(printed);
		criteria3.description = "+2 " + printed + " is being printed\n";

		
		assignment.add(criteria1);
		assignment.add(criteria2);
		assignment.add(criteria3);

		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		List<Evaluation> actual = new Evaluator().evaluate(assignment, assignmentOutput);
		
		for(Evaluation evaluation: actual){
			Assert.assertEquals(true, evaluation.satifies);
		}
		
	}
	
	public void testEvaluationCriteriaBinaryExpression() {
		
		Criteria criteria = new Criteria();

		Exp expression = new Exp("INPUT","!=", "50",2);
		criteria.visitor = new BinaryExpressionVisitor(expression);
		criteria.description = "There is a expression "+ expression +"\n";
		criteria.points = 2;

		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		Evaluation actual = new Evaluator().evaluate(criteria, assignmentOutput);
		Assert.assertEquals(true, actual.satifies);
	}
	

	
}
