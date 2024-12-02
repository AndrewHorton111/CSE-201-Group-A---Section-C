/**
 * Class: CSE 201
 * @author Jacob Artnak
 * @version 1.0
 */


import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class CellarRoom extends Room {
	
	public CellarRoom(String roomDescription, Map<String, String> roomCommands, ArrayList<Item> items, ArrayList<String> objects) {
		super(roomDescription, roomCommands, items, objects);
	}

}