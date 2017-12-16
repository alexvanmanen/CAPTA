package nl.alexvanmanen.capta.dynamic;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodExecutor{
	private Class<?> classs;
	private Method method;
	
	public MethodExecutor(Class classs, Method method) {
		this.classs = classs;
		this.method = method;
	}

	
	public void execute() {
	    try {
	    	String[] params = null; 
			method.invoke(null, (Object) params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} 
	}
	
}