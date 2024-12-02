
/**
 * Class: CSE 201
 * This is the master class that has all of the main method and everything
 * needed to run the UI.
 * 
 * @version 1.0
 * Authors:
 * Joe Follarth, Jacob Artnak, Rhett Bilski, Adam Faglie, Andrew Horton, Koray Bayram
 */

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
	private static final int roomCount = 7; // Added foundation for all rooms

	/**
	 *********************************** READ ME********************************** The run state for the game is how
	 * GameManager calls each separate room. I think it is easiest if each room has
	 * its own "run" method that controls everything it needs for that specific
	 * room. A good example of this is in BeginnerRoom right now. I think the best
	 * way to progress through the game would be using the open door method. If
	 * anyone has a better or different idea feel free to suggest. Any code that was
	 * in this class that I changed I commented out and moved to the very bottom in
	 * case someone needs it or we need to revert. READ
	 * ME**********************************
	 * 
	 */

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
			ArrayList<String> roomCommands = new ArrayList<String>();
			ArrayList<Item> items = new ArrayList<>();
			ArrayList<String> objects = new ArrayList<>();

			// Basic commands available in every room.
			roomCommands.add("exit");
			roomCommands.add("help");
			roomCommands.add("inventory");

			switch (i) {
			case 0:
				roomCommands.add("open door");
				roomCommands.add("items");
				roomCommands.add("objects");
				roomCommands.add("examine");
				roomCommands.add("take");
				items.add(new Item("mysterious note",
						"A worn, faded note that reads:\n\n" + "Welcome to the depths...\n"
								+ "Trust only what you can see.\n" + "Shadows conceal more than they reveal.\n"
								+ "The path forward is hidden in plain sight."));
		        items.add(new Weapon("Sword", "A solid weapon.", 10, 0, 15));
		        items.add(new Armor("Chestplate", "Protection from potential danger", 5, 15));
		        items.add(new Potion("health potion", "A potion that replenishes your health"));
				objects.add("door");

				String roomDescription = "You awaken on the cold stone floor of a small, shadowy room.\n"
						+ " The only light comes from a narrow window high on the wall, casting a faint glow across the room.\n"
						+ "As you sit up, you notice a simple wooden bench against one wall and a tattered piece of paper resting on it.\n"
						+ "There is also a sword, a chestplate, and a health potion on the floor, which you should pick up in order to protect yourself.\n"
						+ "The air is heavy, and a deep silence fills the room.\n"
						+ "An old door stands opposite you, with who knows what on the other side.\n"
						+ "To see a list of available commands type \"help\"";
				Room room1 = new BeginnerRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room1;
				break;
			// I've combined CellarRoom and BeginnerRoom, so this code is no longer needed
			/**
			 * case 1: items.add(new Item("rusty key", "The key is cold and covered in rust.
			 * It’s likely been here for years, maybe decades.")); items.add(new
			 * Item("flashlight", "An old flashlight with scratches on the surface. The
			 * battery is weak, but it flickers to life when you switch it on."));
			 * items.add(new Item("journal page", "A brittle piece of paper with faded
			 * writing. One line catches your eye: ‘Beware the shadows – not all that hides
			 * is empty."));
			 * 
			 * objects.add("cellar"); roomDescription = "You arrive in a damp, shadow-filled
			 * cellar.\n" + "The walls are lined with stone, dripping with moisture, and an
			 * uneasy silence fills the air.\n" + "A faint, stale smell surrounds you.\n" +
			 * "Scattered across a dusty wooden table nearby are a few items: an old
			 * flashlight, a rusted key, and a torn page from a journal.";
			 * 
			 * // Room room2 = new CellarRoom(roomDescription, roomCommands, items,
			 * objects); // roomList[i] = room2; break;
			 */
			// Set up for the first battle room
			case 1:
				roomCommands.add("attack");
				roomCommands.add("dodge");
				roomCommands.add("heal");
				roomDescription = "You enter a circular room with two torches providing light, one on each side.\n"
						+ "Illuminated by the light, a troll stands in the center of the room, blocking the path forward,\n"
						+ "You must defeat the troll in order to move through the dungeon!\n";
				Room room2 = new FirstBattleRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room2;
				break;
			case 2:
				roomDescription = "You move into a new mysterious room, there is something puzzling about your atmospher.\n"
						+ " As you look around you notice a head on the wall and you approach it.\n"
						+ " Suddenly the head starts to talk";
				Room room3 = new RiddleRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room3;
				break;
			case 3:
				roomDescription = "This is the wordle room";
				Room room4 = new PuzzleRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room4;
				break;
			// Set up for the first battle room
			case 4:
				roomDescription = "Room 5 not completed yet!";
				Room room5 = new FirstBattleRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room5;
				break;
			// Set up for the first battle room
			case 5:
				roomDescription = "Room 6 not completed yet!";
				Room room6 = new TreasureRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room6;
				break;
			// Set up for the first battle room
			case 6:
				roomDescription = "Room 7 not completed yet!";
				Room room7 = new FinalBossRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room7;
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
	public static void printHelpMessage(ArrayList<String> roomCommands) {
		System.out.println("List of available commands: ");
		for (String command : roomCommands) {
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
	 * Main method for the game. Initializes the game, sets up the rooms, and begins
	 * the main game loop where the player inputs commands and interacts with the
	 * game.
	 * 
	 * @param args Command-line arguments, if any.
	 */
	public static void main(String[] args) {
		Room[] roomList = setUpRooms(); // Sets up rooms with items, objects, and commands
		ArrayList<Item> inventory = new ArrayList<Item>(); // Player's starting inventory
		Weapon wep1 = new Weapon("No Weapon", "", 0, 0, 0);
        Armor arm1 = new Armor("No Armor", "", 0, 0);
        inventory.add(wep1);
        inventory.add(arm1);
		Player player = new Player("Name", 20, inventory, roomList); // Create player character

		System.out.println("Welcome to 'What Lies Beneath Miami!\n\n"
				+ "The objective of the game is to beat each challenge in each new room you enter.\n"
				+ "After you have beaten the challenge you can progress to the next one through the door.\n"
				+ "Good Luck!");
		// System.out.println(player.getCurrentRoom().roomDescription);
		Scanner scan = new Scanner(System.in);
		CommandHandler commandHandler = new CommandHandler(player, scan); // Initialize command handler

		// Switch case for commands was moved into CommandHandler
		// See the commonCommands method within there for details

		// Runs the "main method" of each room.
		BeginnerRoom bR = (BeginnerRoom) roomList[0];
		bR.run(commandHandler, player);
		
		// Creates the enemy the player will battle in the first battle room.
        ArrayList<Item> enemyInventory = new ArrayList<Item>();
        Weapon wep = new Weapon("Weapon 1", "A weapon", 5, 80, 10);
        Armor arm = new Armor("Armor 1", "A piece of armor", 3, 10);
        enemyInventory.add(wep);
        enemyInventory.add(arm);
        // Dialog for the enemy
        ArrayList<String> enemyDialog = new ArrayList<String>();
        enemyDialog.add("You'll pay for that.");
        enemyDialog.add("That hurts");
        Enemy enemy1 = new Enemy("Bob the Troll", 25, enemyInventory, enemyDialog);
        FirstBattleRoom firstBR = (FirstBattleRoom) roomList[1];
		firstBR.run(commandHandler, player, enemy1);

		RiddleRoom rR = (RiddleRoom) roomList[2];
		rR.run(commandHandler, player);

		PuzzleRoom pR = (PuzzleRoom) roomList[3];
		pR.run(commandHandler);

		MiniBossRoom mBR = (MiniBossRoom) roomList[4];
		//mBR.run(commandHandler, player);
		
		TreasureRoom tR = (TreasureRoom) roomList[5];
		//tR.run(commandHandler, player);
		
		FinalBossRoom finalBR = (FinalBossRoom) roomList[6];
		//finalBR.run(commandHandler, player);

	}
}

// Old system for handling commands
//if (roomCommands.containsKey(input.toLowerCase())) {
//String methodName = roomCommands.get(input.toLowerCase());
//try {
//  Method method = CommandHandler.class.getMethod(methodName);
//  method.invoke(commandHandler);
//} catch (Exception e) {
//
//}
//} else {
//System.out.println("Unknown command. Type 'Help' for a list of commands.");
//}

// Switch case that was moved into command handler
//switch (input.toLowerCase()) {
//// Global
//case "exit" :
//	exitProgram(scan);
//	break;
//// Global
//case "help" :
//	printHelpMessage(roomCommands);
//	break;
//// Local
//case "items" :
//	currentRoom.displayItems();
//	break;
//// Local
//case "objects" :
//	currentRoom.displayObjects();
//	break;
//// Local
//case "examine":
//	commandHandler.examine();
//    break;
//// Local
//case "take":
//	commandHandler.take();
//    break;
//// Local
//case "equip":
//    commandHandler.equip();
//    break;
//// Local
//case "use":
//    commandHandler.use();
//    break;
//case "next room" :
//	player.nextRoom();
//break;
//}