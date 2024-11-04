
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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the master class that has all of the main method and everything
 * needed to run the UI.
 */
public class GameManager {
	private static final int roomCount = 2; // 1st iteration
	
	/**
	 * This method initializes all of the rooms the game will have, and stores them
	 * in an array for later use.
	 * 
	 * @return roomList
	 */
	public static Room[] setUpRooms() {


		// Array of Rooms that will be used throughout the game.
		Room[] roomList = new Room[roomCount];
		for (int i = 0; i < roomCount; i++) {
			// the validCommands ArrayList stores the string of each command
			// that is valid in each room. 
			Map<String, String> roomCommands = new HashMap<>();
			ArrayList<Item> items = new ArrayList<>();
			ArrayList<String> objects = new ArrayList<>();
			
			// The following commands should be available in every room.
			// format - 
			// key: the text the user types 
			// value: the corresponding method to call (leave "" if in switch case)
			roomCommands.put("exit", "");
			roomCommands.put("help", "");
			roomCommands.put("next room", "");

			// Basic object/item commands
			roomCommands.put("items", "");
			roomCommands.put("objects", "");
			
			roomCommands.put("examine", "");
			roomCommands.put("equip", "");
			roomCommands.put("take", "");
			roomCommands.put("use", "");
			// Variations in acceptable commands for each room.
			switch (i) {
			case 0:
				
				roomCommands.put("open door", "openDoor");
				items.add(new Item("mysterious note", "A worn, faded note that reads:\n\n"
						          	+ "Welcome to the depths...\n"
						         	+ "Trust only what you can see.\n"
						           	+ "Shadows conceal more than they reveal.\n"
						           	+ "The path forward is hidden in plain sight."));
				
				items.add(new Potion("health potion", "A potion that replenishes your health"));
	
				
				objects.add("door"); 
				    
				String roomDescription =  "You awaken on the cold stone floor of a small, shadowy room.\n"
						+ " The only light comes from a narrow window high on the wall, casting a faint glow across the room.\n"
						+ "As you sit up, you notice a simple wooden bench against one wall and a tattered piece of paper resting on it.\n"
						+ "The air is heavy, and a deep silence fills the room.\n"
						+ "A sturdy-looking door stands opposite you, closed tight.";
				
			    Room room1 = new BeginnerRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room1;
				break;
			case 1:
//				validCommands.add("Attack");
//				validCommands.add("Dodge");
//				validCommands.add("Heal");
				items.add(new Item("rusty key", "The key is cold and covered in rust. It’s likely been here for years, maybe decades."));
			    items.add(new Item("flashlight", "An old flashlight with scratches on the surface. The battery is weak, but it flickers to life when you switch it on."));
			    items.add(new Item("journal page", "A brittle piece of paper with faded writing. One line catches your eye: ‘Beware the shadows – not all that hides is empty."));
			    
			    objects.add("cellar"); // rust key is used on this
			    
			    roomDescription =  "You arrive in a damp, shadow-filled cellar.\n"
			    		+ "The walls are lined with stone, dripping with moisture, and an uneasy silence fills the air.\n"
			    		+ "A faint, stale smell surrounds you.\n"
			    		+ "Scattered across a dusty wooden table nearby are a few items: an old flashlight, a rusted key, and a torn page from a journal.";
			    
				Room room2 = new CellarRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room2;
				break;
				
			default:
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
	public static void printHelpMessage(Map<String, String> roomCommands) {
		System.out.println("List of available commands: ");
		for (String command : roomCommands.keySet()) {
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
	public static boolean checkInput(Map<String, String> roomCommands, String input) {
		boolean isValid = false;
		
		
		for (String command : roomCommands.keySet()) {
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
		
		System.out.println("Welcome to 'What Lies Beneath Miami!\n\n");
		System.out.println(player.getCurrentRoom().roomDescription);
		Scanner scan = new Scanner(System.in);
		CommandHandler commandHandler = new CommandHandler(player, scan); // contains methods like use, examine
		
		
		// The rest of the main method checks for user input and calls the methods
		// based on what the user typed.
		while (true) {

			// Gets the valid commands for the room the player is in.
			Map<String, String> roomCommands = player.getCurrentRoom().getRoomCommands();

			// Gets input from the user.
			System.out.println("> ");
			String input = scan.nextLine().trim();

			// The following codes checks that any input provided is valid.
			// by confirming that it is in the validCommands ArrayList for
			// the room the user is currently in. If not, the program restarts
			// the loop and asks for another input from the user.
			if (!checkInput(roomCommands, input)) {
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
				printHelpMessage(roomCommands);
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
				
			//Custom functions for each room, e.g "open door" = openDoor()
			
			if (roomCommands.containsKey(input.toLowerCase())) {
                String methodName = roomCommands.get(input.toLowerCase());

                try {
                    Method method = CommandHandler.class.getMethod(methodName);
                    method.invoke(commandHandler);  // Calls the specified method on CommandHandler
                } catch (Exception e) {
                    //System.out.println("Error executing command: " + e.getMessage());
                }
            } else {
                System.out.println("Unknown command. Type 'Help' for a list of commands.");
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
