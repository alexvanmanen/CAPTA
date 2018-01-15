package nl.alexvanmanen.capta.dynamic;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criterion;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.model.Evaluations;

public class Template2 extends nl.alexvanmanen.capta.Template2 {

	private String adlFile;
	private AssignmentOutput assignmentOutput;

	public Template2(String adlFile, AssignmentOutput assignmentOutput)  {
		this.adlFile = adlFile;
		this.assignmentOutput = assignmentOutput;
	}

	public Evaluations evaluate() {

		Evaluations evaluations = new Evaluations();

		Criterion[] criteria = getCriteria();

		if(asksForConsoleInput()){
			Criterion c1= new Criterion();
			c1.description= "Import Scanner";
			c1.points = 1;
			
			Criterion c2= new Criterion();
			c2.description = "Declaring Scanner object";
			c2.points = 2;
			
			Criterion c3 = new Criterion();
			c3.description ="Declaring proper type";
			c3.points = 2;
			
			Criterion c4 = new Criterion();
			c4.description ="Asking for user input";
			c4.points = 2;
			evaluations.add(new Evaluation(c1, true));
			evaluations.add(new Evaluation(c2, true));
			evaluations.add(new Evaluation(c3, true));
			evaluations.add(new Evaluation(c4, true));
		}
		
		
		for (Criterion c : criteria) {
			boolean statifies = true;
			for (Test test : c.getTests()) {
				String content = new Console(assignmentOutput.getFileName()).getConsoleOutput(test.input);
				if (!test.complies(content)) {
					statifies = false;
				}
			}
			evaluations.add(new Evaluation(c, statifies));
		}
		
		return evaluations;
	}
	
	
	

	/*
	 * Opdracht 2_3 - Geheel getal Is het getal groter dan 10? Is het
	 * ongelijk aan 50? Is het getal groter dan 1 en kleiner dan 9? Is het
	 * getal deelbaar door 3 of 4?
	 */
	private Criterion[] getCriteria() {
		Criterion c1 = new Criterion();
		c1.points = 2;
		c1.description = "Groter dan 10";
		c1.addTest(new Test("10", "Het getal is groter dan 10", false));
		c1.addTest(new Test("11", "Het getal is groter dan 10", true));

		Criterion c2 = new Criterion();
		c2.points = 2;
		c2.description = "Ongelijk aan 50";
		c2.addTest(new Test("51", "Het getal is niet gelijk aan 50", true));
		c2.addTest(new Test("50", "Het getal is niet gelijk aan 50", false));

		Criterion c3 = new Criterion();
		c3.points = 2;
		c3.description = "Groter dan 1 en kleiner dan 9";
		c3.addTest(new Test("1", "Het getal is groter dan 1 en kleiner dan 9", false));
		c3.addTest(new Test("2", "Het getal is groter dan 1 en kleiner dan 9", true));
		c3.addTest(new Test("8", "Het getal is groter dan 1 en kleiner dan 9", true));
		c3.addTest(new Test("9", "Het getal is groter dan 1 en kleiner dan 9", false));

		// INPUT % 3 == 0 || INPUT % 4 == 0 #Het getal is deelbaar door 3 of 4
		// Input i9 = new Input("3","Het getal is deelbaar door 3 of 4.",true);
		// Input i10 = new Input("4","Het getal is deelbaar door 3 of 4",true);
		Criterion c4 = new Criterion();
		c4.points = 2;
		c4.description = "deelbaar door 3 of 4";
		c4.addTest(new Test("5", "Het getal is deelbaar door 3 of 4", false));
		c4.addTest(new Test("7", "Het getal is deelbaar door 3 of 4", false));
		c4.addTest(new Test("3", "Het getal is deelbaar door 3 of 4", true));
		c4.addTest(new Test("4", "Het getal is deelbaar door 3 of 4", true));
		c4.addTest(new Test("12", "Het getal is deelbaar door 3 of 4", true));

		Criterion[] criteria = { c1, c2, c3, c4 };
		return criteria;
	}

	public boolean asksForConsoleInput(){
		String content = new Console(assignmentOutput.getFileName()).getConsoleOutput("");
		return content.contains("Unable to execute method");
	}
	
}

