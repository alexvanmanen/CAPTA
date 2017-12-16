package nl.alexvanmanen.capta;

import java.util.ArrayList;
import java.util.List;

import nl.alexvanmanen.capta.model.Assignment;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criteria;
import nl.alexvanmanen.capta.model.Evaluation;

public class Evaluator {

	
	public List<Evaluation> evaluate(Assignment assignment, AssignmentOutput assignmentOutput){
		List<Evaluation> result = new ArrayList<Evaluation>();
		for(Criteria criteria: assignment.getCriterion()){
			result.add(evaluate(criteria, assignmentOutput));
		}
		return result;
	}
	
	public Evaluation evaluate(Criteria criteria, AssignmentOutput assignmentOutput){
		//StaticEvaluator (criteria, assignmentOutput);
		assignmentOutput.getCompilationUnit().accept(criteria.visitor, null);
		if (criteria.visitor.isFound()) {
			return new Evaluation(criteria,true);
		}
		return new Evaluation(criteria,false);
	}
}
