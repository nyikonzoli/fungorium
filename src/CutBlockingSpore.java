public class CutBlockingSpore extends Spore {
    public void applyEffect(Insect i){
        System.out.println("CutBlockingSpore.applyEffect(Insect i)");
        i.setCanCutMycelium(false);
    }
}
