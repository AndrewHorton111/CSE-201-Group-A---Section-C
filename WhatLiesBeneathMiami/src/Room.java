/**
 * Class: CSE 201
 * @author Andrew Horton
 * @author Jacob Artnak
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the parent class of TreasureRoom and BattleRoom, and
 * holds the description of the room and the list of acceptable commands.
 */
public class Room {

	String roomDescription;
	private Map<String, String> roomCommands;  // Store command and corresponding action method name
	private List<Item> items;  // List of items in the room
	boolean roomCleared;
	ArrayList<String> objects; // List of objects (table, cellar, etc.)
	
	/**
	 * Constructor for the Room class.
	 * 
	 * @param roomDescription
	 * @param acceptableCommands
	 */
	public Room(String roomDescription, Map<String, String> roomCommands, ArrayList<Item> items, ArrayList<String> objects) {
		super();
		this.roomDescription = roomDescription;
		this.roomCommands = roomCommands;
		this.items = items;
		this.objects = objects;
		// roomCleared is always false by default. 
		this.roomCleared = false;
	}
	
	/**
	 * Method to get the valid commands for the room.
	 * 
	 * @return roomCommands
	 */
	
	public Map<String, String> getRoomCommands() {
        return roomCommands;
    }

	
	/**
	 * Method to find out if a certain object is in a room
	 * @param objectName
	 * @return true if found, false otherwise
	 */
	public boolean containsObject(String objectName) {
		
		for (String name : objects) {
			if (name.toLowerCase().equals(objectName.toLowerCase()))
				return true;
		}
		
		return false;	
		
	}
	public Item getItem(String itemName) {
		for (Item item : items) {
			if (item.name.toLowerCase().equals(itemName.toLowerCase()))
				return item;
		}
		
		return null;
	}
	
	public void displayItems() {
		
		String result = "Items in the room: ";
		for (Item item : items) {
			result+=item.name+ ", ";
		}
		
		System.out.println(result);
	}
	public void displayObjects() {
		
		String result = "Objects in the room: ";
		for (String object : objects) {
			result+=object+ ", ";
		}
		
		System.out.println(result);
	}
	
	
}
