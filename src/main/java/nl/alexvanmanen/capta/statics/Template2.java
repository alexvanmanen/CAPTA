package nl.alexvanmanen.capta.statics;

import java.util.List;

import nl.alexvanmanen.capta.Evaluator;
import nl.alexvanmanen.capta.helper.ADLReader;
import nl.alexvanmanen.capta.model.Assignment;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.model.Exp;

public class Template2 extends nl.alexvanmanen.capta.Template2 {

	private String adlFile;
	private AssignmentOutput assignmentOutput;

	public Template2(String adlFile, AssignmentOutput assignmentOutput) {
		this.adlFile = adlFile;
		this.assignmentOutput = assignmentOutput;
	}

	public Evaluations evaluate() {
		ADLReader reader = new ADLReader(adlFile);
		List<Exp> list = reader.readFile();

		Assignment assignment = generateAssignment(list);

		Evaluations actual = new Evaluator().evaluate(assignment, assignmentOutput);
		return actual;
	}

}
