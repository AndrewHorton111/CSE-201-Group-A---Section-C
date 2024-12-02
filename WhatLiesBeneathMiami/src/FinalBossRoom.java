
/**
 * Class: CSE 201
 * @version 1.0
 * @author: Andrew Horton
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is where the final boss fight will happen.
 * This is the last room in the game.
 */
public class FinalBossRoom extends Room {

	public FinalBossRoom(String roomDescription, ArrayList<String> roomCommands, ArrayList<Item> items, ArrayList<String> objects) {
		super(roomDescription, roomCommands, items, objects);
	}
	
	public void run(CommandHandler ch, Player player, Enemy enemy) {
		System.out.println(roomDescription);
		Scanner scan = new Scanner(System.in);
		// While loop that runs until either the player
		// or the enemy is defeated.
		int turns = 0;
		while (player.getHealth() > 0 && enemy.getHealth() > 0) {
			System.out.println("\nYou have " + player.health + " health left");
			System.out.println(enemy.name + " has " + enemy.health + " health left");
			System.out.println(("Choose your action: Attack, Dodge, or Heal"));
			System.out.print("> ");
			
			// Get input from the user and check if it is valid
			String input = scan.nextLine();
			if (!CommandHandler.checkInput(roomCommands, input)) {
				continue;
			}
			System.out.println();
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
	        	// Every 3 turns, the boss attacks twice in one turn.
	        	if (turns % 3 == 0) {
	        		ch.attack(enemy, player);
	        	} else if (turns % 3 == 2) { // Give a warning before the boss's double turn
	        		System.out.println("The boss is winding up for a strong attack. Watch Out!");
	        	}
	        	System.out.println(enemy.getName() + ": " + enemy.getRandomDialog());
	        }
	        turns++;
		} // End of while loop
		
		// When the while loop ends, check if the user or enemy won.
		if (player.getHealth() <= 0) {
	        System.out.println("You have been defeated by the The Boss");
	        System.out.println("GAME OVER");
	        // Close the program if the user lost.
	        System.exit(0);
	        
	    } else {
	        System.out.println("You have defeated the Boss and are able to move out of the dungeon!");
	    }
	}
}