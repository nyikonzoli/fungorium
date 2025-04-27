package com.bithappens;

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
        String topmmkey = "null";
        if (g.getTopMushroomMasters().size() > 0) 
            topmmkey = getKey(g.getTopMushroomMasters().get(0), map);
        String topimkey = "null";
        if (g.getTopInsectMasters().size() > 0) 
            topimkey = getKey(g.getTopInsectMasters().get(0), map);
        String retval = "<gamestate> " + topmmkey + " " + topimkey + " " + 
            getKey(g.getCurrentPlayer(), map) + " " + g.getRoundCount() + "\n";
        for (Tekton t : g.getGameField()) {
            retval += "<tekton> " + getKey(t, map) + " " + t.getClass().getSimpleName() + "\n";
            if (!t.getNeighbours().isEmpty()) {
                retval += "<neighbors>";
                for (Tekton n : t.getNeighbours()) {
                    retval += " " + n.getClass().getSimpleName();
                }
                retval += "\n";
            }
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
                retval += "<mushroom> " + getKey(m, map) + " " + getKey(mm, map) + " " + alive + m.getSporeCount() + " " + m.getActions();
                if (m instanceof SuperMushroomBody) { retval += " -s"; }
                for (Spore s :m.getSpores()) {
                    retval += " " + s.getClass().getSimpleName();
                }
                retval += "\n";
            }
            // TODO: finish from insect line
        }
        return retval;
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
    }
    /**
     * Compares the content of the two strings extracted from savefiles.
     * @param expected Expected content of save file.
     * @param actual Actual content of save file.
     * @return Whether the two strings contains the same lines, regardless of their order.
     */
    public static boolean compareSaveFileText(String expected, String actual) {
        Set<String> lines1 = new HashSet<>(Arrays.asList(expected.split("\\r?\\n")));
        Set<String> lines2 = new HashSet<>(Arrays.asList(actual.split("\\r?\\n")));

        return lines1.equals(lines2);
    }
}
