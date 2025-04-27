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
                    retval.append(" " + getKey(n, map));
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
                    getKey(s.getMushroomMaster(), map) + "\n"
                );
            }
        }
        //System.out.println("Actual:\n\n" + retval.toString());
        return retval.toString();
    }
    /**
     * Loads a save file's content onto a Prototype object by updating the game objects stored in it's HashMap.
     * @param objectState String describing the state of the game to be loaded.
     * @param prototype The Prototype to be updated.
     */
    public static void loadSaveToPrototype(String objectState, Prototype prototype) {
        // ha #ERROR a fájlban, corrupted a save
        // ha fájlban neve null vagy "-" akkor null
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(objectState.split("\\r?\\n|\\r")));
        Tekton currentTekton = null;
        for (String line : lines) {
            if (line.isEmpty()) continue;
            ArrayList<String> lineSplit = new ArrayList<>(Arrays.asList(line.split(" ")));
            switch(lineSplit.get(0)) {
                case "<gamestate>":
                readGameState(lineSplit, prototype);
                break;
                case "<current>":
                prototype.getGame().setCurrentPlayer((Player)prototype.objects.get(lineSplit.get(1)));
                break;
                case "<tekton>":
                currentTekton = readTekton(lineSplit, prototype);
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
        game.setRoundCount(Integer.parseInt(gameState.get(1)));
        for (int i = 2; i < gameState.size(); i+=2) {
            Player p = gameState.get(i + 1).equals("i") ? new InsectMaster() : new MushroomMaster();
            prototype.objects.put(gameState.get(i), p);
            game.addPlayer(p);
        }
    }
    private static Tekton readTekton(ArrayList<String> tektonState, Prototype prototype) {
        Tekton t = null;
        switch (tektonState.get(2)) {
            case "Tekton":
            t = new Tekton();
            break;
            case "AbsorbingTekton":
            t = new AbsorbingTekton();
            break;
            case "DeadEndTekton":
            t = new DeadEndTekton();
            break;
            case "HealingTekton":
            t = new HealingTekton();
            break;
            case "HeterogeneousTekton":
            t = new HeterogeneousTekton();
            break;
            case "InfertileTekton":
            t = new InfertileTekton();
            break;
            default:
            t = new Tekton();
        }
        prototype.objects.put(tektonState.get(1), t);
        prototype.getGame().getGameField().add(t);
        return t;
    }
    private static void readNeighbors(ArrayList<String> neighborsString, Prototype prototype, Tekton t) {
        for (int i = 1; i < neighborsString.size(); i++) {
            if (prototype.objects.containsKey(neighborsString.get(i))) {
                Tekton target = (Tekton)prototype.objects.get(neighborsString.get(i));
                t.addNeighbour(target);
                target.addNeighbour(t);
            }

        }
    }
    private static void readMushroom(ArrayList<String> mushroomString, Prototype prototype, Tekton t) {
        MushroomBody m = null;
        if (mushroomString.size() > 6 && mushroomString.get(6).equals("-s")) {
            m = new SuperMushroomBody(t);
        } else {
            m = new MushroomBody(t);
        }
        prototype.objects.put(mushroomString.get(1), m);
        MushroomMaster mm = (MushroomMaster)prototype.objects.get(mushroomString.get(2));
        mm.getMushrooms().add(m);
        m.setAlive(mushroomString.get(3).equals("1"));
        m.setSporeCount(Integer.parseInt(mushroomString.get(4)));
        m.setActions(Integer.parseInt(mushroomString.get(5)));
        t.setMushroomBody(m);   
        for (int i = 6; i < mushroomString.size(); i++) {
            switch (mushroomString.get(i)) {
                case "CutBlockingSpore":
                    m.getSpores().add(new CutBlockingSpore());
                    break;
        
                case "RegularSpore":
                m.getSpores().add(new RegularSpore());
                    break;
        
                case "ParalyzingSpore":
                m.getSpores().add(new ParalyzingSpore());
                    break;
        
                case "SlowingSpore":
                m.getSpores().add(new SlowingSpore());
                    break;
        
                case "SpeedingSpore":
                m.getSpores().add(new SpeedingSpore());
                    break;
        
                case "SplittingSpore":
                m.getSpores().add(new SplittingSpore());
                    break;
                default:
                    break;
            }
        }
    }
    private static void readInsect(ArrayList<String> insectString, Prototype prototype, Tekton t) {
        Insect i = new Insect(t, Integer.parseInt(insectString.get(3)), 
            (InsectMaster)prototype.objects.get(insectString.get(2)));
        t.getInsects().add(i);
        prototype.objects.put(insectString.get(1), i);
        if (insectString.size() > 4) {
            i.setCanCutMycelium(insectString.get(4).equals("1"));
        }
        if (insectString.size() > 5) {
            i.setStunned(insectString.get(5).equals("1"));
        }
    }
    private static void readMycelium(ArrayList<String> myceliumString, Prototype prototype, Tekton t) {
        Tekton target = (Tekton)prototype.objects.get(myceliumString.get(3));
        if (target == null) return;
        Mycelium m = new Mycelium(
            (MushroomMaster)prototype.objects.get(myceliumString.get(2)), t, target
        );
        
        t.getMyceliums().add(m);
        target.getMyceliums().add(m);
        prototype.objects.put(myceliumString.get(1), m);
        if (myceliumString.size() > 4) {
            m.setCut(myceliumString.get(4).equals("1"));
        }
        if (myceliumString.size() > 5) {
            m.setTimeToLive(Integer.parseInt(myceliumString.get(5)));;
        }
    }
    private static void readSpore(ArrayList<String> sporeString, Prototype prototype, Tekton t) {
        Spore s = null;
        switch (sporeString.get(1)) {
            case "CutBlockingSpore":
                s = new CutBlockingSpore();
                break;
    
            case "RegularSpore":
                s = new RegularSpore();
                break;
    
            case "ParalyzingSpore":
                s = new ParalyzingSpore();
                break;
    
            case "SlowingSpore":
                s = new SlowingSpore();
                break;
    
            case "SpeedingSpore":
                s = new SpeedingSpore();
                break;
    
            case "SplittingSpore":
                s = new SplittingSpore();
                break;
            default:
                s = new RegularSpore();
        }
        s.setMushroomMaster((MushroomMaster)prototype.objects.get(sporeString.get(2)));
        t.getSpores().add(s);
    }
}
