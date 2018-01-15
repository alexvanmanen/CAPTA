package nl.alexvanmanen.capta.model;

import java.util.ArrayList;
import java.util.List;


import nl.alexvanmanen.capta.dynamic.Test;
import nl.alexvanmanen.capta.visitor.AbstractVisitor;

public class Criterion {

	public AbstractVisitor visitor;
	public String description;
	public int points;
	private List<Test> tests = new ArrayList<Test>();
	
	public void addTest(Test test){
		tests.add(test);
	}

	public List<Test> getTests() {
		
		return tests;
	}
	
}
