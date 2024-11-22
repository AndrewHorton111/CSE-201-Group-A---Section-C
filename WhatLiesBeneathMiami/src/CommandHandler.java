/**
 * @Author Jacob Artnak
 * This class handles player commands, such as using items, examining objects,
 * equipping items, and moving to the next room. Each command interacts with
 * the Player and Room classes to perform specific actions.
 */

import java.util.Map;
import java.util.Scanner;

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
	 * @param roomCommands Map containing valid commands and associated methods for the room.
	 * @param input Player's command input to be checked.
	 * @return boolean True if input is a valid command; false otherwise.
	 */
	public static boolean checkInput(Map<String, String> roomCommands, String input) {
		boolean isValid = false;
		for (String command : roomCommands.keySet()) {
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
                    System.out.println("You insert the rusty key into the lock and, with some effort, turn it. The lock releases with a loud creak, and the heavy door slowly swings open, revealing a dark hallway beyond.");
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
     */
    public void take() {
        System.out.println("What would you like to take");
        String itemName = scan.nextLine();

        Room currentRoom = player.getCurrentRoom();
        Item item = currentRoom.getItem(itemName);
        if (item != null) {
            System.out.println("You took the " + item.name + ".");
            player.addToInventory(item);
        } else {
            System.out.println("There is no " + itemName + " in the room.");
        }
    }

    /**
     * This method acts as the enemy's turn in combat. The enemy should
     * only attack the player. If the enemy's health is 0 or lower, they
     * shouldn't deal damage and the roomCleared boolean should be set
     * to true for the current Room. 
     * 
     * @param player The player being attacked by the enemy.
     * @param enemy  The enemy engaging in combat with the player.
     */
    public static void enemyTurn(Player player, Enemy enemy) {
        // TODO
    }

    /**
     * This method gets the weapon from the Player and uses its attributes
     * to modify the health of the Enemy. After the Player's turn, call
     * "enemyTurn" so the Enemy can attack the player.
     * 
     * @param player The player attacking the enemy.
     * @param enemy  The enemy being attacked by the player.
     */
    public static void attack(Player player, Enemy enemy) {
        // TODO
        enemyTurn(player, enemy);
    }

    /**
     * This method makes it so that the player has a much higher chance 
     * to dodge the enemy's next attack. After the Player's turn, call
     * "enemyTurn" so the Enemy can attack the player.
     * 
     * @param player The player attempting to dodge the attack.
     * @param enemy  The enemy attempting to attack the player.
     */
    public static void dodge(Player player, Enemy enemy) {
        // TODO
        // dodgeChance + num
        enemyTurn(player, enemy);
        // dodgeChance - num
    }

    /**
     * This method heals the Player if they have health Potions left.
     * This method might be expanded to be able to use different types
     * of Potions in the future, but for this iteration it should just
     * use health Potions. After the Player's turn, call "enemyTurn" so
     * the Enemy can attack the player.
     * 
     * @param player The player using a health potion to restore health.
     * @param enemy  The enemy awaiting its turn.
     */
    public static void heal(Player player, Enemy enemy) {
        // TODO
        enemyTurn(player, enemy);
    }

    /**
     * This method moves the player to the next room, provided they have
     * already cleared the room. If they have cleared the room, print the 
     * room description for the new room. 
     * 
     * @param player The player advancing to the next room.
     */
    public static void nextRoom(Player player) {
        // TODO
    }

    /**
     * Handles the "open door" command, which allows the player to open
     * a door if present in the room. If successful, advances the player to
     * the next room.
     */
    public void openDoor() {
        Room currentRoom = player.getCurrentRoom();
        if (currentRoom.containsObject("door")) {
            System.out.println("You open the door. It creaks loudly, revealing the path ahead.");
            player.nextRoom(); // Mark room as cleared after action
        } else {
            System.out.println("There's no door to open here.");
        }
    }
}