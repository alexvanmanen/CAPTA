package nl.alexvanmanen.capta.visitor;

import com.github.javaparser.ast.expr.BinaryExpr;

import nl.alexvanmanen.capta.model.Exp;

/**
 * Simple visitor implementation for visiting MethodDeclaration nodes.
 */
public class BinaryExpressionVisitor extends AbstractVisitor {

	Exp expression;

	public BinaryExpressionVisitor(Exp expression){
		this.expression = expression;
	}
	
	
	@Override
	public void visit(BinaryExpr binaryExpr, Void arg) {
		if (expression.isEqual(binaryExpr)) {
			found = true;
		}
		super.visit(binaryExpr, arg);
	}

}