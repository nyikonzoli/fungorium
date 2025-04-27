package com.bithappens;
/**
 * Represents a regular spore in the game that can be applied to insects. 
 * This spore does not apply any special effects to the insect when activated.
 */
public class RegularSpore extends Spore {
    /**
     * Constructs a new RegularSpore.
     */
    public RegularSpore() {
        System.out.println("RegularSpore.RegularSpore()");
    }

    /**
     * Applies an effect on an insect
     * @param i Insect subject to the applied effect
     */
    @Override
    public void applyEffect(Insect i){
        //System.out.println("RegularSpore.applyEffect(Insect i)");
        // Nem csinál semmit, csak returnol
        i.decrementActionPoint(1);
    }
}
