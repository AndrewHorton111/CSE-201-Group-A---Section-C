/**
 * Class: CSE 201
 * @author Joe Follarth
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class RiddleRoom extends Room {


    public RiddleRoom(String roomDescription, ArrayList<String> roomCommands, ArrayList<Item> items,
            ArrayList<String> objects) {
        super(roomDescription, roomCommands, items, objects);
    }
    
    /**
     * Called form game manager, responsible for running the room
     * @param cmd The command handler so the room can access common commands
     */
    public void run(CommandHandler cmd, Player player) {
        System.out.println(roomDescription);
        System.out.println("\nTo answer the riddle first type \"answer\"\n"
                + "for a hint type \"hint\"");
        inputs(cmd, player);
    }
    
    private void inputs(CommandHandler ch, Player player) {
        // create the scanner for this room
        Scanner in = new Scanner(System.in);
        int key = randomRiddleNumber();
        HashMap<Integer, String> riddles = riddleStorage();
        // start a loop to use while in the room
        boolean running = true;
        while (running) {
            // print the character indicating user input and prepare to receive input
            System.out.println(riddles.get(key));
            System.out.println("> ");
            String input = in.nextLine().trim();
            // check if the user command is a valid input
            if (CommandHandler.checkInput(roomCommands, input)) {
                // A switch case is used to determine the command. If it is a room
                // special command then included as a case. The default case handles
                // common commands using the CommandHandler class.
                switch (input) {
                case "answer":
                    answer(key);
                    running = false;
                    break;
                case "hint":
                    hint(key);
                    break;
                default:
                    ch.commonCommands(input, this);
                    break;
                }
            }
        }
        in.close();
    }
    
    /**
     * Used for answering the riddle
     */
    private static void answer(int key) {
        //  boolean firstGuessCorrect = false;
        HashMap<Integer, String> riddleAnswers = riddleAnswers();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your answer: ");
        System.out.print("> ");
        String answer = in.nextLine().toLowerCase();
        if (answer.equalsIgnoreCase(riddleAnswers.get(key))) {
            System.out.println("Congratulations adventurer, you have answered my riddle\n"
                    + "You may now move onto the next challenge");
        } else {
            System.out.println("Incorrect Answer, Try again");
        }
        
        in.close();
    }
    
    /**
     * Prints a hint for the riddle
     * @param key Used to identify the hint for the specific riddle
     */
    private static void hint(int key) {
        switch (key) {
        // hint for the footsteps riddle
        case 1:
            System.out.println("You take them as you walk around");
            break;
        // hint for feather riddle
        case 2:
            System.out.println("Birds are covered in them");
            break;
        // hint for the riddle of the sphynx
        case 3:
            System.out.println("This is a very famous riddle also known as the"
                    + " Riddle of the Sphynx");
            break;
        }   
    }

    /**
     * Generates a random number that will be used as a key
     * this key is used in a hashmap for riddles and answers
     */
    private static int randomRiddleNumber() {
        // Load the riddles in
        // used for picking a random number that corresponds to a riddle
        Random random = new Random();
        Integer randomNumber = random.nextInt(3) + 1;
        return randomNumber;
    }

    /**
     * Create the riddles and puts them in to a Hashmap for use later
     * @return A hashmap of the riddles
     */
    private static HashMap<Integer, String> riddleStorage() {
        HashMap<Integer, String> riddles = new HashMap<>();
        riddles.put(1, "The more you take the more you leave behind. What am I?");
        riddles.put(2, "I am easy to lift but hard to throw. What am I?");
        riddles.put(3, "What creature walks on four legs in the morning,"
                + " two legs at noon, and three legs in the evening?");

        return riddles;
    }
    
    private static HashMap<Integer, String> riddleAnswers() {
        HashMap<Integer, String> answers = new HashMap<>();
        answers.put(1, "Foot Steps");
        answers.put(2, "Feather");
        answers.put(3,  "A human");
        return answers;
    }

}