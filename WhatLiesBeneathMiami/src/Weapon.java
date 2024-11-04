/**
 * Class: CSE 201
 * @author Andrew Horton
 * @version 1.0
 */


/**
 * This class is the child class of Item and it holds has the information
 * needed to deal damage to another Character. 
 */
public class Weapon extends Item {

	int damage;
	int hitChance;
	int critChance;
	
	/**
	 * Constructor for the Weapon class.
	 * 
	 * @param name
	 * @param itemDescription
	 * @param damage
	 * @param hitChance
	 * @param critChance
	 */
	public Weapon(String name, String itemDescription, int damage, int hitChance, int critChance) {
		super(name, itemDescription);
		this.damage = damage;
		this.hitChance = hitChance;
		this.critChance = critChance;
	}
}
