package com.bithappens;
/**
 * A specialized type of Tekton that has unique behavior when a new round starts.
 */
public class AbsorbingTekton extends Tekton {
    /**
     * Constructs an AbsorbingTekton instance.
     */
    public AbsorbingTekton() {}

    /**
     * Used to be decreaseTTL. Now only returns the appropriate integer to add to TTL on this Tekton.
     * @param mycelium The Mycelium in question
     * @return The amount to be added to all Myceliums on this Tekton
     */
    @Override
    public int changeTTL(Mycelium mycelium) {
        return -1;
    }
}
