package nl.alexvanmanen.capta.visitor;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Simple visitor implementation for visiting MethodDeclaration nodes.
 */
public class VariableVisitor extends AbstractVisitor {
	String name;
	String type;

	public VariableVisitor(String type, String name) {
		this.name = name;
		this.type = type;
	}

	@Override
	public void visit(VariableDeclarator n, Void arg) {
		/*
		 * here you can access the attributes of the method. this method will be
		 * called for all methods in this CompilationUnit, including inner class
		 * methods
		 */
		found = isVariableDeclared(n, type, name);
		super.visit(n, arg);
	}

	public boolean isVariableDeclared(VariableDeclarator n, String type, String name) {
		return n.getName().toString().equalsIgnoreCase(name) && n.getType().asString().equalsIgnoreCase(type);
	}

}