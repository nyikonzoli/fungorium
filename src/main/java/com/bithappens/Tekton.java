package com.bithappens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Represents a tectonic plate in the mushroom ecosystem. 
 * Tektons can have neighbors, spores, mycelium connections, insects, and mushrooms growing on them.
 */
public class Tekton {
    // TESZT MIATT LEJJEBB LETT VÉVE
    static int MINIMUM_SPORE_COUNT_FOR_MUSHROOM_GROWTH = 3;

    protected ArrayList<Tekton> neighbours;
    protected ArrayList<Spore> spores;
    protected ArrayList<Mycelium> myceliums;
    protected ArrayList<Insect> insects;
    protected MushroomBody mushroomBody;


    /// KONSTRUKTOROK

    public Tekton(){
        neighbours = new ArrayList<>();
        spores = new ArrayList<>();
        myceliums = new ArrayList<>();
        insects = new ArrayList<>();
        mushroomBody = null;
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
        if(hasInsect() || hasMushroom()){
            return null;
        }

        while (myceliums.size() > 0) {
            Mycelium mycelium = myceliums.get(0);
            mycelium.disappear();
        }

        Tekton tektonA = new Tekton();
        Tekton tektonB = new Tekton();

        int mid = neighbours.size() / 2;
        int sporeMid = spores.size() / 2;

        //elso fele ide masodik fele oda
        ArrayList<Tekton> aNneighbours = new ArrayList<>(neighbours.subList(0, mid));
        aNneighbours.add(tektonB);
        ArrayList<Tekton> bNeighbours = new ArrayList<>(neighbours.subList(mid, neighbours.size()));
        bNeighbours.add(tektonA);

        
        //elso fele ide masik oda
        ArrayList<Spore> aSpores = new ArrayList<>(spores.subList(0, sporeMid));
        ArrayList<Spore> bSpores = new ArrayList<>(spores.subList(sporeMid, spores.size()));

        tektonA.setNeighbours(aNneighbours);
        tektonB.setNeighbours(bNeighbours);

        tektonA.setSpores(aSpores);
        tektonB.setSpores(bSpores);

        ArrayList<Tekton> newTektons = new ArrayList<>();
        newTektons.add(tektonA);
        newTektons.add(tektonB);

        return newTektons;
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
        neighbours.addAll(tektons);
    }

    /**
     * Add an insect to this tectonic plate.
     * @param i The insect to be added.
     */
    public void addInsect(Insect i){
        insects.add(i);
    }

    /**
     * Removes a specific insect from this tectonic plate.
     * @param i The insect to be removed.
     */
    public void removeInsect(Insect i){
        insects.remove(i);
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
        this.spores.addAll(spores);
    }
    /**
     * Removes the most recently added element in the spores list
     * @return The Spore that was removed
     */
    public Spore popSpore(){
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
        return myceliums.add(m);
    }

    /**
     * Removes mycelium from tectonic plate
     * @param m Mycelium to be removed
     */
    public void removeMycelium(Mycelium m){
        myceliums.remove(m);
    }
    /**
     * Sets the tekton up for a new round
     */
    public void onRoundStart(){
        // for in case of concurrent modification
        ArrayList<Mycelium> temp = new ArrayList<>(myceliums);
        while (!temp.isEmpty()) {
            Mycelium m = temp.remove(temp.size() - 1);
            m.onRoundStart();
        }
    }
    /**
     * Grows a mushroom, and adds it to the tecton, and to the mushroom master, who initiated the growth
     * @param master The mushroom master, who started the action
     * @return The new mushroom body, or null, if growing was not possible
     */
    public MushroomBody growMushroom(MushroomMaster master){ 
        if(canGrowMushroom(master) && deductNetworkAction(master)){
            MushroomBody newMushroom = new MushroomBody(this);
            mushroomBody = newMushroom;

            for (int i = 0; i < MINIMUM_SPORE_COUNT_FOR_MUSHROOM_GROWTH; i++) {
                removeSpore(spores.get(spores.size() - 1));
            }
            return newMushroom;
        }
        return null;
    }

    /**
     * tells if the Tekton can connect to other tektons via myc (only important in DeadEnd)
     * @return true 
     */
    protected boolean canConnect() {
        return true;
    }

    /**  
     * gets the owner of the myceliums if there is mycelium
     * @return - null is there is no myc, Master of the myceleum if there is
     */
    protected MushroomMaster getMyceliumOwner(){
        if(myceliums.isEmpty()){
            return null;
        } else{
            return myceliums.get(0).getMaster();
        }

    }

    /**  
     * checks if the owner can grow myc on the Tekton
     * @param owner - the master who wants to grow myc
     * @return - true if the Tekton has no myc, or the myc belongs to the same MushroomMaster
     */
    protected boolean canAcceptMycFromMushroomMaster(MushroomMaster owner) {
        return this.getMyceliumOwner() == null || this.getMyceliumOwner().equals(owner);
    }

    /**
     * Grows a mycelium connection between two tectonic plates
     * @param master The owner of the new mycelium connection
     * @param target The target Tekton
     * @return Newly created Mycelium object
     */
    public Mycelium growMycelium(MushroomMaster master, Tekton target){
        Mycelium myc2 = null;

        //ezt a feltetelt nem kommentalom, trivialis
        if(this.canConnect() && target.canConnect() && isNeighbour(target) && this.canAcceptMycFromMushroomMaster(master) && target.canAcceptMycFromMushroomMaster(master) && deductNetworkAction(master)){
            if (this.canReachTektonViaMycelium(target)){    //ha mar van koztuk myc, akkor nem kotjuk ujra ossze
                return myc2;
            } 
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
        return neighbours.contains(t);
    }
    /**
     * Checks two tectons if they are connected via mycelium
     * @param t The neighbor tecton
     * @return Boolean, true, if they are connected
     */
    public boolean canReachTektonViaMycelium(Tekton t){
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
        return spores.size() >= MINIMUM_SPORE_COUNT_FOR_MUSHROOM_GROWTH;
    }

    /**
     * Checks, wether the tecton has any insect on it, or not
     * @return Boolean, true if the tecton has insect on it, otherwise false
     */
    public boolean hasInsect(){
        return !insects.isEmpty();
    }

    public boolean hasMushroom(){
        return mushroomBody != null;
    }
    /**
     * Used to be decreaseTTL. Now only returns the appropriate integer to add to TTL on this Tekton.
     * @param mycelium The Mycelium in question
     * @return The amount to be added to all Myceliums on this Tekton
     */
    public int changeTTL(Mycelium mycelium) {
        if (!mycelium.isConnectedToMushroom()) {
            return -1;
        }
        return 0;
    }
}
