package nl.alexvanmanen.capta.reflection;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestJavaClassLoader extends TestCase {

	public void testLoad(){
		String className = "opdracht1.GoedeMorgen";
		String directory = "/Users/alexvanmanen/Documents/workspace/MasterThesis/bin/";
		Class cls = new JavaClassLoader().load(directory, className);
		Assert.assertEquals(className,cls.getName());   
	}
}
