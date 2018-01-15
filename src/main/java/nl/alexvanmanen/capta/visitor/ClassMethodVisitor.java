package nl.alexvanmanen.capta.visitor;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

/**
 * Simple visitor implementation for visiting ClassOrInterfaceDeclaration nodes.
 */
public class ClassMethodVisitor extends AbstractVisitor {
	private String methodName;
	private String className;

	public ClassMethodVisitor(String methodName, String className) {
		this.methodName = methodName;
		this.className = className;
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Void arg) {
		found = n.getNameAsString().equalsIgnoreCase(className) && !n.getMethodsByName(methodName).isEmpty();
		super.visit(n, arg);
	}
}
