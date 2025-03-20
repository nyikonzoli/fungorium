public class MushroomMaster extends Player{
    

    public void initiateMushroomGrowth(Tekton t){
        MushroomBody mushroom = t.growMushroom(this);
        if(mushroom != null){
            this.incrementScore();
        }
    }
}
