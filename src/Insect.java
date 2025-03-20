public class Insect {
    private Tekton location;
    private int actionPoints;
    private boolean canCutMycelium = true;

    public Insect(){}

    public Tekton getLocation(){
        return location;
    }

    public void moveTo(Tekton newLocation){
        location = newLocation;
    }

    public int getActionPoints(){
        return actionPoints;
    }

    public void setActionPoints(int points){
        actionPoints = points;
    }

    public boolean getCanCutMycelium(){
        return canCutMycelium;
    }

    public void setCanCutMycelium(boolean value){
        canCutMycelium = value;
    }

    public void eatSpore(){}

    public void cutMycelium(Mycelium mycelium){}

    public void decrementActionPoint(){}
}
