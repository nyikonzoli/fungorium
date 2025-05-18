package com.bithappens;

import java.util.ArrayList;


/**
 * Represents a Super Mushroom Body, which extends the functionality of a MushroomBody.
 * It has the ability to spread spores to neighboring Tekton objects.
 */
public class SuperMushroomBody extends  MushroomBody {

    /**
     * Constructs a SuperMushroomBody object.
     * Calls the constructor of the parent class.
     */
    public SuperMushroomBody() {
        super();
    }

    public SuperMushroomBody(Tekton l){
        super(l);
    }

    /**
     * Spreads spores to the given Tekton if certain conditions are met.
     *
     * @param t the Tekton to which spores might be spread
     */
    @Override
    public void  spreadSpore(Tekton t, MushroomMaster mmaster){
        boolean neighboring = getLocation().isNeighbour(t);
        // Check the neighbours of neighbours

        if(!neighboring){
            ArrayList<Tekton> neighbours = getLocation().getNeighbours();
            for (Tekton neighbour : neighbours) {
                if(neighbour.isNeighbour(t)){
                    neighboring = true;
                }
            }
        }

        if(alive && neighboring && sporeCount >= 3 && sporeLevel.size() >= 3 && actions > 0){
            ArrayList<Spore> thrownSpores = new ArrayList<>();
            for (int i = 0; i < SPORE_SPREAD_COUNT; i++) {
                Spore throwSpore = sporeLevel.get(0);
                sporeLevel.remove(throwSpore);
                throwSpore.setMushroomMaster(mmaster);
                thrownSpores.add(throwSpore);
                sporeCount--;
            }
            t.addSpores(thrownSpores);
            actions--;

            if (sporeCount == 0) {
                die();
            }
        }
    }
}
