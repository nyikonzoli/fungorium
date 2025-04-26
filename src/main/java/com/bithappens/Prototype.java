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

        MushroomMaster currentMaster = (MushroomMaster)game.getCurrentPlayer();
        MushroomMaster commandMaster = (MushroomMaster)objects.get(command.get(1));
        Tekton target = (Tekton)objects.get(command.get(2));

        if(!currentMaster.equals(commandMaster)){
            return "Nem ennek a jatekosnak a kore";
        }

        // Ha újat akar növeszteni
        if (command.size() == 3) {
            currentMaster.initiateMushroomGrowth(target);

            // Ellenőrizni, hogy sikerült-e
            if (target.getMushroomBody() == null) {
                return "Nem sikerult a novesztes";
            }
            else{
                return "Sikeres novesztes";
            }
        }
        // Szuper gomba növesztés
        else if (command.size() == 4 && "-s".equals(command.get(3))) {
            MushroomBody growSuperMushroomBody = target.getMushroomBody();
            currentMaster.initiateSuperMushroomGrowth(growSuperMushroomBody);

            // Ellenőrzés
            if (target.getMushroomBody() == null || !(target.getMushroomBody() instanceof SuperMushroomBody)) {
                return "Nem sikerult a fejlesztes";
            }
            else{
                return "Sikeres fejlesztes";
            }
        }

        return "Ervenytelen bemenet";
    }

    private String growmy(ArrayList<String> command) {

        MushroomMaster currentMaster = (MushroomMaster)game.getCurrentPlayer();
        MushroomMaster commandMaster = (MushroomMaster)objects.get(command.get(1));
        Tekton source = (Tekton)objects.get(command.get(2));
        Tekton target = (Tekton)objects.get(command.get(3));

        if(!currentMaster.equals(commandMaster)){
            return "Nem ennek a jatekosnak a kore";
        }

        if (!source.isNeighbour(target)) {
            return "Nem szomszedos a ket tekton";
        }

        currentMaster.initiateMyceliumGrowth(source, target);

        // Ellenorzes
        if (target.canReachTektonViaMycelium(source)) {
            return "Sikeres novesztes";
        }

        return "Sikertelen novesztes";

    }
    private String spreadsp(ArrayList<String> command) {
        
        // spreadsp <forrás gombatest> <tekton>
        MushroomBody mushroom = (MushroomBody)objects.get(command.get(1));
        Tekton target = (Tekton)objects.get(command.get(2));
        MushroomMaster mmaster = (MushroomMaster)game.getCurrentPlayer();
        int sporeBeforeThrow = target.getSpores().size();

        mmaster.initiateSporeSpreading(mushroom, target);

        // Ellenőrzés
        if (sporeBeforeThrow + 3 == target.getSpores().size()) {
            return "Sikeres spora szoras";
        }
        return "Sikertelen szoras";
    }
    private String eatin(ArrayList<String> command) {
        MushroomMaster currentMaster = (MushroomMaster)game.getCurrentPlayer();
        MushroomMaster commandMaster = (MushroomMaster)objects.get(command.get(1));
        Insect eatInsect = (Insect)objects.get(command.get(2));
        Tekton eatTekton = eatInsect.getLocation();
        ArrayList<Mycelium> allMyceliums = eatTekton.getMyceliums();
        int insectNumber = eatTekton.getInsects().size();

        if(!currentMaster.equals(commandMaster)){
            return "Nem ennek a jatekosnak a kore";
        }

        for(Mycelium m : allMyceliums){
            if(m.getMaster().equals(currentMaster) && (m.getTektonEnd().equals(eatTekton) || m.getTektonStart().equals(eatTekton))){
                m.eatInsect(eatInsect, eatTekton);
            }
        }

        // Ellenorzes
        if (insectNumber - 1 == eatTekton.getInsects().size()) {
            return "Sikeres eves";
        }
        return "Sikertelen eves";

    }
    private String move(ArrayList<String> command) {
        // <rovar> <tekton>
        Insect insect = (Insect)objects.get(command.get(1));
        Tekton target = (Tekton)objects.get(command.get(2));
        InsectMaster im = insect.getImaster();
        InsectMaster currentPlayer = (InsectMaster)game.getCurrentPlayer();
        int targetInsects = target.getInsects().size();

        if(!im.equals(currentPlayer)){
            return "Nem ennek a rovarasznak a kore";
        }
        insect.moveTo(target);

        // Ellenorzes
        if (targetInsects + 1 == target.getInsects().size()) {
            return "Siekres mozgas";
        }

        return "Sikertelen mozgas";


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
