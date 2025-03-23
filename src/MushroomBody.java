
import java.util.ArrayList;
import java.util.List;

public class MushroomBody {
    public static int SPORE_SPREAD_COUNT = 3;

    private List<Spore> sporeLevel;
    private Tekton location;
    private boolean  alive;
    private  int sporeCount;
    private  int actions;

    public MushroomBody() {
        super();
        System.out.println("MushroomBody.MushroomBody()");
    }
    /**
     * Produces an amount of spores required on the start of a new round
     */
    public void produceSpore(){
        System.out.println("MushroomBody.produceSpore()");
    }

    public Tekton getLocation(){
        System.out.println("MushroomBody.getLocation()");
        return location;
    }

    public void setLocation(Tekton t){
        location = t;
    }

    public void setActions(int a){
        actions = a;
        System.out.println("MushroomBody.setActions(int a)");
    }

    public int getActions(){
        System.out.println("MushroomBody.getActions()");
        return actions;
    }

    public void spreadSpore(Tekton t){
        System.out.println("MushroomBody.spreadSpore(Tekton t)");
        getLocation().isNeighbour(t);
        if(true){
            ArrayList<Spore> newSpores = new ArrayList<>();
            for (int i = 0; i < SPORE_SPREAD_COUNT; i++) {
                newSpores.add(new RegularSpore());
            }
            t.addSpores(newSpores);
        }
    }

    public SuperMushroomBody promoteToSuperMushroomBody(){
        System.out.println("MushroomBody.promoteTSuperMushroomBody()");
        SuperMushroomBody smb = new SuperMushroomBody();
        return smb;
    }

    public void die(){
        System.out.println("MushroomBody.die()");
        alive = false;
    }


}
