package com.bithappens;
/**
 * Represents an insect in the game that can move between tectons, eat spores, and cut mycelium.
 */
public class Insect {
    private Tekton location;
    private int actionPoints;
    private boolean canCutMycelium = true;
    private boolean stunned = false;
    private String effect;

    private InsectMaster imaster;


    /// CONSTRUCTORS

    /**
     * Constructs a new {@code Insect} instance.
     */
    public Insect(){
        
    }

    public Insect(Tekton t, int a, InsectMaster i){
        location = t;
        actionPoints = a;
        imaster = i;
    }

    public Insect(Tekton t, InsectMaster i){
        location = t;
        actionPoints = 0;
        imaster = i;
    }

    // GETTERS, SETTERS

    /**
     * Gets the current location of the insect.
     * 
     * @return The Tekton where the insect is located.
     */
    public Tekton getLocation(){
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
     * Gets the current action points of the insect.
     * 
     * @return The current number of action points.
     */
    public int getActionPoints(){
        return actionPoints;
    }

    /**
     * Sets the action points of the insect.
     * 
     * @param points The new action points value.
     */
    public void setActionPoints(int points){
        actionPoints = points;
    }

        /**
     * Gets whether the insect can cut mycelium.
     * 
     * @return true if the insect can cut mycelium, false otherwise.
     */
    public boolean getCanCutMycelium(){
        return canCutMycelium;
    }

    /**
     * Sets whether the insect can cut mycelium.
     * 
     * @param value true to allow the insect to cut mycelium, false to prevent it.
     */
    public void setCanCutMycelium(boolean value){
        canCutMycelium = value;
    }

    /**
     * Tells if the insect is stunned or not
     * 
     * @return true if the insect is stunned
     */
    public boolean isStunned(){
        return stunned;
    }

    public void setStunned(boolean s){
        stunned = s;
    }

    public InsectMaster getImaster(){
        return imaster;
    }

    public void setImaster(InsectMaster i){
        imaster = i;
    }

    public void setEffect(String e){
        effect = e;
    }

    public String getEffect(){
        return effect;
    }



    // METHODS


    /**
     * Moves the insect from one tecton to another
     * If the new tecton is reachable, the insect is removed from the tecton, and placed on the other
     * @param newLocation The tecton, where the insect moves
     */
    public boolean moveTo(Tekton newLocation){
        boolean canReach = location.canReachTektonViaMycelium(newLocation);

        if(canReach && actionPoints > 0){
            location.removeInsect(this);
            newLocation.addInsect(this);
            setLocation(newLocation);
            decrementActionPoint(1);
            return true;
        }
        return false;
    }


    // EatSpore-nak mégsem kell paraméterként az insectMaster, ha azt eltároljuk a rovarban is

    public void eatSpore(){
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
        if(canCutMycelium){
            mycelium.setCut(true);
            decrementActionPoint(1);
        }
    }

    /**
     * Decrements the Instect's action points by 1
     */
    public void decrementActionPoint(int minus){
        actionPoints = actionPoints - minus >= 0 ? actionPoints - minus : 0;
    }

    public void addActionPoint(int plus){
        actionPoints += plus;
    }
}
