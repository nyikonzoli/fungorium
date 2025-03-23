public abstract class Player {
    private int score;

    public Player(){}

    public int getScore(){
        System.out.println("Player.getScore()");
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }
    /**
     * Sets the player up for a new round
     */
    public void onRoundStart(){

    }

    public void selfReport(Game g){
        // Might be obsolete
    }
    /** 
     * Increments the Player's score by 1
     */
    public void incrementScore(){
        System.out.println("Player.incrementScore()");
        score++;
    }
}
