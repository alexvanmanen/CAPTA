package nl.alexvanmanen.capta;

import java.io.File;

import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.model.Evaluations;
import nl.alexvanmanen.capta.statics.Template2;

public class Start {

	private String workingDirectory = "/Users/alexvanmanen/Documents/IOPR1/1e_kans/werk_studenten/";

	public void allDynamicTemplate2() {
		File f = new File(workingDirectory);
		for (String studentDirectory : f.list()) {
			if(!studentDirectory.contains(".DS_Store")){
				dynamicEvaluateTemplate2ForStudent(studentDirectory);
			}
			
		}
	}
	
	public static void main(String[] args) {

		// exampleTemplate1();

		// exampleStaticTemplate1();

		Start s = new Start();
		// s.exampleStaticTemplate2();
		// s.exampleDynamicTemplate2();
		s.allDynamicTemplate2();
		
		//s.dynamicEvaluateTemplate2ForStudent("S1106501");

		
		//s.evaluateTemplate2ForStudent("static", adl, studentDirectory);
		
		

		// s.exampleTemplate2();
		// s.exampleDynamicTemplate2();
		// s.allDecoratorTemplate("dynamic");
		// s.allTemplate1("static");
		// s.exampleStaticTemplateDecorator();
		// s.evaluateTemplateDecoratorForStudent("static", "Mitch");
		// s.evaluateTemplateDecoratorForStudent("dynamic", "Mitch");
		// s.evaluateTemplate1ForStudent(type, studentDirectory);

		// s.exampleTemplate2();
		// s.allStaticTemplate1();

		// s.allDynamicTemplate1();
		// s.evaluateTemplate1ForStudent(array, template)
		// s.evaluateDynamicTemplateForStudent("s1106756");

		// s.evaluateTemplate1ForStudent("dynamic", "s1107231");
		// s.exampleStaticTemplate1();

	}

	private Evaluations evaluateDecoratorTemplateForStudent(nl.alexvanmanen.capta.DecoratorTemplate template)
			throws NoAssignmentDefinedException {

		template.setComponent("Pizza", "getPrice");
		template.addConcreteComponent("Classic");
		template.addConcreteComponent("FreshPan");
		template.addConcreteComponent("CheesyCrust");
		template.addConcreteComponent("Italian");

		template.setDecorator("ToppingDecorator");
		template.addConcreteDecarator("Ananas");
		template.addConcreteDecarator("Ham");
		template.addConcreteDecarator("Champignons");
		template.addExecutions("Classic", "Ham", "Ananas");
		template.addExecutions("Italian", "Champignons", "Champignons");

		return template.evaluate();

	}

	private void dynamicEvaluateTemplate2ForStudent(String studentnr) {
		AssignmentOutput assignmentOutput = new AssignmentOutput(
				"/Users/alexvanmanen/Documents/IOPR1/1e_kans/werk_studenten/" + studentnr
						+ "/mnt/userspace/oplevering/Opdracht2/bin");
		nl.alexvanmanen.capta.dynamic.Template2 template = new nl.alexvanmanen.capta.dynamic.Template2(
				"./cases/test.adl", assignmentOutput);
		Evaluations evaluations = template.evaluate();
		System.out.print("studentnr:" + studentnr);
		System.out.println(" - total points: " + evaluations.getTotalPoints());
		evaluations.print();

	}

	private void dynamicEvaluateTemplate1ForStudent(String studentnr) {
		AssignmentOutput assignmentOutput = new AssignmentOutput(
				"/Users/alexvanmanen/Documents/IOPR1/1e_kans/werk_studenten/" + studentnr
						+ "/mnt/userspace/oplevering/Opdracht1/bin");
		nl.alexvanmanen.capta.dynamic.Template1 template = new nl.alexvanmanen.capta.dynamic.Template1(
				assignmentOutput);
		Evaluations evaluations = template.evaluate();
		System.out.println("total points: " + evaluations.getTotalPoints());
		evaluations.print();

	}

	public void exampleTemplate1() {
		exampleStaticTemplate1();
		exampleDynamicTemplate1();

	}

	public void exampleStaticTemplate1() {
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");

		nl.alexvanmanen.capta.statics.Template1 template1Statically = new nl.alexvanmanen.capta.statics.Template1(
				assignmentOutput);
		String className = "Hello";
		String methodName = "main";
		template1Statically.setSignature(className, methodName);
		template1Statically.setVariable("String", "name");
		template1Statically.setWhatIsBeingPrinted("naam");

		Evaluations evaluations = template1Statically.evaluate();
		evaluations.print();
	}

	

	public void exampleDynamicTemplate1() {
		AssignmentOutput assignmentOutput = new AssignmentOutput("./cases/");
		nl.alexvanmanen.capta.dynamic.Template1 template1Statically = new nl.alexvanmanen.capta.dynamic.Template1(
				assignmentOutput);
		Evaluations evaluations = template1Statically.evaluate();
		evaluations.print();
	}

	public void allTemplate1(String type) {
		File f = new File(workingDirectory);
		for (String studentDirectory : f.list()) {
			evaluateTemplate1ForStudent(type, studentDirectory);
		}
	}

