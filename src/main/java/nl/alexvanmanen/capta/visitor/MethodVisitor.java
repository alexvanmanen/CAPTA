package nl.alexvanmanen.capta.visitor;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Simple visitor implementation for visiting MethodDeclaration nodes.
 */
public class MethodVisitor extends AbstractVisitor {
	private String methodName;
	private String className;

	public MethodVisitor(String methodName, String className) {
		this.methodName = methodName;
		this.className = className;
	}

	@Override
	public void visit(MethodDeclaration n, Void arg) {
		found = n.getNameAsString().equalsIgnoreCase(methodName);
		super.visit(n, arg);
	}

}
