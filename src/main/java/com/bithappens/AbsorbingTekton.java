package com.bithappens;
/**
 * A specialized type of Tekton that has unique behavior when a new round starts.
 */
public class AbsorbingTekton extends Tekton {
    /**
     * Constructs an AbsorbingTekton instance.
     */
    public AbsorbingTekton() {
        System.out.println("AbsorbingTekton.AbsorbingTekton()");
    }

    /**
     * Defines the behavior at the start of a new round.
     * This method overrides the onRoundStart method from Tekton.
     */
    @Override
    public void onRoundStart(){
        System.out.println("AbsorbingTekton.onRoundStart()");
    }
}
