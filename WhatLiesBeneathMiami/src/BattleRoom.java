/**
 * Class: CSE 201
 * @author Andrew Horton
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the child class of Room. It holds the list of
 * potential enemies you could fight in this room.
 */
public class BattleRoom extends Room {

	ArrayList<Enemy> enemyList;
	
	/**
	 * Constructor for the BattleRoom class.
	 * 
	 * @param roomDescription
	 * @param acceptableCommands
	 * @param enemyList
	 */
	public BattleRoom(String roomDescription, HashMap<String, String> acceptableCommands, ArrayList<Enemy> enemyList) {
		super(roomDescription, acceptableCommands);
		this.enemyList = enemyList;
	}
}
