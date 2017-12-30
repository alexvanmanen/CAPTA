package nl.alexvanmanen.capta;

import com.github.javaparser.ast.CompilationUnit;

import nl.alexvanmanen.capta.model.Assignment;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criterion;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.model.Evaluations;

public class Evaluator {

	
	public Evaluations evaluate(Assignment assignment, AssignmentOutput assignmentOutput){
		Evaluations result = new Evaluations();
		for(Criterion criterion: assignment.getCriteria()){
			Evaluation evaluate = evaluate(criterion, assignmentOutput);
			result.add(evaluate);
		}
		return result;
	}
	
	public Evaluation evaluate(Criterion criterion, AssignmentOutput assignmentOutput){
		for(CompilationUnit cu: assignmentOutput.getCompilationUnits()){
			cu.accept(criterion.visitor, null);
			if (criterion.visitor.isFound()) {
				return new Evaluation(criterion,true);
			}	
		}
		return new Evaluation(criterion,false);
	}
}
