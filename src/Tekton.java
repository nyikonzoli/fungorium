
import java.util.ArrayList;

/**
 * Represents a tectonic plate in the mushroom ecosystem. 
 * Tektons can have neighbors, spores, mycelium connections, insects, and mushrooms growing on them.
 */
public class Tekton {
    static int MINIMUM_SPORE_COUNT_FOR_MUSHROOM_GROWTH = 10;

    private ArrayList<Tekton> neighbours;
    private ArrayList<Spore> spores;
    private ArrayList<Mycelium> myceliums;
    private ArrayList<Insect> insects;
    private MushroomBody mushroomBody;

    public Tekton(){
        System.out.println("Tekton.Tekton()");
        neighbours = new ArrayList<>();
        spores = new ArrayList<>();
        myceliums = new ArrayList<>();
        insects = new ArrayList<>();
    }

    /**
     * Retrieves the MushroomBody associated with this Tekton.
     * @return The MushroomBody instance, or null if none exists.
     */
    public MushroomBody getMushroomBody(){
        return mushroomBody;
    }

    /**
     * Sets the mushroom body for this tectonic plate.
     * @param m The MushroomBody to associate with this tectonic plate.
     */
    public void setMushroomBody(MushroomBody m){
        System.out.println("Tekton.setMushroomBody(MushroomBody m)");
        mushroomBody = m;
    }
    /**
     * Deducts an action point from a mushroom on the same mycelium network as tectonic plate
     * @param mm Owner of the mycelium network
     * @return Returns whether the deduction was successful
     */
    public boolean deductNetworkAction(MushroomMaster mm){
        System.out.println("deductNetworkAction(MushroomMaster mm)\nWas successful?");
        return Menu.userDecision(); 
    }

    /**
     * Breaks the tecton into two, if it has no insectsand cuts all myceliums
     * @return The 2 new tecton
     */
    public ArrayList<Tekton> split(){
        System.out.println("Tekton.split()");
        if(hasInsect()){
            return null;
        }
        Mycelium m = new Mycelium();
        myceliums.add(m);
        for (Mycelium mycelium : myceliums) {
            mycelium.disappear();
        }

        Tekton tektonA = new Tekton();
        Tekton tektonB = new Tekton();

        tektonA.addNeighbours(new ArrayList<Tekton>());
        tektonB.addNeighbours(new ArrayList<Tekton>());

        return null;
    }

    /**
     * Retrieves the neighboring tectonic plates.
     * @return A list of neighboring Tekton instances.
     */
    public ArrayList<Tekton> getNeighbours(){
        return neighbours;
    }

    /**
     * Adds a single neighboring Tekton to this Tekton's list of neighbors.
     * @param t The Tekton to be added as a neighbor.
     */
    public void addNeighbour(Tekton t){

        neighbours.add(t);
    }

    /**
     * Adds the neighboring tectons to a tecton
     * @param tektons The tectons, which are next to the tecton
     */
    public void addNeighbours(ArrayList<Tekton> tektons){
        System.out.println("addNeighbours(ArrayList<Tekton> tektons)");
        neighbours.addAll(tektons);
    }

    /**
     * Sets the list of neighboring Tektons for this Tekton.
     * @param tektons The list of Tektons to set as neighbors.
     */
    public void setNeighbours(ArrayList<Tekton> tektons){
        neighbours = tektons;
    }

    /**
     * Retrieves the insects present on this tectonic plate.
     * @return A list of insects.
     */
    public ArrayList<Insect> getInsects(){
        return insects;
    }

    /**
     * Add an insect to this tectonic plate.
     * @param i The insect to be added.
     */
    public void addInsect(Insect i){
        insects.add(i);
        System.out.println("Tekton.addInsect(Insect i)");
    }

    /**
     * Removes a specific insect from this tectonic plate.
     * @param i The insect to be removed.
     */
    public void removeInsect(Insect i){
        insects.remove(i);
        System.out.println("Tekton.removeInsect(Insect i)");
    }

    /**
     * Sets the list of insects currently occupying this Tekton.
     * @param insects The list of insects to be assigned.
     */
    public void setInsects(ArrayList<Insect> insects){
        this.insects = insects;
    }

    /**
     * Retrieves the spores present on this tectonic plate.
     * @return A list of spores.
     */
    public ArrayList<Spore> getSpores(){
        return spores;
    }

    /**
     * Adds a single spore to this tectonic plate.
     * @param s The spore to add.
     */
    public void addSpore(Spore s){
        spores.add(s);
    }

