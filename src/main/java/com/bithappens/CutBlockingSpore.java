package com.bithappens;
/**
 * Represents a spore that prevents an insect from cutting mycelium.
 */
public class CutBlockingSpore extends Spore {

    /**
     * Constructs a new CutBlockingSpore instance.
     */
    public CutBlockingSpore() {

    }

    /**
     * Applies the cut-blocking effect on the given insect.
     * 
     * @param i The Insect that the effect is applied to.
     */
    @Override
    public void applyEffect(Insect i) {
        i.setCanCutMycelium(false);
        i.decrementActionPoint(1);
    }
}
