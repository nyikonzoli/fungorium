package com.bithappens;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a MushroomBody, which can spread spores, grow, and interact with its environment.
 */
public class MushroomBody {
    /**
     * The number of spores spread when a MushroomBody spreads spores.
     */
    public static int SPORE_SPREAD_COUNT = 3;

    private List<Spore> sporeLevel;
    private Tekton location;
    private boolean  alive;
    private  int sporeCount;
    private  int actions;

    /**
     * Constructs a MushroomBody instance.
     */
    public MushroomBody() {
        super();
        System.out.println("MushroomBody.MushroomBody()");
    }
    /**
     * Produces an amount of spores required on the start of a new round
     */
    public void produceSpore(){
        System.out.println("MushroomBody.produceSpore()");
    }

    /**
     * Retrieves the location of this MushroomBody.
     * 
     * @return the Tekton where this MushroomBody is located
     */
    public Tekton getLocation(){
        System.out.println("MushroomBody.getLocation()");
        return location;
    }

    /**
     * Sets the location of this MushroomBody.
     * 
     * @param t the new location of the MushroomBody
     */
    public void setLocation(Tekton t){
        location = t;
    }

    /**
     * Sets the number of actions available for this MushroomBody.
     * 
     * @param a the number of actions to set
     */
    public void setActions(int a){
        actions = a;
        System.out.println("MushroomBody.setActions(int a)");
    }

    /**
     * Gets the number of actions available for this MushroomBody.
     * 
     * @return the number of actions
     */
    public int getActions(){
        System.out.println("MushroomBody.getActions()");
        return actions;
    }

    /**
     * Spreads spores to a neighboring Tekton.
     * 
     * @param t the target Tekton for spore spreading
     */
    public void spreadSpore(Tekton t){
        System.out.println("MushroomBody.spreadSpore(Tekton t)");
        getLocation().isNeighbour(t);
        if(true){
            ArrayList<Spore> newSpores = new ArrayList<>();
            for (int i = 0; i < SPORE_SPREAD_COUNT; i++) {
                newSpores.add(new RegularSpore());
            }
            t.addSpores(newSpores);
        }
    }

    /**
     * Promotes this MushroomBody to a SuperMushroomBody.
     * 
     * @return a new instance of SuperMushroomBody
     */
    public SuperMushroomBody promoteToSuperMushroomBody(){
        System.out.println("MushroomBody.promoteTSuperMushroomBody()");
        SuperMushroomBody smb = new SuperMushroomBody();
        return smb;
    }

    /**
     * Marks this MushroomBody as dead.
     */
    public void die(){
        System.out.println("MushroomBody.die()");
        alive = false;
    }


}
