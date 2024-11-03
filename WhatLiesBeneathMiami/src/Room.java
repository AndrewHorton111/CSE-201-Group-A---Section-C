/**
 * Class: CSE 201
 * @author Andrew Horton
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * This class is the parent class of TreasureRoom and BattleRoom, and
 * holds the description of the room and the list of acceptable commands.
 */
public class Room {

	String roomDescription;
	ArrayList<String> validCommands;
	boolean roomCleared;
	
	/**
	 * Constructor for the Room class.
	 * 
	 * @param roomDescription
	 * @param acceptableCommands
	 */
	public Room(String roomDescription, ArrayList<String> validCommands) {
		super();
		this.roomDescription = roomDescription;
		this.validCommands = validCommands;
		// roomCleared is always false by default. 
		roomCleared = false;
	}
	
	/**
	 * Method to get the valid commands for the room.
	 * 
	 * @return validCommands
	 */
	public ArrayList<String> getValidCommands() {
		return validCommands;
	}
}
