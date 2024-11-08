/**
 * Class: CSE 201
 * This is the master class that has all of the main method and everything
 * needed to run the UI.
 * 
 * @version 1.0
 * Authors:
 * Joe Follarth, Jacob Artnak, Rhett Bilski, Adam Faglie, Andrew Horton, Koray Bayram
 */

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameManager {
	private static final int roomCount = 2; // 1st iteration
	
	/**
	 * Initializes all rooms for the game and stores them in an array for use
	 * throughout game play. Each room is configured with specific commands, items,
	 * and objects based on its unique setup.
	 * 
	 * @return Room[] Array of rooms to be used throughout the game.
	 */
	public static Room[] setUpRooms() {
		Room[] roomList = new Room[roomCount];
		for (int i = 0; i < roomCount; i++) {
			Map<String, String> roomCommands = new HashMap<>();
			ArrayList<Item> items = new ArrayList<>();
			ArrayList<String> objects = new ArrayList<>();
			
			// Basic commands available in every room.
			roomCommands.put("exit", "");
			roomCommands.put("help", "");
			roomCommands.put("next room", "");
			roomCommands.put("items", "");
			roomCommands.put("objects", "");
			roomCommands.put("examine", "");
			roomCommands.put("equip", "");
			roomCommands.put("take", "");
			roomCommands.put("use", "");
			
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
				items.add(new Item("rusty key", "The key is cold and covered in rust. It’s likely been here for years, maybe decades."));
			    items.add(new Item("flashlight", "An old flashlight with scratches on the surface. The battery is weak, but it flickers to life when you switch it on."));
			    items.add(new Item("journal page", "A brittle piece of paper with faded writing. One line catches your eye: ‘Beware the shadows – not all that hides is empty."));
			    
			    objects.add("cellar");
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
	 * Prints a list of all valid commands for the current room.
	 * 
	 * @param roomCommands Map of command strings available in the current room.
	 */
	public static void printHelpMessage(Map<String, String> roomCommands) {
		System.out.println("List of available commands: ");
		for (String command : roomCommands.keySet()) {
			System.out.println("\"" + command + "\"");
		}
	}
	
	/**
	 * Terminates the game if the user confirms their choice. The program asks the
	 * player to type "Confirm" to exit. If confirmed, the game is closed.
	 * 
	 * @param scan Scanner instance to read player input.
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
	 * Checks if a player's input is a valid command for the current room. Prints an
	 * error message if the input is invalid.
	 * 
	 * @param roomCommands Map containing valid commands and associated methods for the room.
	 * @param input Player's command input to be checked.
	 * @return boolean True if input is a valid command; false otherwise.
	 */
	public static boolean checkInput(Map<String, String> roomCommands, String input) {
		boolean isValid = false;
		for (String command : roomCommands.keySet()) {
			if (command.equalsIgnoreCase(input)) {
				isValid = true;
				break;
			}
		}
		if (!isValid) {
			System.out.println(input + " is not a valid command.");
			System.out.println("Type \"Help\" to get a list of valid commands.");
		}
		return isValid;
	}
	
	/**
	 * Main method for the game. Initializes the game, sets up the rooms, and begins
	 * the main game loop where the player inputs commands and interacts with the game.
	 * 
	 * @param args Command-line arguments, if any.
	 */
	public static void main(String[] args) {
		Room[] roomList = setUpRooms(); // Sets up rooms with items, objects, and commands
		ArrayList<Item> inventory = new ArrayList<Item>(); // Player's starting inventory
		Player player = new Player("Name", 20, inventory, roomList); // Create player character
		
		// TODO: Initialize enemy (e.g., for use in future combat scenarios)
		Enemy enemy = null;
		
		System.out.println("Welcome to 'What Lies Beneath Miami!\n\n");
		System.out.println(player.getCurrentRoom().roomDescription);
		Scanner scan = new Scanner(System.in);
		CommandHandler commandHandler = new CommandHandler(player, scan); // Initialize command handler
		
		// Main game loop for user input and command execution
		while (true) {
			Map<String, String> roomCommands = player.getCurrentRoom().getRoomCommands(); // Get commands for current room
			System.out.println("> ");
			String input = scan.nextLine().trim();

			// Check if input is a valid command in the room's context
			if (!checkInput(roomCommands, input)) {
				continue;
			}
				
			Room currentRoom = player.getCurrentRoom();
			
			// Handle common commands through switch case
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
			case "items" :
				currentRoom.displayItems();
				break;
			case "objects" :
				currentRoom.displayObjects();
				break;
			case "examine":
				commandHandler.examine();
		        break;
		    case "take":
		    	commandHandler.take();
		        break;
		    case "equip":
		        commandHandler.equip();
		        break;
		    case "use":
		        commandHandler.use();
		        break;
			}

			// Execute room-specific commands using reflection
			if (roomCommands.containsKey(input.toLowerCase())) {
                String methodName = roomCommands.get(input.toLowerCase());
                try {
                    Method method = CommandHandler.class.getMethod(methodName);
                    method.invoke(commandHandler);
                } catch (Exception e) {

                }
            } else {
                System.out.println("Unknown command. Type 'Help' for a list of commands.");
            }
		} // End of while loop
	}
}