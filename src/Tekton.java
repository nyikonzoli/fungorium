
import java.util.ArrayList;



public class Tekton {
    static int MINIMUM_SPORE_COUNT_FOR_MUSHROOM_GROWTH = 10;

    private ArrayList<Tekton> neighbours;
    private ArrayList<Spore> spores;
    private ArrayList<Mycelium> myceliums;
    private ArrayList<Insect> insects;
    private MushroomBody mushroomBody;

    public Tekton(){
        neighbours = new ArrayList<>();
        spores = new ArrayList<>();
        myceliums = new ArrayList<>();
        insects = new ArrayList<>();
    }

    public MushroomBody getMushroomBody(){
        return mushroomBody;
    }

    public void setMushroomBody(MushroomBody m){
        mushroomBody = m;
    }

    public boolean deductNetworkAction(MushroomMaster mm){
        System.out.println("deductNetworkAction(MushroomMaster mm)\nWas successful?");
        return Menu.userDecision(); 
    }

    public ArrayList<Tekton> split(){
        System.out.println("Tekton.split()");
        return null;
    }

    public ArrayList<Tekton> getNeighbours(){
        return neighbours;
    }

    public void addNeighbour(Tekton t){
        neighbours.add(t);
    }

    public void addNeighbours(ArrayList<Tekton> tektons){
        System.out.println("addNeighbours(ArrayList<Tekton> tektons)");
        neighbours.addAll(tektons);
    }

    public void setNeighbours(ArrayList<Tekton> tektons){
        neighbours = tektons;
    }

    public ArrayList<Insect> getInsects(){
        return insects;
    }

    public void addInsect(Insect i){
        insects.add(i);
        System.out.println("Tekton.addInsect(Insect i)");
    }

    public void removeInsect(Insect i){
        insects.remove(i);
        System.out.println("Tekton.removeInsect(Insect i)");
    }

    public void setInsects(ArrayList<Insect> insects){
        this.insects = insects;
    }

    public ArrayList<Spore> getSpores(){
        return spores;
    }

    public void addSpore(Spore s){
        spores.add(s);
    }

    public void addSpores(ArrayList<Spore> spores){
        this.spores.addAll(spores);
    }

    public Spore popSpores(){
        return spores.removeLast();
    }

    public void removeSpore(Spore s){
        spores.remove(s);
    }

    public void setSpores(ArrayList<Spore> spores){
        this.spores = spores;
    }
    //TODO: overrideolni kell tekton leszármazottaknál
    public boolean addMycelium(Mycelium m){
        System.out.println("Tekton.addMycelium(Mycelium m)");
        return myceliums.add(m);
    }

    public void removeMycelium(Mycelium m){
        System.out.println("Tekton.removeMycelium(Mycelium m)");
        myceliums.remove(m);
    }

    public void onRoundStart(){}

    public MushroomBody growMushroom(MushroomMaster master){ //TODO
        if(canGrowMushroom(master)){
            MushroomBody newMushroom = new MushroomBody();
            mushroomBody = newMushroom;
            return newMushroom;
        }
        return null;
    }

    public Mycelium growMycelium(MushroomMaster master, Tekton target){
        System.out.println("Tekton.growMycelium(MushroomMaster master, Tekton target)");
        Mycelium myc2 = null;
        if (isNeighbour(target) && deductNetworkAction(master)) {
            myc2 = new Mycelium(master, this, target);
            this.addMycelium(myc2);
            target.addMycelium(myc2);
        }
        return myc2; 
    }

    public boolean isNeighbour(Tekton t){
        System.out.println("Tekton.isNeighbor(Tekton t)");
        return neighbours.contains(t);
    }

    public boolean canReachTektonViaMycelium(Tekton t){
        System.out.println("Tekton.canReachTektonViaMycelium(Tekton t)");
        for (Mycelium mycelium : myceliums) {
            if(mycelium.getTektonStart() == t || mycelium.getTektonEnd() == t) return true;
        }
        return false;
    }

    public boolean canGrowMushroom(MushroomMaster master){
        System.out.println("Tekton.canGrowMushroom(MushroomMaster master)");
        if(mushroomBody != null) return false;
        return true; //TODO
    }

    public boolean hasInsect(){
        System.out.println("Tekton.hasInsect()");
        return !insects.isEmpty();
    }

}
