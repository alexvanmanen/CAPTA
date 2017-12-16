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
			StringBuffer stringBuffer = new StringBuffer();
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
}
