<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/AndrewHorton111/CSE-201-Group-A---Section-C.git
import java.util.ArrayList;

public class Character {

	String name;
	int health;
	ArrayList<Item> inventory;
	
	/**
	* Method to get the name of the character
	*
	* @return name
	*/
	public String getName() {
		
		return name;
	}
	
	/**
	* Method to get the health of the character
	*
	* @return health
	*/
	public int getHealth() {
		
		return health;
	}
	
	/**
	* Method to set the health of the character
	*
	* @param newHealth
	*/
	public void setHealth(int newHealth) {
		health = newHealth;
	}
	
	/**
	* Method to modify the health of the character based on their
	* current health and the changeAmount parameter
	*
	* @param changeAmount
	*/
	public void modifyHealth(int changeAmount) {
		health += changeAmount;
	}
	
	/**
	* Method to get the inventory of the character
	*
	* @return inventory
	*/
	public ArrayList<Object> getInventory() {
<<<<<<< HEAD
		ArrayList<Object> list = new ArrayList<>();
		return list;
	}
	
	

=======
		ArrayList<Object> inventory = new ArrayList<>();
		return inventory;
	}	
>>>>>>> branch 'master' of https://github.com/AndrewHorton111/CSE-201-Group-A---Section-C.git
}