	private void evaluateTemplate1ForStudent(String type, String studentDirectory) {
		String workingDirectoryPart2 = "/mnt/userspace/oplevering/Opdracht1/";
		if (!studentDirectory.contentEquals(".DS_Store")) {
			System.out.print(studentDirectory);
			Evaluations evaluations;
			AssignmentOutput assignmentOutput;
			try {
				String dir = workingDirectory + studentDirectory + workingDirectoryPart2;
				nl.alexvanmanen.capta.Template1 template1;
				if (type.equalsIgnoreCase("static")) {
					assignmentOutput = new AssignmentOutput(dir + "src/");
					template1 = new nl.alexvanmanen.capta.statics.Template1(assignmentOutput);
				} else {
					assignmentOutput = new AssignmentOutput(dir + "bin/");
					template1 = new nl.alexvanmanen.capta.dynamic.Template1(assignmentOutput);
				}
				evaluations = evaluateTemplate1ForStudent(getRightAssignment(studentDirectory), template1);
				System.out.print(" - " + evaluations.getTotalPoints());
				evaluations.print();
			} catch (NoAssignmentDefinedException e) {
				System.out.println(" - No assignment defined");
			}

			System.out.println();

		}
	}

	private void evaluateTemplate2ForStudent(String type, String adl, String studentDirectory) {
		String workingDirectoryPart2 = "/mnt/userspace/oplevering/Opdracht2/";
		if (!studentDirectory.contentEquals(".DS_Store")) {
			System.out.print(studentDirectory);
			Evaluations evaluations;
			AssignmentOutput assignmentOutput;

			String dir = workingDirectory + studentDirectory + workingDirectoryPart2;
			nl.alexvanmanen.capta.Template2 template;
			if (type.equalsIgnoreCase("static")) {
				assignmentOutput = new AssignmentOutput(dir + "src/");
				template = new nl.alexvanmanen.capta.statics.Template2(adl, assignmentOutput);
			} else {
				assignmentOutput = new AssignmentOutput(dir + "bin/");
				template = new nl.alexvanmanen.capta.dynamic.Template2(adl, assignmentOutput);
			}
			evaluations = template.evaluate();
			System.out.print(" - " + evaluations.getTotalPoints());
			evaluations.print();

			System.out.println();

		}
	}

	public void allDecoratorTemplate(String type) {
		File f = new File("/Users/alexvanmanen/Documents/idepa_thesis/unzipped/");
		for (String studentDirectory : f.list()) {
			evaluateTemplateDecoratorForStudent(type, studentDirectory);
		}
	}

	private void evaluateTemplateDecoratorForStudent(String type, String studentDirectory) {
		String workingDirectoryPart2 = "/Users/alexvanmanen/Documents/idepa_thesis/unzipped/";
		if (!studentDirectory.contentEquals(".DS_Store")) {
			System.out.print(studentDirectory);
			Evaluations evaluations;
			AssignmentOutput assignmentOutput;
			try {
				String dir = workingDirectoryPart2 + studentDirectory + "/";
				nl.alexvanmanen.capta.DecoratorTemplate template;
				if (type.equalsIgnoreCase("static")) {
					assignmentOutput = new AssignmentOutput(dir);
					template = new nl.alexvanmanen.capta.statics.DecoratorTemplateStatic(assignmentOutput);
				} else {
					assignmentOutput = new AssignmentOutput(dir);
					template = new nl.alexvanmanen.capta.dynamic.DecoratorTemplate(assignmentOutput);
				}
				evaluations = evaluateDecoratorTemplateForStudent(template);
				System.out.print(" - " + evaluations.getTotalPoints());
				evaluations.print();
			} catch (NoAssignmentDefinedException e) {
				System.out.println(" - No assignment defined");
			}

			System.out.println();

		}
	}

	private Evaluations evaluateTemplate1ForStudent(String[] array, nl.alexvanmanen.capta.Template1 template) {
		nl.alexvanmanen.capta.Template1 template1 = template;
		String className = array[0];
		String methodName = "main";
		template1.setSignature(className, methodName);
		template1.setVariable("String", array[2]);
		template1.setWhatIsBeingPrinted(array[1]);
		return template1.evaluate();

	}

	private int getAssignmentNumber(String studentnr, String opdracht) throws NoAssignmentDefinedException {
		File f = new File(workingDirectory + studentnr + "/mnt/userspace/oplevering/opdrachten/" + opdracht);
		String fileName = f.list()[0];
		if (fileName.startsWith(opdracht + "_") && fileName.endsWith(".pdf")) {
			return Integer.parseInt(fileName.substring(10, 11));
		} else {
			throw new NoAssignmentDefinedException();
		}

	}

	private String[] getRightAssignment(String studentnr) throws NoAssignmentDefinedException {
		String[] array = new String[3];
		switch (getAssignmentNumber(studentnr, "Opdracht1")) {
		case 1:
			array[0] = "Hallo";
			array[1] = "Hallo";
			array[2] = "naam";
			break;
		case 2:
			array[0] = "GoedeMorgen";
			array[1] = "Goedemorgen";
			array[2] = "naam";
			break;
		case 3:
			array[0] = "Goodbye";
			array[1] = "Goodbye";
			array[2] = "naam";
			break;
		}
		return array;

	}
}
