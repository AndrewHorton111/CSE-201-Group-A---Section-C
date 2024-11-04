/**
 * Class: CSE 201
 * @author Andrew Horton
 * @version 1.0
 */

/**
 * This class is the child class of Item and it holds has the information
 * needed to protect the Character from damage.
 */
public class Armor extends Item {

	int defense;
	int dodgeChance;
	
	/**
	 * Constructor for the Armor class.
	 * 
	 * @param name
	 * @param itemDescription
	 * @param defense
	 * @param dodgeChance
	 */
	public Armor(String name, String itemDescription, int defense, int dodgeChance) {
		super(name, itemDescription, true);
		this.defense = defense;
		this.dodgeChance = dodgeChance;
	}
}
