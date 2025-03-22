import java.util.HashMap;

public class Skeleton {
    HashMap<String, Object> objects;

    public Skeleton(){
        objects = new HashMap<>();

        // objects.put("tN", null);
        // objects.put("tN2", null);
        // objects.put("mmaster", null);
        // objects.put("tH", null);
        // objects.put("tI", null);
        // objects.put("tD", null);
        // objects.put("tA", null);
        // objects.put("insect", null);
        // objects.put("imaster", null);
        // objects.put("regularSpore", null);
        // objects.put("speedingSpore", null);
        // objects.put("slowingSpore", null);
        // objects.put("paralyzingSpore", null);
        // objects.put("cbSpore", null);
        // objects.put("game", null);  gameField és players mezőket feltölteni

    }

    public void sixTekton(){
        Skeleton s = new Skeleton();

        InfertileTekton tI = new InfertileTekton();
        DeadEndTekton tD = new DeadEndTekton();
        AbsorbingTekton tA = new AbsorbingTekton();
        HeterogeneousTekton tH = new HeterogeneousTekton();
        Tekton tN = new Tekton();
        Tekton tN2 = new Tekton();
        MushroomMaster mmaster = new MushroomMaster();
        Insect insect = new Insect();
        InsectMaster imaster = new InsectMaster();
        Game game = new Game();

        tH.addNeighbour(tI);
        tH.addNeighbour(tA);
        tH.addNeighbour(tD);
        tH.addNeighbour(tN);

        tI.addNeighbour(tH);
        tI.addNeighbour(tD);

        tD.addNeighbour(tI);
        tD.addNeighbour(tH);
        tD.addNeighbour(tA);

        tA.addNeighbour(tN);
        tA.addNeighbour(tH);
        tA.addNeighbour(tD);

        tN.addNeighbour(tN2);

        tN2.addNeighbour(tN);

        insect.setLocation(tN);
        imaster.setInsect(insect);

        game.extendField(tI);
        game.extendField(tD);
        game.extendField(tA);
        game.extendField(tH);
        game.extendField(tN);
        game.extendField(tN2);
        game.addPlayer(imaster);
        game.addPlayer(mmaster);

        s.objects.put("tI", tI);
        s.objects.put("tD", tD);
        s.objects.put("tA", tA);
        s.objects.put("tH", tH);
        s.objects.put("tN", tN);
        s.objects.put("tN2", tN2);
        s.objects.put("mmaster", mmaster);
        s.objects.put("imaster", imaster);
        s.objects.put("insect", insect);
        s.objects.put("game", game);
    }

    public void eatRegularSpore(){

    }

    public void eatSpeedingSpore(){
        
    }

    public void eatSlowingSpore(){
        
    }

    public void eatParalyzingSpore(){
        
    }

    public void eatCutBlockingSpore(){
        
    }
}
