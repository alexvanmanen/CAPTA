package nl.alexvanmanen.capta;

import java.io.FileNotFoundException;

import junit.framework.TestCase;

public class TestTemplate1Statically extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test() throws FileNotFoundException {

		String fileName = "./cases/assignments/Hello.java";

		Template1Statically template1Statically = new Template1Statically(fileName);
		String className = "Hello";
		String methodName = "main";
		template1Statically.setSignature(className, methodName);
		template1Statically.setVariable("String", "name");
		template1Statically.setWhatIsBeingPrinted("Hello");
		
		String result = template1Statically.evaluate();
		System.out.println(result);
		
	}
}
