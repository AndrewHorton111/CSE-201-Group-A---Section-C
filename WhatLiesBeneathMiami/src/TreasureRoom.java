/**
 * Class: CSE 201
 * @author Andrew Horton
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the child class of Room. It holds the Items that the
 * user found find in this room.
 */
public class TreasureRoom extends Room {

	ArrayList<Item> itemList;
	
	/**
	 * Constructor for the TreasureRoom class.
	 * 
	 * @param roomDescription
	 * @param acceptableCommands
	 * @param itemList
	 */
	public TreasureRoom(String roomDescription, HashMap<String, String> acceptableCommands, ArrayList<Item> itemList) {
		super(roomDescription, acceptableCommands);
		this.itemList = itemList;
	}
}
