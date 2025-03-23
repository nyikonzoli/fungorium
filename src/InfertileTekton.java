public class InfertileTekton extends Tekton {
    public InfertileTekton() {
        System.out.println("InfertileTekton.InfertileTekton()");
    }
    @Override
    public MushroomBody growMushroom(MushroomMaster g){
        System.out.println("InfertileTekton.growMushroom(MushroomMaster g)");
        return null;
    }
}