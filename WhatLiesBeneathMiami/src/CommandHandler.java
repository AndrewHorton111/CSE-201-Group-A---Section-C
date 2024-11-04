/**
 * @Author Jacob Artnak
 */
import java.util.Scanner;


public class CommandHandler {
    private Player player;
    private Scanner scan;
    
    public CommandHandler(Player player, Scanner scan) {
        this.player = player;
        this.scan = scan;
    }



    // General "use" function to handle different items
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
     * 
     * @param itemName
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
     * 
     * @param itemName
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
     * Take an item in the room
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
	 * @param player
	 * @param enemy
	 */
	public static void enemyTurn(Player player, Enemy enemy) {
		//TODO
	}
	
	/**
	 * This method gets the weapon from the Player and uses its attributes
	 * to modify the health of the Enemy. After the Player's turn, call
	 * "enemyTurn" so the Enemy can attack the player.
	 * 
	 * @param player
	 * @param enemy
	 */
	public static void attack(Player player, Enemy enemy) {
		//TODO
		enemyTurn(player, enemy);
	}
	
	/**
	 * This method makes it so that the player has a much higher chance 
	 * to dodge the enemy's next attack. After the Player's turn, call
	 * "enemyTurn" so the Enemy can attack the player.
	 * 
	 * @param player
	 * @param enemy
	 */
	public static void dodge(Player player, Enemy enemy) {
		//TODO
		
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
	 * @param player
	 * @param enemy
	 */
	public static void heal(Player player, Enemy enemy) {
		//TODO
		enemyTurn(player, enemy);
	}
	
	/**
	 * This method moves the player to the next room, provided they have
	 * already cleared the room. If they have cleared the room, print the 
	 * room description for the new room. 
	 * 
	 * @param player
	 */
	public static void nextRoom(Player player) {
		//TODO
	}
	
 // Example method to handle opening the door
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
