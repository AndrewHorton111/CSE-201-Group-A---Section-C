
/**
 * Class: CSE 201
 * @author Joe Follarth
 * @author Jacob Artnak
 * @author Rhett Bilski
 * @author Adam Faglie
 * @author Andrew Horton
 * @author Koray Bayram
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * This is the master class that has all of the main method
 * and everything needed to run the UI.
 */
public class GameManager {

	
	/*
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// game
		ArrayList<Item> testInventory = new ArrayList<Item>();
		
		
		ArrayList<String> testDialog = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			testDialog.add(i + " number in list");
		}
		
		System.out.println("Tests for the Enemy Class:");
		Enemy enemy1 = new Enemy("Name", 10, testInventory, testDialog);
		System.out.println(enemy1.getRandomDialog());
		System.out.println(enemy1.getRandomDialog());
		System.out.println(enemy1.getRandomDialog());
		System.out.println(enemy1.getName());
		System.out.println(enemy1.getHealth());
		enemy1.setHealth(20);
		System.out.println(enemy1.getHealth());
		enemy1.modifyHealth(15);
		System.out.println(enemy1.getHealth());
		System.out.println(enemy1.getInventory());
		
		System.out.println("Tests for the Player class:");
		Player player = new Player("Name", 10, testInventory, 1);
		System.out.println(player.getName());
		System.out.println(player.getHealth());
		player.setHealth(20);
		System.out.println(player.getHealth());
		player.modifyHealth(15);
		System.out.println(player.getHealth());
		System.out.println(player.getInventory());
		System.out.println(player.getCurrentRoom());
		player.setName("Updated Name");
		player.nextRoom();
		System.out.println(player.getCurrentRoom());
		System.out.println(player.getName());
		player.displayInventory();
		
	}
}
