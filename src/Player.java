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

    public void onRoundStart(){

    }

    public void selfReport(Game g){

    }
    /** 
     * Increments the Player's score by 1
     */
    public void incrementScore(){
        System.out.println("Player.incrementScore()"); // TODO: lehet Overrideolni kell a gyerekekben csak a skeletonhoz
        score++;
    }
}
