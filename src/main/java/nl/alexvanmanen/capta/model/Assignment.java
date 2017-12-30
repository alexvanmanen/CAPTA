package nl.alexvanmanen.capta.model;

import java.util.ArrayList;
import java.util.List;

public class Assignment {
	private List<Criterion> criteria = new ArrayList<Criterion>();
	String description;
	
	
	public void add(Criterion c){
		criteria.add(c);
	}
	
	public List<Criterion> getCriteria(){
		return criteria;
	}
	
	
}
