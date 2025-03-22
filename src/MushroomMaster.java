import java.util.List;

public class MushroomMaster extends Player{
    
    private List<MushroomBody> mushrooms;

    public MushroomMaster() {
        super();
        System.out.println("MushroomMaster.ctor()");
    }

    // TODO: ezt a szekvenciát még befejzni
    public void initiateMushroomGrowth(Tekton t){
        System.out.println("MushroomMaster.initiateMushroomGrowth(Tekton t)");
        MushroomBody mushroom = t.growMushroom(this);
        if(mushroom != null){
            this.incrementScore();
        }
    }
    /**
     * Initiates mycelium growth between two tectonic plates.
     * @param start Starting Tekton 
     * @param end   Target Tekton
     */
    public void initiateMyceliumGrowth(Tekton start, Tekton end) {
        System.out.println("MushroomMaster.initiateMyceliumGrowth(Tekton start, Tekton end)");
        start.growMycelium(this, end);
    }

    public void initiateSporeSpreading(MushroomBody m, Tekton t){
        System.out.println("MushroomMaster.initiateSporeSpreading(MushroomBody m, Tekton t)");
    }

    public void initiateSuperMushroomGrowth(MushroomBody m){
        System.out.println("MushroomMaster.initiateSuperMushroomGrowth(MushroomBody m)");
    }

    public void addMushroom(MushroomBody m){
        System.out.println("MushroomMaster.addMushroom(MushroomBody m)");
        mushrooms.add(m);
    }

    @Override
    public void onRoundStart(){
        System.out.println("MushroomMaster.onRoundStart()");
    }

    @Override
    public void selfReport(Game g){
        System.out.println("MushroomMaster.selfReport(Game g)");
    }

}

