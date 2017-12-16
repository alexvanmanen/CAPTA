package nl.alexvanmanen.capta.dynamic;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodsRetriever {
	
	public List<MethodExecutor> getExecutors() throws ClassNotFoundException, NoSuchMethodException, SecurityException{
		List<MethodExecutor> list = new ArrayList<MethodExecutor>();
		Class classs = Class.forName("assignments.Math");
		Method method = classs.getMethod("main", String[].class);
		list.add(new MethodExecutor(classs, method));
		return list;
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