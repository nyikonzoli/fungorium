package com.bithappens;

import java.util.ArrayList;

/**
 – This class extends Tekton and overrides the growMycelium(MushroomMaster, Tekton) method to implement the functionality
 * for heterogeneous mycelium growth.
 */
public class HeterogeneousTekton extends Tekton {
    /**
     * Constructs a new HeterogeneousTekton instance.
     */
    public HeterogeneousTekton() {
        neighbours = new ArrayList<>();
        spores = new ArrayList<>();
        myceliums = new ArrayList<>();
        insects = new ArrayList<>();
    }

    /**  
     * checks if the owner can grow myc on the Tekton
     * @param owner - the master who wants to grow myc
     * @return - true, because it can accept from everybody
     */
    @Override
    protected boolean canAcceptMycFromMushroomMaster(MushroomMaster owner) {
        return true;
    }

    
}
