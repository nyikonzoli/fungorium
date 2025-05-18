package com.bithappens;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game, managing players, game field, and rounds.
 */
public class Game {
    int finishGame = 20;

    private List<Player> players;

    private List<Player> topMushroomMasters;
    private List<Player> topInsectMasters;
    
    private Player currentPlayer;
    private List<Tekton> gameField;
    private int roundCount;

    private boolean gameOver;
    
    /**
     * Setter for roundcount
     * @param roundCount
     */
    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    /**
     * Constructs a new Game instance.
     * Initializes all lists for players, top Mushroom Masters, top Insect Masters, and the game field.
     */
    public Game(){
        players = new ArrayList<>();
        topInsectMasters = new ArrayList<>();
        topMushroomMasters = new ArrayList<>();
        gameField = new ArrayList<>();
        roundCount = 0;
        gameOver = false;
    }

    /**
     * Gets the current player
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    /**
     * Sets the current player
     * @param p Current player
     */
    public void setCurrentPlayer(Player p){
        currentPlayer = p;
    }

    /**
     * Getter for topMushroomMasters.
     * @return topMushroomMasters
     */
    public List<Player> getTopMushroomMasters() {
        return topMushroomMasters;
    }

    public void setTopMushroomMasters(List<Player> masters) {
        topMushroomMasters = masters;
    }
    /**
     * Getter for players.
     * @return players
     */
    public List<Player> getPlayers() {
        return players;
    }
    /**
     * Getter for roundCount.
     * @return roundCount
     */
    public int getRoundCount() {
        return roundCount;
    }
    /**
     * Getter for topInsectMasters.
     * @return topInsectMasters
     */
    public List<Player> getTopInsectMasters() {
        return topInsectMasters;
    }

    public void setTopInsectMaster(List<Player> masters) {
        topInsectMasters = masters;
    }

    public boolean getGameOver(){
        return gameOver;
    }

    public void setGameOver(boolean go){
        gameOver = go;
    }


    /**
     * Starts the game, setting up the initial game state.
     */
    public void start(){

        //TODO: pálya beolvasása, Megjegyzés: a load-dal történő pályabeolvasást egyelőre a prototípus végzi, ugyanis ennek nyilván kell tartania az objetumokat neveik szerint
    }
    /**
     * Next Player's turn
     */
    public void nextPlayer() {
        int nextIdx = players.indexOf(currentPlayer) + 1;
        //System.out.println(nextIdx + " " + players.size());
        if (nextIdx >= players.size())  {
            nextIdx = 0;
            nextRound();
        }
        currentPlayer = players.get(nextIdx);
    }
    /**
     * Starts a new round, contains all the pre-round setup required
     */
    public void nextRound(){
        roundCount++;

        for(Player p : players){
            p.onRoundStart();
            p.selfReport(this);
        }
        for(Tekton t : gameField){
            t.onRoundStart();
        }

        if(!gameOver){
            gameOver = winCheck(); 
        }
    }

    /**
     * Ends the game, announces the winners and performs necessary clean-up or state changes.
     */
    public void end(){
        System.out.println("Final Mushroom Master Winner: "  + topMushroomMasters.get(0).getScore() + " pts)");
        System.out.println("Final Insect Master Winner: "  + topInsectMasters.get(0).getScore() + " pts)");
        // TODO: sum better way
    }
    /**
     * updates top players and ends the game, when its over
     */
    public boolean winCheck(){
        if(roundCount >= finishGame){

            topInsectMasters.clear();
            topMushroomMasters.clear();
            
            int maxMushroomScore = -1;
            int maxInsectScore = -1;

            for (Player p : players) {
                String type = p.getTypeName();
                int score   = p.getScore();

                //mushroomMaster
                if ("Mushroom Master".equals(type)) { //TODO: ezt lehet jobb lenne enum-mal megoldani
                    if (score > maxMushroomScore) {
                        maxMushroomScore = score;
                        topMushroomMasters.clear(); //if there is a new max there is one top player
                        topMushroomMasters.add(p);
                    } else if (score == maxMushroomScore) {  //draw
                        topMushroomMasters.add(p);
                    }

                //insectMaster
                } else if ("Insect Master".equals(type)) {
                    if (score > maxInsectScore) {
                        maxInsectScore = score;
                        topInsectMasters.clear();
                        topInsectMasters.add(p);
                    } else if (score == maxInsectScore) {  //draw
                        topInsectMasters.add(p);
                    }
                }
            }

            
            if (topInsectMasters.size() == 1 && topMushroomMasters.size() == 1){
                end();
                return true;
            } else{
                finishGame++; //the game goes on until there is 1 winner from each team
                return false;
            }
        }
        return false;
    }



    /**
     * Reports the current scores of all players.
     */
    public void reportScore(){
        System.out.println("Current scores:");
        for (Player p : players) {
            System.out.println(p.getTypeName() + ": " + p.getScore() + " points\n");
        }
    }

    /**
     * Adds a player to the game.
     * @param p The Player to add to the game.
     */
    public void addPlayer(Player p){
        players.add(p);
    }

    /**
     * Removes a player from the game.
     * @param p The Playe† to remove from the game.
     */
    public void removePlayer(Player p){
        players.remove(p);
    }

    /**
     * Adds a Tekton to the game field.
     * @param t The Tekton to add to the game field.
     */
    public void extendField(Tekton t){
        gameField.add(t);
    }

    /**
     * Removes a Tekton from the game field.
     * @param t The Tekton to remove from the game field.
     */
    public void shrinkField(Tekton t){
        for (Tekton currentTekton : gameField) {
            currentTekton.removeNeighbor(t);
        }
        gameField.remove(t);
    }

    /**
     * Gets the list of Tektons currently on the game field.
     * @return A list of Tekton objects representing the game field.
     */
    public List<Tekton> getGameField(){
        return gameField;
    }

    
}
