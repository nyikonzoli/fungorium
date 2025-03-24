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
        //tektonStart.removeMycelium(this);
        //tektonEnd.removeMycelium(this);
    }

    /**
     * Decreases the time-to-live (TTL) value of the mycelium by 1.
     */
    public void decreaseTTL(){
        System.out.println("Mycelium.decreaseTTL()");
        timeToLive--;
    }
}
