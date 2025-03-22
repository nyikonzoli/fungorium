public class HeterogeneousTekton extends Tekton {
    /**
     * Grows a mycelium connection between two tectonic plates
     * @param master The owner of the new mycelium connection
     * @param target The target Tekton
     * @return Newly created Mycelium object
     */
    @Override
    public Mycelium growMycelium(MushroomMaster master, Tekton target){
        System.out.println("HeterogeneousTekton.growMycelium(MushroomMaster master, Tekton target)");
        Mycelium myc2 = null;
        if (isNeighbour(target) && deductNetworkAction(master)) {
            myc2 = new Mycelium(master, this, target);
            this.addMycelium(myc2);
            target.addMycelium(myc2);
        }
        return myc2; 
    }
}
