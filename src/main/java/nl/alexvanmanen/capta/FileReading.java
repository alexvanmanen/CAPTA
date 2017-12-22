package nl.alexvanmanen.capta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReading {

	
	public List<String> readFile(String fileName){
		List<String> list = new ArrayList<String>();
		try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				list.add(line);
			}
			fileReader.close();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> readDirectory(String directoryName){
		List<String> list = new ArrayList<String>();
		File directory = new File(directoryName);
		
		File[] listOfFiles = directory.listFiles();

		for (File file : listOfFiles) {
			if (file.isDirectory()) {
				list.add(file.getName());
			}
		}

		return list;
	}
}
