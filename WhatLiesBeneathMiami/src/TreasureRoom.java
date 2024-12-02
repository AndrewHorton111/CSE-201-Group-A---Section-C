/**
 * Class: CSE 201
 * @version 1.0
 * @author: Andrew Horton
 * @author: Joe Follrath
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is for the treasure room.
 * It holds no challenge, just some new items the user can equip to prepare for the final battle.
 */
public class TreasureRoom extends Room {

    public TreasureRoom(String roomDescription, ArrayList<String> roomCommands, ArrayList<Item> items, ArrayList<String> objects) {
        super(roomDescription, roomCommands, items, objects);
    }
    
    public void run(CommandHandler ch, Player player) {
        System.out.println(roomDescription);
        inputs(ch, player);
    }
    
    private void inputs(CommandHandler ch, Player player) {
        // create the scanner for this room
        Scanner in = new Scanner(System.in);
        // start a loop to use while in the room
        boolean running = true;
        while (running) {
            // print the character indicating user input and prepare to receive input
            System.out.print("> ");
            String input = in.nextLine().trim();
            // check if the user command is a valid input
            if (CommandHandler.checkInput(roomCommands, input)) {
                // A switch case is used to determine the command. If it is a room
                // special command then included as a case. The default case handles
                // common commands using the CommandHandler class.
                switch (input) {
                case "open door":
                    running = false;
                    break;
                default:
                    ch.commonCommands(input, this);
                    break;
                }
            }
        }
    }
}