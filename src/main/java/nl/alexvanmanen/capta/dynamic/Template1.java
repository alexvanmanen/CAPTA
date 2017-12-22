package nl.alexvanmanen.capta.dynamic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nl.alexvanmanen.capta.model.Criteria;
import nl.alexvanmanen.capta.reflection.JavaClassLoader;

public class Template1 {

	public static void main(String[] args) throws ClassNotFoundException {
		String result = new Template1().start();
		System.out.println(result);

	}

	public String start() {

		String feedback = "";

		Criteria criteria1 = new Criteria();
		criteria1.description = "+1 There is a class Hello\n";
		criteria1.points = 1;

		Criteria criteria2 = new Criteria();
		criteria2.description = "+1 There is a method main\n";
		criteria2.points = 1;
		
		Criteria criteria3 = new Criteria();
		criteria3.description = "+3 Hello is being printed\n";
		criteria3.points = 3;
		
		List<Criteria> list = new ArrayList<Criteria>();
		

		JavaClassLoader jcl = new JavaClassLoader();

		
		try {
			List<Class> classes = jcl.getClasses("./cases/assignments/", "assignments");
			for (Class c : classes) {
				if (c.getName().contains("assignments.Hello")) {
					list.add(criteria1);
					Method main = new MethodsRetriever().getMethod(c, "main");
					if (main != null) {
						list.add(criteria2);

						MethodExecutor methodExecutor = new MethodExecutor(c, main);
						String consoleOutput = new TestHelper().getConsoleOutput(methodExecutor);

						if (consoleOutput.contains("Hello")) {
							list.add(criteria3);
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		for (Criteria s : list) {
			feedback += s.description;
		}
		return feedback;
	}
}
