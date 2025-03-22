public class ParalyzingSpore extends Spore {
    public void applyEffect(Insect i){
        System.out.println("ParalyzingSpore.applyEffect(Insect i)");
        i.setActionPoints(0);
    }
}
