public class MushroomMaster extends Player{
    
    // TODO: ezt a szekvenciát még befejzni
    public void initiateMushroomGrowth(Tekton t){
        System.out.println("MushroomMaster.initiateMushroomGrowth(Tekton t)");
        MushroomBody mushroom = t.growMushroom(this);
        if(mushroom != null){
            this.incrementScore();
        }
    }

    public void initiateMyceliumGrowth(Tekton start, Tekton end) {
        System.out.println("MushroomMaster.initiateMyceliumGrowth(Tekton start, Tekton end)");
        start.growMycelium(this, end);
    }
}
