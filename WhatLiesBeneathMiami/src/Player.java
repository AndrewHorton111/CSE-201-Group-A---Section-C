/**
 * Class: CSE 201
 * @author Andrew Horton
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * This class is the child class of Player and it holds the room the
 * player is currently in and can be used to manage the inventory. 
 */
public class Player extends Character {

	int currentRoom;
	
	/**
	 * Constructor for the Player class.
	 * 
	 * @param name
	 * @param health
	 * @param inventory
	 * @param roomId
	 */
	public Player(String name, int health, ArrayList<Item> inventory, int roomId) {
		super(name, health, inventory);
		currentRoom = roomId;
	}
	
	/**
	 * Method to get the currentRoom the Player is in.
	 * 
	 * @return currentRoom
	 */
	public int getCurrentRoom() {
		return currentRoom;
	}
	
	/**
	 * Method that increments your room number when the Player
	 * completes a room.
	 */
	public void nextRoom() {
		currentRoom++;
	}
	
	/**
	 * Method to set the Player's name
	 * 
	 * @param newName
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	/**
	 * Method that prints the Player's inventory to the screen
	 */
	public void displayInventory() {
		return;
	}
	
	/**
	 * Method that adds in item to the Player's inventory
	 * 
	 * @param itemToAdd
	 */
	public void addToInventory(Item itemToAdd) {
		return;
	}
	
	/**
	 * Method to remove an item from the Player's inventory
	 * with the name of the item.
	 * 
	 * @param itemName
	 */
	public void removeFromInventory(String itemName) {
		return;
	}
	
}
