
import java.util.ArrayList;
import java.util.List;

public class SuperMushroomBody extends  MushroomBody {

    public SuperMushroomBody() {
        super();
        System.out.println("SuperMushroomBody.SuperMushroomBody()");
    }

    @Override
    public void  spreadSpore(Tekton t){
        System.out.println("SuperMushroombody.spreadSpore(Tekton t)");
        Tekton tn2 = this.getLocation();
        boolean isNeighbor = tn2.isNeighbour(t);
        List<Tekton> neighbours = tn2.getNeighbours();
        for (Tekton tekton : neighbours) {
            tekton.isNeighbour(t);
        }
        if(Menu.userDecision()){
            ArrayList<Spore> newSpores = new ArrayList<>();
            for (int i = 0; i < SPORE_SPREAD_COUNT; i++) {
                newSpores.add(new RegularSpore());
            }
            t.addSpores(newSpores);
        }
    }
}
