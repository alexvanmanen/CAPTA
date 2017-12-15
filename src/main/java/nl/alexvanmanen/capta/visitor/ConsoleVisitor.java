package nl.alexvanmanen.capta.visitor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Simple visitor implementation for visiting MethodDeclaration nodes.
 */
public class ConsoleVisitor extends AbstractVisitor {
	String printed;

	public ConsoleVisitor(String printed) {
		this.printed = printed;
	}

	public void visit(MethodCallExpr n, Void arg) {
		found = doesSystemOutPrintlnContainsText(n, printed);
	}

	public boolean doesSystemOutPrintlnContainsText(MethodCallExpr n, String text) {
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;

		for (Node node : n.getChildNodes()) {
			if ("System.out".equalsIgnoreCase(node.toString())) {
				b1 = true;
			}
			if ("println".equalsIgnoreCase(node.toString()) || "print".equalsIgnoreCase(node.toString())) {
				b2 = true;
			}

			if (node.toString().contains(text)) {
				b3 = true;
			}

		}
		return b1 && b2 && b3;

	}

}