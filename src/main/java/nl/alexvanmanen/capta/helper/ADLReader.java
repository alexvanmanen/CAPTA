package nl.alexvanmanen.capta.helper;

import java.util.ArrayList;
import java.util.List;

import nl.alexvanmanen.capta.FileReading;
import nl.alexvanmanen.capta.model.Exp;

public class ADLReader {

	public List<Exp> readFile(String fileName) {
		FileReading fr = new FileReading();
		List<Exp> list = new ArrayList<Exp>();
		for(String s: fr.readFile(fileName)){
			String array[] = s.split(" ");
			Exp e = new Exp(array[0], array[1], array[2], 1);
			list.add(e);
		}
		return list;
	}
}
