package com.bithappens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SaveGame {
    private static String getKey(Object object, HashMap<String, Object> map) {
        // if duplicate key name, unforseen outcome
        for (String key : map.keySet()) {
            if (map.get(key).equals(object)) {
                return key;
            }
        }
        if (object == null) {
            return "-";
        }
        return "#ERROR";
    }
    /**
     * Creates the content of the save file describing the current state of the game.
     * @param game The game to be saved.
     * @return The save file's text.
     */
    public static String objectStateToString(Prototype prototype) {
        Game g = prototype.getGame();

        HashMap<String, Object> map = prototype.objects;
        
        //String retval = "<gamestate> " + topmmkey + " " + topimkey + " " + 
        //    getKey(g.getCurrentPlayer(), map) + " " + g.getRoundCount() + "\n";
        StringBuilder retval = new StringBuilder("<gamestate> " + g.getRoundCount());
        for (Player p : g.getPlayers()) {
            retval.append(" " + getKey(p, map) + " " + 
                (p instanceof MushroomMaster ? "m" : "i"));
        }
        retval.append("\n");
        retval.append("<current> " + getKey(g.getCurrentPlayer(), map) + "\n");
        for (Tekton t : g.getGameField()) {
            retval.append("<tekton> " + getKey(t, map) + " " + t.getClass().getSimpleName() + "\n");
            if (!t.getNeighbours().isEmpty()) {
                retval.append("<neighbors>");
                for (Tekton n : t.getNeighbours()) {
                    retval.append(" " + n.getClass().getSimpleName());
                }
                retval.append("\n");
            }
            // mushroom 
            if (t.getMushroomBody() != null) {
                MushroomBody m = t.getMushroomBody();
                MushroomMaster mm = null;
                for (Player p : g.getPlayers()) {
                    // very ugly ugly code pfew, no other way currently
                    if (p instanceof MushroomMaster && ((MushroomMaster)p).getMushrooms().contains(m)) {
                        mm = (MushroomMaster)p;
                    }
                }
                int alive = m.getAlive() ? 1 : 0;
                retval.append("<mushroom> " + getKey(m, map) + " " + getKey(mm, map) + " " + alive + m.getSporeCount() + " " + m.getActions());
                if (m instanceof SuperMushroomBody) { retval.append(" -s"); }
                for (Spore s :m.getSpores()) {
                    retval.append(" " + s.getClass().getSimpleName());
                }
                retval.append("\n");
                
            }
            // insects
            for (Insect i : t.getInsects()) {
                retval.append("<insect> " + getKey(i, map) + " " + getKey(i.getImaster(), map)
                    + " " + i.getActionPoints() + " " + (i.getCanCutMycelium() ? "1 " : "0 ") 
                    + (i.isStunned() ? "1" : "0") + "\n");
            }
            // myceliums
            for (Mycelium m : t.getMyceliums()) {
                Tekton target;
                target = (m.getTektonStart().equals(t)) ? m.getTektonEnd() : m.getTektonStart();
                retval.append(
                    "<mycelium> " + getKey(m, map) + " " + getKey(m.getMaster(), map) + " "
                    + getKey(target, map) + " " + (m.isCut() ? "1 " : "0 ") + m.getTimeToLive() + "\n"
                );
            }
            // spores 
            for (Spore s : t.getSpores()) {
                retval.append(
                    "<spore> " + s.getClass().getSimpleName() + " " + 
                    getKey(s.getMushroomMaster(), map)
                );
            }
        }
        return retval.toString();
    }
    /**
     * Loads a save file's content onto a Prototype object by updating the game objects stored in it's HashMap.
     * @param objectState String describing the state of the game to be loaded.
     * @param prototype The Prototype to be updated.
     */
    public static void loadSaveToPrototype(String objectState, Prototype prototype) {
        // TODO: ne feledd, prototypeba game külön tárolandó
        // ha #ERROR a fájlban, corrupted a save
        // ha fájlban neve null vagy "-" akkor null
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(objectState.split("\\r?\\n|\\r")));
        Tekton currentTekton = null;
        for (String line : lines) {
            if (line.isEmpty()) continue;
            ArrayList<String> lineSplit = new ArrayList<>(Arrays.asList(objectState.split(" ")));
            switch(lineSplit.get(0)) {
                case "<gamestate>":
                readGameState(lineSplit, prototype);
                break;
                case "<current>":
                prototype.getGame().setCurrentPlayer((Player)prototype.objects.get(lineSplit.get(1)));
                break;
                case "<tekton>":
                readTekton(lineSplit, prototype);
                break;
                case "<neighbors>":
                readNeighbors(lineSplit, prototype, currentTekton);
                break;
                case "<mushroom>":
                readMushroom(lineSplit, prototype, currentTekton);
                break;
                case "<insect>":
                readInsect(lineSplit, prototype, currentTekton);
                break;
                case "<mycelium>":
                readMycelium(lineSplit, prototype, currentTekton);
                break;
                case "<spore>":
                readSpore(lineSplit, prototype, currentTekton);
                break;
            }
        }
    }
    /**
     * Compares the content of the two strings extracted from savefiles.
     * @param expected Expected content of save file.
     * @param actual Actual content of save file.
     * @return Whether the two strings contains the same lines, regardless of their order.
     */
    public static boolean compareSaveFileText(String expected, String actual) {
        Set<String> lines1 = new HashSet<>(Arrays.asList(expected.split("\\r?\\n|\\r")));
        Set<String> lines2 = new HashSet<>(Arrays.asList(actual.split("\\r?\\n|\\r")));

        return lines1.equals(lines2);
    }
    private static void readGameState(ArrayList<String> gameState, Prototype prototype) {
        Game game = new Game();
        prototype.setGame(game);
        
        
    }
    private static void readTekton(ArrayList<String> tektonState, Prototype prototype) {
        
    }
    private static void readNeighbors(ArrayList<String> neighborsString, Prototype prototype, Tekton t) {
        
    }
    private static void readMushroom(ArrayList<String> mushroomString, Prototype prototype, Tekton t) {

    }
    private static void readInsect(ArrayList<String> insectString, Prototype prototype, Tekton t) {

    }
    private static void readMycelium(ArrayList<String> myceliumString, Prototype prototype, Tekton t) {
        
    }
    private static void readSpore(ArrayList<String> sporeString, Prototype prototype, Tekton t) {
        
    }
}
