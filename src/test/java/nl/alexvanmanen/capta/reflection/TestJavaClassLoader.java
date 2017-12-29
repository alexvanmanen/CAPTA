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
	
	public void testGetClasses2() throws ClassNotFoundException{
		boolean actual = false;
		List<Class> classes = new JavaClassLoader().getClasses("/Users/alexvanmanen/Git/CAPTA/target/classes/");
		for(Class c: classes){
			if(c.getName().equalsIgnoreCase("nl.alexvanmanen.capta.reflection.JavaClassLoader")){
				actual = true;
			}
		}
		assertTrue("There is not a class JavaClassLoader", actual);
	}
}
