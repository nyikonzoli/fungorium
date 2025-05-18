package com.bithappens;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;


// Prototype to keep track of all object instances that can be referenced by one of the user's actions
public class Prototype {
    HashMap<String, Object> objects = new HashMap<>();
    // Game is stored differently, there can only be 1 Game instance per Prototype (the game instance
    // also has no explicit name and can't be referenced by the user in any of the commands' arguments)
    private Game game;
    private boolean canSplitRandomly = false;
    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public Prototype(){
        ArrayList<String> command = new ArrayList<>();
        newgame(command); 
    }
    
    public String getKey(Object object) {
        // if duplicate key name, unforseen outcome
        for (String key : objects.keySet()) {
            if (objects.get(key).equals(object)) {
                return key;
            }
        }
        if (object == null) {
            return "-";
        }
        return "#ERROR";
    }

    /**
     * Completes the action specified in it's parameter. Needs to be called for every new line on input.
     * @param inputLine One line of input to be interpreted as a command and executed.
     * @return The output of the command.
     */
    public String handleInput(String inputLine) {
        ArrayList<String> command = new ArrayList<>(Arrays.asList(inputLine.split(" ")));
        String retval = "Command not found";
        switch (command.get(0)) {
            case "newgame":
                retval = newgame(command);
                break;
            case "load":
                retval = load(command);
                break;
            case "save":
                retval = save(command);
                break;
            case "exit":
                // Creates an autosave file on exit command
                retval = save(new ArrayList<String>(Arrays.asList("save", "autosave.txt"))); 
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
    
    /**
     * Handles the input of a "newgame" command. Creates a game field for 2+2 players to play on.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String newgame(ArrayList<String> command) {
        objects.clear();
        canSplitRandomly = true;
        game = new Game();
        Tekton t1 = new Tekton();
        Tekton t2 = new Tekton();
        Tekton t3 = new Tekton();
        Tekton t4 = new Tekton();
        Tekton t5 = new Tekton();
        Tekton t6 = new Tekton();
        HeterogeneousTekton tHetero = new HeterogeneousTekton();
        InfertileTekton tI= new InfertileTekton();
        DeadEndTekton tD = new DeadEndTekton();
        HealingTekton tHealing = new HealingTekton();
        AbsorbingTekton tA = new AbsorbingTekton();

        MushroomMaster mmaster1 = new MushroomMaster();
        MushroomMaster mmaster2 = new MushroomMaster();
        InsectMaster imaster1 = new InsectMaster();
        InsectMaster imaster2 = new InsectMaster();



        ArrayList<Tekton> t1Neighbours = new ArrayList<>();
        t1Neighbours.add(t2);
        t1Neighbours.add(tHetero);
        t1Neighbours.add(t3);
        t1.addNeighbours(t1Neighbours);

        ArrayList<Tekton> t2Neighbours = new ArrayList<>();
        t2Neighbours.add(t1);
        t2Neighbours.add(tHetero);
        t2Neighbours.add(tA);
        t2.addNeighbours(t2Neighbours);

        ArrayList<Tekton> t3Neighbours = new ArrayList<>();
        t3Neighbours.add(t1);
        t3Neighbours.add(tHetero);
        t3Neighbours.add(tHealing);
        t3.addNeighbours(t3Neighbours);

        ArrayList<Tekton> tHeteroNeighbours = new ArrayList<>();
        tHeteroNeighbours.add(t1);
        tHeteroNeighbours.add(t2);
        tHeteroNeighbours.add(tA);
        tHeteroNeighbours.add(t4);
        tHeteroNeighbours.add(t6);
        tHeteroNeighbours.add(tHealing);
        tHeteroNeighbours.add(t3);
        tHetero.addNeighbours(tHeteroNeighbours);

        ArrayList<Tekton> tANeighbours = new ArrayList<>();
        tANeighbours.add(t2);
        tANeighbours.add(tHetero);
        tANeighbours.add(t4);
        tA.addNeighbours(tANeighbours);


        ArrayList<Tekton> tHealingNeighbours = new ArrayList<>();
        tHealingNeighbours.add(t3);
        tHealingNeighbours.add(tHetero);
        tHealingNeighbours.add(t6);
        tHealingNeighbours.add(tI);
        tHealingNeighbours.add(t5);
        tHealing.addNeighbours(tHealingNeighbours);

        ArrayList<Tekton> t4Neighbours = new ArrayList<>();
        t4Neighbours.add(tHetero);
        t4Neighbours.add(tA);
        t4Neighbours.add(tD);
        t4Neighbours.add(t6);
        t4.addNeighbours(t4Neighbours);

        ArrayList<Tekton> t5Neighbours = new ArrayList<>();
        t5Neighbours.add(tHealing);
        t5Neighbours.add(tI);
        t5.addNeighbours(t5Neighbours);

        ArrayList<Tekton> tINeighbours = new ArrayList<>();
        tINeighbours.add(t5);
        tINeighbours.add(tHealing);
        tINeighbours.add(t6);
        tI.addNeighbours(tINeighbours);

        ArrayList<Tekton> t6Neighbours = new ArrayList<>();
        t6Neighbours.add(tHealing);
        t6Neighbours.add(tI);
        t6Neighbours.add(tD);
        t6Neighbours.add(t4);
        t6Neighbours.add(tHetero);
        t6.addNeighbours(t6Neighbours);

        ArrayList<Tekton> tDNeighbours = new ArrayList<>();
        tDNeighbours.add(t6);
        tDNeighbours.add(t4);
        tD.addNeighbours(tDNeighbours);

        Insect insect1 = new Insect(t1, 3, imaster1);
        t1.addInsect(insect1);
        imaster1.addInsect(insect1);

        MushroomBody mb1 = new MushroomBody(t1);
        t1.setMushroomBody(mb1);
        mmaster1.addMushroom(mb1);

        Insect insect2 = new Insect(tD, 3, imaster2);
        tD.addInsect(insect2);
        imaster2.addInsect(insect2);

        MushroomBody mb2 = new MushroomBody(tD);
        tD.setMushroomBody(mb2);
        mmaster2.addMushroom(mb2);

        game.extendField(t1);
        game.extendField(t2);
        game.extendField(t3);
        game.extendField(tHetero);
        game.extendField(tA);
        game.extendField(tHealing);
        game.extendField(t4);
        game.extendField(t5);
        game.extendField(tI);
        game.extendField(t6);
        game.extendField(tD);
        game.addPlayer(mmaster1);
        game.addPlayer(mmaster2);
        game.addPlayer(imaster1);
        game.addPlayer(imaster2);
        game.setCurrentPlayer(mmaster1);

        objects.put("t1", t1);
        objects.put("t2", t2);
        objects.put("t3", t3);
        objects.put("tHetero", tHetero);
        objects.put("tA", tA);
        objects.put("tHealing", tHealing);
        objects.put("t4", t4);
        objects.put("t5", t5);
        objects.put("tI", tI);
        objects.put("t6", t6);
        objects.put("tD", tD);
        objects.put("imaster1", imaster1);
        objects.put("imaster2", imaster2);
        objects.put("mmaster1", mmaster1);
        objects.put("mmaster2", mmaster2);
        objects.put("mb1", mb1);
        objects.put("mb2", mb2);
        objects.put("insect1", insect1);
        objects.put("insect2", insect2);
        

        return "New Game Started";
    }
    /**
     * Handles the input of a "load" command. Loads a save file's content onto this Prototype instance.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String load(ArrayList<String> command) {
        Path path = Path.of(command.get(1));
        try {
            SaveGame.loadSaveToPrototype(Files.readString(path), this);
        } catch (IOException e) {
            return "Load failure: " + e.getMessage();
        }
        
        return "Load success";
    }
    /**
     * Handles the input of a "save" command. Saves the game into a player specified file.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String save(ArrayList<String> command) {
        Path path = Path.of(command.get(1));
        // If file doesn't exist, it's created. If it does, it's overwritten.
        try {
            Files.writeString(path, SaveGame.objectStateToString(this), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            return "Save failure: " + e.getMessage();
        }
        return "Save success: " + command.get(1);
    }
    /**
     * Handles the input of a "list" command. Lists the objet instances' names the player can reference.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String list(ArrayList<String> command) {
        StringBuilder b = new StringBuilder();
        switch (command.get(1)) {
            case "-mu":
                // gombatestek: <azonosító> <tulajdonos> <tekton> <szupergomba?>
                for (String s : Arrays.asList(SaveGame.objectStateToString(this).split("\\r?\\n|\\r"))) {
                    if (s.startsWith("<mushroom>")) {
                        b.append(s + "\n");
                    }
                }
                return "Gombatest: <name> <owner> <alive?> <sporecount> <actions> [-s] [spore1 type] [spore2 type] ...\n" + b.toString();
            case "-my":
                // fonalak: <azonosító> <tulajdonos> <tekton1> <tekton2>
                for (String s : Arrays.asList(SaveGame.objectStateToString(this).split("\\r?\\n|\\r"))) {
                    if (s.startsWith("<mycelium>")) {
                        b.append(s + "\n");
                    }
                }
                return "Fonál: <name> <owner> <target tekton> [cut?] [time to live]\n" + b.toString();
            case "-in":
                for (String s : Arrays.asList(SaveGame.objectStateToString(this).split("\\r?\\n|\\r"))) {
                    if (s.startsWith("<insect>")) {
                        b.append(s + "\n");
                    }
                }
                return "Rovar:  <name> <owner> <action points> [can cut?] [stunned?]\n" + b.toString();
            case "-sp":
                for (String s : Arrays.asList(SaveGame.objectStateToString(this).split("\\r?\\n|\\r"))) {
                    if (s.startsWith("<spore>")) {
                        b.append(s + "\n");
                    }
                }
                return "Spóra: <spore type> <owner>\n" + b.toString();
            case "-te":
                for (String s : Arrays.asList(SaveGame.objectStateToString(this).split("\\r?\\n|\\r"))) {
                    if (s.startsWith("<tekton>")) {
                        b.append(s + "\n");
                    }
                }
                return "Tekton: <name> <type>\n" + b.toString();
            case "-im":
                for (String s : Arrays.asList(SaveGame.objectStateToString(this).split("\\r?\\n|\\r"))) {
                    if (s.startsWith("<insectmaster>")) {
                        b.append(s + "\n");
                    }
                }
                return "insectmaster: \n" + b.toString();
            case "-mm":
                for (String s : Arrays.asList(SaveGame.objectStateToString(this).split("\\r?\\n|\\r"))) {
                    if (s.startsWith("<mushroommaster>")) {
                        b.append(s + "\n");
                    }
                }
                return "mushroommaster: \n" + b.toString();
            default:
                return "Command unknown";
        }
    }
    /**
     * Handles the input of a "growmu" command.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String growmu(ArrayList<String> command) {
        MushroomMaster currentMaster = (MushroomMaster)game.getCurrentPlayer();
        MushroomMaster commandMaster = (MushroomMaster)objects.get(command.get(1));
        Tekton target = (Tekton)objects.get(command.get(2));

        if(!currentMaster.equals(commandMaster)){
            return "Wrong player";
        }

        // Ha újat akar növeszteni
        if (command.size() == 3) {
            currentMaster.initiateMushroomGrowth(target);

            // Verifying the action has taken place
            if (target.getMushroomBody() == null) {
                return "Grow failure";
            }
            else{
                int mushroomCount = 0;
                for (int i = 0; i < game.getPlayers().size(); i++) {
                    if(game.getPlayers().get(i) instanceof MushroomMaster){
                        mushroomCount += ((MushroomMaster)game.getPlayers().get(i)).getMushrooms().size();
                    }
                }
                String mushroomName = "mu" + mushroomCount;
                objects.put(mushroomName, target.getMushroomBody());
                return "Grow success: " + mushroomName;
            }
        }
        // Grow SuperMushroom
        else if (command.size() == 4 && "-s".equals(command.get(3))) {
            MushroomBody growSuperMushroomBody = target.getMushroomBody();
            String superMushroomKey = "mb1";
            for (String key : objects.keySet()) {
                if (objects.get(key).equals(growSuperMushroomBody)) {
                    superMushroomKey = key;
                }
            }
            currentMaster.initiateSuperMushroomGrowth(growSuperMushroomBody);
            // Verifying the action has taken place
            if (target.getMushroomBody() == null || !(target.getMushroomBody() instanceof SuperMushroomBody)) {
                return "Grow failure";
            }
            else{
                objects.replace(superMushroomKey, target.getMushroomBody());
                return "SuperMushroom grow success";
            }
        }

        return "Invalid input";
    }
    /**
     * Handles the input of a "growmy" command.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String growmy(ArrayList<String> command) {

        MushroomMaster currentMaster = (MushroomMaster)game.getCurrentPlayer();
        MushroomMaster commandMaster = (MushroomMaster)objects.get(command.get(1));
        Tekton source = (Tekton)objects.get(command.get(2));
        Tekton target = (Tekton)objects.get(command.get(3));

        if(!currentMaster.equals(commandMaster)){
            return "Wrong player";
        }

        if (!source.isNeighbour(target)) {
            return "Tekton neighbor not found";
        }

        currentMaster.initiateMyceliumGrowth(source, target);
        
        // Ellenorzes - kicsit felokositva
        for (Mycelium m : source.getMyceliums()) {
            if (m.getTektonEnd().equals(target) || m.getTektonStart().equals(target)) {
                int myceliumCount = 0;
                for (int i = 0; i < game.getGameField().size(); i++) {
                    myceliumCount += game.getGameField().get(i).getMyceliums().size();
                }
                myceliumCount /= 2;
                objects.put("m" + (myceliumCount), m);
                return "Grow success: m" + myceliumCount;
            }
        }

        return "Grow failure";

    }
    /**
     * Handles the input of a "spreadsp" command.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String spreadsp(ArrayList<String> command) {
        // spreadsp <forrás gombatest> <tekton>
        MushroomBody mushroom = (MushroomBody)objects.get(command.get(1));
        Tekton target = (Tekton)objects.get(command.get(2));
        MushroomMaster mmaster = (MushroomMaster)game.getCurrentPlayer();
        int sporeBeforeThrow = target.getSpores().size();

        mmaster.initiateSporeSpreading(mushroom, target);

        // Ellenőrzés
        if (sporeBeforeThrow + 3 == target.getSpores().size()) {
            return "Spread success";
        }
        return "Spread failure";
    }
    /**
     * Handles the input of an "eatin" command.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String eatin(ArrayList<String> command) {
        Player currentMaster = game.getCurrentPlayer();
        MushroomMaster commandMaster = (MushroomMaster)objects.get(command.get(1));
        Insect eatInsect = (Insect)objects.get(command.get(2));
        Tekton eatTekton = eatInsect.getLocation();
        ArrayList<Mycelium> allMyceliums = eatTekton.getMyceliums();
        int insectNumber = eatTekton.getInsects().size();

        if(!currentMaster.equals(commandMaster)){
            return "Wrong player";
        }

        for(Mycelium m : allMyceliums){
            if(m.getMaster().equals(currentMaster) && (m.getTektonEnd().equals(eatTekton) || m.getTektonStart().equals(eatTekton))){
                m.eatInsect(eatInsect, eatTekton);
                if (insectNumber - 1 == eatTekton.getInsects().size()) {
                    int mushroomCount = 0;
                    for (int i = 0; i < game.getPlayers().size(); i++) {
                        if(game.getPlayers().get(i) instanceof MushroomMaster){
                            mushroomCount += ((MushroomMaster)game.getPlayers().get(i)).getMushrooms().size();
                        }
                    }
                    objects.put("mu" + (mushroomCount), eatTekton.getMushroomBody());
                    return "Eat success";
                }
            }
        }
        return "Eat failure";
    }
    /**
     * Handles the input of a "move" command.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String move(ArrayList<String> command) {
        // <rovar> <tekton>
        Insect insect = (Insect)objects.get(command.get(1));
        if (insect == null) return "Insect not found";
        Tekton target = (Tekton)objects.get(command.get(2));
        InsectMaster im = insect.getImaster();
        InsectMaster currentPlayer = (InsectMaster)game.getCurrentPlayer();
        int targetInsects = target.getInsects().size();

        if(!im.equals(currentPlayer)){
            return "Wrong player";
        }
        insect.moveTo(target);

        // Ellenorzes
        if (targetInsects + 1 == target.getInsects().size()) {
            return "Move success";
        }

        return "Move failure";


    }
    /**
     * Handles the input of an "eatsp" command.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String eatsp(ArrayList<String> command) {
        // hogy rendesen <név>-1 <név>-2 formában jelenjenek meg splitelt rovarok a 
        // hashmapben
        String iName = command.get(1);
        Insect insect = (Insect)objects.get(iName);
        if (insect == null) return "Insect not found";
        InsectMaster im = insect.getImaster();
        InsectMaster currentPlayer = (InsectMaster)game.getCurrentPlayer();
        if(!im.equals(currentPlayer)){
            return "Wrong player";
        }
        int initialSporeCount = insect.getLocation().getSpores().size();
        int initialImasterInsectCount = im.getAllInsects().size();
        im.initiateSporeEating(insect.getLocation(), insect);
        if (initialSporeCount <= insect.getLocation().getSpores().size()) {
            return "Eat failure";
        }
        // if Insect split
        if (initialImasterInsectCount < im.getAllInsects().size()) {
            objects.remove(iName);
            objects.put(iName + "-1", insect);
            objects.put(iName + "-2", im.getAllInsects().get(im.getAllInsects().size() - 1));
            return "Eat success, Insect split: " + iName + "-1 " + iName + "-2";
        }
        
        return "Eat success";
    }
    /**
     * Handles the input of a "cut" command.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String cut(ArrayList<String> command) {

        Insect cutInsect = (Insect)objects.get(command.get(1));
        Mycelium cutMyc = (Mycelium)objects.get(command.get(2));
        ArrayList<Mycelium> tektonMyceliums = new ArrayList<>(cutInsect.getLocation().getMyceliums());

        InsectMaster im = cutInsect.getImaster();
        im.initiateMyceliumCutting(cutMyc, cutInsect);

        // Check
        for(Mycelium m : tektonMyceliums){
            if (m.equals(cutMyc) && m.isCut()) {
                return "Cut success";
            }
        }
        return "Cut failure";
    }
    /**
     * Handles the input of a "nextround" command.
     * @return The output string of the command
     */
    private String nextround() {
        game.nextRound();
        return "Next round";
    }
    /**
     * Handles the input of a "growmu" command.
     * @return The output string of the command
     */
    private String nextplayer() {
        game.nextPlayer();
        if (canSplitRandomly) {
            randomSplit();
        }
        return "Next player";
    }
    /**
     * Handles the input of a "split" command.
     * @param command ArrayList containing all the arguments in a command
     * @return The output string of the command
     */
    private String split(ArrayList<String> command) {
        // két új tekton neve: <eredeti név>-1, <eredeti név>-2

        Tekton splitTekton = (Tekton)objects.get(command.get(1));
        String splitTektonName = command.get(1);
        ArrayList<Tekton> twoNewTektons = splitTekton.split();
        
        if(twoNewTektons != null){
            game.extendField(twoNewTektons.get(0));
            game.extendField(twoNewTektons.get(1));   
        
            objects.put(splitTektonName + "-1", twoNewTektons.get(0));
            objects.put(splitTektonName + "-2", twoNewTektons.get(1));
        
            game.shrinkField(splitTekton);
            return "Split success: " + command.get(1) + "-1, " + command.get(1) + "-2";
        }
        return "Split failed (invalid input or Tekton cant break (has insect or mushroom))";
    }
    /**
     * Handles the input of a "print" command.
     * @return The output string of the command
     */
    private String print() {
        return SaveGame.objectStateToString(this);
    }

    private String randomSplit() {
        Random r = new Random();
        String out = "";
        //long randomSplit = ZonedDateTime.now().toInstant().toEpochMilli();
        if (r.nextInt(100) % 20 == 0) {
            int splitTektonIndex = r.nextInt(game.getGameField().size());
            Tekton splitTekton = game.getGameField().get(splitTektonIndex);
            String splitTektonKey = getKey(splitTekton);
            System.out.println(splitTektonKey);
            out = handleInput(
                "split " +
                splitTektonKey
            );
            //System.out.println(out);
        }
        return out;
    }
    
    /// RANDOM MAP GENERATION
    /**
     * Generates a seeded pseudorandom Tekton field and starts a new game on it
     * @param seed Generation seed
     * @param playerCount Number of players
     */
    public void randomMapGeneration(long seed, int playerCount) {
        if (playerCount < 4) {
            return;
        }

        game = new Game();
        objects.clear();
        canSplitRandomly = true;

        int imcount = playerCount / 2;
        int mmcount = playerCount - imcount;

        Random rand = new Random(seed);
        int tektonMultiplier = 6;
        Map<Integer, Set<Integer>> adjacency = TriangulatedGraph.generateTriangulatedGraph(seed, mmcount * tektonMultiplier);

        /*
         * Generate Tekton map from graph
         */

        ArrayList<Tekton> generatedTektons = new ArrayList<>();
        for (Integer i : adjacency.keySet()) {
            Tekton t;
            int nextrand = rand.nextInt(10);
            switch (nextrand) {
                case 0:
                    t = new HeterogeneousTekton();
                    break;
                case 1:
                    t = new HealingTekton();
                    break;
                case 2:
                    t = new AbsorbingTekton();
                    break;
                case 3:
                    t = new DeadEndTekton();
                    break;
                case 4:
                    t = new InfertileTekton();
                    break;
                default:
                    t = new Tekton();
                    break;
            }
            generatedTektons.add(t);
        }
        // Tekton generation
        for (int i = 0; i < generatedTektons.size(); i++) {
            Tekton t = generatedTektons.get(i);
            Set<Integer> neighborIndices = adjacency.get(i);
            for (Integer neighborIdx : neighborIndices) {
                t.addNeighbour(generatedTektons.get(neighborIdx));
            }
            game.extendField(t);
            objects.put("T" + (i+1), t);
        }
        // MushroomMaster generation
        ArrayList<Tekton> startTektons = new ArrayList<>();
        for (int i = 0; i < mmcount; i++) {
            MushroomMaster mmaster = new MushroomMaster();
            Tekton start = generatedTektons.get(rand.nextInt(generatedTektons.size()));
            startTektons.add(start);
            MushroomBody mb = new MushroomBody(start);
            start.setMushroomBody(mb);
            mmaster.addMushroom(mb);
            game.addPlayer(mmaster);
            objects.put("mmaster" + (i+1), mmaster);
            objects.put("mb" + (i+1), mb);
        }
        game.setCurrentPlayer(game.getPlayers().get(0));
        // InsectMaster generation
        for (int i = 0; i < imcount; i++) {
            InsectMaster imaster = new InsectMaster();
            Tekton start = startTektons.get(i);
            Insect insect = new Insect(start, 3, imaster);
            start.addInsect(insect);
            imaster.addInsect(insect);
            game.addPlayer(imaster);
            objects.put("imaster" + (i+1), imaster);
            objects.put("insect" + (i+1), insect);
        }
    }
}
