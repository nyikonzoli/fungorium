package com.bithappens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a tectonic plate in the mushroom ecosystem. 
 * Tektons can have neighbors, spores, mycelium connections, insects, and mushrooms growing on them.
 */
public class Tekton {
    static int MINIMUM_SPORE_COUNT_FOR_MUSHROOM_GROWTH = 9;

    protected ArrayList<Tekton> neighbours;
    protected ArrayList<Spore> spores;
    protected ArrayList<Mycelium> myceliums;
    protected ArrayList<Insect> insects;
    protected MushroomBody mushroomBody;


    /// KONSTRUKTOROK

    public Tekton(){
        System.out.println("Tekton.Tekton()");
        neighbours = new ArrayList<>();
        spores = new ArrayList<>();
        myceliums = new ArrayList<>();
        insects = new ArrayList<>();
    }

    

    /// GETTEREK/SETTEREK

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
     * Retrieves the neighboring tectonic plates.
     * @return A list of neighboring Tekton instances.
     */
    public ArrayList<Tekton> getNeighbours(){
        return neighbours;
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
     * Sets the list of spores currently present on this Tekton.
     * @param spores The list of spores to be assigned.
     */
    public void setSpores(ArrayList<Spore> spores){
        this.spores = spores;
    }

        /**
     * The myceliums on this tectonic plate
     * @return A list of myceleums
     */
    public ArrayList<Mycelium> getMyceliums(){
        return myceliums;
    }

    public void setMyceliums(ArrayList<Mycelium> m){
        myceliums = m;
    }


    /// FÜGGVÉNYEK


    /**
     * Deducts an action point from a mushroom on the same mycelium network as tectonic plate
     * @param mm Owner of the mycelium network
     * @return Returns whether the deduction was successful
     */
    public boolean deductNetworkAction(MushroomMaster mm){
        // TODO: 
        // Mycelium gráfon át megkeresi az első olyan Tektont, amelyen van mm-hez tartozó MushroomBody,
        // amelynek van pontja, és azt le is vonja
        // Ha sikeresen levont egy networkon lévő gombáról pontot, true-val tér vissza
        // kiegészítés: Ha már a kiinduló (ez a tekton) 
        // System.out.println("deductNetworkAction(MushroomMaster mm)\nWas successful?");
        // return Menu.userDecision(); 
        if (mushroomBody != null && mm.getMushrooms().contains(mushroomBody) && mushroomBody.actions > 0) {
                mushroomBody.setActions(mushroomBody.getActions() - 1);
                return true;
        }
    
        Set<Tekton> visited = new HashSet<>();
        Queue<Tekton> discoverable = new LinkedList<>();
        discoverable.offer(this);
    
        while (!discoverable.isEmpty()) {
            Tekton current = discoverable.poll();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
    
            if (current.mushroomBody != null && 
                mm.getMushrooms().contains(current.mushroomBody) && 
                current.mushroomBody.actions > 0) {
                current.mushroomBody.setActions(current.mushroomBody.getActions() - 1);
                return true;
            }
    
            for (Mycelium mycelium : current.getMyceliums()) {
                Tekton neighbor = (mycelium.getTektonStart() == current) ? 
                                 mycelium.getTektonEnd() : 
                                 mycelium.getTektonStart();
                
                if (!visited.contains(neighbor)) {
                    discoverable.offer(neighbor);
                }
            }
        }
    
        return false;    
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

        for (Mycelium mycelium : myceliums) {
            mycelium.disappear();
        }

        Tekton tektonA = new Tekton();
        Tekton tektonB = new Tekton();

        ArrayList<Tekton> A_neighbours = new ArrayList<>();
        ArrayList<Tekton> B_neighbours = new ArrayList<>();

        for(Tekton t : neighbours){

        }


        return null;
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
        return spores.remove(spores.size() - 1);
    }

    /**
     * Removes a specific spore from this tectonic plate.
     * @param s The spore to be removed.
     */
    public void removeSpore(Spore s){
        spores.remove(s);
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
        return spores.size() >= MINIMUM_SPORE_COUNT_FOR_MUSHROOM_GROWTH;
    }

    /**
     * Checks, wether the tecton has any insect on it, or not
     * @return Boolean, true if the tecton has insect on it, otherwise false
     */
    public boolean hasInsect(){
        return !insects.isEmpty();
    }

    public void decreaseTTL() {

    }
}
