
import java.util.List;

public class MushroomBody {
    private List<Spore> sporeLevel;
    private Tekton location;
    private boolean  alive;
    private  int sporeCount;
    private  int actions;

    public MushroomBody() {
        super();
        System.out.println("Mushroombody.ctor()");
    }

    public void produceSpore(){
        System.out.println("Mushroombody.produceSpore()");
    }

    public void spreadSpore(Tekton t){
        System.out.println("Mushroombody.spreadSpore(Tekton t)");
    }

    public SuperMushroomBody promoteTSuperMushroomBody(){
        System.out.println("Mushroombody.promoteTSuperMushroomBody()");
        return null;
    }

    public void die(){
        System.out.println("Mushroombody.die()");
        alive = false;
    }


}
