package com.bithappens;
/**
 * Represents a paralyzing spore that applies a paralying effect effect to an insect so it can't have any furhter action during the current round.
 */
public class ParalyzingSpore extends Spore {
    /**
     * Constructs a new ParalyzingSpore.
     */
    public ParalyzingSpore() {

    }

    /**
     * Applies the paralyzing effect on the given insect. This will set the insect's action points to 0,
     * preventing the insect from performing any actions during the current round.
     * 
     * @param i The Insect that will be affected by the paralyzing spore.
     */
    @Override
    public void applyEffect(Insect i){
        i.setEffect("Insect Paralyzed");
        i.setActionPoints(0);
        i.setStunned(true);
    }
}
