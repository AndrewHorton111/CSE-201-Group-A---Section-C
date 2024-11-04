
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

import java.util.ArrayList;
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
		// Left as place holders for now.
		String[] descriptionList = new String[7];
		descriptionList[0] = "You awaken in a damp, shadow-filled cellar. The walls are lined with stone, dripping with moisture, and an uneasy silence fills the air. A faint, stale smell surrounds you. Scattered across a dusty wooden table nearby are a few items: an old flashlight, a rusted key, and a torn page from a journal.";
		descriptionList[1] = "Room Description #2";
		descriptionList[2] = "Room Description #3";
		descriptionList[3] = "Room Description #4";
		descriptionList[4] = "Room Description #5";
		descriptionList[5] = "Room Description #6";
		descriptionList[6] = "Room Description #7";

		// Array of Rooms that will be used throughout the game.
		Room[] roomList = new Room[7];
		for (int i = 0; i < 7; i++) {
			// the validCommands ArrayList stores the string of each command
			// that is valid in each room. 
			ArrayList<String> validCommands = new ArrayList<>();
			ArrayList<Item> items = new ArrayList<>();
			ArrayList<String> objects = new ArrayList<>();
			
			// The following commands should be available in every room.
			validCommands.add("Exit");
			validCommands.add("Help");
			validCommands.add("Next room");

			// Basic object/item commands
			validCommands.add("Items");
			validCommands.add("Objects");
			
			validCommands.add("Examine");
			validCommands.add("Equip");
			validCommands.add("Take");
			validCommands.add("Use");
			// Variations in acceptable commands for each room.
			switch (i) {
			case 0:
				
				

			    items.add(new Item("Rusty Key", "The key is cold and covered in rust. It’s likely been here for years, maybe decades."));
			    items.add(new Item("Flashlight", "An old flashlight with scratches on the surface. The battery is weak, but it flickers to life when you switch it on."));
			    items.add(new Item("Journal Page", "A brittle piece of paper with faded writing. One line catches your eye: ‘Beware the shadows – not all that hides is empty."));
			    
			    objects.add("Cellar"); // rust key is used on this
			    
				Room room1 = new CellarRoom(descriptionList[i], validCommands, items, objects);
				roomList[i] = room1;
				break;
			case 1:
				validCommands.add("Attack");
				validCommands.add("Dodge");
				validCommands.add("Heal");
				Room room2 = new Room(descriptionList[i], validCommands, items, objects);
				roomList[i] = room2;
				break;
			// Other rooms not done for this iteration.
			case 2:
				Room room3 = new Room(descriptionList[i], validCommands, items, objects);
				roomList[i] = room3;
				break;
			case 3:
				Room room4 = new Room(descriptionList[i], validCommands, items, objects);
				roomList[i] = room4;
				break;
			case 4:
				Room room5 = new Room(descriptionList[i], validCommands, items, objects);
				roomList[i] = room5;
				break;
			case 5:
				Room room6 = new Room(descriptionList[i], validCommands, items, objects);
				roomList[i] = room6;
				break;
			case 6:
				Room room7 = new Room(descriptionList[i], validCommands, items, objects);
				roomList[i] = room7;
				break;
			}

		}

		return roomList;
	}


	
	/**
	 * This method acts as the enemy's turn in combat. The enemy should
	 * only attack the player. If the enemy's health is 0 or lower, they
	 * shouldn't deal damage and the roomCleared boolean should be set
	 * to true for the current Room. 
	 * 
	 * @param player
	 * @param enemy
	 */
	public static void enemyTurn(Player player, Enemy enemy) {
		//TODO
	}
	
	/**
	 * This method gets the weapon from the Player and uses its attributes
	 * to modify the health of the Enemy. After the Player's turn, call
	 * "enemyTurn" so the Enemy can attack the player.
	 * 
	 * @param player
	 * @param enemy
	 */
	public static void attack(Player player, Enemy enemy) {
		//TODO
		enemyTurn(player, enemy);
	}
	
	/**
	 * This method makes it so that the player has a much higher chance 
	 * to dodge the enemy's next attack. After the Player's turn, call
	 * "enemyTurn" so the Enemy can attack the player.
	 * 
	 * @param player
	 * @param enemy
	 */
	public static void dodge(Player player, Enemy enemy) {
		//TODO
		
		// dodgeChance + num
		enemyTurn(player, enemy);
		// dodgeChance - num
	}
	
	/**
	 * This method heals the Player if they have health Potions left.
	 * This method might be expanded to be able to use different types
	 * of Potions in the future, but for this iteration it should just
	 * use health Potions. After the Player's turn, call "enemyTurn" so
	 * the Enemy can attack the player.
	 * 
	 * @param player
	 * @param enemy
	 */
	public static void heal(Player player, Enemy enemy) {
		//TODO
		enemyTurn(player, enemy);
	}
	
	/**
	 * This method moves the player to the next room, provided they have
	 * already cleared the room. If they have cleared the room, print the 
	 * room description for the new room. 
	 * 
	 * @param player
	 */
	public static void nextRoom(Player player) {
		//TODO
	}
	
	/**
	 * This method prints all valid commands in the given room.
	 * 
	 * @param validCommands
	 */
	public static void printHelpMessage(ArrayList<String> validCommands) {
		System.out.println("List of available commands: ");
		for (String command : validCommands) {
			System.out.println("\"" + command + "\"");
		}
	}
	
	/**
	 * This method closes the program if the user confirms to their choice.
	 * 
	 * @param scan
	 */
	public static void exitProgram(Scanner scan) {
		System.out.println("Type \"Confirm\" to exit the program, or anything else to cancel");
		String response = scan.nextLine();
		if (response.equalsIgnoreCase("Confirm")) {
			System.out.println("Closing the program");
			scan.close();
			System.exit(0);
		}
	}

	/**
	 * This helper function takes an ArrayList of valid commands and an input 
	 * from the user and loops through the ArrayList to see is the input is in
	 * the ArrayList, and therefore a valid command. 
	 * 
	 * @param validCommands
	 * @param input
	 * @return isValid
	 */
	public static boolean checkInput(ArrayList<String> validCommands, String input) {
		boolean isValid = false;
		for (String command : validCommands) {
			if (command.equalsIgnoreCase(input)) {
				isValid = true;
				break;
			}
		}
		
		// Print an error message if the input is not valid.
		if (!isValid) {
			System.out.println(input + " is not a valid command.");
			System.out.println("Type \"Help\" to get a list of valid commands.");
		}
		return isValid;
	}
	
	/**
	 * The main method. It gets the input from the user and calls other methods
	 * based on what the user enters and what room they are in.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// The first part of the main method sets up the Room and the Player
		// objects that will be needed throughout the game. 
		
		// Sets up all seven Rooms that will be used.
		Room[] roomList = setUpRooms();
		
		
		// Makes the player. Just default values for now. 
		ArrayList<Item> inventory = new ArrayList<Item>();
		Player player = new Player("Name", 20, inventory, roomList);
		
		
		
		//TODO
		// Create an enemy the player will battle in room 2.
		Enemy enemy = null;
		
		System.out.println("Welcome to 'What Lies Beneath Miami'!");
		System.out.println(player.getCurrentRoom().roomDescription);
		Scanner scan = new Scanner(System.in);
		CommandHandler commandHandler = new CommandHandler(player, scan); // contains methods like use, examine
		
		
		// The rest of the main method checks for user input and calls the methods
		// based on what the user typed.
		while (true) {

			// Gets the valid commands for the room the player is in.
			ArrayList<String> validCommands = player.getCurrentRoom().getValidCommands();

			// Gets input from the user.
			System.out.println("> ");
			String input = scan.nextLine();

			// The following codes checks that any input provided is valid.
			// by confirming that it is in the validCommands ArrayList for
			// the room the user is currently in. If not, the program restarts
			// the loop and asks for another input from the user.
			if (!checkInput(validCommands, input)) {
				continue;
			}
				
			Room currentRoom = player.getCurrentRoom();
			// If this part of the code is reached, then the input the user
			// provided was valid, and the switch case below will call another
			// method based on what command is being input.
			switch (input.toLowerCase()) {
			case "exit" :
				exitProgram(scan);
				break;
			case "help" :
				printHelpMessage(validCommands);
				break;
			case "next room" :
				player.nextRoom();
				break;
			
			case "items" : //displays all items in the room
				currentRoom.displayItems();
				break;
			case "objects" : // displays all objects in the room
				currentRoom.displayObjects();
				break;
				
			case "examine":
				commandHandler.examine();  // Allows the player to examine items
		        break;
		    case "take":
		    	commandHandler.take();  // Player takes an item
		        break;
		    case "equip":
		        commandHandler.equip();  // Equip the flashlight
		        break;
		    case "use":
		        commandHandler.use();  // Use the key on the door
		        break;
			}
				
			// The following code is used for testing various classes.
			// They will not be used in the final program.
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
		} // End of while loop
		
		
	}
	
}
