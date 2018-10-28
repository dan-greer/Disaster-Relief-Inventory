import java.util.*;

public class DisasterInventory {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		// Create a structure for holding all of the relief locations
		List<Location> reliefLocations = FileHandler.readLocationsFile();
		
		// Get the user's location selection
		Location chosen = getLocationSelection(reliefLocations, in);
		
		// Print out the user's location selection
		System.out.println(chosen.getName() + " selected.");
		System.out.println();
		
		
		// Repeatedly get the user's instructions and perform operations on the
		// location's inventory
		char choice = getChoice();
		while (choice != '4') {
			
			switch (choice) {
			case '1':
				
				// Add to the inventory
				addItems(chosen.inventory, in);
				System.out.println();
				break;
			case '2':

				// Update the inventory
				updateItems(chosen.inventory, in);
				System.out.println();
				break;
			case '3':

				// Print the inventory to the console
				chosen.printInventory();
				System.out.println();
				break;
			case '4':
				
				// Program will finish executing
				System.out.println();
				break;
			default:
				System.err.println("ERROR: Choice was invalid");
			}
			choice = getChoice();
		}
		
		
		// Finish and write to HTML, inventory, and locations files
		FileHandler.writeHTML(reliefLocations);
		FileHandler.writeLocationHTML(chosen);
		FileHandler.writeInventoryFile(chosen.inventory, chosen.getName());
		FileHandler.writeLocationsFile(reliefLocations);
		
		in.close();
		System.out.println("Exited successfully");

	}
	
	/**
	 * Takes the list of locations and gets the user's location selection.
	 * 
	 * @author Dan Greer
	 * @param reliefLocations - list of the relief locations
	 * @param in - Scanner reading from console
	 * @return the user's location selection
	 */
	private static Location getLocationSelection(List<Location> reliefLocations, Scanner in) {
		
		int selInt = 1;
		int count = 1;
		
		// If the location list is empty, get a new location
		Location chosen = new Location("empty");
		if (reliefLocations.size() == 0) {
			System.out.println("Enter a new location name: ");
			String locationName = in.nextLine();
			chosen = new Location(locationName);
			reliefLocations.add(chosen);
		} else {
			
			// Print out all the locations and prompt for the user's selection
			for (Location loc: reliefLocations) {
				System.out.println(count + ") " + loc.getName());
				count++;
			}
			System.out.println(count + ") Create new location");
			System.out.print("Selection: ");
			String selection = in.nextLine();
			
			// Get the user's selection and either get it or prompt for a new location
			selInt = Integer.parseInt(selection);
			if (selInt != count) {
				chosen = reliefLocations.get(selInt - 1);
			} else {
				System.out.println("Enter a new location name: ");
				String locationName = in.nextLine();
				chosen = new Location(locationName);
				reliefLocations.add(chosen);
			}
		}
		
		return chosen;
	}

	/**
	 * Prompts the user for their operation choice and validates the input.
	 * 
	 * @author Dan Greer
	 * @return the user's choice for operation
	 */
	public static char getChoice() {
		Scanner in = new Scanner(System.in);
		String fullInput = validateFullInput(in);
		
		char choice = fullInput.charAt(0);
		int toCheck = Character.getNumericValue(choice);
		
		while (toCheck < 1 || toCheck > 4) {
			
			fullInput = validateFullInput(in);
			
			choice = fullInput.charAt(0);
			toCheck = Character.getNumericValue(choice);
			
		}
		return choice;
	}
	
	/**
	 * Adds the user's new item to the inventory.
	 * 
	 * @author Ally Chitwood
	 * @param inventory - a map containing the items and quantities for a location
	 * @param in - Scanner for keyboard entry from the console
	 */
	public static void addItems(Map<String, Integer> inventory, Scanner in) {
		
		// Prompt for type of item
		System.out.println("Which type of item(s) would you like to add? ");
		String itemType = in.nextLine();
		
		// If the location already has the item, change the quantity
		if (inventory.containsKey(itemType)) {
			
			int currentQuantity = inventory.get(itemType);
			System.out.println("Current quantity of [" + itemType + "] is " + currentQuantity);
			System.out.println("How many [" + itemType + "] would you like to add to the current quantity?");
			String quantity = in.nextLine();

			int newQuantity = Integer.parseInt(quantity);
			inventory.replace(itemType, currentQuantity, currentQuantity + newQuantity);
			System.out.println("Updated inventory to hold " + (currentQuantity + newQuantity) + " of [" + itemType + "]");

			
		} else {
			
			// If it doesn't add the item and quantity to the inventory
			System.out.println("There are no [" + itemType + "] in the current inventory. How many would you like to add?");
			String quantity = in.nextLine();
			int newQuantity = Integer.parseInt(quantity);
			inventory.put(itemType, newQuantity);
			System.out.println("Updated inventory to hold " + newQuantity + " of [" + itemType + "]");
		}
		
		
	}
	
	/**
	 * Updates the quantity of a user-selected item.
	 * 
	 * @author Ally Chitwood
	 * @param inventory - map containing the location's items and their quantities
	 * @param in - Scanner for keyboard entry from the console
	 */
	public static void updateItems(Map<String, Integer> inventory, Scanner in) {
		
		// Prompt for item type
		System.out.println("Which type of item(s) would you like to update? ");
		String itemType = in.nextLine();
		
		// If the inventory contains the key, update the quantity, otherwise print out to use
		// the "Add" command
		if (inventory.containsKey(itemType)) {
			
			int currentQuantity = inventory.get(itemType);
			
			System.out.println("Current quantity of [" + itemType + "] is " + currentQuantity);
			System.out.println("What would you like the new quantity of [" + itemType + "] to be?");
			
			String quantity = in.nextLine();
			int newQuantity = Integer.parseInt(quantity);
			
			inventory.replace(itemType, currentQuantity, newQuantity);
			System.out.println("Updated inventory to hold " + newQuantity + " of [" + itemType + "].");
			
		} else {
			
			System.out.println("There are no [" + itemType + "] in the current inventory.");
			System.out.println("Run the \"1) Add...\" command instead.");
			
		}
	}
	
	/**
	 * Prints the operation options to the user.
	 * 
	 * @author Dan Greer
	 */
	public static void printOptions() {
		System.out.println("1) Add new item to inventory");
		System.out.println("2) Update quantity in inventory");
		System.out.println("3) View full inventory");
		System.out.println("4) Quit");
		System.out.print("Selection: ");
	}
	
	/**
	 * Verifies the user entered a valid input for their operation choice.
	 * 
	 * @author Dan Greer
	 * @param in - Scanner for keyboard entry from the console
	 * @return
	 */
	public static String validateFullInput(Scanner in) {
		
		// Prompt the user for an operation selection
		printOptions();
		String fullInput = in.nextLine();
		System.out.println();
		
		// Continue to prompt the user until they enter valid input
		while (fullInput.length() != 1 && !fullInput.matches("/\\d+(\\.\\d+)?/")) {
			System.out.println("ERROR: Input should be one numerical character between 1 and 4.");
			
			printOptions();
			fullInput = in.nextLine();
			
			System.out.println();
		}
		
		return fullInput;
	
	}

}
