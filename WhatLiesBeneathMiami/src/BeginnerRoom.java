/**
 * Class: CSE 201
 * @author Jacob Artnak
 * @version 1.0
 */


import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class BeginnerRoom extends Room {
	
	public BeginnerRoom(String roomDescription, Map<String,String> roomCommands, ArrayList<Item> items, ArrayList<String> objects) {
		super(roomDescription, roomCommands, items, objects);
	}
	
	public void run(CommandHandler ch) {
		System.out.println(roomDescription);
	}
	
	private void inputs() {
		// create the scanner for this room
		Scanner in = new Scanner(System.in);
		// start a loop to use while in the room
		while (true) {
			// print the character indicating user input and prepare to receive input
			System.out.println("> ");
			String input = in.next();
			// check if the user command is a valid input
			if (CommandHandler.checkInput(roomCommands, input)) {
				
			}
		}
	}
	
	// Methods that handle room commands can be found below
	private void openDoor() {
		
	}




}
