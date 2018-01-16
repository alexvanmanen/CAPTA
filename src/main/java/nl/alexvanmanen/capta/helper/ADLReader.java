package nl.alexvanmanen.capta.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import nl.alexvanmanen.capta.FileReading;
import nl.alexvanmanen.capta.model.Exp;

public class ADLReader {

	private String fileName;
	
	public ADLReader(String fileName){
		this.fileName = fileName;
	}
	
	public List<Exp> readFile() {
		FileReading fr = new FileReading();
		List<Exp> list = new ArrayList<Exp>();
		for (String s : fr.readFile(fileName)) {
			String array[] = s.split(" ");
			Exp e = new Exp(array[0], array[1], array[2], 1);
			list.add(e);
		}
		return list;
	}

	public String getValue(String key) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(fileName);
			prop.load(input);

			return prop.getProperty(key).replace("\"", "");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}
	
	public List<String> getValues(String key){
		List<String> result = new ArrayList<String>();
		String values = getValue(key);
		for(String value: values.split(",")){
			result.add(value.trim());
		}
		return result;
	}
	
	public List<List<String>> getListOfValues(String key){
		 List<List<String>> result = new ArrayList<List<String>>();
		String list = getValue(key);
		for (String values: list.split(";")) {
			List<String> subResult = new ArrayList<String>();
			for (String value: values.split(",")) {
				subResult.add(value);
			}
			result.add(subResult);
		}
		return result;
	}

}
