/**
 * @author Adam Faglie
 * 
 * 
 */

import java.util.ArrayList;
import java.util.Map;

/**
 * 
 */
public class MiniBossRoom extends Room {

	ArrayList<String> dialogList = new ArrayList<>;
	dialogList.add("I'm better than you");
	dialogList.add("You suck");
	
	ArrayList<Item> inventory = new ArrayList<>;
	
	Enemy miniBoss = new Enemy("Mini Boss", 50, this.inventory, this.dialogList);
	
	public MiniBossRoom(String roomDescription, Map<String, String> roomCommands, ArrayList<Item> items, ArrayList<String> objects) {
		super(roomDescription, roomCommands, items, objects);
	}
	
	/*
	 * Starts fight with mini boss
	 */
	public void initiateCombat() {
		
	}
	
	/*
	 * Calculates the characters damage taken during the fight
	 */
	public void calculateDamage() {
		
	}
	
	/*
	 * displays mini boss's dialog during the fight
	 */
	public void displayDialog() {
		
	}
	
	
}
