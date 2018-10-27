import java.io.*;
import java.util.*;

public class FileHandler {

	private FileHandler() {
		// Never gets called
	}

	public static Map<String, Integer> readFile() {
		Map<String, Integer> inventory = new TreeMap<>();
		try {
			BufferedReader inputFile = new BufferedReader(new FileReader("data/inventory.txt"));
			String line = inputFile.readLine();
			while (line != null) {
				int posOfSpace = line.indexOf(' ');
				
				String item = line.substring(0, posOfSpace);
				String value = line.substring(posOfSpace + 1);
				int trueValue = Integer.parseInt(value);
				
				inventory.put(item, trueValue);
				
				line = inputFile.readLine();
			}
			inputFile.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File not found.");
		} catch (IOException e) {
			System.err.println("ERROR: File input could not be read.");
		}
		
		return inventory;
		
	}

	public static void writeHTML(Map<String, Integer> inventory) {
		// Write to an HTML table
		
	}

	public static void writeInventoryFile(Map<String, Integer> inventory) {
		// TODO Auto-generated method stub
		try {
			BufferedWriter outputFile = new BufferedWriter(new FileWriter("data/inventory.txt"));
			
			for (Map.Entry<String, Integer> entry: inventory.entrySet()) {
				outputFile.write(entry.getKey() + " " + entry.getValue());
				outputFile.newLine();
			}
			
			outputFile.close();
		} catch (IOException e) {
			System.err.println("ERROR: File could not be written.");
		}
	}

}
