package nl.alexvanmanen.capta.reflection;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestJavaClassLoader extends TestCase {

	public void testLoad(){
		String className = "nl.alexvanmanen.capta.reflection.JavaClassLoader";
		String directory = "/Users/alexvanmanen/Git/CAPTA/target/classes/nl/alexvanmanen/capta/reflection";
		Class cls = new JavaClassLoader().load(directory, className);
		Assert.assertEquals(className,cls.getName());   
	}
	
	public void testGetClasses() throws ClassNotFoundException{
		List<Class> classes = new JavaClassLoader().getClasses("/Users/alexvanmanen/Git/CAPTA/target/classes/", "nl.alexvanmanen.capta.reflection.JavaClassLoader");
		for(Class c: classes){
			System.out.println(c.getName());
		}
	}
}
