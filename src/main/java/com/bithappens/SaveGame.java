package com.bithappens;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SaveGame {
    /**
     * Creates the content of the save file describing the current state of the game.
     * @param game The game to be saved.
     * @return The save file's text.
     */
    public static String objectStateToString(Game game) {
        return "todo";
    }
    /**
     * Loads a save file's content onto a Prototype object by updating the game objects stored in it's HashMap.
     * @param objectState String describing the state of the game to be loaded.
     * @param prototype The Prototype to be updated.
     */
    public static void loadSaveToPrototype(String objectState, Prototype prototype) {
        // TODO: ne feledd, prototypeba game külön tárolandó
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
