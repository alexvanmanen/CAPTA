package nl.alexvanmanen.capta.dynamic;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nl.alexvanmanen.capta.reflection.JavaClassLoader;

public class MethodsRetriever {
	
	public List<MethodExecutor> getExecutors() throws ClassNotFoundException, NoSuchMethodException, SecurityException{
		List<MethodExecutor> list = new ArrayList<MethodExecutor>();
		JavaClassLoader jcl = new JavaClassLoader();

		List<Class> classes = jcl.getClasses("./cases/assignments/", "assignments");
		for (Class c : classes) {
			if(c.getCanonicalName().equalsIgnoreCase("assignments.Math")){
				Method method = c.getMethod("main", String[].class);
				list.add(new MethodExecutor(c, method));
				
			}
		}
		return list;
		//Class classs = Class.forName("assignments.Math");
	}
	
	public Method getMethod(Class clazz, String methodName) {
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for(Method method: declaredMethods){
			
			if(method.getName().equalsIgnoreCase(methodName)){
				return method;
			}	
		}
		return null;
	}
}