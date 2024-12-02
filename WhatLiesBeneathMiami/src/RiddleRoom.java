/**
 * Class: CSE 201
 * @author Joe Follarth
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class RiddleRoom extends Room {


	public RiddleRoom(String roomDescription, ArrayList<String> roomCommands, ArrayList<Item> items,
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
		boolean solved = false;
		// print the character indicating user input and prepare to receive input
		int key = printRandomRiddle();
		while (running) {
			System.out.println("> ");
			String input = in.nextLine().trim();
			// check if the user command is a valid input
			if (CommandHandler.checkInput(roomCommands, input)) {
				// A switch case is used to determine the command. If it is a room
				// special command then included as a case. The default case handles
				// common commands using the CommandHandler class.
				switch (input) {
				case "answer":
					answer(key);
					solved = true;
					break;
				case "hint":
				    hint(key);
				    break;
				case "open door":
                    if (solved == true) {
                        running = openDoor(ch, running);
                    } else {
                        System.out.println("Answer the riddle first");
                    }
                    break;
				default:
					ch.commonCommands(input, this);
					break;
				}
			}
		}
	}
	
	private static void hint(int key) {
	    if (key == 1) {
	        System.out.println("Everytime you walk you leave this behind");
	    } else if (key == 2) {
	        System.out.println("Be as light as a ---");
	    } else {
	        System.out.println("Homo Sapiens");
	    }
	}
	
	/**
	 * Used for answering the riddle
	 */
	private static void answer(int key) {
		//  boolean firstGuessCorrect = false;
		boolean correct = false;
		HashMap<Integer, String> riddleAnswers = riddleAnswers();
		Scanner in = new Scanner(System.in);
		while (!correct) {
			System.out.println("Enter your answer: ");
			System.out.println("> ");
			String answer = in.nextLine().toLowerCase();
			
			if (answer.equals(riddleAnswers.get(key))) {
				System.out.println("Congratulations adventurer, you have answered my riddle\n"
						+ "To move onto the next room you may open the door and proceed");
				correct = true;
			} else {
				System.out.println("Incorrect Answer, Try again");
				// firstGuessCorrect = true;
			}
		}
	}

	/**
	 * responsible for retrieving a random riddle that will be
	 * displayed to the user
	 */
	private static int printRandomRiddle() {
		// Load the riddles in
		HashMap<Integer, String> riddles = riddleStorage();
		// used for picking a random number that corresponds to a riddle
		Random random = new Random();
		Integer randomNumber = random.nextInt(3) + 1;

		// Print out the riddle here
		System.out.println(riddles.get(randomNumber));
		return randomNumber;
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
	
	private static HashMap<Integer, String> riddleAnswers() {
		HashMap<Integer, String> answers = new HashMap<>();
		answers.put(1, "foot steps");
		answers.put(2, "feather");
		answers.put(3,  "a human");
		return answers;
	}
	
	private boolean openDoor(CommandHandler ch, boolean running) {
        running = ch.openDoor();
        return running;
    }

}