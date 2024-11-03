
/**
 * Class: CSE 201
 * @author Joe Follarth
 * @author Jacob Artnak
 * @author Rhett Bilski
 * @author Adam Faglie
 * @author Andrew Horton
 * @author Koray Bayram
 * @version 1.0
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * This is the master class that has all of the main method and everything
 * needed to run the UI.
 */
public class GameManager {

	/**
	 * This method initializes all of the rooms the game will have, and stores them
	 * in an array for later use.
	 * 
	 * @return roomList
	 */
	public static Room[] setUpRooms() {
		// Array of roomDescriptions to be used when creating the Rooms.
		String[] descriptionList = new String[7];
		descriptionList[0] = "Room Description #1";
		descriptionList[1] = "Room Description #2";
		descriptionList[2] = "Room Description #3";
		descriptionList[3] = "Room Description #4";
		descriptionList[4] = "Room Description #5";
		descriptionList[5] = "Room Description #6";
		descriptionList[6] = "Room Description #7";

		// Array of Rooms that will be used throughout the game.
		Room[] roomList = new Room[7];
		for (int i = 0; i < 7; i++) {
			// List of commands that are valid in each room. The is input the user
			// would enter to get the method that is stored in the value.
			HashMap<String, String> validCommands = new HashMap<String, String>();
			// Commands that are valid in every room.
			validCommands.put("Exit", "Call method");
			validCommands.put("Help", "Call method");
			validCommands.put("Next room", "Call Method");

			// Variations in acceptable commands for each room.
			switch (i) {
			case 0:
				ArrayList<Item> itemList = new ArrayList<Item>();
				TreasureRoom room1 = new TreasureRoom(descriptionList[i], validCommands, itemList);
				roomList[i] = room1;
				break;
			case 1:
				ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
				// ArrayList<String> dialogList = new ArrayList<String>();
				// ArrayList<Item> inventory = new ArrayList<Item>();
				// Enemy e = new Enemy("Bob", 20, inventory, dialogList);
				BattleRoom room2 = new BattleRoom(descriptionList[i], validCommands, enemyList);
				roomList[i] = room2;
				break;
			// Other rooms not done for this iteration.
			case 2:
				Room room3 = new Room(descriptionList[i], validCommands);
				roomList[i] = room3;
				break;
			case 3:
				Room room4 = new Room(descriptionList[i], validCommands);
				roomList[i] = room4;
				break;
			case 4:
				Room room5 = new Room(descriptionList[i], validCommands);
				roomList[i] = room5;
				break;
			case 5:
				Room room6 = new Room(descriptionList[i], validCommands);
				roomList[i] = room6;
				break;
			case 6:
				Room room7 = new Room(descriptionList[i], validCommands);
				roomList[i] = room7;
				break;
			}

		}

		return roomList;
	}

	/**
	 * This method prints all valid commands in the given room.
	 * 
	 * @param validCommands
	 */
	public static void printHelpMessage(HashMap<String, String> validCommands) {
		System.out.println("List of available commands: ");
		for (String key : validCommands.keySet()) {
			System.out.println("\"" + key + "\"");
		}
	}
	
	/**
	 * This method closes the program.
	 * 
	 * @param scan
	 */
	public static void exitProgram(Scanner scan) {
		System.out.println("Type Y to exit, type N to keep using the program.");
		String response = scan.nextLine();
		if (response.equalsIgnoreCase("Y")) {
			System.out.println("Closing the program");
			scan.close();
			System.exit(0);
		}
	}

