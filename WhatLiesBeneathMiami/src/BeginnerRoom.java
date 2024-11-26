/**
 * Class: CSE 201
 * @author Joe Follrath
 * @version 1.0, Finished
 */


import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class BeginnerRoom extends Room {
	
	public BeginnerRoom(String roomDescription, Map<String,String> roomCommands, ArrayList<Item> items, ArrayList<String> objects) {
		super(roomDescription, roomCommands, items, objects);
	}
	
	public void run(CommandHandler ch, Player player) {
		System.out.println(roomDescription);
		inputs(ch, player);
	}
	
	private void inputs(CommandHandler ch, Player player) {
		// create the scanner for this room
		Scanner in = new Scanner(System.in);
		// start a loop to use while in the room
		boolean running = true;
		while (running) {
			// print the character indicating user input and prepare to receive input
			System.out.println("> ");
			String input = in.nextLine().trim();
			// check if the user command is a valid input
			if (CommandHandler.checkInput(roomCommands, input)) {
				// A switch case is used to determine the command. If it is a room
				// special command then included as a case. The default case handles
				// common commands using the CommandHandler class.
				switch (input) {
				case "open door":
					openDoor(ch);
					running = false;
				case "current room":
					currentRoom(player);
				default:
					ch.commonCommands(input, this);
				}
			}
		}
	}
	
	// Methods that handle room commands can be found below
	// BeginnerRoom doesn't have many but more would be below if it did
	private void openDoor(CommandHandler ch) {
		ch.openDoor();
	}
	
	private void currentRoom(Player player) {
		System.out.println(player.getCurrentRoom());
	}




}
