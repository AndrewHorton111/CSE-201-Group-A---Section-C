
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
		        items.add(new Potion("Health potion", "A potion that replenishes your health", "health", 15, 2));
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
				roomCommands.add("hint");
				roomCommands.add("answer");
				roomDescription = "\nYou move into a new mysterious room, there is something puzzling about your atmosphere.\n"
						+ "As you look around you notice a head on the wall and you approach it.\n"
						+ "Suddenly the head starts to talk.\n"
						+ "\"To advance you must beat me in a battle of wits.\"\n"
						+ "\"Answer my question and you may pass.\"";
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
				roomDescription = "The next room you enter is very dark, with the only light coming from the door behind you.\n"
						+ "However, you hear a faint rattle sound coming from underneath the ground in the room.\n"
						+ "Suddenly, Jill the Snake appears from the ground and immediatly moves to attack you!";
				roomCommands.add("attack");
				roomCommands.add("dodge");
				roomCommands.add("heal");
				Room room5 = new MiniBossRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room5;
				break;
			// Set up for the first battle room
			case 5:
				roomDescription = "You move into a medium-sized room that is filled with millions of dollars in gold and jewlery.\n"
						+ "Among the riches, you also find a large axe and a boots made of diamonds that you can take.\n"
						+ "At the end of the room is an ominous door, that you feel hides your toughest challenge yet!";
				roomCommands.add("open door");
				roomCommands.add("items");
				roomCommands.add("objects");
				roomCommands.add("examine");
				roomCommands.add("take");
				items.add(new Weapon("Axe", "A strong and powerful weapon", 20, 0, 25));
		        items.add(new Armor("Boots", "Boots that are have high damage reduction and high mobility", 12, 30));
		        items.add(new Potion("Health potion", "A potion that replenishes your health", "health", 15, 3));
				Room room6 = new TreasureRoom(roomDescription, roomCommands, items, objects);
				roomList[i] = room6;
				break;
			// Set up for the first battle room
			case 6:
				roomDescription = "As you open the door, you move into a large arena.\n"
						+ "Along the outside, you see that there are hundreds of goblins, trolls, and other monsters.\n"
						+ "After a moment, a large creature drops from the ceiling to challenge you.\n"
						+ "The crowd chants its name: \n"
						+ "Crawdaddy! Crawdaddy! Crawdaddy!\n";
				roomCommands.add("attack");
				roomCommands.add("dodge");
				roomCommands.add("heal");
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
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name adventurer?");
        System.out.print("> ");
        String playerName = scan.nextLine();
		Player player = new Player(playerName, 30, inventory, roomList); // Create player character

		System.out.println("Welcome to 'What Lies Beneath Miami!\n\n"
				+ "The objective of the game is to beat each challenge in each new room you enter.\n"
				+ "After you have beaten the challenge you can progress to the next one through the door.\n"
				+ "Good Luck!");
		// System.out.println(player.getCurrentRoom().roomDescription);
		CommandHandler commandHandler = new CommandHandler(player, scan); // Initialize command handler

		// Switch case for commands was moved into CommandHandler
		// See the commonCommands method within there for details

		// Runs the "main method" of each room.
		BeginnerRoom bR = (BeginnerRoom) roomList[0];
		bR.run(commandHandler, player);
		
		// Creates the enemy the player will battle in the first battle room.
        ArrayList<Item> enemyInventory = new ArrayList<Item>();
        Weapon wep = new Weapon("Weapon 1", "A weapon", 3, 0, 10);
        Armor arm = new Armor("Armor 1", "A piece of armor", 3, 10);
        enemyInventory.add(wep);
        enemyInventory.add(arm);
        // Dialog for the enemy
        ArrayList<String> enemyDialog = new ArrayList<String>();
        enemyDialog.add("You'll pay for that!");
        enemyDialog.add("I'm going to eat you for dinner!");
        Enemy enemy1 = new Enemy("Bob the Troll", 25, enemyInventory, enemyDialog);
        FirstBattleRoom firstBR = (FirstBattleRoom) roomList[1];
		firstBR.run(commandHandler, player, enemy1);

		RiddleRoom rR = (RiddleRoom) roomList[2];
		rR.run(commandHandler, player);

		PuzzleRoom pR = (PuzzleRoom) roomList[3];
		pR.run(commandHandler);

		// Creates the enemy the player will battle in the mini boss room.
        ArrayList<Item> enemyInventory2 = new ArrayList<Item>();
        Weapon wep2 = new Weapon("Weapon 2", "A weapon", 6, 0, 15);
        Armor arm2 = new Armor("Armor 2", "A piece of armor", 4, 15);
        enemyInventory2.add(wep2);
        enemyInventory2.add(arm2);
        // Dialog for the enemy
        ArrayList<String> enemyDialog2 = new ArrayList<String>();
        enemyDialog2.add("You'll regret fighting me!");
        enemyDialog2.add("Enjoy you're last moments!");
        Enemy enemy2 = new Enemy("Jill the Snake", 50, enemyInventory2, enemyDialog2);
		MiniBossRoom mBR = (MiniBossRoom) roomList[4];
		mBR.run(commandHandler, player, enemy2);
		
		TreasureRoom tR = (TreasureRoom) roomList[5];
		tR.run(commandHandler, player);
		
		// Creates the enemy the player will battle in the final boss room.
        ArrayList<Item> enemyInventory3 = new ArrayList<Item>();
        Weapon wep3 = new Weapon("Weapon 3", "A weapon", 10, 0, 15);
        Armor arm3 = new Armor("Armor 3", "A piece of armor", 5, 20);
        enemyInventory3.add(wep3);
        enemyInventory3.add(arm3);
        // Dialog for the enemy
        ArrayList<String> enemyDialog3 = new ArrayList<String>();
        enemyDialog3.add("You'll never leave this dungeon!");
        enemyDialog3.add("You're going to have to dropout from life!");
        Enemy enemy3 = new Enemy("The Crawdaddy", 100, enemyInventory3, enemyDialog3);
		FinalBossRoom finalBR = (FinalBossRoom) roomList[6];
		finalBR.run(commandHandler, player, enemy3);

		// The epilogue to the story, wrapping up the story and the game.
		System.out.println("You escape the dungeon and save Miami. Yippie :)");
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