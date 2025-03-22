public class SpeedingSpore extends Spore {
    public void applyEffect(Insect i){
        System.out.println("SpeedingSpore.applyEffect(Insect i)");
        i.setActionPoints(100);
    }
}