	/*
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// game
		Room[] roomList = setUpRooms();

		Scanner scan = new Scanner(System.in);

		// The rest of the main method checks for user input and calls the methods
		// based on what the user typed.
		while (true) {

			HashMap<String, String> validCommands = roomList[0].getValidCommands();
			ArrayList<Item> inventory = new ArrayList<Item>();
			Player player = new Player("Name", 20, inventory, roomList);

			System.out.println("Enter your input: ");
			String input = scan.nextLine();

			// The following codes checks that any input provided is valid.
			// Takes the input given to use as the key, with its corresponding
			// value being either being a method with no parameters that can be
			// called directly, a string that just needs to be printed, or the
			// String "Call method", which needs to call another method that
			// requires a parameter or another object to call the method from. 
			String value = validCommands.get(input);
			if (value == null) { // Command is not value, print error and retry.
				System.out.print(input + " is not a valid command.");
				System.out.println("Type \"Help\" to get a list of valid commands.");
				continue;
			}
			// If the value in the HashMap is a method call that has no parameters,
			// then the method can be called directly via reflection. 
			try {
				Method method = GameManager.class.getMethod(value);
				method.invoke(null);

			} catch (Exception e) {
				// If the value in the HashMap was not a call to a method with no
				// parameters, check if the value was just a statement that needs
				// to be printed with no method calls. 
				if (!value.equalsIgnoreCase("Call method")) {
					System.out.println(value);
					continue;
				}
				
				// If this part of the code is reached, then the method will need
				// to be called directly due to needing parameters or another object.
				// It looks at the key rather than the value.
				switch (input) {
				case "Exit" :
					exitProgram(scan);
					break;
				case "Help" :
					printHelpMessage(validCommands);
					break;
				case "Next room" :
					player.nextRoom();
					break;
				}
				
			}
			// The following code is used for testing various classes.
			// They will not be in the final program.
			/**
			 * ArrayList<Item> testInventory = new ArrayList<Item>(); Weapon wep = new
			 * Weapon("Weapon", "Used to attack", 5, 90, 10); Armor arm = new Armor("Armor",
			 * "Used to defend", 15, 20); Potion pot = new Potion("Potion", "Used to buff
			 * oneself", "Health", 25, 3);
			 * 
			 * testInventory.add(wep); testInventory.add(arm); testInventory.add(pot);
			 * 
			 * 
			 * ArrayList<String> testDialog = new ArrayList<String>(); for (int i = 0; i <
			 * 10; i++) { testDialog.add(i + " number in list"); }
			 * 
			 * System.out.println("Tests for the Enemy Class:"); Enemy enemy1 = new
			 * Enemy("Name", 10, testInventory, testDialog);
			 * System.out.println(enemy1.getRandomDialog());
			 * System.out.println(enemy1.getRandomDialog());
			 * System.out.println(enemy1.getRandomDialog());
			 * System.out.println(enemy1.getName()); System.out.println(enemy1.getHealth());
			 * enemy1.setHealth(20); System.out.println(enemy1.getHealth());
			 * enemy1.modifyHealth(15); System.out.println(enemy1.getHealth());
			 * System.out.println(enemy1.getInventory());
			 * 
			 * System.out.println("Tests for the Player class:"); Player player = new
			 * Player("Name", 10, testInventory, 1); System.out.println(player.getName());
			 * System.out.println(player.getHealth()); player.setHealth(20);
			 * System.out.println(player.getHealth()); player.modifyHealth(15);
			 * System.out.println(player.getHealth());
			 * System.out.println(player.getInventory());
			 * System.out.println(player.getCurrentRoom()); player.setName("Updated Name");
			 * player.nextRoom(); System.out.println(player.getCurrentRoom());
			 * System.out.println(player.getName()); player.displayInventory();
			 * 
			 * System.out.println("Tests for the Item classes:"); Weapon wep2 = new
			 * Weapon("New Weapon", "New weapon description", 10, 95, 20); Armor arm2 = new
			 * Armor("New Armor", "New armor description", 30, 40); Potion pot2 = new
			 * Potion("New Potion", "Also used to buff oneself", "Health", 50, 1); Potion
			 * pot3 = new Potion("Potion", "Used to buff oneself", "Health", 25, 3);
			 * player.addToInventory(wep2); player.addToInventory(arm2);
			 * player.addToInventory(pot2); player.addToInventory(pot3);
			 * System.out.println("Added new items:"); player.displayInventory();
			 * System.out.println("Removed one of each potion:");
			 * player.removeFromInventory("Potion"); player.removeFromInventory("New
			 * Potion"); player.removeFromInventory("New Potion");
			 * player.removeFromInventory("Doesn't exist"); player.displayInventory();
			 */
		}
	}
}
