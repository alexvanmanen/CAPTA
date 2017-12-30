package nl.alexvanmanen.capta;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import nl.alexvanmanen.capta.model.Assignment;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criteria;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.model.Evaluations;

public class Evaluator {

	
	public Evaluations evaluate(Assignment assignment, AssignmentOutput assignmentOutput){
		Evaluations result = new Evaluations();
		for(Criteria criteria: assignment.getCriterion()){
			result.add(evaluate(criteria, assignmentOutput));
		}
		return result;
	}
	
	public Evaluation evaluate(Criteria criteria, AssignmentOutput assignmentOutput){
		//StaticEvaluator (criteria, assignmentOutput);
		for(CompilationUnit cu: assignmentOutput.getCompilationUnits()){
			cu.accept(criteria.visitor, null);
			if (criteria.visitor.isFound()) {
				return new Evaluation(criteria,true);
			}	
		}
		return new Evaluation(criteria,false);
	}
}
