package nl.alexvanmanen.capta.reflection;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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

}