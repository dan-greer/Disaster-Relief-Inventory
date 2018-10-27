import java.io.*;
import java.util.*;

public class FileHandler {

	private FileHandler() {
		// Never gets called
	}

	public static Map<String, Integer> readFile(String locationName) {
		Map<String, Integer> inventory = new TreeMap<>();
		try {
			BufferedReader inputFile = new BufferedReader(new FileReader("data/" + locationName + ".txt"));
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

	public static void writeHTML(List<Location> reliefLocations) {
		// Write to an HTML table

		try {
			BufferedWriter outputHTML = new BufferedWriter(new FileWriter("data/indexInventory.html"));
			outputHTML.write("<!DOCTYPE html>");
			outputHTML.newLine();
			
			outputHTML.write("<html lang=\"en\">");
			outputHTML.newLine();
			outputHTML.write("<link rel=\"stylesheet\" href=\"inventoryStyle.css\" type=\"text/css\"");
			
			// Head
			outputHTML.write("\t<head>");
			outputHTML.newLine();
			outputHTML.write("\t\t<title> Disaster Relief Inventory</title>");
			outputHTML.newLine();
			outputHTML.write("\t</head>");
			outputHTML.newLine();
			
			// Body
			outputHTML.write("\t<body>");
			outputHTML.newLine();
			outputHTML.write("\t<h1>Locations<h1>");
			for (Location loc: reliefLocations) {
				outputHTML.write("\t\t<a href=\"data/" + loc.locationName + ".html>" + loc.locationName + "</a>");
			}
			
			outputHTML.write("\t</body>");
			outputHTML.newLine();
			
			
			outputHTML.write("</html>");
			outputHTML.newLine();
			
			
			outputHTML.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public static void writeLocationHTML(Location loc) {
		try {
			BufferedWriter outputHTML = new BufferedWriter(new FileWriter("data/" + loc.locationName + ".html"));
			outputHTML.write("<!DOCTYPE html>");
			outputHTML.newLine();
			
			outputHTML.write("<html lang=\"en\">");
			outputHTML.newLine();
			outputHTML.write("<link rel=\"stylesheet\" href=\"inventoryStyle.css\" type=\"text/css\"");
			
			// Head
			outputHTML.write("\t<head>");
			outputHTML.newLine();
			outputHTML.write("\t\t<title>" + loc.locationName + " Inventory</title>");
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
			for (Map.Entry<String, Integer> entry : loc.inventory.entrySet()) {
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

	public static void writeInventoryFile(Map<String, Integer> inventory, String locationName) {
		try {
			BufferedWriter outputFile = new BufferedWriter(new FileWriter("data/" + locationName + ".txt"));
			
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
