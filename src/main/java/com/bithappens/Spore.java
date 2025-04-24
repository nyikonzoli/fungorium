package com.bithappens;
/**
 * Represents an abstract spore that can apply effects on insects.
 */
public abstract class Spore {

    protected  MushroomMaster mmaster;

    public MushroomMaster getMushroomMaster(){
        return mmaster;
    }

    public void setMushroomMaster(MushroomMaster m){
        mmaster = m;
    }

    /**
     * Applies an effect on an insect
     * @param i Insect subject to the applied effect
     */
    public abstract void applyEffect(Insect i);
}
