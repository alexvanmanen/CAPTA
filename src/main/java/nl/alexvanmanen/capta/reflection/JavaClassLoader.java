package nl.alexvanmanen.capta.reflection;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class JavaClassLoader  {


	public Class load(String directory, String className){
		try {
			File file = new File(directory);
		    URL url = file.toURL();          
		    URL[] urls = new URL[]{url};
		    ClassLoader cl = new URLClassLoader(urls);
		    Class cls = cl.loadClass(className);
		    return cls;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Class> getClasses() throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		String directory = "/Users/alexvanmanen/Documents/workspace/MasterThesis/bin/opdracht1/";
		File root = new File(directory);
		File[] files = root.listFiles();
		
		directory = directory.replace("opdracht1/", "");

		for (File f : files) {
			if (f.isFile() && f.getName().endsWith(".class")) {
				String className = f.getName().replaceAll(".class", "");
				className = "opdracht1." + className;
				Class c = load(directory, className);
				classes.add(c);
				System.out.println("There is: " + c);
			}
		}
		return classes;
	}

}