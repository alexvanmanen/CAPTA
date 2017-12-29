package nl.alexvanmanen.capta.model;

public class Evaluation implements Comparable<Evaluation>{
	public Criteria criteria;
	public boolean satifies;
	
	public Evaluation(Criteria criteria, boolean statifies) {
		this.criteria = criteria;
		this.satifies = statifies;
		
	}
	
	public boolean equals(Evaluation other){
		return other.criteria == this.criteria;
	}

	@Override
	public int compareTo(Evaluation other) {
		return this.criteria.description.compareTo(other.criteria.description);
	}
	
	
}