package com.bithappens;

import java.util.ArrayList;

/**
 * A specialized type of Tekton that does not allow the growth of mycelium connections.
 */
public class DeadEndTekton extends Tekton {
    /**
     * Constructs a DeadEndTekton instance.     */
    public DeadEndTekton() {
        neighbours = new ArrayList<>();
        spores = new ArrayList<>();
        myceliums = new ArrayList<>();
        insects = new ArrayList<>();
    }



    /**
     * tells if the Tekton can connect to other tektons (only important in DeadEnd)
     * @return true if there is no mycelium on the tekton
     */
    @Override
    protected boolean canConnect() {
        return this.myceliums.isEmpty();
    }
}
