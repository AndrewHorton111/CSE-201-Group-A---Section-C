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
	public Potion(String name, String itemDescription) {
		this(name, itemDescription, "health", 1, 1);
	}
	
	public Potion(String name, String itemDescription, String type, int effectPower, int amount) {
		super(name, itemDescription);
		this.type = type;
		this.effectPower = effectPower;
		this.amount = amount;
	}
	
	
	/**
	* Method to get the number of Potions of this kind in the Player's inventory
	*
	* @return amount
	*/
	public int getAmount() {
		
		return amount;
	}
	
	/**
	* Method to set the number of Potions of this kind in the Player's inventory
	*
	* @param newAmount
	*/
	public void setAmount(int newAmount) {
		amount = newAmount;
	}
	
	/**
	* Method to modify the amount of the potions a Player has in thier inventory
	*
	* @param changeAmount
	*/
	public void modifyAmount(int changeAmount) {
		amount += changeAmount;
	}
}
