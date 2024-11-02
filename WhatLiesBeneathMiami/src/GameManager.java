
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

//import java.util.ArrayList;

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
		

		// The following code is used for testing various classes.
		// They will not be in the final program.
		/**
		ArrayList<Item> testInventory = new ArrayList<Item>();
		Weapon wep = new Weapon("Weapon", "Used to attack", 5, 90, 10);
		Armor arm = new Armor("Armor", "Used to defend", 15, 20);
		Potion pot = new Potion("Potion", "Used to buff oneself", "Health", 25, 3);
		
		testInventory.add(wep);
		testInventory.add(arm);
		testInventory.add(pot);
		
		
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
		
		System.out.println("Tests for the Item classes:");
		Weapon wep2 = new Weapon("New Weapon", "New weapon description", 10, 95, 20);
		Armor arm2 = new Armor("New Armor", "New armor description", 30, 40);
		Potion pot2 = new Potion("New Potion", "Also used to buff oneself", "Health", 50, 1);
		Potion pot3 = new Potion("Potion", "Used to buff oneself", "Health", 25, 3);
		player.addToInventory(wep2);
		player.addToInventory(arm2);
		player.addToInventory(pot2);
		player.addToInventory(pot3);
		System.out.println("Added new items:");
		player.displayInventory();
		System.out.println("Removed one of each potion:");
		player.removeFromInventory("Potion");
		player.removeFromInventory("New Potion");
		player.removeFromInventory("New Potion");
		player.removeFromInventory("Doesn't exist");
		player.displayInventory();
		*/
	}
}
