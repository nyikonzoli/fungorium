package com.bithappens;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Represents a mycelium connection between two tectonic plates in the game.
 */
public class Mycelium {
    static int INITITAL_TTL = 3;

    private MushroomMaster grower;
    private int timeToLive;


    private Tekton tektonStart, tektonEnd;
    private boolean hasBeenCut = false;

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
        grower = m;
        tektonStart = start;
        tektonEnd = end;
        timeToLive = INITITAL_TTL;
    }
    /**
     * Getter for the owner of the Mycelium.
     * @return Owner MushroomMaster
     */
    public MushroomMaster getMaster(){
        return grower;
    }
    /**
     * Getter for the TTL value of the Mycelium.
     * @return TTL value
     */
    public int getTimeToLive() {
        return timeToLive;
    }
    /**
     * Setter for TTL
     * @param ttl TTL
     */
    public void setTimeToLive(int ttl) { timeToLive = ttl; }

    /**
     * Gets the starting tecton of the mycelium connection.
     * 
     * @return The Tekton where the mycelium starts.
     */
    public Tekton getTektonStart(){
        return tektonStart;
    }

    /**
     * Gets the ending tecton of the mycelium connection.
     * 
     * @return The Tekton where the mycelium ends.
     */
    public Tekton getTektonEnd(){
        return tektonEnd;
    }
    /**
     * Getter for boolean determining whether the Mycelium has been cut.
     * @return True if the Mycelium has been cut
     */
    public boolean isCut() {
        return hasBeenCut;
    }
    /**
     * Setter for boolean determining whether the Mycelium has been cut.
     * @param hasBeenCut True if the Mycelium has been cut
     */
    public void setCut(boolean hasBeenCut) {
        this.hasBeenCut = hasBeenCut;
    }

    /**
     * Starts the process of destroying this Mycelium instance
     */
    public void disappear() {
        tektonStart.removeMycelium(this);
        tektonEnd.removeMycelium(this);
    }

    /**
     * Decreases the time-to-live (TTL) value of the mycelium by 1.
     */
    public void decreaseTTL(){
        timeToLive--;
    }

    private boolean visitLock = false;
    /**
     * Decreases ttl if the Myceleum is not connected to a Mushroom or is cut
     */
    public void onRoundStart(){
        // if no Tekton called onRoundStart on this instance
        if (!visitLock) {
            // if one or both tektons decide to lower it (and one isn't healingtekton) then lower by only one
            // if one is healing and one is absorbing, the healing is the king
            int finalTTLchange = (tektonStart.changeTTL(this) + tektonEnd.changeTTL(this)) < 0 ? -1 : 0;
            if (hasBeenCut) finalTTLchange--;
            timeToLive += finalTTLchange;
            if (timeToLive <= 0) disappear();
        } else {
            visitLock = !visitLock;
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
            // needs to have the same owner too
            if (currentTekton.getMushroomBody() != null && grower.getMushrooms().contains(currentTekton.getMushroomBody())) {     
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
            if (t.getMushroomBody() != null) return;
            MushroomBody mb = new MushroomBody(t);
            t.setMushroomBody(mb);
            t.removeInsect(i);
            grower.incrementScore();
            grower.addMushroom(mb);
        }
    }
}
