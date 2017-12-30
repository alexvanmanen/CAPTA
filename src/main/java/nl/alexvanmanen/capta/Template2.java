package nl.alexvanmanen.capta;

import java.util.List;

import nl.alexvanmanen.capta.model.Assignment;
import nl.alexvanmanen.capta.model.Criterion;
import nl.alexvanmanen.capta.model.Exp;
import nl.alexvanmanen.capta.visitor.BinaryExpressionVisitor;

public abstract class Template2 implements Template {

	
	public Assignment generateAssignment(List<Exp> list) {
		Assignment assignment = new Assignment();
		for(Exp exp: list){
			Criterion criterion = new Criterion();

			criterion.visitor = new BinaryExpressionVisitor(exp);
			criterion.description = "There is a expression " + exp+ "\n";
			criterion.points = 2;
			assignment.add(criterion);
		}
		return assignment;
	}

}
