package com.bithappens;
/**
 * Represents a spore that slows down an insect by decreasing it's action points.
 */
public class SlowingSpore extends Spore {

    /**
     * Constructs a new SlowingSpore.
     */
    public SlowingSpore() {

    }

    /**
     * Applies an effect on an insect. The effect of this spore is to decrease the insects action points.,
     * 
     * @param i The insect subject to the applied effect. The insect's action points will be set to zero.
     */
    @Override
    public void applyEffect(Insect i){
        i.decrementActionPoint(2);
    }
}
