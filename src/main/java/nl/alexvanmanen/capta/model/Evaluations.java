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
			if(e.criteria.equals(other.criteria)){
				result = true;
			}
		}
		return result;
	}

	public void print() {
		List<Evaluation> list = new ArrayList<Evaluation>(evaluationSet);
		Collections.sort(list);
		for(Evaluation e: list){
			if(e.satifies){
				System.out.print(" - "+ e.criteria.points + " - " + e.criteria.description.replace("\n", ""));
			}
		}
	}
	
	public void checkIfCriteriaAreMet(List<Criteria> list){
		for(Evaluation e: evaluationSet){
			if(list.contains(e.criteria)){
				e.satifies = true;
			}
		}
	}
	
	
}