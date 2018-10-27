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
				String item = line;
				String value = inputFile.readLine();
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
		try {
			BufferedWriter outputHTML = new BufferedWriter(new FileWriter("data/inventory.html"));
			outputHTML.write("<!DOCTYPE html>");
			outputHTML.newLine();
			
			outputHTML.write("<html lang=\"en\">");
			outputHTML.newLine();
			outputHTML.write("<link rel=\"stylesheet\" href=\"inventoryStyle.css\" type=\"text/css\"");
			
			// Head
			outputHTML.write("\t<head>");
			outputHTML.newLine();
			outputHTML.write("\t\t<title>Disaster Inventory</title>");
			outputHTML.newLine();
			outputHTML.write("\t</head>");
			outputHTML.newLine();
			
			// Body
			outputHTML.write("\t<body>");
			outputHTML.newLine();
			
			outputHTML.write("\t\t<table>");
			outputHTML.newLine();
			outputHTML.write("\t\t\t<tr>");
			outputHTML.newLine();
			outputHTML.write("\t\t\t\t<th>Item</th>");
			outputHTML.newLine();
			outputHTML.write("\t\t\t\t<th>Quantity</th>");
			outputHTML.newLine();
			outputHTML.write("\t\t\t</tr>");
			outputHTML.newLine();
			for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
				outputHTML.write("\t\t\t<tr>");
				outputHTML.newLine();
				outputHTML.write("\t\t\t\t<td>" + entry.getKey() + "</td>");
				outputHTML.newLine();
				outputHTML.write("\t\t\t\t<td>" + entry.getValue() + "</td>");
				outputHTML.newLine();
				outputHTML.write("\t\t\t</tr>");
				outputHTML.newLine();
			}
			outputHTML.write("\t\t</table>");
			outputHTML.newLine();
			
			outputHTML.write("\t</body>");
			outputHTML.newLine();
			
			
			
			
			outputHTML.write("</html>");
			outputHTML.newLine();
			
			outputHTML.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeInventoryFile(Map<String, Integer> inventory) {
		try {
			BufferedWriter outputFile = new BufferedWriter(new FileWriter("data/inventory.txt"));
			
			for (Map.Entry<String, Integer> entry: inventory.entrySet()) {
				outputFile.write(entry.getKey());
				outputFile.newLine();
				outputFile.write(entry.getValue().toString());
				outputFile.newLine();
			}
			
			outputFile.close();
		} catch (IOException e) {
			System.err.println("ERROR: File could not be written.");
		}
	}

}
