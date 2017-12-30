package nl.alexvanmanen.capta.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Evaluations {

	private Set<Evaluation> evaluationSet = new HashSet<Evaluation>();

	public void add(Evaluation evaluation) {
		if(!isDouble(evaluation)){
			evaluationSet.add(evaluation);
		}	
	}

	private boolean isDouble(Evaluation other){
		boolean result = false;
		for(Evaluation e: evaluationSet){
			if(e.criterion.equals(other.criterion)){
				result = true;
			}
		}
		return result;
	}

	public void print() {
		if(evaluationSet != null){
			List<Evaluation> list = new ArrayList<Evaluation>(evaluationSet);
			Collections.sort(list);
			for(Evaluation e: list){
				if(e.satifies){
					System.out.print(" - "+ e.criterion.points + " - " + e.criterion.description.replace("\n", ""));
				}
			}
		} else {
			System.out.println("evaluations is empty");
		}
	}
	
	public void checkIfCriteriaAreMet(List<Criterion> list){
		for(Evaluation e: evaluationSet){
			if(list.contains(e.criterion)){
				e.satifies = true;
			}
		}
	}
	
	public Evaluation getFirst(){
		return evaluationSet.iterator().next();
	}
	
	public boolean areAllEvaluationsSatisfied(){
		for(Evaluation e: evaluationSet){
			if(!e.satifies){
				return false;
			}
		}
		return true;
	}
	
	
}
