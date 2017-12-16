package nl.alexvanmanen.capta.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class AssignmentOutput {

	private CompilationUnit compilationUnit;
	
	
	public AssignmentOutput(String fileName){
		FileInputStream in;
		try {
			in = new FileInputStream(fileName);
			compilationUnit = JavaParser.parse(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CompilationUnit getCompilationUnit(){
		return compilationUnit;
	}
	
	
}
