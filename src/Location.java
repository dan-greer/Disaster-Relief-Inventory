import java.util.*;

public class Location {
	String locationName;
	Map<String, Integer> inventory;

	/**
	 * Constructor for Location.
	 * 
	 * @author Dan Greer
	 * @param locationName - String with name of the location
	 */
	public Location(String locationName) {
		this.locationName = locationName;
		this.inventory = FileHandler.readFile(locationName);
	}
	
	/**
	 * Accessor for the location's name.
	 * 
	 * @author Dan Greer
	 * @return the name of the location
	 */
	public String getName() {
		return this.locationName;
	}
	
	/**
	 * Prints the inventory of the location.
	 * 
	 * @author Dan Greer
	 */
	public void printInventory() {
		System.out.println("Inventory: ");
		for (Map.Entry<String, Integer> entry: this.inventory.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

}
