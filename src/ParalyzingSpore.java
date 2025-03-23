public class ParalyzingSpore extends Spore {
    public ParalyzingSpore() {
        System.out.println("ParalyzingSpore.ParalyzingSpore()");
    }
    public void applyEffect(Insect i){
        System.out.println("ParalyzingSpore.applyEffect(Insect i)");
        i.setActionPoints(0);
    }
}
