import java.util.*;

public class DisasterInventory {

	public static void main(String[] args) {
		// Read from inventory file
		Map<String, Integer> inventory = FileHandler.readFile();
		
		
		// Loop through prompt
		char choice = getChoice();
		System.out.println();
		
		while (choice != '4') {
			switch (choice) {
			case '1':
				// Execute add to inventory
				addItems(inventory);
				break;
			case '2':
				// Execute update inventory
				break;
			case '3':
				// Execute view inventory
				break;
			case '4':
				// Do nothing
				break;
			default:
				System.err.println("ERROR: Choice was invalid");
			}
			choice = getChoice();
			System.out.println();
		}
		
		
		// Finish and write to HTML and inventory file
		FileHandler.writeHTML(inventory);
		FileHandler.writeInventoryFile(inventory);
		
		System.out.println("Exited successfully");

	}
	
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
	
	public static void addItems(Map<String, Integer> inventory) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Which type of item(s) would you like to add? ");
		String itemType = in.nextLine();
		
		if (inventory.containsKey(itemType)) {
			int currentQuantity = inventory.get(itemType);
			System.out.println("Current quantity of [" + itemType + "]is " + currentQuantity);
			System.out.println("How many [" + itemType + "] would you like to add to the current quantity?");
			String quantity = in.nextLine();
			int newQuantity = Integer.parseInt(quantity);
			inventory.replace(itemType, currentQuantity, currentQuantity + newQuantity);
			System.out.println("Updated inventory to hold " + (currentQuantity + newQuantity) + " of [" + itemType + "]");
			
		} else {
			
			System.out.println("There are no [" + itemType + "] in the current inventory. How many would you like to add?");
			String quantity = in.nextLine();
			int newQuantity = Integer.parseInt(quantity);
			inventory.put(itemType, newQuantity);
			System.out.println("Updated inventory to hold " + newQuantity + " of [" + itemType + "]");
		}
		
		
	}
	
	public static void printOptions() {
		System.out.println("1) Add new item to inventory");
		System.out.println("2) Update quantity in inventory");
		System.out.println("3) View full inventory");
		System.out.println("4) Quit");
		System.out.print("Selection: ");
	}
	
	public static String validateFullInput(Scanner in) {
		printOptions();
		String fullInput = in.nextLine();
		System.out.println();
		
		while (fullInput.length() != 1) {
			System.out.println("ERROR: Input should be one numerical character between 1 and 4.");
			
			printOptions();
			fullInput = in.nextLine();
			
			System.out.println();
		}
		
		return fullInput;
	
	}

}
