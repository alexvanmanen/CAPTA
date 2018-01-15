package nl.alexvanmanen.capta.visitor;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

/**
 * Simple visitor implementation for visiting ClassOrInterfaceDeclaration nodes.
 */
public class ClassExtendsVisitor extends AbstractVisitor {
	private String extendsFrom;
	private String className;

	public ClassExtendsVisitor(String className, String extendsFrom) {
		this.extendsFrom = extendsFrom;
		this.className = className;
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Void arg) {

		found = n.getNameAsString().contains(className) && doesExtendsFrom(n, extendsFrom);
		super.visit(n, arg);
	}

	private boolean doesExtendsFrom(ClassOrInterfaceDeclaration n, String extendsFrom) {
		NodeList<ClassOrInterfaceType> extendedTypes = n.getExtendedTypes();
		for (ClassOrInterfaceType type : extendedTypes) {
			if (type.getNameAsString().contains(extendsFrom)) {
				return true;
			}
		}
		return false;
	}

}
