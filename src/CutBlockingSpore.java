public class CutBlockingSpore extends Spore {
    public CutBlockingSpore() {
        System.out.println("CutBlockingSpore.CutBlockingSpore()");
    }
    public void applyEffect(Insect i){
        System.out.println("CutBlockingSpore.applyEffect(Insect i)");
        i.setCanCutMycelium(false);
    }
}
