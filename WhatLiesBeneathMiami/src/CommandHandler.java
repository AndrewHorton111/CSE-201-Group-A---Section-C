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
}
