package com.bithappens;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Mushroom Master, a type of Player that manages mushroom bodies
 * and interacts with the game environment by growing mushrooms, spreading spores,
 * and initiating mycelium growth.
 */
public class MushroomMaster extends Player{
    
    private List<MushroomBody> mushrooms;
    /**
     * Getter for mushrooms.
     * @return mushrooms
     */
    public List<MushroomBody> getMushrooms() {
        return mushrooms;
    }


    /**
     * Constructs a MushroomMaster object, initializing the mushroom list.
     */
    public MushroomMaster() {
        super();
        mushrooms = new ArrayList<>();
        //System.out.println("MushroomMaster.MushroomMaster()");
    }


    /**
     * Gets the name of the Player type
     * @return name of the player type
     */
    @Override
    public String getTypeName() {
        return "Mushroom Master";
    }


    /**
     * Initiates the growth of a mushroom on the given Tekton.
     *
     * @param t the Tekton where the mushroom might grow
     */
    public void initiateMushroomGrowth(Tekton t){
        //System.out.println("MushroomMaster.initiateMushroomGrowth(Tekton t)");
        MushroomBody mushroom = t.growMushroom(this);

        if(mushroom != null){
            this.incrementScore();
            this.addMushroom(mushroom);
        }
    }
    /**
     * Initiates mycelium growth between two tectonic plates.
     * @param start Starting Tekton 
     * @param end   Target Tekton
     */
    public void initiateMyceliumGrowth(Tekton start, Tekton end) {
        start.growMycelium(this, end);
    }

    /**
     * Initiates the spreading of spores from a MushroomBody to a given Tekton.
     *
     * @param m the MushroomBody spreading the spores
     * @param t the target Tekton
     */
    public void initiateSporeSpreading(MushroomBody m, Tekton t){
        m.spreadSpore(t, this);
    }

    /**
     * Attempts to promote a MushroomBody to a SuperMushroomBody.
     *
     * @param m the MushroomBody to be promoted
     */
    public void initiateSuperMushroomGrowth(MushroomBody m){
        SuperMushroomBody smb = m.promoteToSuperMushroomBody();

        if(smb != null){
            mushrooms.remove(m);
            mushrooms.add(smb);
            smb.getLocation().setMushroomBody(smb);

        }
    }

    /**
     * Adds a MushroomBody to the list of mushrooms managed by this MushroomMaster.
     *
     * @param m the MushroomBody to add
     */
    public void addMushroom(MushroomBody m){
        mushrooms.add(m);
    }

    /**
     * Executes actions at the start of a round, triggering spore production
     * and resetting actions for all mushrooms under this master's control.
     */
    @Override
    public void onRoundStart(){

        for(MushroomBody body : mushrooms){
            body.produceSpore();
            body.setActions(1);
        }
    }

    /**
     * Reports the state of this MushroomMaster to the given Game instance.
     *
     * @param g the Game instance to report to
     */
    @Override
    public void selfReport(Game g){
        List<Player> topMasters = g.getTopMushroomMasters();
        if(topMasters.size() != 0){
            if(topMasters.get(0).getScore() == this.getScore()){
                topMasters.add(this);
            }
            else if(topMasters.get(0).getScore() < this.getScore()){
                topMasters.clear();
                topMasters.add(this);
            }
            g.setTopMushroomMasters(topMasters);
        }
    }

    @Override
    public List<Object> getOwnedObjects() {
        ArrayList<Object> retlist = new ArrayList<>();
        for (MushroomBody m : mushrooms) {
            retlist.add(m);
        }
        return retlist;
    }
}

