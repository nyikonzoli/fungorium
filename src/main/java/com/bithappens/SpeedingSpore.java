package com.bithappens;
/**
 * Represents a spore that increases the action points of an insect.
 */
public class SpeedingSpore extends Spore {
    /**
     * Constructs a new SpeedingSpore.
     */
    public SpeedingSpore() {
        System.out.println("SpeedingSpore.SpeedingSpore()");
    }

    /**
     * Applies an effect on an insect. The effect of this spore is to increase the insect's action points.
     * 
     * @param i The insect subject to the applied effect.
     */
    @Override
    public void applyEffect(Insect i){
        System.out.println("SpeedingSpore.applyEffect(Insect i)");
        i.addActionPoint(2);
    }
}
