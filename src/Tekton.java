
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

    public boolean deductNetworkAction(){
        return true; //TODO
    }

    public ArrayList<Tekton> split(){
        return null; //TODO
    }

    public ArrayList<Tekton> getNeighbours(){
        return neighbours;
    }

    public void addNeighbour(Tekton t){
        neighbours.add(t);
    }

    public void addNeighbours(ArrayList<Tekton> tektons){
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
    }

    public void removeInsect(Insect i){
        insects.remove(i);
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

    public boolean addMycelium(Mycelium m){
        return myceliums.add(m); //TODO
    }

    public void removeMycelium(Mycelium m){
        myceliums.remove(m);
    }

    public void onRoundStart(){}

    public MushroomBody growMushroom(MushroomMaster master){
        if(canGrowMushroom(master)){
            MushroomBody newMushroom = new MushroomBody();
            mushroomBody = newMushroom;
            return newMushroom;
        }
        return null;
    }

    public Mycelium growMycelium(MushroomMaster master, Tekton target){
        return null; //TODO
    }

    public boolean isNeighbour(Tekton t){
        return neighbours.contains(t);
    }

    public boolean canReachTektonViaMycelium(Tekton t){
        for (Mycelium mycelium : myceliums) {
            if(mycelium.getTektonStart() == t || mycelium.getTektonEnd() == t) return true;
        }
        return false;
    }

    public boolean canGrowMushroom(MushroomMaster master){
        if(mushroomBody != null) return false;
        return true; //TODO
    }

    public boolean hasInsect(){
        return !insects.isEmpty();
    }

}
