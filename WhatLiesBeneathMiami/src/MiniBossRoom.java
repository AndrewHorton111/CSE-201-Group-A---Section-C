/**
 * @author Adam Faglie
 * 
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */
public class MiniBossRoom extends Room {

	ArrayList<String> dialogList = new ArrayList<>();
	{
		dialogList.add("I'm better than you");
		dialogList.add("You suck");
	}
	
	ArrayList<Item> inventory = new ArrayList<>();
	
	Enemy miniBoss = new Enemy("Mini Boss", 50, this.inventory, this.dialogList);
	
	Player player;
	
	public MiniBossRoom(String roomDescription, ArrayList<String> roomCommands, ArrayList<Item> items, ArrayList<String> objects, Player player) {
		super(roomDescription, roomCommands, items, objects);
		this.player = player;
	}
	
	/*
	 * Starts fight with mini boss
	 */
	public void initiateCombat() {
		System.out.println("You think you can beat me? Looks like you'll try!");
		Scanner scanner = new Scanner(System.in);
		
		// while neither player or mini boss has been beaten
		while (player.getHealth() > 0 && miniBoss.getHealth() > 0) {
			System.out.println("\nYour Health: " + player.getHealth());
			System.out.println("Mini Boss Health: " + miniBoss.getHealth());
			System.out.println(("Choose your action: [1] Attack [2] Dodge [3] Heal"));
			
			String action = scanner.nextLine();
			switch(action) {
				case "1": // attack
					int playerDamage = calculateDamage("player");
					miniBoss.setHealth(miniBoss.getHealth() - playerDamage);
	                System.out.println("You deal " + playerDamage + " damage to the Mini Boss!");
	                break;
	             
				case "2": // dodge
					System.out.println("You attempt to dodge the Mini Boss's next attack!");
	                if (Math.random() < 0.5) {
	                    System.out.println("You successfully dodged the attack!");
	                    continue; // Skip Mini Boss's turn
	                } else {
	                    System.out.println("You failed to dodge!");
	                }
	                break;
	                
				case "3": // heal
					Item potion = player.getItem("health potion");
					if (potion != null) {
	                    System.out.println("You use a health potion to heal yourself!");
	                    player.modifyHealth(20); // Adjust healing value as needed
	                    player.removeFromInventory(potion.name);
	                } else {
	                    System.out.println("You don't have any health potions!");
	                }
	                break;

	            default:
	                System.out.println("Invalid choice. The Mini Boss takes advantage of your hesitation!");
	                break;
			}
			
			// Mini Boss attacks if still alive
	        if (miniBoss.getHealth() > 0) {
	            int miniBossDamage = calculateDamage("miniBoss");
	            player.setHealth(player.getHealth() - miniBossDamage);
	            System.out.println("The Mini Boss attacks and deals " + miniBossDamage + " damage to you!");
	            displayDialog();
	        }
		}
		
		if (player.getHealth() <= 0) {
	        System.out.println("You have been defeated by the Mini Boss! Sucks to suck");
	    } else {
	        System.out.println("You have defeated the Mini Boss!");
	    }
	}
	
	/*
	 * Calculates the characters damage taken during the fight
	 */
	public int calculateDamage(String attacker) {
		int playerDamage = 10; // base damage
		Weapon sword = (Weapon) player.getItem("sword");
		if (sword != null) {
			playerDamage = sword.damage;
		}
		int miniBossDamage = 15; // adjust this for difficulty scaling
		
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
		int index = (int)(Math.random() * dialogList.size());
		System.out.println(miniBoss.getName() + ": " + dialogList.get(index));
	}
	
	public void testMethod() {
		
	}	
}