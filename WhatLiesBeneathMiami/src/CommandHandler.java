
/**
 * @author Jacob Artnak, Joe Follrath, Andrew Horton
 * This class handles player commands, such as using items, examining objects,
 * equipping items, and moving to the next room. Each command interacts with
 * the Player and Room classes to perform specific actions.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class CommandHandler {
	private Player player;
	private Scanner scan;

	/**
	 * Constructor for CommandHandler.
	 * 
	 * @param player The player interacting with the game.
	 * @param scan   The scanner used to read player input.
	 */
	public CommandHandler(Player player, Scanner scan) {
		this.player = player;
		this.scan = scan;
	}

	/**
	 * Checks if a player's input is a valid command for the current room. Prints an
	 * error message if the input is invalid.
	 * 
	 * @param roomCommands Map containing valid commands and associated methods for
	 *                     the room.
	 * @param input        Player's command input to be checked.
	 * @return boolean True if input is a valid command; false otherwise.
	 */
	public static boolean checkInput(ArrayList<String> roomCommands, String input) {
		boolean isValid = false;
		for (String command : roomCommands) {
			if (command.equalsIgnoreCase(input)) {
				isValid = true;
				break;
			}
		}
		if (!isValid) {
			System.out.println(input + " is not a valid command.");
			System.out.println("Type \"Help\" to get a list of valid commands.");
		}
		return isValid;
	}

	/**
	 * Contains common commands that every room needs access to. Moved to
	 * CommandHandler 11/24/2024 so every room will have access
	 * 
	 * @param input       The Command the user typed
	 * @param currentRoom The current room that the player is in
	 */
	public void commonCommands(String input, Room currentRoom) {
		// Handle common commands through switch case
		// This will function the same way as in GameManager and can be
		// accessed by every room easily
		switch (input.toLowerCase()) {
		// Global
		case "exit":
			GameManager.exitProgram(scan);
			break;
		// Global
		case "help":
			GameManager.printHelpMessage(currentRoom.roomCommands);
			break;
		// Global
			case "inventory":
			player.displayInventory();
			break;
		// Local
		case "items":
			currentRoom.displayItems();
			break;
		// Local
		case "objects":
			currentRoom.displayObjects();
			break;
		// Local
		case "examine":
			examine();
			break;
		// Local
		case "take":
			take(currentRoom);
			break;
		// Local
		case "equip":
			equip();
			break;
		// Local
		case "use":
			use();
			break;
		}
	}

	/**
	 * This will be used to explain the commands. Helps provide clarity if the user
	 * is stuck on the controls. TODO: Everything
	 */
	public void commonCommandHelp(String input) {
		switch (input.toLowerCase()) {

		}
	}

	/**
	 * General "use" function to handle different items. Prompts the player to
	 * specify an item to use, checks if it's in the inventory, and performs
	 * item-specific actions depending on the item.
	 */
	public void use() {
		System.out.println("What would you like to use?");
		String itemName = scan.nextLine();

		// Check if the item is in the player's inventory
		Item item = player.getItem(itemName);
		if (item == null) {
			System.out.println("You don't have a " + itemName + " to use.");
			return;
		}

		Room currentRoom = player.getCurrentRoom();
		// Determine action based on item name
		switch (item.name.toLowerCase()) {
		case "rusty key":
			if (currentRoom.containsObject("cellar")) {
				System.out.println(
						"You insert the rusty key into the lock and, with some effort, turn it. The lock releases with a loud creak, and the heavy door slowly swings open, revealing a dark hallway beyond.");
				player.nextRoom();
			} else {
				System.out.println("The rusty key doesn't seem to fit anything here.");
			}
			break;

		case "health potion":
			System.out.println("You drink the health potion and feel rejuvenated.");
			player.modifyHealth(20);
			player.removeFromInventory(item.name);
			break;

		default:
			System.out.println("You can't use the " + itemName + " here.");
			break;
		}
	}

	/**
	 * Prompts the player to examine an item, checks if the item exists in the
	 * current room, and displays its description if found.
	 */
	public void examine() {
		System.out.println("What would you like to examine?");
		String itemName = scan.nextLine();

		Room currentRoom = player.getCurrentRoom();
		Item item = currentRoom.getItem(itemName);

		if (item != null) {
			System.out.println(item.itemDescription);
		} else {
			System.out.println("There is no " + itemName + " to examine here.");
		}
	}

	/**
	 * Prompts the player to equip an item, checks if the item is in the player's
	 * inventory and equippable, and equips the item if possible.
	 */
	public void equip() {
		System.out.println("What would you like to equip?");
		String itemName = scan.nextLine();

		Item item = player.getItem(itemName);
		if (item != null) {
			if (item.equippable) {
				player.setEquipped(item.name, true); // equip the item
				System.out.println("You equipped the " + item.name + ".");
			} else {
				System.out.println(item.name + " is not equippable.");
			}
		} else {
			System.out.println("You don't have a " + itemName + " to equip.");
		}
	}

	/**
	 * Prompts the player to take an item from the room. If the item exists in the
	 * current room, it is added to the player's inventory.
	 * 
	 * @param currentRoom
	 */
	public void take(Room currentRoom) {
		System.out.println("What would you like to take");
		String itemName = scan.nextLine();

		Item item = currentRoom.getItem(itemName);
		if (item != null) {
			System.out.println("You took the " + item.name + ".");
			player.addToInventory(item);
			currentRoom.removeItem(item);
		} else {
			System.out.println("There is no " + itemName + " in the room.");
		}
	}

	/**
	 * This method gets the weapon from the attacking character and
	 * uses its attributes to modify the health of the attacked.
	 * 
	 * @param attacker The character attacking the attacked.
	 * @param enemy  The character being attacked by the attacker.
	 */
	public void attack(Character attacker, Character attacked) {
		Item weapon = attacker.inventory.get(0);
		Weapon attackerWeapon = (Weapon) weapon;
		Item armor = attacker.inventory.get(1);
		Armor attackedArmor = (Armor) armor;
		Random random = new Random();
		
		// See if the attacked dodges the attack
		if (random.nextInt(101) < attackedArmor.getDodgeChance()) {
			System.out.println(attacked.getName() + " dodged the attack!");
		} else {
			// Since the attack lands, see if the attack is a critical hit
			int damage;
			if (random.nextInt(101) < attackerWeapon.getCritChance()) {
				System.out.println(attacker.getName() + " got a CRITICAL hit!");
				damage = attackerWeapon.getDamage() * 2;
			} else {
				System.out.println(attacker.getName() + "'s attack hit!");
				damage = attackerWeapon.getDamage();
			}
			// Deal damage to the attacked
			damage = damage + random.nextInt(6) - attackedArmor.getDefense();
			if (damage <= 0) {
				System.out.println("The attack dealt no damage");
			} else {
				attacked.modifyHealth(-1 * damage);
				System.out.println(attacker.getName() + " dealt " + damage + " damage!");
			}
		}
	}

	/**
	 * This method makes it so that the player has a much higher chance to dodge the
	 * enemy's next attack.
	 * 
	 * @param player The player attempting to dodge the attack.
	 */
	public boolean dodge(Player player) {
		Item armor = player.inventory.get(1);
		Armor playerArmor = (Armor) armor;
		int currentDodgeChance = playerArmor.getDodgeChance() * 4;
		Random random = new Random();
		if (random.nextInt(101) < currentDodgeChance) {
			System.out.println("You expertly move out of the way of the attack!");
			return false;
		} else {
			System.out.println("You can't get your footing, and don't have a higher chance to dodge the incoming attack");
			return true;
		}

	}

	/**
	 * This method heals the Player if they have health Potions left. This method
	 * might be expanded to be able to use different types of Potions in the future,
	 * but for this iteration it should just use health Potions.
	 * 
	 * @param player The player using a health potion to restore health.
	 */
	public void heal(Player player) {
		// Checks if you have health potions left.
		Potion pot = (Potion) player.inventory.get(2);
		if (pot.amount <= 0) {
			System.out.println("You are out of health potions and lose your turn.");
		} else {
			player.modifyHealth(pot.effectPower);
			player.removeFromInventory(pot.name);
			System.out.println("You were healed for " + pot.effectPower + " health. You have " + pot.amount + " potions left");
		}
	}

	/**
	 * This method moves the player to the next room, provided they have already
	 * cleared the room. If they have cleared the room, print the room description
	 * for the new room.
	 * 
	 * @param player The player advancing to the next room.
	 */
	public void nextRoom(Player player) {
		// TODO
	}

	/**
	 * Handles the "open door" command, which allows the player to open a door if
	 * present in the room. If successful, advances the player to the next room.
	 */
	public boolean openDoor() {
		Room currentRoom = player.getCurrentRoom();
		if (currentRoom.containsObject("door")) {
			if (currentRoom.getItem("sword") == null && currentRoom.getItem("chestplate") == null && currentRoom.getItem("health potion") == null) {
				System.out.println("You open the door. It creaks loudly, revealing the path ahead.");
				return false;
				// player.nextRoom(); // Mark room as cleared after action
			} else {
				System.out.println("You should take the sword, chestplate, and health potions so that you are prepared for the dangers that lie ahead.");
				return true;
			}
		} else {
			System.out.println("There's no door to open here.");
			return true;
		}
	}
}