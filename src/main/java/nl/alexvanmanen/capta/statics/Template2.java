package nl.alexvanmanen.capta.statics;


import java.util.List;

import nl.alexvanmanen.capta.Evaluator;
import nl.alexvanmanen.capta.helper.ADLReader;
import nl.alexvanmanen.capta.model.Assignment;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Criteria;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.model.Exp;
import nl.alexvanmanen.capta.visitor.BinaryExpressionVisitor;

public class Template2 {

	private String adlFile;
	private AssignmentOutput assignmentOutput;

	public Template2(String adlFile, AssignmentOutput assignmentOutput) {
		this.adlFile = adlFile;
		this.assignmentOutput = assignmentOutput;
	}

	public Evaluations evaluate() {
			ADLReader reader = new ADLReader();
			List<Exp> list = reader.readFile(adlFile);

			Assignment assignment = new Assignment();
			for(Exp exp: list){
				Criteria criteria = new Criteria();

				criteria.visitor = new BinaryExpressionVisitor(exp);
				criteria.description = "There is a expression " + exp+ "\n";
				criteria.points = 2;
				assignment.add(criteria);
			}
			
			Evaluations actual = new Evaluator().evaluate(assignment, assignmentOutput);
			return actual;
		}
}
