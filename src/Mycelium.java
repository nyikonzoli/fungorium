import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Represents a mycelium connection between two tectonic plates in the game.
 */
public class Mycelium {
    static int INITITAL_TTL = 20;

    private MushroomMaster grower;
    private int timeToLive;
    private Tekton tektonStart, tektonEnd;

    /**
     * Default constructor for Mycelium.
     */
    public Mycelium(){}

    /**
     * Constructs a new Mycelium instance with the specified grower and tectons.
     * 
     * @param m The MushroomMaster who grew the mycelium.
     * @param start The starting Tekton of the mycelium connection.
     * @param end The ending Tekton of the mycelium connection.
     */
    public Mycelium(MushroomMaster m, Tekton start, Tekton end){
        System.out.println("Mycelium.Mycelium(MushroomMaster m, Tekton start, Tekton end)");
        grower = m;
        tektonStart = start;
        tektonEnd = end;
        timeToLive = INITITAL_TTL;
    }

    /**
     * Gets the starting tecton of the mycelium connection.
     * 
     * @return The Tekton where the mycelium starts.
     */
    public Tekton getTektonStart(){
        System.out.println("Mycelium.getTektonStart()");
        return tektonStart;
    }

    /**
     * Gets the ending tecton of the mycelium connection.
     * 
     * @return The Tekton where the mycelium ends.
     */
    public Tekton getTektonEnd(){
        System.out.println("Mycelium.getTektonEnd()");
        return tektonEnd;
    }
    
    
    /**
     * Starts the process of destroying this Mycelium instance
     */
    public void disappear() {
        System.out.println("Mycelium.disappear()");
        tektonStart.removeMycelium(this);
        tektonEnd.removeMycelium(this);
    }

    /**
     * Decreases the time-to-live (TTL) value of the mycelium by 1.
     */
    public void decreaseTTL(){
        System.out.println("Mycelium.decreaseTTL()");
        timeToLive--;
    }

    /**
     * Decreases ttl if the myceleum is not connected to a Mushroom
     * @param decreaseTTLifNotConnected true if its not connected
     */
    public void onRoundStart(boolean decreaseTTLifNotConnected){
        if(decreaseTTLifNotConnected){
            decreaseTTL();
        }

    }


    /**
     * Checks if the Myceleum network is connected to a mushroomBody
     * @return true if there is a mushroomBody on the network, false if not
     */
    public boolean isConnectedToMushroom(){
        Set<Tekton> visited = new HashSet<>();       
        Queue<Tekton> queue = new LinkedList<>();    
        queue.add(tektonStart);    
        
        while (!queue.isEmpty()) {
            Tekton currentTekton = queue.poll();
            if (visited.contains(currentTekton))
                continue;
            visited.add(currentTekton);

            if (currentTekton.getMushroomBody() != null) {     
                return true;                 
            }

            //check all the Mycelium on the Tekton
            for (Mycelium m : currentTekton.getMyceliums()) {
                //get the Tekton from the Myceleum regardless of the direction
                Tekton neighbour = m.tektonStart == currentTekton ? m.tektonEnd : m.tektonStart;

                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }
        return false;
    }

    
    /**
     * starts the insect eating
     * @param i The Insect that has to be eaten
     * @param t The Tekton which the insect is on
     */
    public void eatInsect(Insect i, Tekton t){
        if(i.isStunned()){
            t.removeInsect(i);
            MushroomBody mb = t.growMushroom(grower);
            grower.incrementScore();
            grower.addMushroom(mb);
        }
    }
}
