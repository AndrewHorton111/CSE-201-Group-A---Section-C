/**
 * Class: CSE 201
 * @author Andrew Horton
 * @version 1.0
 */

/**
 * This class is the child class of Item and it holds has the information
 * needed to buff the Character that use a Potion.
 */
public class Potion extends Item {

	String type;
	int effectPower;
	int amount;
	
	/**
	 * Constructor for the Potion class.
	 * 
	 * @param name
	 * @param itemDescription
	 * @param type
	 * @param effectPower
	 * @param amount
	 */
	public Potion(String name, String itemDescription, String type, int effectPower, int amount) {
		super(name, itemDescription);
		this.type = type;
		this.effectPower = effectPower;
		this.amount = amount;
	}
}
