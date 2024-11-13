/**
 * @author Adam Faglie
 * 
 * 
 */

import java.util.ArrayList;
import java.util.Map;
import java.Math.Random;

/**
 * 
 */
public class MiniBossRoom extends Room {

	ArrayList<String> dialogList = new ArrayList<>();
	dialogList.add("I'm better than you");
	dialogList.add("You suck");
	
	ArrayList<Item> inventory = new ArrayList<>();
	
	Enemy miniBoss = new Enemy("Mini Boss", 50, this.inventory, this.dialogList);
	
	Player player;
	
	public MiniBossRoom(String roomDescription, Map<String, String> roomCommands, ArrayList<Item> items, ArrayList<String> objects, Player player) {
		super(roomDescription, roomCommands, items, objects);
		this.player = player;
	}
	
	/*
	 * Starts fight with mini boss
	 */
	public void initiateCombat() {
		System.out.println("You think you can beat me? Looks like you'll try!");
		
		// while neither player or mini boss has been beaten
		while (player.getHealth() > 0 && miniBoss.getHealth() > 0) {
			// lets player attack first
			int damage = calculateDamage("player");
			miniBoss.setHealth(miniBoss.getHealth() - damage);
			System.out.println("Mini Boss Health: " + miniBoss.getHealth();
			displayDialog();
			
			// mini boss attack after player attacks
			damage = calculateDamage("miniBoss");
			player.setHealth(player.getHealth() - damage);
			System.out.println("Your Health: " + this.player.getHealth());
					+ 
			displayDialog();
		}
	}
	
	/*
	 * Calculates the characters damage taken during the fight
	 */
	public int calculateDamage(String attacker) {
		int playerDamage = CommandHandler.player.getItem("sword").damage;
		int miniBossDamage = 15; // temporary
		if (attacker.equals("player")) {
			return (int)(Math.random() * playerDamage);
		} else  {
			return (int)(Math.random() * miniBossDamage);
		}
	}	
	
	/*
	 * displays mini boss's dialog during the fight
	 */
	public void displayDialog() {
		
	}
	
	public void testMethod() {
		
	}
	
	
}
