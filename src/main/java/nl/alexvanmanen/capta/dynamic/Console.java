package nl.alexvanmanen.capta.dynamic;
import java.lang.reflect.InvocationTargetException;

public class Console {
	private String path;
	
	public Console(String path){
		this.path = path;
	}
	
	public String getConsoleOutput(String input)  {
		String content = "";
		try {
			for (MethodExecutor me : new MethodsRetriever().getExecutors(path)) {
				content += new TestHelper().getConsoleOutput(me, input);
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
}
