package nl.alexvanmanen.capta.reflection;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class JavaClassLoader  {


	public Class load(String directory, String className) {
		try {
			File file = new File(directory);
			URL url = file.toURL();
			URL[] urls = new URL[] { url };
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
	
	public List<Class> getClasses(String directory, String classpath) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		
		File root = new File(directory);
		File[] files = root.listFiles();
		
		directory = directory.replace(classpath+"/", "");

		for (File f : files) {
			if (f.isFile() && f.getName().endsWith(".class")) {
				String className = f.getName().replaceAll(".class", "");
				className = classpath+"." + className;
				Class c = load(directory, className);
				classes.add(c);
			}
		}
		return classes;
	}
	

	
	public List<Class> getClasses(String directory){
		List<Class> classes = new ArrayList<Class>();
		for(String className: retrieveClassFiles(directory, "")){
			Class c = load(directory, className);
			classes.add(c);
		}
		return classes;
	}
	
	private List<String> retrieveClassFiles(String directory, String classpath){
		List<String> result = new ArrayList<String>();
		File root = new File(directory);

		for (File f : root.listFiles()) {
			if (f.isFile() && f.getName().endsWith(".class")) {
				String className = f.getName().replaceAll(".class", "");
				result.add(classpath+className);
			} else if(f.isDirectory()){
				result.addAll(retrieveClassFiles(f.getAbsolutePath(), classpath+f.getName()+"."));
			}
		}
		return result;
	}

}