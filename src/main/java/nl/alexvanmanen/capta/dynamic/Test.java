package nl.alexvanmanen.capta.dynamic;

public class Test{
	Test (String input, String expectedOutput, boolean expectedOutputPresent){
		this.input = input;
		this.expectedOutput = expectedOutput;
		this.expectedOutputPresent = expectedOutputPresent;
		
	}
	String input;
	String expectedOutput;
	boolean expectedOutputPresent;
	//Criterion criterion;
	
	
	public boolean complies(String text){
		if(expectedOutputPresent) {
			return text.contains(expectedOutput);
		} else {
			return !text.contains(expectedOutput);
		}
	}
	
	public String toString(){
		return input + " " + expectedOutput+ " " + expectedOutputPresent;
	}
	
}