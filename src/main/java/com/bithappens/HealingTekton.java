package com.bithappens;

public class HealingTekton extends Tekton{
    
    public HealingTekton(){}

    /**
     * Used to be decreaseTTL. Now only returns the appropriate integer to add to TTL on this Tekton.
     * @param mycelium The Mycelium in question
     * @return The amount to be added to all Myceliums on this Tekton
     */
    @Override
    public int changeTTL(Mycelium mycelium) {
        return 1;
    }
}
