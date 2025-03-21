public abstract class Player {
    private int score;

    public Player(){}

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void onRoundStart(){

    }

    public void selfReport(){

    }

    public void incrementScore(){
        System.err.println("Player.incrementScore()"); // TODO: lehet Overrideolni kell a gyerekekben csak a skeletonhoz
        score++;
    }
}
