package nl.alexvanmanen.capta.dynamic;
import java.lang.reflect.InvocationTargetException;

public class Console {

	public String getConsoleOutput(String input)  {
		String content = "";
		try {
			for (MethodExecutor me : new MethodsRetriever().getExecutors()) {
				content += new TestHelper().getConsoleOutput(me, input);
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return content;
	}
}
