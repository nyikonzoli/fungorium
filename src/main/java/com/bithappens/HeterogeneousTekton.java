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
     * Grows a mycelium connection between two tectonic plates
     * @param master The owner of the new mycelium connection
     * @param target The target Tekton
     * @return Newly created Mycelium object
     */
    @Override
    public Mycelium growMycelium(MushroomMaster master, Tekton target){
        Mycelium myc2 = null;
        if (isNeighbour(target) && deductNetworkAction(master)) {
            myc2 = new Mycelium(master, this, target);
            this.addMycelium(myc2);
            target.addMycelium(myc2);
        }
        return myc2; 
    }
}
