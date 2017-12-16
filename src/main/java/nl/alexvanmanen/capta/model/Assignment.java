package nl.alexvanmanen.capta.model;

import java.util.ArrayList;
import java.util.List;

public class Assignment {
	private List<Criteria> criterion = new ArrayList<Criteria>();
	String description;
	
	
	public void add(Criteria c){
		criterion.add(c);
	}
	
	public List<Criteria> getCriterion(){
		return criterion;
	}
	
	
}
