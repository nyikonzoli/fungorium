public class SpeedingSpore extends Spore {
    public SpeedingSpore() {
        System.out.println("SpeedingSpore.SpeedingSpore()");
    }
    public void applyEffect(Insect i){
        System.out.println("SpeedingSpore.applyEffect(Insect i)");
        i.setActionPoints(100);
    }
}
