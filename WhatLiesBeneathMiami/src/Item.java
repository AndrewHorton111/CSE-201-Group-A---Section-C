/**
 * Class: CSE 201
 * @author Andrew Horton
 * @version 1.0
 */

/**
 * This class is the parent class of Weapon, Armor and Potion. It holds the
 * name and description of each Item.
 */
public class Item {

	String name;
	String itemDescription;
	boolean equippable;
	
	/**
	 * Constructor for the Item class.
	 * 
	 * @param name
	 * @param itemDescription
	 */
	public Item(String name, String itemDescription, boolean equippable) {
		super();
		this.name = name;
		this.itemDescription = itemDescription;
		this.equippable = equippable;
	}
	public Item(String name, String itemDescription) {
		this(name, itemDescription, false);
	}
	
	
}