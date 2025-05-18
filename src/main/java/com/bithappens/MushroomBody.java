package com.bithappens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a MushroomBody, which can spread spores, grow, and interact with its environment.
 */
public class MushroomBody {
    /**
     * The number of spores spread when a MushroomBody spreads spores.
     */
    public static int SPORE_SPREAD_COUNT = 3;

    protected List<Spore> sporeLevel;
    protected Tekton location;
    protected boolean  alive;
    protected int sporeCount;
    protected int actions;

    /// KONSTRUKTOROK

    /**
     * Constructs a MushroomBody instance.
     */
    public MushroomBody() {
        //System.out.println("MushroomBody.MushroomBody()");
        sporeLevel = new ArrayList<>();
    }

    public MushroomBody(Tekton l){
        location = l;
        alive = true;
        sporeCount = 15;
        actions = 3;
        sporeLevel = new ArrayList<>();
    }


    /// GETTEREK/SETTEREK
    
        /**
     * Retrieves the location of this MushroomBody.
     * 
     * @return the Tekton where this MushroomBody is located
     */
    public Tekton getLocation(){
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
    }

    /**
     * Gets the number of actions available for this MushroomBody.
     * 
     * @return the number of actions
     */
    public int getActions(){
        return actions;
    }

    public void setSpores(List<Spore> s){
        sporeLevel = s;
    }

    public List<Spore> getSpores(){
        return  sporeLevel;
    }

    public void setAlive(boolean a){
        alive = a;
    }

    public boolean getAlive(){
        return alive;
    }

    public void setSporeCount(int sc){
        sporeCount = sc;
    }

    public int getSporeCount(){
        return sporeCount;
    }

    /// FÜGGVÉNYEK

    /**
     * Produces an amount of spores required on the start of a new round
     */
    public void produceSpore(){

        if(!alive){
            return;
        }

        Random randomSpore = new Random();
        int randomSporeType = randomSpore.nextInt(6);

        switch (randomSporeType) {
            case 0:
                Spore regular = new RegularSpore();
                sporeLevel.add(regular);
                break;
            case 1:
                Spore slow = new SlowingSpore();
                sporeLevel.add(slow);
                break;
            case 2:
                Spore speed = new SpeedingSpore();
                sporeLevel.add(speed);
                break;
            case 3:
                Spore cutBlocking = new  CutBlockingSpore();
                sporeLevel.add(cutBlocking);
                break;
            case 4:
                Spore paralyze = new ParalyzingSpore();
                sporeLevel.add(paralyze);
                break;
            case 5:
                Spore splitting = new SplittingSpore();
                sporeLevel.add(splitting);
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Spreads spores to a neighboring Tekton.
     * 
     * @param t the target Tekton for spore spreading
     */
    public void spreadSpore(Tekton t, MushroomMaster mmaster){
        boolean neighboring = getLocation().isNeighbour(t);

        if(alive && neighboring && sporeCount >= 3 && sporeLevel.size() >= 3 && actions > 0){
            
            ArrayList<Spore> thrownSpores = new ArrayList<>();
            for (int i = 0; i < SPORE_SPREAD_COUNT; i++) {
                Spore throwSpore = sporeLevel.get(0);
                sporeLevel.remove(throwSpore);
                throwSpore.setMushroomMaster(mmaster);
                thrownSpores.add(throwSpore);
                sporeCount--;
            }
            t.addSpores(thrownSpores);
            actions--;

            if (sporeCount == 0) {
                die();
            }
        }
    }

    /**
     * Promotes this MushroomBody to a SuperMushroomBody.
     * 
     * @return a new instance of SuperMushroomBody
     */
    public SuperMushroomBody promoteToSuperMushroomBody(){

        if(!alive || actions <= 0){
            return null;
        }
        Tekton l = location;
        die();
        SuperMushroomBody smb = new SuperMushroomBody(l);
        return smb;
    }

    /**
     * Marks this MushroomBody as dead.
     */
    public void die(){
        alive = false;
    }


}
