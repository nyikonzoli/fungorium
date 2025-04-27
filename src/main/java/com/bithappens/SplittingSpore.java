package com.bithappens;
/**
 * Represents a spore that makes an insect split into 2.
 */
public class SplittingSpore extends Spore {

    /**
     * Constructs a new SlowingSpore.
     */
    public SplittingSpore() {
        System.out.println("SplittingSpore.SplittingSpore()");
    }

    /**
     * Applies an effect on an insect. The effect of this spore is to create a new insect.
     * 
     * @param i The insect subject to the applied effect. A new insect is created.
     */
    @Override
    public void applyEffect(Insect i){
        i.decrementActionPoint(1);
        InsectMaster master = i.getImaster();
        master.insectSplit(i);
    }
}