    /**
     * Adds multiple spores to this tectonic plate.
     * @param s The list of spores to add.
     */
    public void addSpores(ArrayList<Spore> spores){
        System.out.println("Tekton.addSpores(List<Spore> spores)");
        this.spores.addAll(spores);
    }
    /**
     * Removes the most recently added element in the spores list
     * @return The Spore that was removed
     */
    public Spore popSpore(){
        System.out.println("Tekton.popSpore()");
        return spores.removeLast();
    }

    /**
     * Removes a specific spore from this tectonic plate.
     * @param s The spore to be removed.
     */
    public void removeSpore(Spore s){
        spores.remove(s);
    }

    /**
     * Sets the list of spores currently present on this Tekton.
     * @param spores The list of spores to be assigned.
     */
    public void setSpores(ArrayList<Spore> spores){
        this.spores = spores;
    }
    /**
     * Tries to add a mycelium to a tectonic plate
     * @param m The Mycelium to be added
     * @return Boolean indicating whether the operation was successful 
     */
    public boolean addMycelium(Mycelium m){
        System.out.println("Tekton.addMycelium(Mycelium m)");
        return myceliums.add(m);
    }

    /**
     * The myceliums on this tectonic plate
     * @return A list of myceleums
     */
    public ArrayList<Mycelium> getMyceliums(){
        return myceliums;
    }


    /**
     * Removes mycelium from tectonic plate
     * @param m Mycelium to be removed
     */
    public void removeMycelium(Mycelium m){
        System.out.println("Tekton.removeMycelium(Mycelium m)");
        myceliums.remove(m);
    }
    /**
     * Sets the tekton up for a new round
     */
    public void onRoundStart(){
        System.out.println("Tekton.onRoundStart()");
    }
    /**
     * Grows a mushroom, and adds it to the tecton, and to the mushroom master, who initiated the growth
     * @param master The mushroom master, who started the action
     * @return The new mushroom body, or null, if growing was not possible
     */
    public MushroomBody growMushroom(MushroomMaster master){ 
        if(canGrowMushroom(master)){
            MushroomBody newMushroom = new MushroomBody();
            mushroomBody = newMushroom;
            return newMushroom;
        }
        return null;
    }
    /**
     * Grows a mycelium connection between two tectonic plates
     * @param master The owner of the new mycelium connection
     * @param target The target Tekton
     * @return Newly created Mycelium object
     */
    public Mycelium growMycelium(MushroomMaster master, Tekton target){
        System.out.println("Tekton.growMycelium(MushroomMaster master, Tekton target)\nIs there already Mycelium with different owner on the Tekton?");
        if (Menu.userDecision()) {
            return null;
        }
        Mycelium myc2 = null;
        if (isNeighbour(target) && deductNetworkAction(master)) {
            myc2 = new Mycelium(master, this, target);
            this.addMycelium(myc2);
            target.addMycelium(myc2);
        }
        return myc2; 
    }
    /**
     * Decides if two tectonic plates are neighbors
     * @param t Target Tekton
     * @return Boolean that is true when the two tectonic plates are neighbors
     */
    public boolean isNeighbour(Tekton t){
        System.out.println("Tekton.isNeighbor(Tekton t)");
        return neighbours.contains(t);
    }
    /**
     * Checks two tectons if they are connected via mycelium
     * @param t The neighbor tecton
     * @return Boolean, true, if they are connected
     */
    public boolean canReachTektonViaMycelium(Tekton t){
        System.out.println("Tekton.canReachTektonViaMycelium(Tekton t)");
        for (Mycelium mycelium : myceliums) {
            if(mycelium.getTektonStart() == t || mycelium.getTektonEnd() == t) return true;
        }
        return false;
    }
    /**
     * Decides if a mushroom can grow on a tecton
     * @param master The mushroomMaster, who initiates the growth
     * @return Boolean that is true if the master can grow a mushroom body
     */
    public boolean canGrowMushroom(MushroomMaster master){
        System.out.println("Tekton.canGrowMushroom(MushroomMaster master)");
        if(Menu.userDecision()) {
            return true;
        }
        return false;
    }

    /**
     * Checks, wether the tecton has any insect on it, or not
     * @return Boolean, true if the tecton has insect on it, otherwise false
     */
    public boolean hasInsect(){
        System.out.println("Tekton.hasInsect()");
        if(Menu.userDecision()) {
            return true;
        }
        return false;
        //return !insects.isEmpty();
    }


  

}
