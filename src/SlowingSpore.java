public class SlowingSpore extends Spore {
    public SlowingSpore() {
        System.out.println("SlowingSpore.SlowingSpore()");
    }
    public void applyEffect(Insect i){
        System.out.println("SlowingSpore.applyEffect(Insect i)");
        i.setActionPoints(0);
    }
}
