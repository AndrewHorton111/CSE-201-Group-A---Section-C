/**
 * Class: CSE 201
 * @author Joe Follarth
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Scanner;

public class RiddleRoom extends Room {
	Scanner in = new Scanner(System.in);
	
	public void printWelcome() {
		System.out.println("Welcome to the riddle room");
		System.out.println("To make it past you will need to answer my riddles correctly");
		System.out.println("Are you prepared to test your intelligence?");
		System.out.println();
		System.out.println("Press 'Y' to recieve riddles");
		
	}
	
	private ArrayList<String> riddleStorage() {
		ArrayList<String> riddles = new ArrayList<>();
		riddles.add("The more you take the more you leave behind. What am I?");
		riddles.add("I am easy to lift but hard to throw. What am I?");
		riddles.add("What creature walks on four legs in the morning,"
				+ " two legs at noon, and three legs in the evening?");
		
		return riddles;
	}
}
