package nl.alexvanmanen.capta.dynamic;
import java.lang.reflect.InvocationTargetException;

public class Console {

	public String getConsoleOutput(String input) throws ClassNotFoundException, NoSuchMethodException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		String content = "";
		for (MethodExecutor me : new MethodsRetriever().getExecutors()) {
			content += new TestHelper().getConsoleOutput(me, input);
		}
		return content;
	}
}
