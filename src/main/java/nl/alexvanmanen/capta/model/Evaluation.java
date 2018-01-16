package nl.alexvanmanen.capta.model;

public class Evaluation implements Comparable<Evaluation>{
	public Criterion criterion;
	public boolean satisfies;
	
	public Evaluation(Criterion criterion, boolean statifies) {
		this.criterion = criterion;
		this.satisfies = statifies;
		
	}
	
	public boolean equals(Evaluation other){
		return other.criterion == this.criterion;
	}

	@Override
	public int compareTo(Evaluation other) {
		return this.criterion.description.compareTo(other.criterion.description);
	}
	
	
}