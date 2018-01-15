package nl.alexvanmanen.capta.dynamic;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

public class TestHelper {

	
	public String getConsoleOutput(MethodExecutor execute, String input)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		PrintStream stdout = System.out;
		InputStream stdin = System.in;

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(byteArrayInputStream);
		System.setOut(new PrintStream(byteArrayOutputStream));
		
		execute.execute();

		String content = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
		System.setOut(stdout);
		System.setIn(stdin);
		if(execute.getError() != null){
			return execute.getError();
		} else {
			return content;
		}
		
	}
	
	public String getConsoleOutput(MethodExecutor execute) {
		PrintStream stdout = System.out;

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		System.setOut(new PrintStream(byteArrayOutputStream));
		execute.execute();

		String content = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
		System.setOut(stdout);

		return content;
	}
	
	
	
	

}
