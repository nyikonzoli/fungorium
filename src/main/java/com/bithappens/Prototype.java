package com.bithappens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// prototipus jatekallapot tarolasa hashmapben, palyaepites
public class Prototype {
    HashMap<String, Object> objects = new HashMap<>();
    // Game is stored differently, there can only be 1 Game instance per Prototype
    private Game game;
    public Game getGame() { return game; }

    
    /**
     * Completes the action specified in it's parameter. Needs to be called for every new line on input.
     * @param inputLine One line of input to be interpreted as a command and executed.
     * @return The output of the command.
     */
    public String handleInput(String inputLine) {
        ArrayList<String> command = new ArrayList<>(Arrays.asList(inputLine.split(" ")));
        String retval = "Command not found";
        switch (command.get(0)) {
            case "load":
                retval = load(command);
                break;
            case "save":
                retval = save(command);
                break;
            case "exit":
                retval = save(command); //ha kilépésnél ha a user elfelejtené is elmentsuk a jatekot
                break;
            case "list":
                retval = list(command);
                break;
            case "growmu":
                retval = growmu(command);
                break;
            case "growmy":
                retval = growmy(command);
                break;
            case "spreadsp":
                retval = spreadsp(command);
                break;
            case "eatin":
                retval = eatin(command);
                break;
            case "move":
                retval = move(command);
                break;
            case "eatsp":
                retval = eatsp(command);
                break;
            case "cut":
                retval = cut(command);
                break;
            case "nextround":
                retval = nextround();
                break;
            case "nextplayer":
                retval = nextplayer();
                break;
            case "split":
                retval = split(command);
                break;
            case "print":
                retval = print();
                break;
            
            default:
                break;
        }
        return retval;
    }
    
    private String load(ArrayList<String> command) {
        return "todo";
    }
    private String save(ArrayList<String> command) {
        return "todo";
    }
    private String list(ArrayList<String> command) {
        return "todo";
    }
    private String growmu(ArrayList<String> command) {
        return "todo";
    }
    private String growmy(ArrayList<String> command) {
        return "todo";
    }
    private String spreadsp(ArrayList<String> command) {
        //System.out.println("spreadsp called");
        return "todo";
    }
    private String eatin(ArrayList<String> command) {
        return "todo";
    }
    private String move(ArrayList<String> command) {
        return "todo";
    }
    private String eatsp(ArrayList<String> command) {
        return "todo";
    }
    private String cut(ArrayList<String> command) {
        return "todo";
    }
    private String nextround() {
        //System.out.println("nextround called");
        return "todo";
    }
    private String nextplayer() {
        return "todo";
    }
    private String split(ArrayList<String> command) {
        // két új tekton neve: <eredeti név>-1, <eredeti név>-2
        return "todo";
    }
    private String print() {
        return "todo";
    }
}
