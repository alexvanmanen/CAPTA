package nl.alexvanmanen.capta.model;

import com.github.javaparser.ast.expr.BinaryExpr;

public class Exp {
	String left;
	BinaryExpr.Operator operator;
	String right;
	int points; 
	boolean correct;
	
	Exp (String left, BinaryExpr.Operator operator, String right, int points){
		this.left = left;
		this.operator = operator;
		this.right = right;
		this.points = points;
	}
	
	public Exp (String left, String operator, String right, int points){
		this.left = left;
		this.operator = getOperator(operator);
		this.right = right;
		this.points = points;
	}
	
	public void isCorrect(){
		correct = true;
	}
	public String toString(){
		return left + " " + operator + " " + right + " - " + points + " " + correct;
	}
	
	private BinaryExpr.Operator getOperator(String operator){
		switch(operator){
			case "!=" : return BinaryExpr.Operator.NOT_EQUALS;
			case "==" : return BinaryExpr.Operator.EQUALS;
			case ">"  : return BinaryExpr.Operator.GREATER;
			case ">=" : return BinaryExpr.Operator.GREATER_EQUALS;
			case "<"  : return BinaryExpr.Operator.LESS;
			case "<=" : return BinaryExpr.Operator.LESS_EQUALS;
			case "%"  : return BinaryExpr.Operator.REMAINDER;
			default: return null;
		}
	}
	
	public boolean isEqual(BinaryExpr binaryExpr){
		return binaryExpr.getOperator().equals(this.operator) && binaryExpr.getRight().toString().equalsIgnoreCase(this.right);
		//return binaryExpr.getLeft().toString().equalsIgnoreCase(this.left) && binaryExpr.getOperator().equals(this.operator) && binaryExpr.getRight().toString().equalsIgnoreCase(this.right);
	}
}
