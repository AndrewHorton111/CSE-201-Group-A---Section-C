
/**
 * Class: CSE 201
 * @version 1.0
 * @author: Andrew Horton
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class for room number 2, which contains the first enemy the
 * player will fight.
 */
public class FirstBattleRoom extends Room {

	public FirstBattleRoom(String roomDescription, ArrayList<String> roomCommands, ArrayList<Item> items,
			ArrayList<String> objects) {
		super(roomDescription, roomCommands, items, objects);
	}

	public void run(CommandHandler ch, Player player, Enemy enemy) {
		System.out.println(roomDescription);
		Scanner scan = new Scanner(System.in);
		// While loop that runs until either the player
		// or the enemy is defeated.
		while (player.getHealth() > 0 && enemy.getHealth() > 0) {
			System.out.println("You have " + player.health + " health left");
			System.out.println(enemy.name + " has " + enemy.health + " health left");
			System.out.println(("Choose your action: Attack, Dodge, or Heal"));
			System.out.println("> ");
			
			// Get input from the user and check if it is valid
			String input = scan.nextLine();
			if (!CommandHandler.checkInput(roomCommands, input)) {
				continue;
			}
			
			// If input is valid, do the corresponding action
			switch(input) {
				case "attack":
	                ch.attack(player, enemy);
	                break;
	             
				case "dodge":
					boolean enemyAttack = ch.dodge(player);
					if (!enemyAttack) {
						continue;
					}
	                break;
	                
				case "heal":
					ch.heal(player);
	                break;
				default:
					ch.commonCommands(input, this);
					break;
			}
			
			// The enemy attacks if it is still alive
	        if (enemy.getHealth() > 0) {
	            int enemyDamage = calculateDamage("enemy");
	            player.setHealth(player.getHealth() - enemyDamage);
	            System.out.println("The Mini Boss attacks and deals " + enemyDamage + " damage to you!");
	            displayDialog();
	        }
		} // End of while loop
		
		// When the while loop ends, check if the user or enemy won.
		if (player.getHealth() <= 0) {
	        System.out.println("You have been defeated by the Bob the Troll!");
	        System.out.println("GAME OVER");
	        // Close the program if the user lost.
	        System.exit(0);
	        
	    } else {
	        System.out.println("You have defeated the Troll and move on to the next room!");
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
}
