package com.bithappens;
/**
 * Represents a player who controls an insect in the game.
 */
public class InsectMaster extends Player {
    private Insect insect;

    /**
     * Constructs a new InsectMaster instance.
     */
    public InsectMaster(){
        System.out.println("InsectMaster.InsectMaster()");
    }


    /**
     * Gets the name of the Player type
     * @return name of the player type
     */
    @Override
    public String getTypeName() {
        return "Insect Master";
    }

    /**
     * Gets the insect controlled by the InsectMaster.
     * 
     * @return The Insect controlled by the InsectMaster.
     */
    public Insect getInsect(){
        System.out.println("InsectMaster.getInsect()");
        return insect;
    }

    /**
     * Sets the insect controlled by the InsectMaster.
     * 
     * @param newInsect The new Insect to be controlled by the InsectMaster.
     */
    public void setInsect(Insect newInsect){
        System.out.println("InsectMaster.setInsect(Insect newInsect)");
        insect = newInsect;
    }
    /**
     * Initiates the process of eating a spore on the given tectonic plate
     * @param t The Tekton where the Insect will try to eat a Spore
     */
    public void initiateSporeEating(Tekton t){
        System.out.println("InsectMaster.initiateSporeEating(Tekton t)");
        insect.eatSpore(this);
    }

    /**
     * Initiates the process of the insect moving to a new tecton.
     * 
     * @param t The Tekton where the insect will move to.
     */
    public void initiateMovement(Tekton t){
        System.out.println("InsectMaster.initiateMovement(Tekton t)");
        insect.moveTo(t);
    }
    /**
     * Initiates the process of removing a mycelium between two tectonic plates
     * @param m Mycelium to be removed
     */
    public void initiateMyceliumCutting(Mycelium m){
        System.out.println("InsectMaster.initiateMyceliumCutting(Mycelium m)");
        insect.cutMycelium(m);
    }

    /**
     * Prepares the insect at the start of a new round, resetting the action points.
     */
    @Override
    public void onRoundStart(){
        System.out.println("InsectMaster.onRoundStart()");
        insect.setActionPoints(1);
    }

    /**
     * Reports the current state of the InsectMaster at the start of the round.
     * 
     * @param g The Game instance for reporting,
     */
    @Override
    public void selfReport(Game g){
        System.out.println("InsectMaster.selfReport()");
    }
}
