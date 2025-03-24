
public class RegularSpore extends Spore {
    public RegularSpore() {
        System.out.println("RegularSpore.RegularSpore()");
    }

    /**
     * Applies an effect on an insect
     * @param i Insect subject to the applied effect
     */
    public void applyEffect(Insect i){
        System.out.println("RegularSpore.applyEffect(Insect i)");
    }
}
