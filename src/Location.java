import java.util.*;

public class Location {
	String locationName;
	Map<String, Integer> inventory;

	public Location(String locationName, String fileName) {
		this.locationName = locationName;
		this.inventory = FileHandler.readFile(fileName);
	}
	
	public String getName() {
		return this.locationName;
	}
	
	public void printInventory() {
		for (Map.Entry<String, Integer> entry: this.inventory.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

}
