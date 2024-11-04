/**
 * Class: CSE 201
 * This class represents a player in the game, holding information about
 * the player's current room, inventory, and equipped items.
 * 
 * @author Andrew Horton
 * @version 1.0
 */

import java.util.ArrayList;

public class Player extends Character {

	Room[] roomList;
	int roomId;
	Room currentRoom;
	ArrayList<String> equippedItems;

	/**
	 * Constructor for the Player class.
	 * Initializes the player with a name, health, inventory, and a list of rooms.
	 * The player starts in the first room by default.
	 * 
	 * @param name      The name of the player.
	 * @param health    The health of the player.
	 * @param inventory The list of items in the player's inventory.
	 * @param roomList  The list of rooms in the game.
	 */
	public Player(String name, int health, ArrayList<Item> inventory, Room[] roomList) {
		super(name, health, inventory);
		this.roomList = roomList;
		roomId = 0;
		currentRoom = roomList[0];
		equippedItems = new ArrayList<>();
	}

	/**
	 * Retrieves the current room the player is in.
	 * 
	 * @return The room the player is currently in.
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Advances the player to the next room if there are more rooms available.
	 * Updates the current room and prints the new room's description.
	 * If the player is in the final room, a message is printed instead.
	 */
	public void nextRoom() {
		if (roomId < roomList.length - 1) {
			currentRoom.roomCleared = true;
			roomId++;
			currentRoom = roomList[roomId];
			System.out.println("You have moved to the next room:\n" + currentRoom.roomDescription);
		} else {
			System.out.println("You are in the final room and cannot move further.");
		}
	}

	/**
	 * Sets the name of the player.
	 * 
	 * @param newName The new name for the player.
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * Displays the player's inventory to the screen, including details about each item.
	 * The first item in the inventory is displayed as a weapon, the second as armor,
	 * and remaining items are displayed as potions, if present.
	 */
	public void displayInventory() {
		if (inventory.size() < 1) {
			System.out.println("You have no items in your inventory.");
			return;
		}

		// Display weapon details.
		if (inventory.size() >= 1) {
			Weapon wep = (Weapon) inventory.get(0);
			System.out.println("Weapon: " + wep.name + " - " + wep.itemDescription);
			System.out.println("\tWeapon Damage: " + wep.damage);
			System.out.println("\tWeapon Hit Chance: " + wep.hitChance);
			System.out.println("\tWeapon Crit Chance: " + wep.critChance);
		}

		// Display armor details.
		if (inventory.size() >= 2) {
			Armor arm = (Armor) inventory.get(1);
			System.out.println("Armor: " + arm.name + " - " + arm.itemDescription);
			System.out.println("\tArmor Defense: " + arm.defense);
			System.out.println("\tArmor Dodge Chance: " + arm.dodgeChance);
		}

		// Display potion details.
		for (int i = 2; i < inventory.size(); i++) {
			Potion pot = (Potion) inventory.get(i);
			System.out.println("Potion: " + pot.name + " - " + pot.itemDescription);
			System.out.println("\tEffect Power: " + pot.effectPower);
			System.out.println("\tAmount In Inventory: " + pot.amount);
		}
	}

	/**
	 * Adds an item to the player's inventory. If the item is a weapon or armor,
	 * it replaces any existing weapon or armor in the inventory. For potions,
	 * it checks if the potion already exists and increments its quantity if so.
	 * 
	 * @param itemToAdd The item to add to the player's inventory.
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
		} else if (itemToAdd instanceof Potion) {
			// Increase amount if potion already exists in inventory.
			Potion newItem = (Potion) itemToAdd;
			for (int i = 2; i < inventory.size(); i++) {
				if (newItem.name.equals(inventory.get(i).name)) {
					Potion matchingPotion = (Potion) inventory.get(i);
					matchingPotion.modifyAmount(newItem.amount);
					System.out.println("The item has been added to your inventory.");
					return;
				}
			}
			inventory.add(itemToAdd);
		} else {
			inventory.add(itemToAdd);
		}
		System.out.println("The item has been added to your inventory.");
	}

	/**
	 * Removes a potion from the player's inventory by decreasing its amount.
	 * If the item is not a potion, it removes the item from the inventory entirely.
	 * 
	 * @param itemName The name of the item to remove from the inventory.
	 */
	public void removeFromInventory(String itemName) {
		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if (itemName.equals(item.name)) {
				if (item instanceof Potion) {
					Potion matchingPotion = (Potion) item;
					if (matchingPotion.amount <= 0) {
						System.out.println("You are currently out of " + itemName + " potions.");
						return;
					}
					matchingPotion.modifyAmount(-1);
					System.out.println("The potion was used.");
					return;
				} else {
					// Remove non-potion item (e.g., key).
					inventory.remove(i);
				}
			}
		}
		System.out.println("You do not have any " + itemName + " potions.");
	}

	/**
	 * Equips or unequips an item by adding or removing it from the equipped items list.
	 * Only equippable items are added. If unequipping, checks if the item exists.
	 * 
	 * @param itemName The name of the item to equip or unequip.
	 * @param action   True to equip, false to unequip the item.
	 */
	public void setEquipped(String itemName, boolean action) {
		if (action) {
			equippedItems.add(itemName);
		} else {
			Item item = getItem(itemName);
			if (item != null) // Make sure the item exists
				equippedItems.remove(itemName);
		}
	}

	/**
	 * Retrieves an item from the player's inventory by its name.
	 * 
	 * @param itemName The name of the item to retrieve.
	 * @return The item if found, or null if the item is not in the inventory.
	 */
	public Item getItem(String itemName) {
		for (Item item : inventory) {
			if (item.name.equalsIgnoreCase(itemName)) {
				return item;
			}
		}
		return null;
	}
}