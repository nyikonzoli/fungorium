public class SlowingSpore extends Spore {
    public void applyEffect(Insect i){
        System.out.println("SlowingSpore.applyEffect(Insect i)");
        i.setActionPoints(0);
    }
}
