public class Insect {
    private Tekton location;
    private int actionPoints;
    private boolean canCutMycelium = true;

    public Insect(){
        System.out.println("Insect.ctor()");
    }

    public Tekton getLocation(){
        System.out.println("Insect.getLocation()");
        return location;
    }

    public void moveTo(Tekton newLocation){
        System.out.println("Insect.moveTo(Tekton newLocation)");
        boolean canReach = location.canReachTektonViaMycelium(newLocation);
        if(canReach){
            location = newLocation;
        }
        
    }

    public int getActionPoints(){
        System.out.println("Insect.getActionPoints()");
        return actionPoints;
    }

    public void setActionPoints(int points){
        System.out.println("Insect.setActionPoints(int points)");
        actionPoints = points;
    }

    public boolean getCanCutMycelium(){
        System.out.println("Insect.getCanCutMycelium()");
        return canCutMycelium;
    }

    public void setCanCutMycelium(boolean value){
        System.out.println("Insect.setCanCutMycelium(boolean value)");
        canCutMycelium = value;
    }

    public void eatSpore(){
        System.out.println("Insect.eatSpore()");
    }

    public void cutMycelium(Mycelium mycelium){
        System.out.println("Insect.cutMycelium(Mycelium mycelium)");
    }

    public void decrementActionPoint(){
        actionPoints--;
        System.out.println("Insect.decrementActionPoint()");
    }

    public void setTekton(Tekton newLocation){
        System.out.println("Insect.setTekton(Tekton newLocation)");
        location = newLocation;
    }
}
