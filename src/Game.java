import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game, managing players, game field, and rounds.
 */
public class Game {
    private List<Player> players;
    private List<MushroomMaster> topMushroomMasters;
    private List<InsectMaster> topInsectMasters;
    private Player currentPlayer;
    private List<Tekton> gameField;
    private int roundCount;

    /**
     * Constructs a new Game instance.
     * Initializes all lists for players, top Mushroom Masters, top Insect Masters, and the game field.
     */
    public Game(){
        players = new ArrayList<>();
        topInsectMasters = new ArrayList<>();
        topMushroomMasters = new ArrayList<>();
        gameField = new ArrayList<>();
        System.out.println("Game.Game()");
    }

    /**
     * Starts the game, setting up the initial game state.
     */
    public void start(){
        System.out.println("Game.start()");
    }

    /**
     * Starts a new round, contains all the pre-round setup required
     */
    public void nextRound(){
        System.out.println("Game.nextRound()");
        for(Player p : players){
            p.onRoundStart();
            selfReport(p);
        }
        winCheck(); 
        for(Tekton t : gameField){
            t.onRoundStart();
        }
    }

    /**
     * Ends the game and performs necessary clean-up or state changes.
     */
    public void end(){
        System.out.println("Game.end()");
    }
    /**
     * Checks for win condition
     */
    public void winCheck(){
        System.out.println("Game.winCheck()");
    }

    /**
     * Reports the current scores of all players.
     */
    public void reportScore(){
        System.out.println("Game.reportScore()");
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
        gameField.remove(t);
    }

    /**
     * Gets the list of Tektons currently on the game field.
     * @return A list of Tekton objects representing the game field.
     */
    public List<Tekton> getGameField(){
        return gameField;
    }
    /**
     * Compares players to the currently winning players
     * @param p Player to compare to the currently winning players
     */
    public void selfReport(Player p){
        System.out.println("Game.selfReport(Player p)");
        int test = p.getScore();
    }
}
