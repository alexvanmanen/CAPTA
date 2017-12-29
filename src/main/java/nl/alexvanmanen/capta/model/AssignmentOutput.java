package nl.alexvanmanen.capta.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;

import nl.alexvanmanen.capta.reflection.JavaClassLoader;

public class AssignmentOutput {

	private List<CompilationUnit> compilationUnits = new ArrayList<CompilationUnit>();
	private List<Class> classes;

	
	public AssignmentOutput(String fileName){
		retrieveJavaFiles(new File(fileName));
		retrieveClassFiles(fileName);
	}
	

	public void retrieveClassFiles(String fileName){
		JavaClassLoader jcl = new JavaClassLoader();
		
		classes = jcl.getClasses(fileName);
		
	}

	private void retrieveJavaFiles(File file) {
		for (File subFile : file.listFiles()) {
			//System.out.println("subfile: " + subFile.getPath().endsWith(".java") +" - " + subFile.getPath());
			try {
				addCompilationUnit(subFile);
			} catch (FileNotFoundException e) {
				try {
					retrieveJavaFiles(subFile);
				} catch (java.lang.NullPointerException ex) {
					//Not a directory
				}

			}
		}
	}
	
	private void addCompilationUnit(File file) throws FileNotFoundException{
		FileInputStream in;

		try {
			in = new FileInputStream(file);
			compilationUnits.add(JavaParser.parse(in));
		}  catch (ParseProblemException e){
			//Unable to parse. That's no problem.
		}
	}
	
	
	public List<CompilationUnit> getCompilationUnits(){
		return compilationUnits;
	}
	
	public List<Class> getClassFiles(){
		return classes;
	}
	
	
}
