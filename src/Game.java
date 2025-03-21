import java.util.List;

public class Game {
    private List<Player> players;
    private List<MushroomMaster> topMushroomMasters;
    private List<InsectMaster> topInsectMasters;
    private Player currentPlayer;
    private List<Tekton> gameField;
    private int roundCount;

    public Game(){
        System.out.println("Game.ctor()");
    }

    public void start(){
        System.out.println("Game.start()");
    }

    public void nextRound(){
        System.out.println("Game.nextRound()");
    }

    public void end(){
        System.out.println("Game.end()");
    }

    public void winCheck(){
        System.out.println("Game.winCheck()");
    }

    public void reportScore(){
        System.out.println("Game.reportScore()");
    }

}
