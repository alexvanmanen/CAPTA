package nl.alexvanmanen.capta.visitor;

import java.util.Set;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

/**
 * Simple visitor implementation for visiting MethodDeclaration nodes.
 */
public class DecoratorExecutionVisitor extends AbstractVisitor {
	Set<String> concreteDecorators;
	String concreteComponent;

	public DecoratorExecutionVisitor(Set<String> concreteDecorators, String concreteComponent) {
		this.concreteComponent = concreteComponent;
		this.concreteDecorators = concreteDecorators;
	}

	public void visit(ObjectCreationExpr n, Void arg) {
		boolean concreteDecoratorsInitialized = false;

		concreteDecoratorsInitialized = concreteDecoratorsInitialized(n);

		for (Node node : n.getChildNodes()) {
			
			for(String concreteDecorator: concreteDecorators) {
				if(concreteDecoratorsInitialized && node.toString().contains(concreteDecorator) && node.toString().contains(concreteComponent)){
					found = true;
				}
			}
		}
	}

	private boolean concreteDecoratorsInitialized(ObjectCreationExpr n) {
		int i = concreteDecorators.size();
		for (Node node : n.getChildNodes()) {
			
			for(String concreteDecorator: concreteDecorators){
				if(node.toString().contains(concreteDecorator)){
					i--;
				}
			}
		}
		return i <= 1;
	}

}