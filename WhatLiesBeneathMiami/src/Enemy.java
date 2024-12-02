/**
 * Class: CSE 201
 * @author Andrew Horton
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is the child class of Player and it holds the dialog
 * that the monster can say during combat. 
 */
public class Enemy extends Character {

	ArrayList<String> dialogList;
	Random random = new Random();
	
	/**
	 * Constructor for the Enemy class.
	 * 
	 * @param name
	 * @param health
	 * @param inventory
	 * @param dialogList
	 */
	public Enemy(String name, int health, ArrayList<Item> inventory, ArrayList<String> dialogList) {
		super(name, health, inventory);
		this.dialogList = dialogList;
	}
	
	/**
	 * This method prints a random dialog from its dialogList
	 */
	public String getRandomDialog() {
		int randNum = random.nextInt(dialogList.size());
		return dialogList.get(randNum);
	}
	
	
}