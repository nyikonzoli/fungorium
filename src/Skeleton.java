import java.util.ArrayList;
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

        
        ArrayList<Tekton> tH_neighbours = new ArrayList<>();
        tH_neighbours.add(tI);
        tH_neighbours.add(tA);
        tH_neighbours.add(tD);
        tH_neighbours.add(tN);
        tH.addNeighbours(tH_neighbours);

        ArrayList<Tekton> tI_neighbours = new ArrayList<>();
        tI_neighbours.add(tH);
        tI_neighbours.add(tD);
        tI.addNeighbours(tI_neighbours);

        ArrayList<Tekton> tD_neighbours = new ArrayList<>();
        tD_neighbours.add(tI);
        tD_neighbours.add(tH);
        tD_neighbours.add(tA);
        tD.addNeighbours(tD_neighbours);

        ArrayList<Tekton> tA_neighbours = new ArrayList<>();
        tA_neighbours.add(tN);
        tA_neighbours.add(tH);
        tA_neighbours.add(tD);
        tA.addNeighbours(tA_neighbours);

        ArrayList<Tekton> tN_neighbours = new ArrayList<>();
        tN_neighbours.add(tN2);
        tN.addNeighbours(tN_neighbours);

        ArrayList<Tekton> tN2_neighbours = new ArrayList<>();
        tN2_neighbours.add(tN);
        tN2.addNeighbours(tN2_neighbours);

        insect.setLocation(tN);
        tN.addInsect(insect);
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
        Skeleton s = new Skeleton();
        Tekton tN = new Tekton();
        InsectMaster imaster = new InsectMaster();
        Insect insect = new Insect();
        RegularSpore regularspore = new RegularSpore();

        insect.setLocation(tN);
        imaster.setInsect(insect);
        ArrayList<Spore> spores = new ArrayList<>();
        spores.add(regularspore);
        tN.setSpores(spores);

        s.objects.put("tN", tN);
        s.objects.put("insect", insect);
        s.objects.put("imaster", imaster);
        s.objects.put("regularspore", regularspore);

    }

    public void eatSpeedingSpore(){
        Skeleton s = new Skeleton();
        Tekton tN = new Tekton();
        InsectMaster imaster = new InsectMaster();
        Insect insect = new Insect();
        SpeedingSpore speedingSpore = new SpeedingSpore();

        insect.setLocation(tN);
        imaster.setInsect(insect);
        ArrayList<Spore> spores = new ArrayList<>();
        spores.add(speedingSpore);
        tN.setSpores(spores);

        s.objects.put("tN", tN);
        s.objects.put("insect", insect);
        s.objects.put("imaster", imaster);
        s.objects.put("speedingSpore", speedingSpore);
    }

    public void eatSlowingSpore(){
        Skeleton s = new Skeleton();
        Tekton tN = new Tekton();
        InsectMaster imaster = new InsectMaster();
        Insect insect = new Insect();
        SlowingSpore slowingSpore = new SlowingSpore();

        insect.setLocation(tN);
        imaster.setInsect(insect);
        ArrayList<Spore> spores = new ArrayList<>();
        spores.add(slowingSpore);
        tN.setSpores(spores);

        s.objects.put("tN", tN);
        s.objects.put("insect", insect);
        s.objects.put("imaster", imaster);
        s.objects.put("slowingSpore", slowingSpore);
    }

    public void eatParalyzingSpore(){
        Skeleton s = new Skeleton();
        Tekton tN = new Tekton();
        InsectMaster imaster = new InsectMaster();
        Insect insect = new Insect();
        ParalyzingSpore paralyzingSpore = new ParalyzingSpore();

        insect.setLocation(tN);
        imaster.setInsect(insect);
        ArrayList<Spore> spores = new ArrayList<>();
        spores.add(paralyzingSpore);
        tN.setSpores(spores);

        s.objects.put("tN", tN);
        s.objects.put("insect", insect);
        s.objects.put("imaster", imaster);
        s.objects.put("paralyzingSpore", paralyzingSpore);
    }

    public void eatCutBlockingSpore(){
        Skeleton s = new Skeleton();
        Tekton tN = new Tekton();
        InsectMaster imaster = new InsectMaster();
        Insect insect = new Insect();
        CutBlockingSpore cbSpore = new CutBlockingSpore();

        insect.setLocation(tN);
        imaster.setInsect(insect);
        ArrayList<Spore> spores = new ArrayList<>();
        spores.add(cbSpore);
        tN.setSpores(spores);

        s.objects.put("tN", tN);
        s.objects.put("insect", insect);
        s.objects.put("imaster", imaster);
        s.objects.put("cbSpore", cbSpore);
    }
}
