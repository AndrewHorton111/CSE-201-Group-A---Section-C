/**
 * Class: CSE 201
 * @author: Rhett Bilski
 * @version: 1.0
 */

import java.util.*;
// import java.util.Map;
import java.util.Random;

/**
 * Assisted heavily by
 * https://github.com/mtcnbzks/java-wordle/blob/main/src/main/java/Wordle.java
 * 
 */
public class PuzzleRoom extends Room {

	protected int tryCount = 1;
	protected ArrayList<String> words = new ArrayList<String>();
	
	public PuzzleRoom(String roomDescription, ArrayList<String> roomCommands, ArrayList<Item> items,
			ArrayList<String> objects) {
		super(roomDescription, roomCommands, items, objects);
	}
	
	public void run(CommandHandler ch) {
		wordle();
	}

	public void wordle() {
		Scanner kb = new Scanner(System.in);
		String guessWord;

		while (true) {
			System.out.print("Enter your word: ");
			guessWord = kb.nextLine().toUpperCase();

			if (guessWord.length() != 5) {
				System.out.println("The length of the word must be five!" + " Try again!");
			} else if (guessWord.equals(TARGET_WORD)) {
				System.out.printf("You got it! It took you %d tries!", tryCount);
				kb.close();
				return;
			} else {
				checkWord(guessWord);
			}

			if (tryCount > 6) {
				System.out.println("You're out of tries :(");
				System.out.println("The right answer was " + TARGET_WORD + ".");
				kb.close();
				return;
			}
		}
	}

	public void setWords() {
		// #1
		words.add("MIAMI");
		// #2
		words.add("CLASS");
		// #3
		words.add("SMART");
		// #4
		words.add("MAJOR");
		// #5
		words.add("MINOR");
		// #6
		words.add("GRADE");
		// #7
		words.add("TUTOR");
		// #8
		words.add("TEACH");
		// #9
		words.add("HONOR");
		// #10
		words.add("BRICK");
	}


	public String getWord(ArrayList<String> words) {
		setWords();
		//int randomIndex = (int) (Math.random() * words.size());
		Random random = new Random();
		int randomIndex = random.nextInt(words.size());
		return words.get(randomIndex);
	}

	/**
	 * This should probably be moved to the top for clarity
	 */
	public final String TARGET_WORD = getWord(words);
	// Portion assisted by
	// https://medium.com/strategio/build-a-wordle-clone-in-java-c7b7b924fb8d
	public final String ANSI_RESET = "\u001B[0m";
	public final String ANSI_GREEN = "\u001B[32m";
	public final String ANSI_YELLOW = "\u001B[33m";

	public String checkWord(String word) {
		System.out.printf("Try #%d (%s) : \n", tryCount, word);
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);

			if (isCharacterInWord(c)) {
				if (TARGET_WORD.charAt(i) == c) {
					// STRING BUILDER: GREEN
					b.append(ANSI_GREEN + c + ANSI_RESET);
				} else {
					// STRING BUILDER: YELLOW
					b.append(ANSI_YELLOW + c + ANSI_RESET);
				}
			} else {
				// STRING BUILDER: WHITE
				b.append(c);
			}
		}
		tryCount++;
		System.out.println();
		return b.toString();

	}

	public boolean isCharacterInWord(char c) {
		for (int i = 0; i < TARGET_WORD.length(); i++) {
			if (TARGET_WORD.charAt(i) == c) {
				return true;
			}
		}
		return false;
	}

}
