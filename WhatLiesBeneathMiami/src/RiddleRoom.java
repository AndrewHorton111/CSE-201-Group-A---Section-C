/**
 * Class: CSE 201
 * @author Joe Follarth
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class RiddleRoom extends Room {


	public RiddleRoom(String roomDescription, Map<String, String> roomCommands, ArrayList<Item> items,
			ArrayList<String> objects) {
		super(roomDescription, roomCommands, items, objects);
	}
	
	/**
	 * Called form game manager, responsible for running the room
	 * @param cmd The command handler so the room can access common commands
	 */
	public void run(CommandHandler cmd, Player player) {
		System.out.println(roomDescription);
		System.out.println("To answer the riddle first type \"answer\"\n"
				+ "for a hint type \"hint\"");
		inputs(cmd, player);
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
				case "":

				default:
					ch.commonCommands(input, this);
					break;
				}
			}
		}
	}

	/**
	 * Welcome message
	 */
	public static void printWelcome() {
		System.out.println("Welcome to the riddle room");
		System.out.println("To make it past you will need to answer my riddles correctly");
		System.out.println("Are you prepared to test your intelligence?");
		System.out.println();
		System.out.println("Press 'Y' to recieve riddles");
	}	

	/**
	 * responsible for retrieving a random riddle that will be
	 * displayed to the user
	 */
	private static void printRandomRiddle() {
		// Load the riddles in
		HashMap<Integer, String> riddles = riddleStorage();
		// used for picking a random number that corresponds to a riddle
		Random random = new Random();
		Integer randomNumber = random.nextInt(3) + 1;

		// Print out the riddle here
		System.out.println(riddles.get(randomNumber));

	}

	/**
	 * Create the riddles and puts them in to a Hashmap for use later
	 * @return A hashmap of the riddles
	 */
	private static HashMap<Integer, String> riddleStorage() {
		HashMap<Integer, String> riddles = new HashMap<>();
		riddles.put(1, "The more you take the more you leave behind. What am I?");
		riddles.put(2, "I am easy to lift but hard to throw. What am I?");
		riddles.put(3, "What creature walks on four legs in the morning,"
				+ " two legs at noon, and three legs in the evening?");

		return riddles;
	}

	public void riddleHints(int riddleNum) {
		
	}
}
