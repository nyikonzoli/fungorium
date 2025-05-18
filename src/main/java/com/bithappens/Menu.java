package com.bithappens;
import java.util.Scanner;

// TODO: A Prototype osztályt a Menu-ben kell példányosítani. 
// TODO: A Prototype.handleInput-ot minden user által kiadott parancsra használandó.

public class Menu {
    private static Scanner sc;
    
    /**
     * Method to read and evaluate user input on the System.in stream
     */
    public static void readInput() {
        sc = new Scanner(System.in);
        Prototype prototype = new Prototype();
        String line;

        // FRAME
        FungoriumFrame frame = new FungoriumFrame(prototype);
        
        do {
            line = sc.nextLine();
            String output = prototype.handleInput(line);
            
            System.out.println(output + "\n");
        }
        while (!"exit".equals(line));
        prototype.handleInput("save autosave.txt");
        sc.close();
        System.out.println("\n");
    }


    /**
     * Asks the user to make a yes-or-no decision
     * @return Returns a boolean corresponding to the user's answer
     */
    public static boolean userDecision() {
        System.out.println("[y/n]: ");
        char c = 0;
        while (c != 'y' && c != 'n') {
            if (sc.hasNext()) {
                c = sc.next().charAt(0);
            }
        }
        return c == 'y';
    }
}
