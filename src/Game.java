import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private List<MushroomMaster> topMushroomMasters;
    private List<InsectMaster> topInsectMasters;
    private Player currentPlayer;
    private List<Tekton> gameField;
    private int roundCount;

    public Game(){
        players = new ArrayList<>();
        topInsectMasters = new ArrayList<>();
        topMushroomMasters = new ArrayList<>();
        gameField = new ArrayList<>();
        System.out.println("Game.Game()");
    }

    public void start(){
        System.out.println("Game.start()");
    }

    public void nextRound(){
        System.out.println("Game.nextRound()");
        for(Player p : players){
            if(p instanceof  MushroomMaster)
                currentPlayer = p;
        }
        currentPlayer.onRoundStart();
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

    public void addPlayer(Player p){
        players.add(p);
    }

    public void removePlayer(Player p){
        players.remove(p);
    }

    public void extendField(Tekton t){
        gameField.add(t);
    }

    public void shrinkField(Tekton t){
        gameField.remove(t);
    }

    public List<Tekton> getGameField(){
        return gameField;
    }

    public void selfReport(MushroomMaster mm){
        System.out.println("Game.selfReport(MushroomMaster mm)");
        mm.getScore();
    }
}
