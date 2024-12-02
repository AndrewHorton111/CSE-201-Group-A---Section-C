
/**
 * Class: CSE 201
 * @author Andrew Horton
 * @author Koray Bayram
 * @author Jacob Artnak
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * This class is the parent class of Player and Enemy. It holds the
 * name, health, and inventory for both of these classes.
 */
public class Character {

	String name;
	int health;
	ArrayList<Item> inventory;
	
	/**
	 * Constructor for the Character class.
	 * 
	 * @param name
	 * @param health
	 * @param inventory
	 */
	public Character(String name, int health, ArrayList<Item> inventory) {
		super();
		this.name = name;
		this.health = health;
		this.inventory = inventory;
	}
	
	/**
	* Method to get the name of the character
	*
	* @return name
	*/
	public String getName() {
		
		return name;
	}
	
	/**
	* Method to get the health of the character
	*
	* @return health
	*/
	public int getHealth() {
		
		return health;
	}
	
	/**
	* Method to set the health of the character
	*
	* @param newHealth
	*/
	public void setHealth(int newHealth) {
		health = newHealth;
	}
	
	/**
	* Method to modify the health of the character based on their
	* current health and the changeAmount parameter
	*
	* @param changeAmount
	*/
	public void modifyHealth(int changeAmount) {
		health += changeAmount;
	}
	
	/**
	* Method to get the inventory of the character
	*
	* @return inventory
	*/
	public ArrayList<Item> getInventory() {
		System.out.println(inventory);
		return inventory;
	}	
}