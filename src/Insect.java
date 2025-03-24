/**
 * Represents an insect in the game that can move between tectons, eat spores, and cut mycelium.
 */
public class Insect {
    private Tekton location;
    private int actionPoints;
    private boolean canCutMycelium = true;

    /**
     * Constructs a new {@code Insect} instance.
     */
    public Insect(){
        System.out.println("Insect.Insect()");
    }

    /**
     * Gets the current location of the insect.
     * 
     * @return The Tekton where the insect is located.
     */
    public Tekton getLocation(){
        System.out.println("Insect.getLocation()");
        return location;
    }

    /**
     * Sets the insect's location to the given tecton.
     * 
     * @param t The Tekton to set as the insect's location.
     */
    public void setLocation(Tekton t) {
        location = t;
    }

    /**
     * Moves the insect from one tecton to another
     * If the new tecton is reachable, the insect is removed from the tecton, and placed on the other
     * @param newLocation The tecton, where the insect moves
     */
    public void moveTo(Tekton newLocation){
        System.out.println("Insect.moveTo(Tekton newLocation)");
        boolean canReach = location.canReachTektonViaMycelium(newLocation);
        if(Menu.userDecision()) {
            location.removeInsect(this);
            newLocation.addInsect(this);
            setTekton(newLocation);
            decrementActionPoint();
        }
        
    }

    /**
     * Gets the current action points of the insect.
     * 
     * @return The current number of action points.
     */
    public int getActionPoints(){
        System.out.println("Insect.getActionPoints()");
        return actionPoints;
    }

    /**
     * Sets the action points of the insect.
     * 
     * @param points The new action points value.
     */
    public void setActionPoints(int points){
        System.out.println("Insect.setActionPoints(int points)");
        actionPoints = points;
    }

    /**
     * Gets whether the insect can cut mycelium.
     * 
     * @return true if the insect can cut mycelium, false otherwise.
     */
    public boolean getCanCutMycelium(){
        System.out.println("Insect.getCanCutMycelium()");
        return canCutMycelium;
    }

    /**
     * Sets whether the insect can cut mycelium.
     * 
     * @param value true to allow the insect to cut mycelium, false to prevent it.
     */
    public void setCanCutMycelium(boolean value){
        System.out.println("Insect.setCanCutMycelium(boolean value)");
        canCutMycelium = value;
    }
    /**
     * Eats the most recently added Spore on the Tekton it's on
     * @param imaster The Player responsible for the Insect
     */
    public void eatSpore(Player imaster){
        System.out.println("Insect.eatSpore()");
        Spore s = location.popSpore();
        if (s != null) {
            s.applyEffect(this);
            imaster.incrementScore();
        }
    }
    /**
     * The Insect cuts the Mycelium by calling it's disappear() function
     * @param mycelium Mycelium to be removed
     */
    public void cutMycelium(Mycelium mycelium){
        System.out.println("Insect.cutMycelium(Mycelium mycelium)\nCan cut mycelium?");
        if (Menu.userDecision()) {
            mycelium.disappear();
            decrementActionPoint();
        }

    }
    /**
     * Decrements the Instect's action points by 1
     */
    public void decrementActionPoint(){
        System.out.println("Insect.decrementActionPoint()");
        actionPoints--;
    }

    /**
     * Sets the insect's location to a new tecton.
     * 
     * @param newLocation The new Tekton location for the insect.
     */
    public void setTekton(Tekton newLocation){
        System.out.println("Insect.setTekton(Tekton newLocation)");
        location = newLocation;
    }
}
