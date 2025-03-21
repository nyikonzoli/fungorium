public class Mycelium {
    static int INITITAL_TTL = 20;

    private MushroomMaster grower;
    private int timeToLive;
    private Tekton tektonStart, tektonEnd;

    public Mycelium(MushroomMaster m, Tekton start, Tekton end){
        System.out.println("Mycelium.Mycelium(MushroomMaster m, Tekton start, Tekton end)");
        grower = m;
        tektonStart = start;
        tektonEnd = end;
        timeToLive = INITITAL_TTL;
    }

    public Tekton getTektonStart(){
        System.out.println("Mycelium.getTektonStart()");
        return tektonStart;
    }

    public Tekton getTektonEnd(){
        System.out.println("Mycelium.getTektonEnd()");
        return tektonEnd;
    }

    public void disappear() {
        System.out.println("Mycelium.disappear()");
    }

    public void decreaseTTL(){
        System.out.println("Mycelium.decreaseTTL()");
        timeToLive--;
    }

    public void disappear(){
        System.out.println("Mycelium.disappear()");
    }
}
