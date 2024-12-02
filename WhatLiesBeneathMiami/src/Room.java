/**
 * Class: CSE 201
 * This class represents a Room in the game, containing a description, items,
 * objects, and acceptable commands for interaction. It serves as a base class 
 * for specialized room types like TreasureRoom and BattleRoom.
 * 
 * @author Andrew Horton
 * @author Jacob Artnak
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

public class Room {

	String roomDescription;
	protected ArrayList<String> roomCommands;  // Stores command and corresponding action method name
	private List<Item> items;  // List of items in the room
	boolean roomCleared;
	ArrayList<String> objects; // List of objects (e.g., table, cellar, etc.)

	/**
	 * Constructor for the Room class.
	 * 
	 * @param roomDescription A description of the room's appearance and atmosphere.
	 * @param roomCommands    A map containing valid commands and their associated
	 *                        action methods.
	 * @param items           A list of items available in the room.
	 * @param objects         A list of objects present in the room.
	 */
	public Room(String roomDescription, ArrayList<String> roomCommands, ArrayList<Item> items, ArrayList<String> objects) {
		super();
		this.roomDescription = roomDescription;
		this.roomCommands = roomCommands;
		this.items = items;
		this.objects = objects;
		// roomCleared is always false by default.
		this.roomCleared = false;
	}

	/**
	 * Retrieves the valid commands for the room.
	 * 
	 * @return A map of roomCommands, where the keys are command strings and values
	 *         are associated method names.
	 */
	public ArrayList<String> getRoomCommands() {
        return roomCommands;
    }

	/**
	 * Checks if a specified object is present in the room.
	 * 
	 * @param objectName The name of the object to look for.
	 * @return true if the object is found, false otherwise.
	 */
	public boolean containsObject(String objectName) {
		for (String name : objects) {
			if (name.equalsIgnoreCase(objectName)) {
				return true;
			}
		}
		return false;	
	}

	/**
	 * Retrieves an item in the room by its name.
	 * 
	 * @param itemName The name of the item to retrieve.
	 * @return The item if found, or null if the item is not present in the room.
	 */
	public Item getItem(String itemName) {
		for (Item item : items) {
			if (item.name.equalsIgnoreCase(itemName)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Displays all items currently available in the room.
	 */
	public void displayItems() {
		String result = "Items in the room: ";
		for (Item item : items) {
			result += item.name + ", ";
		}
		System.out.println(result);
	}

	/**
	 * Displays all objects currently present in the room.
	 */
	public void displayObjects() {
		String result = "Objects in the room: ";
		for (String object : objects) {
			result += object + ", ";
		}
		System.out.println(result);
	}
	
	/**
	 * Removes an item from a room's item list.
	 * 
	 * @param itemName
	 */
	public void removeItem(Item item) {
		for (Item i : items) {
			if (i == item) {
				items.remove(item);
				return;
			}
		}
	}
}