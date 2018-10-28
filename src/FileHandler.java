import java.io.*;
import java.util.*;

public class FileHandler {

	private FileHandler() {
		// Never gets called
	}

	/**
	 * Reads location's inventory file.
	 * 
	 * @author Dan Greer
	 * @param locationName - String with the name of the location
	 * @return a Map with the location's inventory
	 */
	public static Map<String, Integer> readFile(String locationName) {
		
		Map<String, Integer> inventory = new TreeMap<>();
		
		try {
			
			// Read all of the inventory from the location's inventory file
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
			
			// If the file doesn't exist yet, create it
			File newFile = new File(locationName + ".txt");
			try {
				newFile.createNewFile();
			} catch (IOException e1) {
				System.err.println("ERROR: File could not be created.");
			}
			
		} catch (IOException e) {
			System.err.println("ERROR: File input could not be read.");
		}
		
		return inventory;
		
	}

	/**
	 * Writes the HTML index file for all of the locations.  The file will contain links to all of
	 * the location's inventories.
	 * 
	 * @author Ally Chitwood
	 * @param reliefLocations - List with all of the relief locations
	 */
	public static void writeHTML(List<Location> reliefLocations) {
		// Write to an HTML table

		try {
			
			// Write the top of the HTML file
			BufferedWriter outputHTML = new BufferedWriter(new FileWriter("data/indexInventory.html"));
			outputHTML.write("<!DOCTYPE html>");
			outputHTML.newLine();
			outputHTML.write("<html lang=\"en\">");
			outputHTML.newLine();
			
			// Write the header of the HTML file
			outputHTML.write("\t<head>");
			outputHTML.newLine();
			outputHTML.write("\t\t<title> Disaster Relief Inventory</title>");
			outputHTML.newLine();
			outputHTML.write("\t\t<link rel=\"stylesheet\" href=\"inventoryStyle.css\" type=\"text/css\"");
			outputHTML.newLine();
			outputHTML.write("\t</head>");
			outputHTML.newLine();
			
			// Write the body of the HTML file
			outputHTML.write("\t<body>");
			outputHTML.newLine();
			outputHTML.write("\t<h1>Disaster Relief Locations<h1>");
			
			// Write links to all of the locations files
			for (Location loc: reliefLocations) {
				outputHTML.write("\t\t<a href=\"" + loc.locationName + ".html\">" + loc.locationName + "</a>");
				outputHTML.newLine();
				outputHTML.write("\t\t<br/>");
				outputHTML.newLine();
			}
			
			outputHTML.write("\t</body>");
			outputHTML.newLine();
			
			
			outputHTML.write("</html>");
			outputHTML.newLine();
			
			
			outputHTML.close();
		} catch (IOException e) {
			System.err.println("ERROR: Unable to write index HTML file.");
		}
			
	}
	
	/**
	 * Writes a specific location's inventory to it's HTML file.
	 * 
	 * @author Ally Chitwood
	 * @param loc - Location whose inventory is being written
	 */
	public static void writeLocationHTML(Location loc) {
		try {
			
			// Writes the top of the HTML file
			BufferedWriter outputHTML = new BufferedWriter(new FileWriter("data/" + loc.locationName + ".html"));
			outputHTML.write("<!DOCTYPE html>");
			outputHTML.newLine();
			outputHTML.write("<html lang=\"en\">");
			outputHTML.newLine();
			
			// Write the header of the HTML file
			outputHTML.write("\t<head>");
			outputHTML.newLine();
			outputHTML.write("\t\t<title>" + loc.locationName + " Inventory</title>");
			outputHTML.newLine();
			outputHTML.write("\t\t<link rel=\"stylesheet\" href=\"inventoryStyle.css\" type=\"text/css\"");
			outputHTML.newLine();
			outputHTML.write("\t</head>");
			outputHTML.newLine();
			
			// Write the body of the HTML file
			outputHTML.write("\t<body>");
			outputHTML.newLine();
			
			// Link to the index page
			outputHTML.write("\t\t<a href=\"indexInventory.html\">Home</a>");
			
			// Print out the location name and all of it's items with their quantities
			outputHTML.write("\t\t<h2>" + loc.getName() + " Inventory</h2>");
			outputHTML.newLine();;
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
			System.err.println("ERROR: Unable to write location HTML file.");
		}
	}

	/**
	 * Writes the location's inventory to a text file to be read from later.
	 * 
	 * @author Dan Greer
	 * @param inventory - Map with the location's items and their quantities
	 * @param locationName - location with the inventory
	 */
	public static void writeInventoryFile(Map<String, Integer> inventory, String locationName) {
		try {
			
			// Write the full inventory to the locatin's text file
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

	/**
	 * Writes the file containing a list of all the locations.
	 * 
	 * @author Dan Greer
	 * @param reliefLocations - List of all the locations
	 */
	public static void writeLocationsFile(List<Location> reliefLocations) {
		try {
			
			// Write all of the locations to the locations text file
			BufferedWriter locationsWriter = new BufferedWriter(new FileWriter("data/locations.txt"));
			for (Location loc : reliefLocations) {
				locationsWriter.write(loc.getName());
				locationsWriter.newLine();
			}
			
			locationsWriter.close();
		} catch (IOException e) {
			System.err.println("ERROR: Could not write to locations.txt");
		}
		
	}

	/**
	 * Reads from the locations text file and puts the locations into a List.
	 * 
	 * @author Dan Greer
	 * @return a List containing all of the locations previously entered by the user
	 */
	public static List<Location> readLocationsFile() {
		List<Location> reliefLocations = new ArrayList<>();
		try {
			
			// Reads all of the lists from the locations text file and puts them all into a List
			BufferedReader readLocations = new BufferedReader(new FileReader("data/locations.txt"));
			String line = readLocations.readLine();
			
			while (line != null) {
				reliefLocations.add(new Location(line));
				line = readLocations.readLine();
			}
			
			readLocations.close();
		} catch (IOException e1) {
			System.err.println("ERROR: Could not read locations.txt");
		}
		return reliefLocations;
	}

}
