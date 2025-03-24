/**
 * A specialized type of Tekton that does not allow the growth of mycelium connections.
 */
public class DeadEndTekton extends Tekton {
    /**
     * Constructs a DeadEndTekton instance.     */
    public DeadEndTekton() {
        System.out.println("DeadEndTekton.DeadEndTekton()");
    }
    /**
     * Grows a mycelium connection between two tectonic plates
     * @param master The owner of the new mycelium connection
     * @param target The target Tekton
     * @return Newly created Mycelium object
     */
    public Mycelium growMycelium(MushroomMaster master, Tekton target){
        System.out.println("DeadEndTekton.growMycelium(MushroomMaster master, Tekton target)");
        return null;
    }
}
