
/**
 * Class: CSE 201
 * @author Andrew Horton
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * This class is the child class of Player and it holds the room the player is
 * currently in and can be used to manage the inventory.
 */
public class Player extends Character {

	Room[] roomList;
	int roomId;
	Room currentRoom;

	/**
	 * Constructor for the Player class.
	 * 
	 * @param name
	 * @param health
	 * @param inventory
	 * @param roomId
	 */
	public Player(String name, int health, ArrayList<Item> inventory, Room[] roomList) {
		super(name, health, inventory);
		this.roomList = roomList;
		// Player always starts in the first room
		roomId = 0;
		currentRoom = roomList[0];
	}

	/**
	 * Method to get the currentRoom the Player is in.
	 * 
	 * @return currentRoom
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Method that increments your room number when the Player completes a room.
	 */
	public void nextRoom() {
		roomId++;
		currentRoom = roomList[roomId];
	}

	/**
	 * Method to set the Player's name
	 * 
	 * @param newName
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Method that prints the Player's inventory to the screen
	 */
	public void displayInventory() {
		if (inventory.size() < 1) {
			System.out.println("You have no items in your inventory.");
			return;
		}

		// The first spot in the Inventory ArrayList will always be a Weapon.
		if (inventory.size() >= 1) {
			Weapon wep = (Weapon) inventory.get(0);
			System.out.println("Weapon: " + wep.name + " - " + wep.itemDescription);
			System.out.println("\tWeapon Damage: " + wep.damage);
			System.out.println("\tWeapon Hit Chance: " + wep.hitChance);
			System.out.println("\tWeapon Crit Chance: " + wep.critChance);
		}

		// The second spot in the Inventory ArrayList will always be Armor.
		if (inventory.size() >= 2) {
			Armor arm = (Armor) inventory.get(1);
			System.out.println("Armor: " + arm.name + " - " + arm.itemDescription);
			System.out.println("\tArmor Defense: " + arm.defense);
			System.out.println("\tArmor Dodge Chance: " + arm.dodgeChance);
		}

		// The remaining items in Inventory, if any, are Potions.
		for (int i = 2; i < inventory.size(); i++) {
			Potion pot = (Potion) inventory.get(i);
			System.out.println("Potion: " + pot.name + " - " + pot.itemDescription);
			System.out.println("\tEffect Power: " + pot.effectPower);
			System.out.println("\tAmount In Inventory: " + pot.amount);
		}
		return;
	}

	/**
	 * Method that adds in item to the Player's inventory
	 * 
	 * @param itemToAdd
	 */
	public void addToInventory(Item itemToAdd) {
		if (itemToAdd instanceof Weapon) {
			// Replace the Weapon if another is added.
			Weapon newItem = (Weapon) itemToAdd;
			inventory.set(0, newItem);
		} else if (itemToAdd instanceof Armor) {
			// Replace the Armor if another is added.
			Armor newItem = (Armor) itemToAdd;
			inventory.set(1, newItem);
		} else {
			Potion newItem = (Potion) itemToAdd;
			// Loop through all Potions and increase amount if already in inventory
			for (int i = 2; i < inventory.size(); i++) {
				if (newItem.name.equals(inventory.get(i).name)) {
					Potion matchingPotion = (Potion) inventory.get(i);
					matchingPotion.modifyAmount(newItem.amount);
					System.out.println("The item has been added to your inventory.");
					return;
				}
			}
			// If the Potion is not already in the inventory, add it.
			inventory.add(newItem);
		}
		System.out.println("The item has been added to your inventory.");
		return;
	}

	/**
	 * Method to remove a Potion from the Player's inventory with the name of the
	 * Potion.
	 * 
	 * @param itemName
	 */
	public void removeFromInventory(String itemName) {
		// Loop through all Potions in inventory and if a matching Potion
		// is found, decrease its amount by 1. Print an error message if no
		// Potions match the name given.
		for (int i = 2; i < inventory.size(); i++) {
			if (itemName.equals(inventory.get(i).name)) {
				Potion matchingPotion = (Potion) inventory.get(i);
				if (matchingPotion.amount <= 0) {
					System.out.println("You are currently out of " + itemName + " potions.");
					return;
				}
				matchingPotion.modifyAmount(-1);
				System.out.println("The potion was used.");
				return;
			}
		}
		// If no Potion was found, then the input was incorrect.
		System.out.println("You do not have any " + itemName + " potions.");
		return;
	}

}
