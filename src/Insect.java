public class Insect {
    private Tekton location;
    private int actionPoints;
    private boolean canCutMycelium = true;

    public Insect(){
        System.out.println("Insect.Insect()");
    }

    public Tekton getLocation(){
        System.out.println("Insect.getLocation()");
        return location;
    }

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

    public int getActionPoints(){
        System.out.println("Insect.getActionPoints()");
        return actionPoints;
    }

    public void setActionPoints(int points){
        System.out.println("Insect.setActionPoints(int points)");
        actionPoints = points;
    }

    public boolean getCanCutMycelium(){
        System.out.println("Insect.getCanCutMycelium()");
        return canCutMycelium;
    }

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

    public void setTekton(Tekton newLocation){
        System.out.println("Insect.setTekton(Tekton newLocation)");
        location = newLocation;
    }
}
