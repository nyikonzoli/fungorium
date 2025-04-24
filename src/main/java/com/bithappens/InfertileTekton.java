package com.bithappens;
/**
 * Represents a specific type of Tekton that is infertile and does not produce mushrooms.
 */
public class InfertileTekton extends Tekton {
    /**
     * Constructs a new InfertileTekton instance.
     */
    public InfertileTekton() {
        System.out.println("InfertileTekton.InfertileTekton()");
    }

     /**
     * Attempts to grow a mushroom on this Tekton, but returns null since this Tekton
     * is infertile and cannot grow mushrooms.
     * 
     * @param g The  MushroomMaster that would be responsible for growing the mushroom.
     * @return null since an InfertileTekton cannot grow mushrooms.
     */
    @Override
    public MushroomBody growMushroom(MushroomMaster g){
        System.out.println("InfertileTekton.growMushroom(MushroomMaster g)");
        return null;
    }
}