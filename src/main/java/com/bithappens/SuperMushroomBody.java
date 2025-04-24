package com.bithappens;

import java.util.ArrayList;
import java.util.List;


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
        System.out.println("SuperMushroomBody.SuperMushroomBody()");
    }

    /**
     * Spreads spores to the given Tekton if certain conditions are met.
     *
     * @param t the Tekton to which spores might be spread
     */
    @Override
    public void  spreadSpore(Tekton t){
        System.out.println("SuperMushroombody.spreadSpore(Tekton t)");
        Tekton tn2 = this.getLocation();
        boolean isNeighbor = tn2.isNeighbour(t);
        List<Tekton> neighbours = tn2.getNeighbours();
        for (Tekton tekton : neighbours) {
            tekton.isNeighbour(t);
        }
        if(Menu.userDecision()){
            ArrayList<Spore> newSpores = new ArrayList<>();
            for (int i = 0; i < SPORE_SPREAD_COUNT; i++) {
                newSpores.add(new RegularSpore());
            }
            t.addSpores(newSpores);
        }
    }
}
