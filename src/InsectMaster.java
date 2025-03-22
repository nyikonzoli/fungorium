public class InsectMaster extends Player {
    private Insect insect;

    public InsectMaster(){
        System.out.println("InsectMaster.InsectMaster()");
    }

    public Insect getInsect(){
        System.out.println("InsectMaster.getInsect()");
        return insect;
    }

    public void setInsect(Insect newInsect){
        System.out.println("InsectMaster.setInsect(Insect newInsect)");
        insect = newInsect;
    }

    public void initiateSporeEating(Tekton t){
        System.out.println("InsectMaster.initiateSporeEating(Tekton t)");
    }

    public void initiateMovement(Tekton t){
        System.out.println("InsectMaster.initiateMovement(Tekton t)");
        insect.moveTo(t);
    }
    /**
     * Initiates the process of removing a mycelium between two tectonic plates
     * @param m Mycelium to be removed
     */
    public void initiateMyceliumCutting(Mycelium m){
        System.out.println("InsectMaster.initiateMyceliumCutting(Mycelium m)");
        insect.cutMycelium(m);
    }

    public void onRoundStart(){
        System.out.println("InsectMaster.onRoundStart()");
    }

    public void selfReport(){
        System.out.println("InsectMaster.selfReport()");
    }
}
