package com.bithappens;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player who controls an insect in the game.
 */
public class InsectMaster extends Player {
    private ArrayList<Insect> insects = new ArrayList<>();

    /**
     * Constructs a new InsectMaster instance.
     */
    public InsectMaster(){

    }
    @Override
    public List<Object> getOwnedObjects() {
        ArrayList<Object> retlist = new ArrayList<>();
        for (Insect i : insects) {
            retlist.add(i);
        }
        return retlist;
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
    public Insect getInsect(int index){
        return insects.get(index);
    }

    public ArrayList<Insect> getAllInsects(){
        return insects;
    }

    public void addInsect(Insect i){
        insects.add(i);
    }

    public void removeInsect(Insect i){
        insects.remove(i);
    }

    /**
     * Sets the insect controlled by the InsectMaster.
     * 
     * @param newInsect The new Insect to be controlled by the InsectMaster.
     */
    public void setInsects(ArrayList<Insect> i){
        insects = i;
    }
    /**
     * Initiates the process of eating a spore on the given tectonic plate
     * @param t The Tekton where the Insect will try to eat a Spore
     */
    public void initiateSporeEating(Tekton t, Insect chosenInsect){
        chosenInsect.eatSpore();
        //TODO: Tekton param. nem kell: ezt átírni a Prototype.eatsp-ben is
    }

    /**
     * Initiates the process of the insect moving to a new tecton.
     * 
     * @param t The Tekton where the insect will move to.
     */
    public void initiateMovement(Tekton t, Insect chosenInsect){
        chosenInsect.moveTo(t);
    }
    /**
     * Initiates the process of removing a mycelium between two tectonic plates
     * @param m Mycelium to be removed
     */
    public void initiateMyceliumCutting(Mycelium m, Insect chosenInsect){
        chosenInsect.cutMycelium(m);
    }

    /**
     * Prepares the insect at the start of a new round, resetting the action points.
     */
    @Override
    public void onRoundStart(){
        for(Insect i : insects){
            i.setActionPoints(5);
            i.setCanCutMycelium(true);
            i.setStunned(false);
        }
    }

    /**
     * Reports the current state of the InsectMaster at the start of the round.
     * 
     * @param g The Game instance for reporting,
     */
    @Override
    public void selfReport(Game g){
        List<Player> topMasters = g.getTopInsectMasters();
        if(topMasters.size() != 0){
            if(topMasters.get(0).getScore() == this.getScore()){
                topMasters.add(this);
            }
            else if(topMasters.get(0).getScore() < this.getScore()){
                topMasters.clear();
                topMasters.add(this);
            }
            g.setTopInsectMaster(topMasters);
        }
    }

    public void insectSplit(Insect i){
        Tekton splitLocation = i.getLocation();
        InsectMaster splitMaster = i.getImaster();
        Insect splitInsect = new Insect(splitLocation, splitMaster);
        splitLocation.addInsect(splitInsect);
        addInsect(splitInsect);
    }
}
