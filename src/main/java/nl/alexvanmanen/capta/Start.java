package nl.alexvanmanen.capta;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluation;
import nl.alexvanmanen.capta.statics.Template2;

public class Start {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, NoSuchMethodException, SecurityException, IOException {

		exampleTemplate1();

		exampleTemplate2();

	}

	private static void exampleDynamicTemplate2() {
		nl.alexvanmanen.capta.dynamic.Template2.main(null);
	}

	public static void exampleTemplate2() {
		exampleStaticTemplate2();
		exampleDynamicTemplate2();
	}

	private static void exampleStaticTemplate2() {
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/assignments/Math.java");
		Template2 template = new Template2("./cases/test.adl", assignmentOutput);

		for (Evaluation evaluation : template.evaluate()) {
			System.out.println(evaluation.criteria.description);
		}
	}

	public static void exampleTemplate1() {
		exampleStaticTemplate1();
		exampleDynamicTemplate1();

	}

	public static void exampleStaticTemplate1() {
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/assignments/Hello.java");
		nl.alexvanmanen.capta.statics.Template1 template1Statically = new nl.alexvanmanen.capta.statics.Template1(
				assignmentOutput);
		String className = "Hello";
		String methodName = "main";
		template1Statically.setSignature(className, methodName);
		template1Statically.setVariable("String", "name");
		template1Statically.setWhatIsBeingPrinted("Hello");

		String result = template1Statically.evaluate();
		System.out.println(result);
	}

	public static void exampleDynamicTemplate1() {
		nl.alexvanmanen.capta.dynamic.Template1 template1Statically = new nl.alexvanmanen.capta.dynamic.Template1();
		String result = template1Statically.start();
		System.out.println(result);
	}

}
