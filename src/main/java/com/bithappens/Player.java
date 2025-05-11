package com.bithappens;

import java.util.List;

/**
 * Represents a player in the game.
 */
public abstract class Player {
    private int score;

    /**
     * Constructs a new Player.
     */
    public Player(){
        score = 0;
    }

    /**
     * Gets the name of the Player type
     * @return name of the player type
     */
    public abstract String getTypeName();

    /**
     * Returns the score of the player.
     * 
     * @return The current score of the player.
     */
    public int getScore(){
        return score;
    }

    /**
     * Sets the score of the player.
     * 
     * @param score The new score for the player.
     */
    public void setScore(int score){
        this.score = score;
    }
    /**
     * Sets the player up for a new round
     */
    public void onRoundStart(){

    }

    /**
     * A method for reporting the player's status in the game.
     * 
     * @param g The game instance to report the player's status to.
     */
    public void selfReport(Game g){
        // Might be obsolete
    }
    /** 
     * Increments the Player's score by 1
     */
    public void incrementScore(){
        score++;
    }

    public List<Object> getOwnedObjects() {
        return null;
    }  
}
