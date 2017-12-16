package nl.alexvanmanen.capta.visitor;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public abstract class AbstractVisitor extends VoidVisitorAdapter<Void> {

	protected boolean found;
	
	public boolean isFound() {
		return found;
	}
	

}
