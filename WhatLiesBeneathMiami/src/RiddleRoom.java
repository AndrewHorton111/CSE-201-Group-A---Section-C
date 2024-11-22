
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
			ArrayList<String> objects, Player player) {
		super(roomDescription, roomCommands, items, objects);
	}
	
	public void run(CommandHandler cmd) {
		Scanner in = new Scanner(System.in);
		System.out.println(roomDescription);
	}

	public static void printWelcome() {
		System.out.println("Welcome to the riddle room");
		System.out.println("To make it past you will need to answer my riddles correctly");
		System.out.println("Are you prepared to test your intelligence?");
		System.out.println();
		System.out.println("Press 'Y' to recieve riddles");
	}	

	private static void printRiddles() {
		// Load the riddles in
		HashMap<Integer, String> riddles = riddleStorage();
		// used for picking a random number that corresponds to a riddle
		Random random = new Random();
		Integer randomNumber = random.nextInt(3) + 1;

		// Print out the riddle here
		System.out.println(riddles.get(randomNumber));

	}

	// To choose a riddle randomly put all of them in a map and assign a key that is
	// numbers 1-x. Then using random have that select the key at the start
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
