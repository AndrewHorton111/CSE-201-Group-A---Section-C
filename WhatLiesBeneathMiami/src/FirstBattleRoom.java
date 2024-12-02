
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
			System.out.print("> ");
			
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
	        	ch.attack(enemy, player);
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
}
