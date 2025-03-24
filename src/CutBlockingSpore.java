/**
 * Represents a spore that prevents an insect from cutting mycelium.
 */
public class CutBlockingSpore extends Spore {

    /**
     * Constructs a new CutBlockingSpore instance.
     */
    public CutBlockingSpore() {
        System.out.println("CutBlockingSpore.CutBlockingSpore()");
    }

    /**
     * Applies the cut-blocking effect on the given insect.
     * 
     * @param i The Insect that the effect is applied to.
     */
    @Override
    public void applyEffect(Insect i) {
        System.out.println("CutBlockingSpore.applyEffect(Insect i)");
        i.setCanCutMycelium(false);
    }
}
