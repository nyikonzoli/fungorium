public class Mycelium {
    static int INITITAL_TTL = 20;

    private MushroomMaster grower;
    private int timeToLive;
    private Tekton tektonStart, tektonEnd;

    public Mycelium(MushroomMaster m, Tekton start, Tekton end){
        grower = m;
        tektonStart = start;
        tektonEnd = end;
        timeToLive = INITITAL_TTL;
    }

    public Tekton getTektonStart(){
        return tektonStart;
    }

    public Tekton getTektonEnd(){
        return tektonEnd;
    }

    public void decreaseTTL(){
        timeToLive--;
    }

    public void disappear(){
        System.out.println("Mycelium.disappear()");
    }
}
